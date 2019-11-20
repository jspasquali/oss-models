/**
 * Copyright 2019 Symphony Communication Services, LLC.
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
 *
 *----------------------------------------------------------------------------------------------------
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2019-03-13 17:20:38 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

import com.symphony.oss.models.crypto.canon.facade.WrappedKey;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseReadOnly;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseWritable;
import com.symphony.oss.models.fundmental.canon.IMemberIdObject;
import com.symphony.oss.models.fundmental.canon.ISecurityContextMemberEntity;
import com.symphony.oss.models.fundmental.canon.ISecurityContextMemberKeys;
import com.symphony.oss.models.fundmental.canon.ISequenceHashes;
import com.symphony.oss.models.fundmental.canon.SecurityContextMemberEntity;
import com.symphony.oss.models.fundmental.canon.SecurityContextMemberKeys;
import com.symphony.oss.models.fundmental.canon.SecurityContextPermission;
import com.symphony.oss.models.fundmental.canon.SequenceHashes;

/**
 * Facade for Object ObjectSchema(SecurityContextMember)
 *
 * wrappedKey is the SecurityContext SecretKey wrapped by the member's Public ExchangeKey. encryptedKey is the SecurityContext SecretKey encrypted with the member's AccountSecretKey. This may be added as an update by the member as a performance optimization.
 * Generated from ObjectSchema(SecurityContextMember) at #/components/schemas/SecurityContextMember
 */
