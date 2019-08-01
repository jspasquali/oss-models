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

import java.security.PrivateKey;

import org.symphonyoss.s2.canon.runtime.IEntity;

import com.symphony.oss.models.fundmental.canon.EncodedSignature;
import com.symphony.oss.models.fundmental.canon.EncryptedData;
import com.symphony.oss.models.fundmental.canon.PemPrivateKey;

/**
 * An open signing key.
 * 
 * This class contains static factory methods.
 * 
 * @author Bruce Skingle
 *
 */
public class OpenSigningKey extends SigningKey implements IOpenSigningKey
{
  private final PrivateKey privateKey_;

  private OpenSigningKey(ISigningKey exchangeKey, PrivateKey privateKey)
  {
    super(exchangeKey);
    
    setPayloadContainer(exchangeKey.getPayloadContainer());
    privateKey_ = privateKey;
  }
  
  /**
   * Builder for an Open Signing Key.
   * 
   * To generate a new exchange key use:
   * <code>

     new OpenSigningKey.Builder()
        .withPrincipalHash(initialPrinicpalObject.getAbsoluteHash())
        .withCipherSuiteId(cipherSuite_.getId())
        .withEncodedPublicKey(exchangePublicKey)
        .withPrivateKey(exchangeKeyPair.getPrivate())
        .withSigningKey(signingKey_)
        .build()
        ;
        
   * </code> 
   * To wrap an existing closed exchange key (publicKey) use:
   * <code>
     
     new OpenSigningKey.Builder()
        .withPublicKey(publicKey)
        .withPrivateKey(exchangeKeyPair.getPrivate())
        .build()
        
   * </code>
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractSigningKeyBuilder<Builder, IOpenSigningKey>
  {
    private IOpenSigningKey    signingKey_;
    private PrivateKey         privateKey_;
    private IFundamentalObject publicKey_;
    
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }
    
    /**
     * Set the private key.
     * 
     * @param privateKey The private key.
     * 
     * @return This (fluent method).
     */
    public Builder withPrivateKey(PrivateKey privateKey)
    {
      privateKey_ = privateKey;
      
      return self();
    }

    /**
     * Set the signing key with which to sign the created key.
     * 
     * @param signingKey The signing key with which to sign the created key.
     * 
     * @return This (fluent method).
     */
    public Builder withSigningKey(IOpenSigningKey signingKey)
    {
      signingKey_ = signingKey;
      
      return self();
    }

    /**
     * Set the public key object.
     * 
     * If this is set then the builder simply wraps this object with the open facade.
     * 
     * @param publicKey The public key object.
     * 
     * @return This (fluent method).
     */
    public Builder withPublicKey(IFundamentalObject publicKey)
    {
      publicKey_ = publicKey;
      
      return self();
    }
    
    @Override
    protected IOpenSigningKey construct()
    {
      if(signingKey_ != null)
        withSigningKeyHash(signingKey_.getAbsoluteHash());
      
      if(publicKey_ == null)
      {
        publicKey_ = new FundamentalObject.ObjectBuilder()
            .withSigningKey(signingKey_)
            .withPayload(new SigningKey(this))
            .build();
      }
      
      return new OpenSigningKey((ISigningKey)publicKey_.getPayload(), privateKey_);
    }
  }
  
  @Override
  public PrivateKey getPrivateKey()
  {
    return privateKey_;
  }
  
  @Override
  public PemPrivateKey getEncodedPrivateKey()
  {
    return getCipherSuite().privateKeyToPem(privateKey_);
  }

  @Override
  public EncodedSignature sign(EncryptedData payload)
  {
    return getCipherSuite().sign(payload.getValue(), privateKey_);
  }

  @Override
  public EncodedSignature sign(IEntity payload)
  {
    return getCipherSuite().sign(payload.getJsonObject().serialize(), privateKey_);
  }

  /**
   * Create an unsigned SigningKey.
   * 
   * This should only be used for environment root objects.
   * 
   * @param signingKeyBuilder   A builder containing the key attributes.
   * @param privateKey          The private key matching the public key provided in the builder.
   * @return                    An open signing key object.
   * 
   * @Deprecated use Builder.
   * <code>
     
     new OpenSigningKey.Builder()
        .withPrincipalHash(initialPrinicpalObject.getAbsoluteHash())
        .withCipherSuiteId(cipherSuite_.getId())
        .withEncodedPublicKey(exchangePublicKey)
        .withPrivateKey(exchangeKeyPair.getPrivate())
        .build()
        ;
        
   * </code>
   */
  @Deprecated
  public static IOpenSigningKey createUnsigned(Builder signingKeyBuilder, PrivateKey privateKey)
  {
    IFundamentalObject exchangeKeyBlob = FundamentalObject.createUnsigned(signingKeyBuilder);
    
    return new OpenSigningKey((ISigningKey)exchangeKeyBlob.getPayload(), privateKey);
  }
  
  /**
   * Create a signed SigningKey.
   * 
   * We are going to create a signing key object, which is itself signed with a different key. The signingKeyBuilder
   * parameter contains the attributes of the key we are creating and signingKey is another complete signing key with
   * which the new object will be signed.
   * 
   * @param signingKeyBuilder  A builder containing the key attributes.
   * @param privateKey          The private key matching the public key provided in the builder.
   * @param signingKey          The key with which the returned object is to be signed.
   * @return                    An open signing key object.
   * 
   * @Deprecated use Builder.
   * <code>
     
     new OpenSigningKey.Builder()
        .withPrincipalHash(initialPrinicpalObject.getAbsoluteHash())
        .withCipherSuiteId(cipherSuite_.getId())
        .withEncodedPublicKey(exchangePublicKey)
        .withPrivateKey(exchangeKeyPair.getPrivate())
        .withSigningKey(signingKey_)
        .build()
        ;
        
   * </code>
   */
  @Deprecated
  public static IOpenSigningKey createSigned(Builder signingKeyBuilder, PrivateKey privateKey, IOpenSigningKey signingKey)
  {
    IFundamentalObject signingKeyBlob = FundamentalObject.createSigned(signingKeyBuilder, signingKey);
    
    return new OpenSigningKey((ISigningKey)signingKeyBlob.getPayload(), privateKey);
  }

  public static IOpenSigningKey deserialize(ISigningKey signingKey, PrivateKey privateKey)
  {
    return new OpenSigningKey(signingKey, privateKey);
  }
}
