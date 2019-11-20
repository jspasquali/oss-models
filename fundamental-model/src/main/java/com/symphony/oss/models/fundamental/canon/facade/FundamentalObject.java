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
 *  At                  2018-02-23 15:21:01 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.common.hash.HashProvider;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.store.IFuguePodId;

import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.crypto.cipher.SignatureVerificationException;
import com.symphony.oss.models.fundmental.canon.DeletedObject;
import com.symphony.oss.models.fundmental.canon.DeletionType;
import com.symphony.oss.models.fundmental.canon.FundamentalObjectEntity;
import com.symphony.oss.models.fundmental.canon.HashType;
import com.symphony.oss.models.fundmental.canon.IDeletedObject;
import com.symphony.oss.models.fundmental.canon.IFundamentalObjectEntity;
import com.symphony.oss.models.fundmental.canon.ISequenceHashes;
import com.symphony.oss.models.fundmental.canon.PodFundamentalId;

/**
 * Facade for Object ObjectSchema(FundamentalObject)
 * Generated from ObjectSchema(FundamentalObject) at #/components/schemas/FundamentalObject
 */
@Immutable
public class FundamentalObject extends FundamentalObjectEntity implements IFundamentalObject
{
  private final Hash               absoluteHash_;
  private final String             rangeKey_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public FundamentalObject(AbstractFundamentalObjectBuilder<?,?> builder)
  {
    super(forceHashType(builder));
    
    absoluteHash_ = generateHash();
    rangeKey_ = generateRangeKey();
    
    setPayloadContainer();
  }

  private static AbstractFundamentalObjectBuilder<?,?> forceHashType(AbstractFundamentalObjectBuilder<?,?> builder)
  {
    return builder
      .withHashType(HashType.newBuilder().build(Hash.getDefaultHashTypeId()));
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public FundamentalObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    absoluteHash_ = generateHash();
    rangeKey_ = generateRangeKey();
    
    setPayloadContainer();
  }
  
  /**
   * Abstract builder for FundamentalObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractFundamentalObjectBuilder<B extends AbstractFundamentalObjectBuilder<B,T>, T extends IFundamentalObjectEntity> extends AbstractFundamentalObjectEntityBuilder<B,T>
  {
    protected Instant   createdDate_;
    protected Instant   purgeDate_;
    protected Duration  purgeDuration_;
    
    protected AbstractFundamentalObjectBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractFundamentalObjectBuilder(Class<B> type, IFundamentalObjectEntity initial)
    {
      super(type, initial);
    }

    /**
     * Set the createdDate for the object.
     * 
     * @param createdDate The createdDate for the built object.
     * 
     * @return This (fluent method)
     */
    public B withCreatedDate(Instant createdDate)
    {
      createdDate_ = createdDate;
      
      return self();
    }

    /**
     * Set the purgeDate for the object.
     * 
     * @param purgeDate The purgeDate for the built object.
     * 
     * @return This (fluent method)
     */
    public B withPurgeDate(Instant purgeDate)
    {
      purgeDuration_ = null;
      purgeDate_ = purgeDate;
      
      return self();
    }
    
    /**
     * Set the date when the object should be purged, as an offset from the current time.
     * 
     * @param amount  The amount of time.
     * @param unit    The unit of time.
     * 
     * @return This (fluent method)
     */
    public B withPurgeTime(long amount, TemporalUnit unit)
    {
      purgeDate_ = null;
      purgeDuration_ = Duration.of(amount, unit);
      
      return self();
    }

