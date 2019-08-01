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

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.canon.runtime.exception.PermissionDeniedException;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

import com.symphony.oss.models.fundamental.canon.facade.FundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSimpleSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.PodAndUserId;
import com.symphony.oss.models.fundamental.canon.facade.SecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.SimpleSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.UserIdObject;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseReadOnly;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseWritable;
import com.symphony.oss.models.fundmental.canon.ContentIdObject;
import com.symphony.oss.models.fundmental.canon.ContentIdType;
import com.symphony.oss.models.fundmental.canon.ISequence;
import com.symphony.oss.models.fundmental.canon.Sequence;
import com.symphony.oss.models.fundmental.canon.SequenceType;
import com.symphony.oss.models.system.canon.IPrincipalEntity;
import com.symphony.oss.models.system.canon.PrincipalContentId;
import com.symphony.oss.models.system.canon.PrincipalEntity;

/**
 * Facade for Object ObjectSchema(Principal)
 * Generated from ObjectSchema(Principal) at #/components/schemas/Principal
 */
@Immutable
public class Principal extends PrincipalEntity implements IPrincipal
{
  /** Type ID for client created ID objects with a principal subject type, ensures a client can't generate IDs which collide with system IDs */
  public static final String  CLIENT_TYPE_ID = TYPE_ID + ".ClientGenerated";
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Principal(AbstractPrincipalBuilder<?,?> builder)
  {
    super(builder);
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Principal(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Principal(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Principal(IPrincipal other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for Principal. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractPrincipalBuilder<B extends AbstractPrincipalBuilder<B,T>, T extends IPrincipalEntity> extends AbstractPrincipalEntityBuilder<B,T>
  {
    protected IOpenSimpleSecurityContext          securityContext_;
    protected IOpenSigningKey                     signingKey_;
    protected IFundamentalDatabaseWritable  fundamentalDatabase_;
    protected ITraceContext                       trace_;
    
    protected AbstractPrincipalBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractPrincipalBuilder(Class<B> type, IPrincipalEntity initial)
    {
      super(type, initial);
    }
    
    /**
     * Set the security context which will protect the created principal.
     * 
     * @param securityContext The open security context with which the Principal is to be encrypted. 
     * 
     * @return This (fluent method)
     */
    public B withSecurityContext(IOpenSimpleSecurityContext securityContext)
    {
      securityContext_ = securityContext;
      
      return self();
    }

    /**
     * Generate a signed object.
     * 
     * @param signingKey The open signing key with which the object is to be signed.
     * 
     * @return This (fluent method)
     */
    public B withSigningKey(IOpenSigningKey signingKey)
    {
      signingKey_ = signingKey;
      
      return self();
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
      
      require(securityContext_,           "Security Context");
      
      require(fundamentalDatabase_,       "Fundamental Database");
      require(trace_,                     "Trace Context");
    }

    protected ISequence createSequence()
    {
      Sequence.Builder builder = new Sequence.Builder()
          .withType(SequenceType.CURRENT)
          .withSecurityContextHash(securityContext_.getBaseHash())
          ;
      
      if(signingKey_ != null)
        builder.withSigningKeyHash(signingKey_.getAbsoluteHash());
          
      ISequence sequence = builder.build();
      
      IFundamentalObject sequenceObject = new FundamentalObject.ObjectBuilder()
          .withSigningKey(signingKey_)
          .withPayload(sequence)
          .build()
          ;
      
      fundamentalDatabase_.save(sequenceObject, trace_);
      
      return sequence;
    }
  }
  
  public static IPrincipal  fetchByUserIdChecked(PodAndUserId userId, IFundamentalDatabaseReadOnly fundamentalDatabase, IOpenPrincipalCredential credential)
      throws NoSuchObjectException
  {
    return fundamentalDatabase.fetchCurrentById(new UserIdObject.Builder().withPodAndUserId(userId).build(),
        credential, IPrincipal.class);
  }
  
  public static IPrincipal  fetchByUserId(PodAndUserId userId, IFundamentalDatabaseReadOnly fundamentalDatabase, IOpenPrincipalCredential credential)
  {
    try
    {
      return fetchByUserIdChecked(userId, fundamentalDatabase, credential);
    }
    catch (NoSuchObjectException e)
    {
      throw new PermissionDeniedException("Invalid principal", e);
    }
  }

  /**
   * Return the ID object for the sequence of items delivered to the given principal.
   * 
   * @param principalBaseHash The base hash of the principal whose sequence this ID represents.
   * 
   * @return The ID object for the sequence of items delivered to the given principal.
   */
  public static IFundamentalId getPrincipalDatafeedSequenceId(Hash principalBaseHash)
  {
    return getPrincipalSequenceId(principalBaseHash, PrincipalContentId.DATAFEED.toString());
  }

  /**
   * Return the ID object for the sequence of items in which the given principal is at-mentioned.
   * 
   * @param principalBaseHash The base hash of the principal whose sequence this ID represents.
   * 
   * @return The ID object for the sequence of items delivered to the given principal.
   */
  public static IFundamentalId getPrincipalMentionSequenceId(Hash principalBaseHash)
  {
    return getPrincipalSequenceId(principalBaseHash, PrincipalContentId.MENTIONS.toString());
  }

  /**
   * Return the ID object for the sequence of items in which the given principal is at-mentioned.
   * 
   * @param principalBaseHash The base hash of the principal whose sequence this ID represents.
   * 
   * @return The ID object for the sequence of items delivered to the given principal.
   */
  public static IFundamentalId getPrincipalSecurityContextSequenceId(Hash principalBaseHash)
  {
    return getPrincipalSequenceId(principalBaseHash, SecurityContext.TYPE_ID);
  }

  private static IFundamentalId getPrincipalSequenceId(Hash principalBaseHash, String contentType)
  {
    return new ContentIdObject.Builder()
        .withSubjectHash(principalBaseHash)
        .withSubjectType(Principal.TYPE_ID)
        .withContentType(contentType)
        .withIdType(ContentIdType.CURRENT_SEQUENCE)
        .build();
  }
  
  /**
   * Return the ID object for the principal's personal security context. The key for this context is the user's SBE account key.
   * 
   * @param principalBaseHash The base hash of the principal whose security context this ID represents.
   * 
   * @return The ID object for the principal's personal security context.
   */
  public static IFundamentalId getUserSecurityContextId(Hash principalBaseHash)
  {
    return new ContentIdObject.Builder()
        .withSubjectHash(principalBaseHash)
        .withSubjectType(Principal.TYPE_ID)
        .withContentType(SimpleSecurityContext.TYPE_ID)
        .withIdType(ContentIdType.ATTRIBUTE)
        .build();
  }
}