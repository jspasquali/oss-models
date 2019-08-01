/*
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
 */

package com.symphony.oss.models.system.canon.facade;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.naming.CredentialName;
import org.symphonyoss.s2.fugue.naming.INameFactory;

import com.symphony.oss.models.fundamental.canon.facade.FundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.OpenSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.SecurityContextMember;
import com.symphony.oss.models.fundamental.secret.FundamentalSecretManager;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseWritable;
import com.symphony.oss.models.fundmental.canon.ISequence;
import com.symphony.oss.models.fundmental.canon.ISequenceHashes;
import com.symphony.oss.models.fundmental.canon.MembershipStatus;
import com.symphony.oss.models.fundmental.canon.SecurityContextPermission;
import com.symphony.oss.models.fundmental.canon.Sequence;
import com.symphony.oss.models.fundmental.canon.SequenceHashes;
import com.symphony.oss.models.fundmental.canon.SequenceType;
import com.symphony.oss.models.system.CredentialManager;
import com.symphony.oss.models.system.canon.IPodIdObject;
import com.symphony.oss.models.system.canon.PodIdObject;

/**
 * An open pod, with open operating principal attached.
 * 
 * @author Bruce Skingle
 *
 */
public class OpenPod extends Pod implements IOpenPod
{
  private final IOpenSecurityContext               operatingSecurityContext_;
  private final IOpenPrincipal                     operatingPrincipal_;
  private final ISequence                          podsSequence_;
  private final IOpenSecurityContext               rootSecurityContext_;
  private final IOpenSecurityContext               publicSecurityContext_;
  private final IEnvironment                       environment_;
  private final ISequence                          podPrincipalsSequence_;
  private final ISequence                          contentSequence_;

  private OpenPod(Builder builder)
  {
    super(builder.pod_);
    
    operatingSecurityContext_ = builder.operatingSecurityContext_;
    operatingPrincipal_ = builder.operatingPrincipal_;
    podsSequence_ = builder.podsSequence_;
    rootSecurityContext_ = builder.rootSecurityContext_;
    publicSecurityContext_ = builder.publicSecurityContext_;
    environment_ = builder.environment_;
    podPrincipalsSequence_ = builder.podPrincipalsSequence_;
    contentSequence_ = builder.contentSequence_;
  }

  @Override
  public IOpenSecurityContext getOperatingSecurityContext()
  {
    return operatingSecurityContext_;
  }

  @Override
  public IOpenPrincipal getOperatingPrincipal()
  {
    return operatingPrincipal_;
  }

  @Override
  public ISequence getPodsSequence()
  {
    return podsSequence_;
  }

  @Override
  public IOpenSecurityContext getRootSecurityContext()
  {
    return rootSecurityContext_;
  }

  @Override
  public IOpenSecurityContext getPublicSecurityContext()
  {
    return publicSecurityContext_;
  }

  @Override
  public IEnvironment getEnvironment()
  {
    return environment_;
  }

  @Override
  public ISequence getPodPrincipalsSequence()
  {
    return podPrincipalsSequence_;
  }

  @Override
  public ISequence getContentSequence()
  {
    return contentSequence_;
  }

  /**
   * Builder for OpenPod.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractPodBuilder<Builder, IOpenPod>
  {
    private INameFactory                       nameFactory_;
    private IFundamentalDatabaseWritable       fundamentalDatabase_;
    private FundamentalSecretManager           fundamentalSecretManager_;
    private ITraceContext                      trace_;

    private IOpenPrincipalCredential           environmentCredential_;
    private IOpenSecurityContext               environmentOperatingSecurityContext_;
    private IOpenSecurityContext               operatingSecurityContext_;
    private IOpenPrincipal                     operatingPrincipal_;
    private ISequence                          podsSequence_;
    private IOpenSecurityContext               rootSecurityContext_;
    private IOpenSecurityContext               publicSecurityContext_;
    private IEnvironment                       environment_;
    private ISequence                          podPrincipalsSequence_;
    private ISequence                          contentSequence_;
    private IModelRegistry                     modelRegistry_;
    private Pod                                pod_;
    
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }
    
    /**
     * Set the Name Factory to provide the environment name.
     * 
     * @param nameFactory The Name Factory to provide the environment name.
     * 
     * @return This (fluent method).
     */
    public Builder withNameFactory(INameFactory nameFactory)
    {
      nameFactory_ = nameFactory;
      
      return self();
    }
    
    /**
     * Set the Fundamental Object Database to save objects to.
     * 
     * @param fundamentalDatabase The Fundamental Object Database to save objects to.
     * 
     * @return This (fluent method).
     */
    public Builder withFundamentalDatabase(IFundamentalDatabaseWritable fundamentalDatabase)
    {
      fundamentalDatabase_ = fundamentalDatabase;
      
      return self();
    }
    
    /**
     * Set the Fundamental Secret Manager to save credentials to.
     * 
     * @param fundamentalSecretManager The Fundamental Secret Manager to save credentials to.
     * 
     * @return This (fluent method).
     */
    public Builder withFundamentalSecretManager(FundamentalSecretManager fundamentalSecretManager)
    {
      fundamentalSecretManager_ = fundamentalSecretManager;
      
      return self();
    }
    
    /**
     * Set the trace context to use when saving objects.
     * 
     * @param trace The trace context to use when saving objects.
     * 
     * @return This (fluent method).
     */
    public Builder withTrace(ITraceContext trace)
    {
      trace_ = trace;
      
      return self();
    }
    
    /**
     * Set the Model Registry.
     * 
     * @param modelRegistry The Fundamental Object Database to save objects to.
     * 
     * @return This (fluent method).
     */
    public Builder withModelRegistry(IModelRegistry modelRegistry)
    {
      modelRegistry_ = modelRegistry;
      
      return self();
    }

    @Override
    protected void validate()
    {
      require(nameFactory_,               "Name Factory");
      require(fundamentalDatabase_,       "Fundamental Database");
      require(fundamentalSecretManager_,  "Fundamental Secret Manager");
      require(trace_,                     "Trace Context");
      require(modelRegistry_,             "Model Registry");
      require(getPodUrl(),                "Pod URL");
      require(getPodId(),                 "Pod ID");
      
      super.validate();
    }