    @Override
    protected void validate()
    {
      if(createdDate_ == null)
        createdDate_ = Instant.now();
      
      super.validate();
    }
 }
  
  /**
   * Abstract builder for FundamentalObjects with an object payload. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   */
  public static abstract class AbstractFundamentalObjectObjectBuilder<B extends AbstractFundamentalObjectObjectBuilder<B>> extends AbstractFundamentalObjectBuilder<B,IFundamentalObject>
  {
    protected IOpenSigningKey signingKey_;
    
    protected AbstractFundamentalObjectObjectBuilder(Class<B> type)
    {
      super(type);
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
  }
  
  
  /**
   * Builder for objects with an versioned object payload.
   * 
   * @author Bruce Skingle
   *
   */
  public static class VersionedObjectDeleter extends AbstractFundamentalObjectVersionedObjectBuilder<VersionedObjectDeleter>
  {
    private PodId podId_;

    /**
     * Constructor.
     * 
     * @param existingObject An existing Application Object which is to be deleted. 
     */
    public VersionedObjectDeleter(IVersionedObject existingObject)
    {
      super(VersionedObjectDeleter.class);
      
      withSequences(existingObject.getSequences());

      withBaseHash(existingObject.getBaseHash());
      withPrevHash(existingObject.getAbsoluteHash());
      
      podId_ = existingObject.getPodId();
    }

    @Override
    public VersionedObjectDeleter withPayload(IFundamentalPayload value)
    {
      throw new IllegalArgumentException("You cannot set the payload on a delete");
    }

    @Override
    protected IFundamentalObject construct()
    {
      DeletedObject.Builder builder = new DeletedObject.Builder()
          .withCreatedDate(createdDate_)
          .withSequences(sequences_)
          .withDeletionType(deletionType_)
          .withPodId(podId_)
          ;
      
      if(purgeDate_ == null && purgeDuration_ != null)
        purgeDate_ = createdDate_.plus(purgeDuration_);
        
      if(purgeDate_ != null)
        builder.withPurgeDate(purgeDate_);
      
      if(signingKey_ != null)
        builder
          .withSigningKeyHash(signingKey_.getAbsoluteHash());
      
      if(baseHash_ != null)
        builder.withBaseHash(baseHash_);
      
      if(prevHash_ != null)
        builder.withPrevHash(prevHash_);
      
      IDeletedObject blob = builder.build();
      
      super.withPayload(blob);
      
      if(signingKey_ != null)
        withSignature(signingKey_.sign(blob));
      
      FundamentalObject container = new FundamentalObject(this);
            
      blob.setPayloadContainer(container);
      
      return container;
    }
  }
  
  /**
   * Abstract builder for FundamentalObjects with a versioned object payload. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   */
  public static abstract class AbstractFundamentalObjectVersionedObjectBuilder<B extends AbstractFundamentalObjectVersionedObjectBuilder<B>> extends AbstractFundamentalObjectObjectBuilder<B>
  {
    protected Hash            baseHash_;
    protected Hash            prevHash_;
    protected ISequenceHashes sequences_;
    protected DeletionType    deletionType_;

    protected AbstractFundamentalObjectVersionedObjectBuilder(Class<B> type)
    {
      super(type);
    }

    /**
     * Set the base hash for the built object.
     * 
     * @param baseHash the base hash for the built object.
     * 
     * @return This (fluent method)
     */
    public B withBaseHash(Hash baseHash)
    {
      baseHash_ = baseHash;
      
      return self();
    }

    /**
     * Set the prev hash for the built object.
     * 
     * @param prevHash the prev hash for the built object.
     * 
     * @return This (fluent method)
     */
    public B withPrevHash(Hash prevHash)
    {
      prevHash_ = prevHash;
      
      return self();
    }

    /**
     * Set the list of sequences to which the built object should be added.
     * 
     * @param sequences The list of sequences to which the built object should be added.
     * 
     * @return This (fluent method)
     */
    public B withSequences(ISequenceHashes sequences)
    {
      sequences_ = sequences;
      
      return self();
    }
    
    /**
     * Set the deletion mode.
     * 
     * @param deletionType The type of deletion to be performed.
     * 
     * @return This (fluent method)
     */
    public B withDeletionType(DeletionType deletionType)
    {
      deletionType_ = deletionType;
      
      return self();
    }
  }
  

  /**
   * Builder for objects with an application object payload.
   * 
   * @author Bruce Skingle
   *
   */
  public static class ApplicationObjectBuilder extends AbstractFundamentalObjectApplicationObjectBuilder<ApplicationObjectBuilder>
  {
    /**
     * Constructor.
     */
    public ApplicationObjectBuilder()
    {
      super(ApplicationObjectBuilder.class);
    }
  }
  
  /**
   * Abstract builder for FundamentalObjects with a application object payload. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   */
  public static abstract class AbstractFundamentalObjectApplicationObjectBuilder<B extends AbstractFundamentalObjectApplicationObjectBuilder<B>> extends AbstractFundamentalObjectVersionedObjectBuilder<B>
  {
    protected IApplicationObject         payload_;
    protected PodId                      podId_;
    protected IOpenSimpleSecurityContext securityContext_;

    protected AbstractFundamentalObjectApplicationObjectBuilder(Class<B> type)
    {
      super(type);
    }

    /**
     * Set the owning pod for this object.
     * 
     * @param podId The ID of the pod who owns this object.
     * 
     * @return This (fluent method)
     */
    public B withPodId(PodId podId)
    {
      podId_ = podId;
      
      return self();
    }
    
    /**
     * Generate an object with an encrypted payload.
     * 
     * @param securityContext The open security context with which the payload is to be encrypted. 
     * 
     * @return This (fluent method)
     */
    public B withSecurityContext(IOpenSimpleSecurityContext securityContext)
    {
      securityContext_ = securityContext;
      
      return self();
    }

    @Override
    public IFundamentalObject construct()
    {
      Blob.Builder builder = new Blob.Builder()
          .withCreatedDate(createdDate_)
          .withSecurityContextHash(securityContext_.getAbsoluteHash())
          .withEncryptedPayload(securityContext_.encrypt(payload_))
          .withSequences(sequences_)
          .withPodId(podId_)
          .withPayloadType(payload_.getCanonType())
          ;
      
      if(purgeDate_ == null && purgeDuration_ != null)
        purgeDate_ = createdDate_.plus(purgeDuration_);
      
      if(purgeDate_ != null)
        builder.withPurgeDate(purgeDate_);
      
      if(signingKey_ != null)
        builder
          .withSigningKeyHash(signingKey_.getAbsoluteHash());
      
      if(baseHash_ != null)
        builder.withBaseHash(baseHash_);
      
      if(prevHash_ != null)
        builder.withPrevHash(prevHash_);
      
      IBlob blob = builder.build();
      
      payload_.setContainer(blob);
      
      super.withPayload(blob);
      
      if(signingKey_ != null)
        withSignature(signingKey_.sign(blob));
      
      FundamentalObject container = new FundamentalObject(this);
            
      blob.setPayloadContainer(container);
      
      return container;
    }
    
    @Override
    protected void validate()
    {
      if(payload_ == null)
        throw new IllegalStateException("Payload is required.");
      
      if(securityContext_ == null)
        throw new IllegalStateException("Security context is required.");
      
      super.validate();
    }

    /**
     * Set the payload for the object.
     * 
     * @param payload The payload for the object.
     * 
     * @return This (fluent method)
     */
    public B withPayload(IApplicationObject payload)
    {
      payload_ = payload;
      
      return self();
    }
    
    @Override
    public B withPayload(IFundamentalPayload value)
    {
      throw new IllegalStateException("An IApplicationObject is required.");
    }
  }
  
  /**
   * Builder for objects with an IEntity payload.
   * 
   * These objects are stored as unencrypted Clob objects in the datastore.
   * 
   * @author Bruce Skingle
   *
   */
  public static class EntityObjectBuilder extends AbstractFundamentalObjectEntityObjectBuilder<EntityObjectBuilder>
  {
    /**
     * Constructor.
     */
    public EntityObjectBuilder()
    {
      super(EntityObjectBuilder.class);
    }
  }
  
  /**
   * Abstract builder for FundamentalObjects with a application object payload. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   */
  public static abstract class AbstractFundamentalObjectEntityObjectBuilder<B extends AbstractFundamentalObjectEntityObjectBuilder<B>> extends AbstractFundamentalObjectVersionedObjectBuilder<B>
  {
    protected IEntity      payload_;
    protected PodId        podId_;

    protected AbstractFundamentalObjectEntityObjectBuilder(Class<B> type)
    {
      super(type);
    }

    /**
     * Set the owning pod for this object.
     * 
     * @param podId The ID of the pod who owns this object.
     * 
     * @return This (fluent method)
     */
    public B withPodId(PodId podId)
    {
      podId_ = podId;
      
      return self();
    }

    @Override
    public IFundamentalObject construct()
    {
      Clob.Builder builder = new Clob.Builder()
          .withCreatedDate(createdDate_)
          .withSequences(sequences_)
          .withPodId(podId_)
          .withPayload(payload_)
          ;
      
      if(purgeDate_ == null && purgeDuration_ != null)
        purgeDate_ = createdDate_.plus(purgeDuration_);
      
      if(purgeDate_ != null)
        builder.withPurgeDate(purgeDate_);
      
      if(signingKey_ != null)
        builder
          .withSigningKeyHash(signingKey_.getAbsoluteHash());
      
      if(baseHash_ != null)
        builder.withBaseHash(baseHash_);
      
      if(prevHash_ != null)
        builder.withPrevHash(prevHash_);
      
      IClob blob = builder.build();
      
      if(payload_ instanceof IApplicationObject)
        ((IApplicationObject)payload_).setContainer(blob);
      
      super.withPayload(blob);
      
      if(signingKey_ != null)
        withSignature(signingKey_.sign(blob));
      
      FundamentalObject container = new FundamentalObject(this);
            
      blob.setPayloadContainer(container);
      
      return container;
    }
    
    @Override
    protected void validate()
    {
      if(payload_ == null)
        throw new IllegalStateException("Payload is required.");
      
      super.validate();
    }

    /**
     * Set the payload for the object.
     * 
     * @param payload         The payload for the object.
     * 
     * @return This (fluent method)
     */
    public B withPayload(IEntity payload)
    {
      payload_ = payload;
      
      return self();
    }
    
    @Override
    public B withPayload(IFundamentalPayload payload)
    {
      payload_ = payload;
      
      return self();
    }
  }
  

  
  /**
   * Builder for objects with an object payload.
   * 
   * @author Bruce Skingle
   *
   */
  public static class ObjectBuilder extends AbstractFundamentalObjectObjectBuilder<ObjectBuilder>
  {
    private IObjectPayload objectPayload_;

    /**
     * Constructor.
     */
    public ObjectBuilder()
    {
      super(ObjectBuilder.class);
    }

    @Override
    public IFundamentalObject construct()
    {
      if(signingKey_ == null)
      {
        if(objectPayload_.getSigningKeyHash() != null)
          throw new IllegalStateException("Payload indicates it is signed but signing key is not provided.");
      }
      else
      {
        if(!signingKey_.getAbsoluteHash().equals(objectPayload_.getSigningKeyHash()))
          throw new IllegalStateException("Payload indicates it is signed by key " + objectPayload_.getSigningKeyHash() + 
              " but signing key " + signingKey_.getAbsoluteHash() + " is provided.");
        
        withSignature(signingKey_.sign(objectPayload_));
      }
      
      FundamentalObject container = new FundamentalObject(this);
            
      objectPayload_.setPayloadContainer(container);
      
      return container;
    }
    
    @Override
    protected void validate()
    {
      require(objectPayload_, "Payload");
      
      super.withPayload(objectPayload_);
      
      super.validate();
    }

    /**
     * Set the payload for the object.
     * 
     * @param payload The payload for the object.
     * 
     * @return This (fluent method)
     */
    public ObjectBuilder withPayload(IObjectPayload payload)
    {
      objectPayload_ = payload;
      
      return self();
    }
  }
  
  /**
   * Builder for objects with an ID Payload.
   * 
   * @author Bruce Skingle
   *
   */
  public static class IdBuilder extends AbstractFundamentalObjectBuilder<IdBuilder, IFundamentalObject>
  {
    private IFundamentalId             fundamentalId_;
    private IIdPayloadContainer        idPayloadContainer_;
    
    /**
     * Constructor.
     */
    public IdBuilder()
    {
      super(IdBuilder.class);
    }

    @Override
    public IFundamentalObject construct()
    {
      FundamentalObject container = new FundamentalObject(this);
            
      idPayloadContainer_.setPayloadContainer(container);
      
      return container;
    }
    
    /**
     * Set the ID for this object.
     * 
     * @param fundamentalId The ID Payload for the object.
     * 
     * @return This (fluent method)
     */
    public IdBuilder withFundamentalId(IFundamentalId fundamentalId)
    {
      fundamentalId_ = fundamentalId;
      
      return self();
    }

    @Override
    protected void validate()
    {
      if(fundamentalId_ == null)
        throw new IllegalStateException("Fundamental ID is required");
      
      IdPayloadContainer.Builder builder = new IdPayloadContainer.Builder()
          .withId(fundamentalId_)
          .withCreatedDate(createdDate_)
          .withPurgeDate(purgeDate_)
          ;
      
      if(fundamentalId_ instanceof PodFundamentalId)
        builder.withPodId(((PodFundamentalId) fundamentalId_).getPodId());
      
      idPayloadContainer_ = builder
          .build()
          ;
      
      withPayload(idPayloadContainer_);

      super.validate();
    }
    
  }

  protected Hash generateHash()
  {
    if(getPayload() instanceof IdPayloadContainer)
      return ((IdPayloadContainer)getPayload()).getId().getAbsoluteHash();
    
    return HashProvider.getHashOf(getHashType().asInteger(), serialize());
  }

  protected String generateRangeKey()
  {
    IFundamentalPayload payload = getPayload();
    
    if(payload == null)
      return null;
    
    return payload.generateRangeKey(absoluteHash_);
  }

  protected void setPayloadContainer()
  {
    IFundamentalPayload payload = getPayload();
    
    if(payload != null)
      payload.setPayloadContainer(this);
  }

  @Override
  public Hash getAbsoluteHash()
  {
    return absoluteHash_;
  }

  @Override
  public String getRangeKey()
  {
    return rangeKey_;
  }

  @Override
  public IFuguePodId getPodId()
  {
    return getPayload().getPodId();
  }

  @Override
  public void trace(ITraceContext trace, String operationId)
  {
    trace.trace(operationId, getPayload().getClass().getSimpleName(), getAbsoluteHash().toString());
  }

  @Override
  public String getDescription()
  {
    IFundamentalPayload payload = getPayload();
    
    if(payload == null)
      return getClass().getSimpleName() + " fundamentalAbsoluteHash=" + getAbsoluteHash();
    
    return payload.getDescription() + " fundamentalAbsoluteHash=" + getAbsoluteHash();
  }
  
  @Override
  public void verifySignature(ISigningKey signingKey) throws SignatureVerificationException
  {
    signingKey.verifySignature(getSignature(), getPayload().serialize());
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */