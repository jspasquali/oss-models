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
 *  At                  2018-05-14 14:37:44 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.io.IOException;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.CanonRuntime;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.IJsonDomNode;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.JsonString;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.dom.json.jackson.JacksonAdaptor;
import org.symphonyoss.s2.common.hash.Hash;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.symphony.oss.models.fundmental.canon.ApplicationObjectEntity;

/**
 * Facade for Object ObjectSchema(ApplicationObject)
 *
 * If tenantId is present then the security context belongs to a tenant operating principal (i.e. this is a legacy wrapped object)
 * Generated from ObjectSchema(ApplicationObject) at #/components/schemas/ApplicationObject
 */
@Immutable
public class ApplicationObject extends ApplicationObjectEntity implements IApplicationObject
{
  private static final String       UNENCRYPTED   = "This object is unencrypted, it has no Blob";
  private static final String       NO_CONTAINER   = "This object has no container";
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private IBlob                     blob_;
  private IVersionedObject          container_;
  

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ApplicationObject(AbstractApplicationObjectBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ApplicationObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }

  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ApplicationObject(IApplicationObject other)
  {
    super(other);
    
    try
    {
      blob_ = other.getBlob();
    }
    catch(IllegalStateException e)
    {
      blob_ = null;
    }
    
    container_ = other.getContainer();
  }

  /**
   * Constructor from serialized form.
   * 
   * @param jsonObject A Jackson parse tree of the serialized form.
   */
  public ApplicationObject(ObjectNode jsonObject)
  {
    // The modelRegistry parameter is required because of the shape of Canon generated code, but is not used in this case so we pass null.
    super(adapt(jsonObject), null);
  }
  
  private static ImmutableJsonObject adapt(ObjectNode jsonObject)
  {
    MutableJsonObject mutableJsonObject = JacksonAdaptor.adaptObject(jsonObject);
    
    IJsonDomNode typeId = mutableJsonObject.get(CanonRuntime.JSON_TYPE);
    
    if(typeId == null)
    {
      mutableJsonObject.addIfNotNull(CanonRuntime.JSON_TYPE, TYPE_ID);
      mutableJsonObject.addIfNotNull(CanonRuntime.JSON_VERSION, TYPE_VERSION);
    }
    else if(!(typeId instanceof JsonString))
    {
      throw new IllegalArgumentException("If _type is present it must be a string value");
    }
    
    return mutableJsonObject.immutify();
  }
  
  /**
   * Constructor from serialized form.
   * 
   * @param json The serialized form.
   */
  public ApplicationObject(String json)
  {
    this(parse(json));
  }
  
  private static ObjectNode parse(String json)
  { 
    try
    {
      return (ObjectNode)OBJECT_MAPPER.readTree(json);
    }
    catch(IOException | ClassCastException e)
    {
      throw new IllegalArgumentException("A valid JSON object is required.");
    }
  }

  @Override
  @Deprecated
  public void setBlob(IBlob blob)
  {
    container_ = blob_ = blob;
  }
  
  @Override
  public IVersionedObject getContainer()
  {
    return container_;
  }

  @Override
  public void setContainer(IVersionedObject container)
  {
    container_ = container;
    
    if(container instanceof IBlob)
      blob_ = (IBlob) container;
  }

  @Override
  @Deprecated
  public IBlob getBlob()
  {
    if(blob_ == null)
      throw new IllegalStateException(UNENCRYPTED);
    
    return blob_;
  }
  
  @Override
  public Hash getAbsoluteHash()
  {
    if(container_ == null)
      throw new IllegalStateException(NO_CONTAINER);
    
    return container_.getAbsoluteHash();
  }

  @Override
  public Hash getPrevHash()
  {
    if(container_ == null)
      throw new IllegalStateException(NO_CONTAINER);
    
    return container_.getPrevHash();
  }

  @Override
  public Hash getBaseHash()
  {
    if(container_ == null)
      throw new IllegalStateException(NO_CONTAINER);
    
    return container_.getBaseHash();
  }

  @Override
  public IFundamentalObject getFundamentalObject()
  {
    if(container_ == null)
      throw new IllegalStateException(NO_CONTAINER);
    
    return container_.getPayloadContainer();
  }

//  @Override
//  public IFundamentalObject encrypt(IOpenSecurityContext securityContext, @Nullable TenantId tenantId)
//  {
//    try
//    {
//      BlobEntity.Builder builder = new Blob.Builder()
//        .withTenantId(tenantId)
////        .withCreatedDate(Instant.now())
//        .withSecurityContextHash(securityContext.getAbsoluteHash())
//        .withEncryptedPayload(securityContext.encrypt(this))
//        .withApplicationObject(this)
//        ;
//      
//      return FundamentalObject.createUnsigned(builder);
//      
////      return blob_;
//    }
//    catch(InvalidValueException e)
//    {
//      throw new CodingFault(e);
//    }
//  }
//  
//  @Override
//  public IFundamentalObject encryptAndSign(IOpenSecurityContext securityContext, IOpenSigningKey principalKey, @Nullable TenantId tenantId)
//  {
//    try
//    {
//      EncryptedData payload = securityContext.encrypt(this);
//      
//      return blob_ = new Blob.Builder()
//        .withTenantId(tenantId)
//        .withClientSigningKeyHash(principalKey.getAbsoluteHash())
//        .withClientSignature(principalKey.sign(payload))
//        .withClientCreatedDate(Instant.now())
//        .withSecurityContextHash(securityContext.getAbsoluteHash())
//        .withEncryptedPayload(payload)
//        .build()
//        ;
//    }
//    catch(InvalidValueException e)
//    {
//      throw new CodingFault(e);
//    }
//  }
  
//  /**
//   * Create an object in the Realm root.
//   * 
//   * Because this is part of the bootstrap process we can't sign as usual.
//   * 
//   * @param applicationPayload
//   * @param securityContext
//   * @return
//   */
//  public static IApplicationObject createRootObject(IEntity applicationPayload, IOpenSecurityContext securityContext)
//  {
//    try
//    {
//      return new ApplicationObject.Builder()
//        .withClientCreatedDate(Instant.now())
//        .withSecurityContextHash(securityContext.getAbsoluteHash())
//        //.withEncryptedPayload(securityContext.encrypt(applicationPayload))
//        .build()
//        ;
//    }
//    catch(InvalidValueException e)
//    {
//      throw new CodingFault(e);
//    }
//  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */