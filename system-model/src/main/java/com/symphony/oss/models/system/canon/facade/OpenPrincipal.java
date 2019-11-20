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

package com.symphony.oss.models.system.canon.facade;

import java.security.KeyPair;
import java.util.LinkedList;
import java.util.List;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.core.canon.facade.UserId;
import com.symphony.oss.models.crypto.canon.PemPublicKey;
import com.symphony.oss.models.crypto.cipher.CipherSuite;
import com.symphony.oss.models.crypto.cipher.ICipherSuite;
import com.symphony.oss.models.fundamental.canon.facade.FundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IOpenExchangeKey;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSimpleSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.IPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IUserIdObject;
import com.symphony.oss.models.fundamental.canon.facade.OpenExchangeKey;
import com.symphony.oss.models.fundamental.canon.facade.OpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.OpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.PrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.SecurityContextMember;
import com.symphony.oss.models.fundamental.canon.facade.UserIdObject;
import com.symphony.oss.models.fundmental.canon.ISequence;
import com.symphony.oss.models.fundmental.canon.ISequenceHashes;
import com.symphony.oss.models.fundmental.canon.MembershipStatus;
import com.symphony.oss.models.fundmental.canon.SecurityContextPermission;
import com.symphony.oss.models.fundmental.canon.SequenceHashes;
import com.symphony.oss.models.system.canon.IPrincipalEntity;
import com.symphony.oss.models.system.canon.PrincipalType;

/**
 * An Open Principal, with an IPrincipalCredential attached.
 * 
 * @author Bruce Skingle
 *
 */
public class OpenPrincipal extends Principal implements IOpenPrincipal
{
  private final IOpenPrincipalCredential credential_;
  private final IOpenExchangeKey         exchangeKey_;
  private final IOpenSigningKey          signingKey_;

  /**
   * Constructor.
   * 
   * @param principal   The Principal.
   * @param credential  The principal credential.
   * @param exchangeKey The open exchange key for the principal.
   * @param signingKey  The open signing key for the principal.
   */
  public OpenPrincipal(IPrincipal principal, IOpenPrincipalCredential credential, IOpenExchangeKey exchangeKey, IOpenSigningKey signingKey)
  {
    super(principal);
    setContainer(principal.getContainer());
    credential_ = credential;
    exchangeKey_ = exchangeKey;
    signingKey_ = signingKey;
  }

  @Override
  public IOpenPrincipalCredential getCredential()
  {
    return credential_;
  }
  
  @Override
  public IOpenExchangeKey getExchangeKey()
  {
    return exchangeKey_;
  }

  @Override
  public IOpenSigningKey getSigningKey()
  {
    return signingKey_;
  }

  /**
   * Abstract builder for OpenPrincipal.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   */
  public static abstract class AbstractOpenPrincipalBuilder<B extends AbstractOpenPrincipalBuilder<B>> extends AbstractPrincipalBuilder<B,IOpenPrincipal>
  {
    class SecurityContextMembershipInfo
    {
      IOpenSimpleSecurityContext  securityContext_;
      SecurityContextPermission   permission_;
      
      SecurityContextMembershipInfo(IOpenSimpleSecurityContext securityContext, SecurityContextPermission permission)
      {
        securityContext_  = securityContext;
        permission_       = permission;
      }
    }
    
    protected ICipherSuite                        cipherSuite_                   = CipherSuite.getDefault();
    protected List<SecurityContextMembershipInfo> securityContextMembershipInfo_ = new LinkedList<>();
    protected IModelRegistry                      modelRegistry_;
    protected ISequenceHashes                     sequences_;

    protected AbstractOpenPrincipalBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractOpenPrincipalBuilder(Class<B> type, IPrincipalEntity initial)
    {
      super(type, initial);
    }
    
    /**
     * Set the Model Registry.
     * 
     * @param modelRegistry The Fundamental Object Database to save objects to.
     * 
     * @return This (fluent method).
     */
    public B withModelRegistry(IModelRegistry modelRegistry)
    {
      modelRegistry_ = modelRegistry;
      
      return self();
    }
    
    /**
     * Add the created principal to the given security context.
     * 
     * @param securityContext The security context to which the new principal should be added.
     * @param permission      The permission the new principal should have in the given security context.
     * 
     * @return This (fluent method)
     */
    public B withSecurityContextMember(IOpenSimpleSecurityContext securityContext, SecurityContextPermission permission)
    {
      securityContextMembershipInfo_.add(new SecurityContextMembershipInfo(securityContext, permission));
      
      return self();
    }
    
    /**
     * Set the list of sequences to which the built object should be added.
     * 
     * @param sequences A list of sequences to which the built object should be added.
     * 
     * @return This (fluent method)
     */
    public B withSequences(ISequenceHashes sequences)
    {
      sequences_ = sequences;
      
      return self();
    }

    @Override
    protected void validate()
    {
      super.validate();

      require(getEnvironmentId(),   "Environment ID");
      require(modelRegistry_,       "Model Registry");
    }

    @Override
    protected IOpenPrincipal construct()
    {
      KeyPair               exchangeKeyPair   = cipherSuite_.generateKeyPair();
      PemPublicKey          exchangePublicKey = cipherSuite_.publicKeyToPem(exchangeKeyPair.getPublic());
      KeyPair               signingKeyPair    = cipherSuite_.generateKeyPair();
      PemPublicKey          signingPublicKey  = cipherSuite_.publicKeyToPem(signingKeyPair.getPublic());
      
      ISequence exchangeKeySequence = createSequence();
      
      withExchangeKeySequenceHash(exchangeKeySequence.getAbsoluteHash());
      
      ISequence signingKeySequence = createSequence();
      
      withSigningKeySequenceHash(signingKeySequence.getAbsoluteHash());
      
      IPrincipal principal = createPrincipalObject();
      
      IOpenExchangeKey subjectExchangeKey = new OpenExchangeKey.Builder()
          .withPrincipalBaseHash(principal.getBaseHash())
          .withCipherSuiteId(cipherSuite_.getId())
          .withEncodedPublicKey(exchangePublicKey)
          .withPrivateKey(exchangeKeyPair.getPrivate())
          .withSigningKey(signingKey_)
          .withSequences(new SequenceHashes.Builder().withCurrent(exchangeKeySequence.getAbsoluteHash()).build())
          .build()
          ;
      
      fundamentalDatabase_.save(subjectExchangeKey.getPayloadContainer(), trace_);
      
      IOpenSigningKey subjectSigningKey = new OpenSigningKey.Builder()
          .withPrincipalBaseHash(principal.getBaseHash())
          .withCipherSuiteId(cipherSuite_.getId())
          .withEncodedPublicKey(signingPublicKey)
          .withPrivateKey(signingKeyPair.getPrivate())
          .withSigningKey(signingKey_)
          .withSequences(new SequenceHashes.Builder().withCurrent(signingKeySequence.getAbsoluteHash()).build())
          .build()
          ;
      
      fundamentalDatabase_.save(subjectSigningKey.getPayloadContainer(), trace_);
      
      for(SecurityContextMembershipInfo membershipInfo : securityContextMembershipInfo_)
      {
        SecurityContextMember.Builder builder = new SecurityContextMember.Builder()
          .withFundamentalDatabase(fundamentalDatabase_)
          .withTrace(trace_)
          .withExchangeKey(subjectExchangeKey)
          .withSecurityContext(membershipInfo.securityContext_)
          .withPermission(membershipInfo.permission_)
          .withMembershipStatus(MembershipStatus.MEMBER)
        ;
        
        if(signingKey_ == null)
          builder.withSigningKey(subjectSigningKey);
        else
          builder.withSigningKey(signingKey_);
        
        builder.build();
      }
      
      PrincipalCredential.Builder credentialBuilder = new PrincipalCredential.Builder()
          .withEnvironmentId(getEnvironmentId())
          .withPrincipalBaseHash(principal.getBaseHash())
          .withExchangeKeyHash(subjectExchangeKey.getAbsoluteHash())
          .withEncodedExchangeKey(cipherSuite_.privateKeyToPem(exchangeKeyPair.getPrivate()))
          .withSigningKeyHash(subjectSigningKey.getAbsoluteHash())
          .withEncodedSigningKey(cipherSuite_.privateKeyToPem(signingKeyPair.getPrivate()));
      
      if(getPodId() != null)
        credentialBuilder.withPodId(getPodId().getValue());
      
      IPrincipalCredential credential = credentialBuilder.build();
      
      IOpenPrincipalCredential openPrincipalCredential = new OpenPrincipalCredential(credential, subjectExchangeKey, subjectSigningKey, fundamentalDatabase_, modelRegistry_);
      
      return new OpenPrincipal(principal, openPrincipalCredential, subjectExchangeKey, subjectSigningKey);
    }

