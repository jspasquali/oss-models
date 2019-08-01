/**
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
 *
 *----------------------------------------------------------------------------------------------------
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2018-05-14 11:35:56 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.system.canon.facade;

import java.util.function.Consumer;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.exception.NoSuchObjectException;

import com.symphony.oss.models.fundamental.canon.facade.ExchangeKey;
import com.symphony.oss.models.fundamental.canon.facade.FundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IAbstractPublicKey;
import com.symphony.oss.models.fundamental.canon.facade.IExchangeKey;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.ISigningKey;
import com.symphony.oss.models.fundamental.canon.facade.IUserIdObject;
import com.symphony.oss.models.fundamental.canon.facade.PodAndUserId;
import com.symphony.oss.models.fundamental.canon.facade.PodId;
import com.symphony.oss.models.fundamental.canon.facade.SigningKey;
import com.symphony.oss.models.fundamental.canon.facade.UserIdObject;
import com.symphony.oss.models.fundmental.canon.CipherSuiteId;
import com.symphony.oss.models.fundmental.canon.ISequence;
import com.symphony.oss.models.fundmental.canon.PemPublicKey;
import com.symphony.oss.models.fundmental.canon.SequenceHashes;
import com.symphony.oss.models.system.canon.IPrincipalEntity;
import com.symphony.oss.models.system.canon.PrincipalType;

/**
 * Facade for Object ObjectSchema(Principal)
 * Generated from ObjectSchema(Principal) at #/components/schemas/Principal
 */
@Immutable
public class NewPrincipal extends Principal implements IPrincipal
{

  private final IFundamentalObject principalObject_;
  private final IFundamentalObject subjectSigningKeyObject_;
  private final IFundamentalObject subjectExchangeKeyObject_;
  
  private NewPrincipal(IPrincipal principal, Builder builder)
  {
    super(principal);
    
    principalObject_ = builder.principalObject_;
    subjectSigningKeyObject_ = builder.subjectSigningKeyObject_;
    subjectExchangeKeyObject_ = builder.subjectExchangeKeyObject_;
  }
  
  public IFundamentalObject getPrincipalObject()
  {
    return principalObject_;
  }

  public IFundamentalObject getSubjectSigningKeyObject()
  {
    return subjectSigningKeyObject_;
  }

  public IFundamentalObject getSubjectExchangeKeyObject()
  {
    return subjectExchangeKeyObject_;
  }

  /**
   * Builder for SBE Principals. If there are sub-classes of this type then their builders sub-class this builder.
   * 
   * This class requires a userId and a podId and then checks that the pod element of the userId matches the pod ID.
   * This is not strictly necessary, we could just take the pod ID from the user ID but since the HTTP authentication token
   * contains both, this is a (possibly redundant, but) validation step.
   */
  public static class Builder extends AbstractPrincipalBuilder<Builder, NewPrincipal>
  {
    protected PodAndUserId             userId_;
    protected PodId                    podId_;
    protected IOpenPrincipalCredential credential_;
    protected CipherSuiteId            cipherSuiteId_;
    protected PemPublicKey             subjectExchangeKey_;
    protected PemPublicKey             subjectSigningKey_;
    private IPrincipal                 principal_;
    private IFundamentalObject         principalObject_;
    private IFundamentalObject         subjectSigningKeyObject_;
    private IFundamentalObject         subjectExchangeKeyObject_;
    
    public Builder()
    {
      super(Builder.class);
    }
    
    public Builder(IPrincipalEntity initial)
    {
      super(Builder.class, initial);
    }
    
    public Builder withUserId(PodAndUserId userId)
    {
      userId_ = userId;
      
      return self();
    }
    
    public Builder withPodId(PodId podId)
    {
      podId_ = podId;
      
      return self();
    }
    
    public Builder withCipherSuiteId(CipherSuiteId cipherSuiteId)
    {
      cipherSuiteId_ = cipherSuiteId;
      
      return self();
    }
    
    public Builder withSubjectExchangeKey(PemPublicKey subjectExchangeKey)
    {
      subjectExchangeKey_ = subjectExchangeKey;
      
      return self();
    }
    
    public Builder withSubjectSigningKey(PemPublicKey subjectSigningKey)
    {
      subjectSigningKey_ = subjectSigningKey;
      
      return self();
    }

    /**
     * Set the credential.
     * 
     * @param credential An environment credential for deserializing objects.
     * 
     * @return This (fluent method)
     */
    public Builder withCredential(IOpenPrincipalCredential credential)
    {
      credential_ = credential;
      
      return withSigningKey(credential.getSigningKey());
    }

    @Override
    protected void validate()
    {
      super.validate();
      
      require(userId_,                    "User ID");
      require(podId_,                     "Pod ID");
      require(cipherSuiteId_,             "Cipher Suite ID");
      require(credential_,                "Credential");
      require(subjectSigningKey_,         "Subject Signing Key");
      require(subjectExchangeKey_,        "Subject Exchange Key");
      
      if(!podId_.equals(userId_.getPodId()))
        throw new IllegalStateException("User ID is not from the given pod ID");
    }
    
    class KeyChecker<K extends IAbstractPublicKey> implements Consumer<K>
    {
      final PemPublicKey expectedKey_;
      K existingKey_;
      
      KeyChecker(PemPublicKey expectedKey)
      {
        expectedKey_ = expectedKey;
      }
      
      @Override
      public void accept(K existingKey)
      {
        if(existingKey.getEncodedPublicKey().equals(expectedKey_))
          existingKey_ = existingKey;
      }
    }

    @Override
    protected NewPrincipal construct()
    {
      try
      {
        principal_ = Principal.fetchByUserIdChecked(userId_, fundamentalDatabase_, credential_);
        principalObject_ = principal_.getBlob().getPayloadContainer(); 
      }
      catch (NoSuchObjectException e)
      {
        // New principal - create it

        ISequence exchangeKeySequence = createSequence();
        
        withExchangeKeySequenceHash(exchangeKeySequence.getAbsoluteHash());
        
        ISequence signingKeySequence = createSequence();
        
        withSigningKeySequenceHash(signingKeySequence.getAbsoluteHash());
        
        withPrincipalType(PrincipalType.USER)
            .withLegacyUserId(userId_)
            .withPodId(podId_);
        
        principal_ = new Principal(this);
        
        IUserIdObject userId = new UserIdObject.Builder().withPodAndUserId(userId_).build();
        
        principalObject_ = new FundamentalObject.ApplicationObjectBuilder()
            .withPayload(principal_)
            .withBaseHash(userId.getAbsoluteHash())
            .withPrevHash(userId.getAbsoluteHash())
            .withPodId(podId_)
            .withSecurityContext(securityContext_)
            .withSigningKey(credential_.getSigningKey())
            .withSequences(new SequenceHashes.Builder().withCurrent(Pod.getPrincipalsSequenceHashId(podId_).getAbsoluteHash()).build())
            .build();
        
        fundamentalDatabase_.save(userId, trace_);
        fundamentalDatabase_.save(principalObject_, trace_);
      }
      
      KeyChecker<IExchangeKey> exchangeKeyChecker = new KeyChecker<>(subjectExchangeKey_);
      
      fundamentalDatabase_.fetchSequenceRecentObjects(principal_.getExchangeKeySequenceHash(), null, null, 
          exchangeKeyChecker, credential_, IExchangeKey.class);
           
      if(exchangeKeyChecker.existingKey_ == null)
      {
        IExchangeKey subjectExchangeKey = new ExchangeKey.Builder()
            .withPrincipalBaseHash(principal_.getBaseHash())
            .withCipherSuiteId(cipherSuiteId_)
            .withEncodedPublicKey(subjectExchangeKey_)
            .withSigningKeyHash(credential_.getSigningKey().getAbsoluteHash())
            .withSequences(new SequenceHashes.Builder().withCurrent(principal_.getExchangeKeySequenceHash()).build())
            .build();
        
        subjectExchangeKeyObject_ = new FundamentalObject.ObjectBuilder()
            .withPayload(subjectExchangeKey)
            .withSigningKey(credential_.getSigningKey())
            .build();
          
        fundamentalDatabase_.save(subjectExchangeKeyObject_, trace_);
      }
      else
      {
        subjectExchangeKeyObject_ = exchangeKeyChecker.existingKey_.getPayloadContainer();
      }
      
      KeyChecker<ISigningKey> signingKeyChecker = new KeyChecker<>(subjectSigningKey_);
      
      fundamentalDatabase_.fetchSequenceRecentObjects(principal_.getSigningKeySequenceHash(), null, null, 
          signingKeyChecker, credential_, ISigningKey.class);
      
      if(signingKeyChecker.existingKey_ == null)
      {
        ISigningKey subjectSigningKey = new SigningKey.Builder()
            .withPrincipalBaseHash(principal_.getBaseHash())
            .withCipherSuiteId(cipherSuiteId_)
            .withEncodedPublicKey(subjectSigningKey_)
            .withSigningKeyHash(credential_.getSigningKey().getAbsoluteHash())
            .withSequences(new SequenceHashes.Builder().withCurrent(principal_.getSigningKeySequenceHash()).build())
            .build();
      
        subjectSigningKeyObject_ = new FundamentalObject.ObjectBuilder()
            .withPayload(subjectSigningKey)
            .withSigningKey(credential_.getSigningKey())
            .build();
       
        fundamentalDatabase_.save(subjectSigningKeyObject_, trace_);
      }
      else
      {
        subjectSigningKeyObject_ = signingKeyChecker.existingKey_.getPayloadContainer();
      }
      
      return new NewPrincipal(principal_, this);
    }
  }
}