    @Override
    protected IOpenPod construct()
    {
      try
      {
        environmentCredential_                = fundamentalSecretManager_.getCredential(nameFactory_.getEnvironmentCredentialName(CredentialManager.OPERATING_PRINCIPAL_NAME));
        environment_                          = fundamentalDatabase_.fetchCurrentById(Environment.ID, environmentCredential_, IEnvironment.class);
        environmentOperatingSecurityContext_  = fundamentalDatabase_.fetchOpenSecurityContext(environment_.getSecurityContextHash(), environmentCredential_);
        podsSequence_                         = fundamentalDatabase_.fetchAbsolute(environment_.getPodsSequenceHash(), environmentCredential_, ISequence.class);
        
        operatingSecurityContext_ = new OpenSecurityContext.Generator()
            .withFundamentalDatabase(fundamentalDatabase_)
            .withTrace(trace_)
            .withSigningKey(environmentCredential_.getSigningKey())
            .withPodId(getPodId())
            .build();
        
        withOperatingSecurityContextHash(operatingSecurityContext_.getAbsoluteHash());
        
        IFundamentalId podPrincipalsSequenceId = Pod.getPrincipalsSequenceHashId(getPodId());
        
        podPrincipalsSequence_ = new Sequence.Builder()
            .withBaseHash(podPrincipalsSequenceId.getAbsoluteHash())
            .withPrevHash(podPrincipalsSequenceId.getAbsoluteHash())
            .withType(SequenceType.CURRENT)
            .withSecurityContextHash(operatingSecurityContext_.getAbsoluteHash())
            .withSigningKeyHash(environmentCredential_.getSigningKey().getAbsoluteHash())
            .withPodId(getPodId())
            
            .build();
        
        IFundamentalObject podPrincipalsSequenceObject = new FundamentalObject.ObjectBuilder()
            .withSigningKey(environmentCredential_.getSigningKey())
            .withPayload(podPrincipalsSequence_)
            .build();
        
        withPrincipalsSequenceHash(podPrincipalsSequenceObject.getAbsoluteHash());
        
        fundamentalDatabase_.save(podPrincipalsSequenceId, trace_);
        fundamentalDatabase_.save(podPrincipalsSequenceObject, trace_);
        
        operatingPrincipal_ = new OpenPrincipal.PodOperatingPrincipalBuilder()
            .withFundamentalDatabase(fundamentalDatabase_)
            .withModelRegistry(modelRegistry_)
            .withTrace(trace_)
            .withSigningKey(environmentCredential_.getSigningKey())
            .withEnvironmentId(nameFactory_.getEnvironmentId())
            .withPodId(getPodId())
            .withSecurityContext(operatingSecurityContext_)
            .withSecurityContextMember(operatingSecurityContext_, SecurityContextPermission.OWNER)
            .withSecurityContextMember(environmentOperatingSecurityContext_, SecurityContextPermission.MEMBER)
            .withSequences(new SequenceHashes.Builder().withCurrent(podPrincipalsSequence_.getBaseHash()).build())
            .build();
        
        withOperatingPrincipalHash(operatingPrincipal_.getBaseHash());
        
        CredentialName name = nameFactory_.getCredentialName(getPodId().getValue(), CredentialManager.OPERATING_PRINCIPAL_NAME);
        fundamentalSecretManager_.putSecret(name, operatingPrincipal_.getCredential());
        
        IOpenSigningKey signingKey = operatingPrincipal_.getCredential().getSigningKey();
        
//        THere isn't much point doing this here 'cos we don't want the operating principal to be a member of this and we
//        don't yet have any actual user principals
//        rootSecurityContext_ = new OpenSecurityContext.Generator()
//        .withFundamentalDatabase(fundamentalDatabase_)
//        .withTrace(trace_)
//            .withSigningKey(signingKey)
//            .build();
        
        publicSecurityContext_ = new OpenSecurityContext.Generator()
            .withFundamentalDatabase(fundamentalDatabase_)
            .withTrace(trace_)
            .withSigningKey(signingKey)
            .withPodId(getPodId())
            .build();
        
        new SecurityContextMember.Builder()
            .withSecurityContext(publicSecurityContext_)
            .withExchangeKey(operatingPrincipal_.getExchangeKey())
            .withSigningKey(signingKey)
            .withMembershipStatus(MembershipStatus.MEMBER)
            .withPermission(SecurityContextPermission.OWNER)
            .withPodId(getPodId())
            .withFundamentalDatabase(fundamentalDatabase_)
            .withTrace(trace_)
          .build();
        
        withPublicSecurityContextHash(publicSecurityContext_.getAbsoluteHash());
        
        // TODO: make the security contexts members of each other.
//        operatingSecurityContext_.addMember(rootSecurityContext_.getExchangeKey(), signingKey,
//            MembershipStatus.MEMBER, SecurityContextPermission.OWNER, fundamentalDatabase_, trace_);
        
        IFundamentalId podContentSequenceId = Pod.getContentSequenceHashId(getPodId());
        
            
        contentSequence_ = new Sequence.Builder()
            .withBaseHash(podContentSequenceId.getAbsoluteHash())
            .withPrevHash(podContentSequenceId.getAbsoluteHash())
            .withType(SequenceType.ABSOLUTE)
            .withSecurityContextHash(operatingSecurityContext_.getAbsoluteHash())
            .withSigningKeyHash(signingKey.getAbsoluteHash())
            .withPodId(getPodId())
            .build();
        
        IFundamentalObject contentSequenceObject = new FundamentalObject.ObjectBuilder()
            .withSigningKey(signingKey)
            .withPayload(contentSequence_)
            .build();
        
        fundamentalDatabase_.save(podContentSequenceId, trace_);
        fundamentalDatabase_.save(contentSequenceObject, trace_);
        
        IPodIdObject podId = new PodIdObject.Builder().withPodId(getPodId()).build();

        pod_ = new Pod(this);
        
        ISequenceHashes sequences = new SequenceHashes.Builder()
            .withCurrent(podsSequence_.getBaseHash())
            .build();
        
        IFundamentalObject podObject = new FundamentalObject.ApplicationObjectBuilder()
            .withBaseHash(podId.getAbsoluteHash())
            .withPrevHash(podId.getAbsoluteHash())
            .withSecurityContext(environmentOperatingSecurityContext_)
            .withSigningKey(signingKey)
            .withPayload(pod_)
            .withSequences(sequences)
            .withPodId(pod_.getPodId())
            .build();
                
        fundamentalDatabase_.save(podId, trace_);
        fundamentalDatabase_.save(podObject, trace_);

        return new OpenPod(this);
      }
      catch(NoSuchObjectException e1)
      {
        throw new IllegalStateException("Unable to create pod", e1);
      }
    }
  }
}