    protected IPrincipal createPrincipalObject()
    {
      IPrincipal principal = new Principal(this);
      
      IFundamentalObject principalObject = new FundamentalObject.ApplicationObjectBuilder()
        .withPayload(principal)
        .withPodId(getPodId())
        .withSecurityContext(securityContext_)
        .withSigningKey(signingKey_)
        .withSequences(sequences_)
        .build();
      
      fundamentalDatabase_.save(principalObject, trace_);
      
      return principal;
    }
  }
  
  /**
   * Builder for an Environment Operating Principal.
   * 
   * @author Bruce Skingle
   *
   */
  public static class EnvironmentOperatingPrincipalBuilder extends AbstractOpenPrincipalBuilder<EnvironmentOperatingPrincipalBuilder>
  {
    /**
     * Constructor.
     */
    public EnvironmentOperatingPrincipalBuilder()
    {
      super(EnvironmentOperatingPrincipalBuilder.class);
    }

    @Override
    protected void validate()
    {
      super.validate();
      
      withPrincipalType(PrincipalType.ENVIRONMENT_OPERATING);
    }
  }
  
  /**
   * Builder for a Pod Operating Principal.
   * 
   * @author Bruce Skingle
   *
   */
  public static class PodOperatingPrincipalBuilder extends AbstractOpenPrincipalBuilder<PodOperatingPrincipalBuilder>
  {
    /**
     * Constructor.
     */
    public PodOperatingPrincipalBuilder()
    {
      super(PodOperatingPrincipalBuilder.class);
    }

    @Override
    protected void validate()
    {
      super.validate();
      
      if(getPodId() == null)
      {
        throw new IllegalStateException("PodId is required.");
        
      }

      withPrincipalType(PrincipalType.POD_OPERATING);
    }
  }
  
  /**
   * Builder for a User Principal.
   * 
   * THIS IS NOT HOW ACTUAL USER PRINCIPALS ARE CREATED
   * 
   * In the current system user principals are created by a client calling establishPrincipal passing their
   * public keys. The server does not normally know the private keys for user principals but this class
   * generates them.
   * 
   * This is currently only used for testing.
   * 
   * @author Bruce Skingle
   *
   */
  public static class UserPrincipalBuilder extends AbstractOpenPrincipalBuilder<UserPrincipalBuilder>
  {
    protected PodAndUserId  userId_;
    
    /**
     * Constructor.
     */
    public UserPrincipalBuilder()
    {
      super(UserPrincipalBuilder.class);
    }
    
    @Override
    protected IPrincipal createPrincipalObject()
    {
      IUserIdObject userId = new UserIdObject.Builder().withPodAndUserId(userId_).build();
      
      fundamentalDatabase_.save(userId, trace_);

      withLegacyUserId(userId_);
      IPrincipal principal = new Principal(this);
      
      IFundamentalObject principalObject = new FundamentalObject.ApplicationObjectBuilder()
        .withBaseHash(userId.getAbsoluteHash())
        .withPrevHash(userId.getAbsoluteHash())
        .withPayload(principal)
        .withPodId(getPodId())
        .withSecurityContext(securityContext_)
        .withSigningKey(signingKey_)
        .withSequences(sequences_)
        .build();
      
      fundamentalDatabase_.save(principalObject, trace_);
      
      return principal;
    }

    public UserPrincipalBuilder withUserId(PodId podId, long localUserId)
    {
      withPodId(podId);
      
      userId_ = PodAndUserId.newBuilder().build(UserId.replacePodId(localUserId, podId.getValue()));
      
      return self();
    }

    @Override
    protected void validate()
    {
      super.validate();
      
      if(getPodId() == null)
      {
        throw new IllegalStateException("PodId is required.");
      }
      
      if(userId_ == null)
      {
        throw new IllegalStateException("uaerId is required.");
      }

      withPrincipalType(PrincipalType.USER);
    }
  }
}

