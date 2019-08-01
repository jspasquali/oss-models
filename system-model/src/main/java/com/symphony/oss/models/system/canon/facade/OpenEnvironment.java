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
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.naming.CredentialName;
import org.symphonyoss.s2.fugue.naming.INameFactory;

import com.symphony.oss.models.fundamental.canon.facade.FundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.secret.FundamentalSecretManager;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseWritable;
import com.symphony.oss.models.fundmental.canon.ISequence;
import com.symphony.oss.models.fundmental.canon.SecurityContextPermission;
import com.symphony.oss.models.fundmental.canon.Sequence;
import com.symphony.oss.models.fundmental.canon.SequenceType;
import com.symphony.oss.models.system.CredentialManager;

/**
 * An open environment.
 * 
 * Includes the open environment operating principal.
 * 
 * @author Bruce Skingle
 *
 */
public class OpenEnvironment extends Environment implements IOpenEnvironment
{
  private final IOpenPrincipal operatingPrincipal_;

  private OpenEnvironment(IEnvironment environment, IOpenPrincipal operatingPrincipal)
  {
    super(environment);
    
    operatingPrincipal_ = operatingPrincipal;
  }

  @Override
  public IOpenPrincipal getOperatingPrincipal()
  {
    return operatingPrincipal_;
  }

  /**
   * Builder for OpenEnvironment.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractEnvironmentBuilder<Builder, IOpenEnvironment>
  {
    private INameFactory                       nameFactory_;
    private IFundamentalDatabaseWritable fundamentalDatabase_;
    private FundamentalSecretManager           fundamentalSecretManager_;
    private ITraceContext                      trace_;

    private IOpenSecurityContext               environmentSecurityContext_;
    private IOpenPrincipal                     operatingPrincipal_;
    private ISequence podsSequence_;
    private IModelRegistry modelRegistry_;
    
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
    public Builder withFundamentalTrace(ITraceContext trace)
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
      super.validate();
      
      require(nameFactory_,               "Name Factory");
      require(fundamentalDatabase_,       "Fundamental Database");
      require(fundamentalSecretManager_,  "Fundamental Secret Manager");
      require(trace_,                     "Trace Context");
      require(modelRegistry_,             "Model Registry");
    }

    @Override
    protected IOpenEnvironment construct()
    {
      withEnvironmentId(nameFactory_.getEnvironmentId());
      
      environmentSecurityContext_ = new RootSecurityContext.UnsignedGenerator()
          .withFundamentalDatabase(fundamentalDatabase_)
          .withTrace(trace_)
          .build();
      
      withSecurityContextHash(environmentSecurityContext_.getAbsoluteHash());
      
      System.out.println("Create environment operating principal environmentSecurityContext = " + environmentSecurityContext_);
      
      operatingPrincipal_ = new OpenPrincipal.EnvironmentOperatingPrincipalBuilder()
          .withFundamentalDatabase(fundamentalDatabase_)
          .withModelRegistry(modelRegistry_)
          // no signing key for environment .withSigningKey(signingKey)
          .withTrace(trace_)
          .withEnvironmentId(nameFactory_.getEnvironmentId())
          .withSecurityContext(environmentSecurityContext_)
          .withSecurityContextMember(environmentSecurityContext_, SecurityContextPermission.OWNER)
          .build();
          
          
//          Principal.createEnvironmentOperatingPrincipal(nameFactory_,
//          environmentSecurityContext_,
//          fundamentalDatabase_, trace_);
      

      withOperatingPrincipalHash(operatingPrincipal_.getBaseHash());
      
      IOpenSigningKey signingKey = operatingPrincipal_.getCredential().getSigningKey();
      
      podsSequence_ = new Sequence.Builder()
          .withType(SequenceType.CURRENT)
          .withSecurityContextHash(environmentSecurityContext_.getBaseHash())
          .withSigningKeyHash(signingKey.getAbsoluteHash())
          .build();
      
      IFundamentalObject podsSequenceObject = new FundamentalObject.ObjectBuilder()
          .withPayload(podsSequence_)
          .withSigningKey(signingKey)
          .build();
      
      fundamentalDatabase_.save(podsSequenceObject, trace_);
      
      withPodsSequenceHash(podsSequenceObject.getAbsoluteHash());
      
      IEnvironment environment = new Environment(this);
      
      IFundamentalObject environmentObject = new FundamentalObject.ApplicationObjectBuilder()
          .withPayload(environment)
          .withBaseHash(Environment.ID.getAbsoluteHash())
          .withPrevHash(Environment.ID.getAbsoluteHash())
          .withSecurityContext(environmentSecurityContext_)
          .withSigningKey(signingKey)
          .build();

      fundamentalDatabase_.save(ID, trace_);
      fundamentalDatabase_.save(environmentObject,  trace_);
      
//      fundamentalDatabase_.saveIdIfNotExists(ID, environment, signingKey, environmentSecurityContext_, trace_);
      
      //save(environmentObject, trace_);
      
      CredentialName name = nameFactory_.getEnvironmentCredentialName(CredentialManager.OPERATING_PRINCIPAL_NAME);
      fundamentalSecretManager_.putSecret(name, operatingPrincipal_.getCredential());
        
      return new OpenEnvironment(environment, operatingPrincipal_);
    }
    
  }
}