@Immutable
public class SecurityContextMember extends SecurityContextMemberEntity implements ISecurityContextMember
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public SecurityContextMember(AbstractSecurityContextMemberBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SecurityContextMember(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SecurityContextMember(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public SecurityContextMember(ISecurityContextMember other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for SecurityContextMember. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractSecurityContextMemberBuilder<B extends AbstractSecurityContextMemberBuilder<B,T>, T extends ISecurityContextMemberEntity> extends AbstractSecurityContextMemberEntityBuilder<B,T>
  {
    protected IExchangeKey                 exchangeKey_;
    protected IOpenSimpleSecurityContext   securityContext_;

    protected AbstractSecurityContextMemberBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractSecurityContextMemberBuilder(Class<B> type, ISecurityContextMemberEntity initial)
    {
      super(type, initial);
    }

    @Override
    protected void validate()
    {
      if(exchangeKey_ != null)
      {
        withMemberBaseHash(exchangeKey_.getPrincipalBaseHash());
        
        require(securityContext_, "If exchange key is given then security context is required.");
      }
      
      if(securityContext_ != null)
      {
        withGroupBaseHash(securityContext_.getBaseHash());
      }
      else
      {
        require(getGroupBaseHash(), "Security Context or Group Base Hash");
      }
      
      if(getPermission() == null)
        withPermission(SecurityContextPermission.MEMBER);
      
      ISequenceHashes sequences = new SequenceHashes.Builder()
        .withCurrent(SimpleSecurityContext.getMembersSequenceId(getGroupBaseHash()).getAbsoluteHash())
        .build()
        ;
      
      withSequences(sequences);

      super.validate();
    }

    /**
     * Set the exchange key for the member.
     * 
     * @param exchangeKey The exchange key for the member.
     * 
     * @return This (fluent method).
     */
    public B withExchangeKey(IExchangeKey exchangeKey)
    {
      exchangeKey_ = exchangeKey;
      
      return self();
    }

    /**
     * Set the security context of which we are creating a member.
     * 
     * @param securityContext The security context of which we are creating a member.
     * 
     * @return This (fluent method).
     */
    public B withSecurityContext(IOpenSimpleSecurityContext securityContext)
    {
      securityContext_ = securityContext;
      
      return self();
    }
  }
  
  /**
   * Builder for SecurityContextMember
   *
   */
  public static class Builder extends AbstractSecurityContextMemberBuilder<Builder, ISecurityContextMember>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    /**
     * Constructor initialised from another object instance.
     * 
     * @param initial
     *          An instance of the built type from which values are to be
     *          initialised.
     */
    public Builder(ISecurityContextMemberEntity initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected ISecurityContextMember construct()
    {
      preConstruct();
  
      SecurityContextMember  member        = new SecurityContextMember(this);
      IFundamentalObject     memberObject  = new FundamentalObject.ObjectBuilder()
           .withSigningKey(signingKey_)
           .withPayload(member)
           .build()
           ;
       
      fundamentalDatabase_.save(memberObject, trace_);
       
      if(exchangeKey_ != null)
      {
         createMemberKeysFor(securityContext_, exchangeKey_, fundamentalDatabase_,
             signingKey_, trace_);
      }
       
      return member;
    }
  }
    
  public static ISecurityContextMemberKeys fetchMemberKeysFor(Hash securityContextHash, Hash principalBaseHash,
      IFundamentalDatabaseReadOnly fundamentalDatabase, IOpenPrincipalCredential credential)
      throws NoSuchObjectException
  {
    IMemberIdObject keysId = getMemberKeysIdFor(securityContextHash, principalBaseHash);
    
    return fundamentalDatabase.fetchCurrentById(keysId, credential, ISecurityContextMemberKeys.class);
  }

  public static void createMemberKeysFor(IOpenSimpleSecurityContext securityContext,
      IExchangeKey exchangeKey,
      IFundamentalDatabaseWritable fundamentalDatabase,
      IOpenSigningKey signingKey,
      ITraceContext trace)
  {
    
    WrappedKey wrappedKey = exchangeKey.getCipherSuite().wrap(securityContext.getSecretKey(), exchangeKey.getPublicKey());
    
    createMemberKeysFor(securityContext.getAbsoluteHash(), exchangeKey.getPrincipalBaseHash(),
        exchangeKey.getAbsoluteHash(),
        wrappedKey,
        null,
        fundamentalDatabase, signingKey, trace);
  }
  
  public static void createMemberKeysFor(Hash securityContextHash, Hash principalBaseHash,
      Hash exchangeKeyHash,
      WrappedKey wrappedKey,
      WrappedKey encryptedKey,
      IFundamentalDatabaseWritable fundamentalDatabase,
      IOpenSigningKey signingKey,
      ITraceContext trace)
  {
    IMemberIdObject keysId = getMemberKeysIdFor(securityContextHash, principalBaseHash);
    
    ;
    try
    {
      ISecurityContextMemberKeys existingKeys = fundamentalDatabase.fetchCurrent(keysId.getAbsoluteHash(), ISecurityContextMemberKeys.class);
      
      boolean update = false;
      
      if(existingKeys.getWrappedKey() == null)
      {
        if(wrappedKey == null)
        {
          wrappedKey = existingKeys.getWrappedKey();
        }
        else
        {
          update = true;
        }
      }
      
      if(existingKeys.getEncryptedKey() == null)
      {
        if(encryptedKey == null)
        {
          encryptedKey = existingKeys.getEncryptedKey();
        }
        else
        {
          update = true;
        }
      }
      
      if(update)
      {
        // keys are not the same
        
        ISecurityContextMemberKeys memberKeys = new SecurityContextMemberKeys.Builder()
            .withMemberExchangeKeyHash(exchangeKeyHash)
            .withSigningKeyHash(signingKey.getBaseHash())
            .withWrappedKey(wrappedKey)
            .withEncryptedKey(encryptedKey)
            .withBaseHash(keysId.getAbsoluteHash())
            .withPrevHash(existingKeys.getAbsoluteHash())
            .build()
            ;
        
        IFundamentalObject memberKeysObject = new FundamentalObject.ObjectBuilder()
          .withPayload(memberKeys)
          .withSigningKey(signingKey)
          .build();

        fundamentalDatabase.save(memberKeysObject, trace);
      }
    }
    catch (NoSuchObjectException e)
    {
      fundamentalDatabase.save(keysId, trace);
      
      ISecurityContextMemberKeys memberKeys = new SecurityContextMemberKeys.Builder()
          .withMemberExchangeKeyHash(exchangeKeyHash)
          .withSigningKeyHash(signingKey.getBaseHash())
          .withWrappedKey(wrappedKey)
          .withEncryptedKey(encryptedKey)
          .withBaseHash(keysId.getAbsoluteHash())
          .withPrevHash(keysId.getAbsoluteHash())
          .build()
          ;
      
      IFundamentalObject memberKeysObject = new FundamentalObject.ObjectBuilder()
        .withPayload(memberKeys)
        .withSigningKey(signingKey)
        .build();

      fundamentalDatabase.save(memberKeysObject, trace);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */