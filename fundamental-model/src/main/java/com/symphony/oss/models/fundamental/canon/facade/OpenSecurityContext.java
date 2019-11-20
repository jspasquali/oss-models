/*
 * Copyright 2018 Symphony Communication Services, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.security.KeyPair;
import java.security.PrivateKey;

import javax.crypto.SecretKey;

import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.immutable.ImmutableByteArray;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.core.trace.NoOpTraceContext;

import com.symphony.oss.models.crypto.canon.Base64SecretKey;
import com.symphony.oss.models.crypto.canon.EncryptedData;
import com.symphony.oss.models.crypto.canon.PemPrivateKey;
import com.symphony.oss.models.crypto.cipher.CipherSuite;
import com.symphony.oss.models.crypto.cipher.ICipherSuite;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseWritable;
import com.symphony.oss.models.fundmental.canon.IOpenSecurityContextInfo;
import com.symphony.oss.models.fundmental.canon.ISequence;
import com.symphony.oss.models.fundmental.canon.Sequence;
import com.symphony.oss.models.fundmental.canon.SequenceType;

/**
 * An open SecurityContext which has private and secret keys available.
 * 
 * @author Bruce Skingle
 *
 */
public class OpenSecurityContext extends SecurityContext implements IOpenSecurityContext
{
  private final PrivateKey privateKey_;
  private final SecretKey secretKey_;

  /**
   * Constructor.
   * 
   * @param securityContext An existing (closed) SecurityContext
   * @param privateKey      The private key.
   * @param secretKey       The secret key.
   */
  public OpenSecurityContext(ISecurityContext securityContext, PrivateKey privateKey, SecretKey secretKey)
  {
    super(securityContext);
    
    setPayloadContainer(securityContext.getPayloadContainer());
    privateKey_ = privateKey;
    secretKey_ = secretKey;
  }
  
  /**
   * Constructor.
   * 
   * @param openSecurityContextInfo Info object containing all values for security context including private and secret key.
   * @param modelRegistry           A model registry.
   */
  public OpenSecurityContext(IOpenSecurityContextInfo openSecurityContextInfo, IModelRegistry modelRegistry)
  {
    this(createSecurityContext(openSecurityContextInfo.getJsonObject(), modelRegistry),
        openSecurityContextInfo.getEncodedPrivateKey(),
        openSecurityContextInfo.getEncodedSecretKey());
        
  }

  private static SecurityContext createSecurityContext(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    SecurityContext securityContext = new SecurityContext(jsonObject, modelRegistry);
    
    // This sets itself as the payload container of the securityContext which is necessary. Do not remove.
    new FundamentalObject.Builder()
      .withPayload(securityContext)
      .build();
    
    return securityContext;
  }

  private OpenSecurityContext(SecurityContext securityContext, PemPrivateKey encodedPrivateKey,
      Base64SecretKey encodedSecretKey)
  {
    this(securityContext,
        securityContext.getCipherSuite().privateKeyFromPem(encodedPrivateKey),
        securityContext.getCipherSuite().secretKeyFromBase64(encodedSecretKey)
        );
  }

  protected static abstract class AbstractGenerator<B extends AbstractGenerator<B>> extends AbstractSecurityContextBuilder<B, IOpenSecurityContext>
  {
    protected IOpenSigningKey                    signingKey_;
    protected IFundamentalDatabaseWritable fundamentalDatabase_;
    protected ITraceContext                      trace_;
    
    protected AbstractGenerator(Class<B> type)
    {
      super(type);
    }
    
    /**
     * Set the Fundamental Object Database to save objects to.
     * 
     * @param fundamentalDatabase The Fundamental Object Database to save objects to.
     * 
     * @return This (fluent method).
     */
    public B withFundamentalDatabase(IFundamentalDatabaseWritable fundamentalDatabase)
    {
      fundamentalDatabase_ = fundamentalDatabase;
      
      return self();
    }
    
    /**
     * Set the trace context to use when saving objects.
     * 
     * @param trace The trace context to use when saving objects.
     * 
     * @return This (fluent method).
     */
    public B withTrace(ITraceContext trace)
    {
      trace_ = trace;
      
      return self();
    }

    @Override
    protected void validate()
    {
      super.validate();
      
      if(trace_ == null)
        trace_ = NoOpTraceContext.INSTANCE;
      
      require(fundamentalDatabase_, "Fundamental Database");
    }

    @Override
    protected IOpenSecurityContext construct()
    {
      ICipherSuite  cipherSuite = CipherSuite.getDefault();
      KeyPair       keyPair     = cipherSuite.generateKeyPair();
      SecretKey     secretKey   = cipherSuite.generateKey();
      
      /* 
       * We are not setting a parent here which means that membership is connected directly
       * to this security context. When we create rotation 1 and others we need to set the
       * parent to the rotation zero context.
       */
      this
        .withCipherSuiteId(cipherSuite.getId())
        .withEncodedExchangeKey(cipherSuite.publicKeyToPem(keyPair.getPublic()))
        .withEncryptedPrivateKey(cipherSuite.wrap(keyPair.getPrivate(), secretKey))
        .withRotationId(RotationId.ZERO)
        ;
      
      if(signingKey_ != null)
        withSigningKeyHash(signingKey_.getAbsoluteHash());

      SecurityContext securityContext = new SecurityContext(this);
      
      IFundamentalObject securityContextObject = new FundamentalObject.ObjectBuilder()
          .withSigningKey(signingKey_)
          .withPayload(securityContext)
          .build();
      
      Sequence.Builder membersSequenceBuilder = new Sequence.Builder()
          .withType(SequenceType.CURRENT)
          .withSecurityContextHash(securityContext.getBaseHash())
          .withBaseHash(securityContext.getMembersSequenceId().getAbsoluteHash())
          .withPrevHash(securityContext.getMembersSequenceId().getAbsoluteHash())
          .withPodId(_podId_)
          ;
      
      if(signingKey_ != null)
        membersSequenceBuilder.withSigningKeyHash(signingKey_.getAbsoluteHash());
      
      ISequence membersSequence = membersSequenceBuilder.build();
      
      FundamentalObject.ObjectBuilder membersSequenceObjectBuilder = new FundamentalObject.ObjectBuilder()
          .withPayload(membersSequence)
          ;
      
      if(signingKey_ != null)
        membersSequenceObjectBuilder.withSigningKey(signingKey_);
      
      IFundamentalObject membersSequenceObject = 
          membersSequenceObjectBuilder.build();
      
      fundamentalDatabase_.save(securityContext.getMembersSequenceId(), trace_);
      fundamentalDatabase_.save(membersSequenceObject, trace_);
      fundamentalDatabase_.save(securityContextObject, trace_);
      
      return new OpenSecurityContext(securityContext, keyPair.getPrivate(), secretKey);
    }
  }
  
  /**
   * Builder to generate new security contexts.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Generator extends AbstractGenerator<Generator>
  {
    /**
     * Constructor.
     */
    public Generator()
    {
      super(Generator.class);
    }

    /**
     * Generate a signed object.
     * 
     * @param signingKey The open signing key with which the object is to be signed.
     * 
     * @return This (fluent method)
     */
    public Generator withSigningKey(IOpenSigningKey signingKey)
    {
      signingKey_ = signingKey;
      
      return self();
    }

    @Override
    protected void validate()
    {
      super.validate();
      
      require(signingKey_, "Signing key");
    }
    
  }

  @Override
  public PrivateKey getPrivateKey()
  {
    return privateKey_;
  }

  @Override
  public SecretKey getSecretKey()
  {
    return secretKey_;
  }

  @Override
  public EncryptedData encrypt(IEntity entity)
  {
    return getCipherSuite().encrypt(getSecretKey(), entity.getJsonObject().serialize());
  }

  @Override
  public ImmutableByteArray decrypt(EncryptedData cipherText)
  {
    return getCipherSuite().decrypt(getSecretKey(), cipherText);
  }
}
