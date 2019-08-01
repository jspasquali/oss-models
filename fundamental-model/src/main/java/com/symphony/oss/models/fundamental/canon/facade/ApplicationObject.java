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

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.fundamental.canon.facade.IApplicationObject;
import com.symphony.oss.models.fundamental.canon.facade.IBlob;
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
  private static final String UNENCRYPTED = "This object is unencrypted, it has no Blob";
  
  private IBlob blob_;
  

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
    
    blob_ = other.getBlob();
  }
  
  @Override
  public void setBlob(IBlob blob)
  {
    blob_ = blob;
  }
  
  @Override
  public IBlob getBlob()
  {
    if(blob_ == null)
      throw new IllegalStateException(UNENCRYPTED);
    
    return blob_;
  }
  
  @Override
  public Hash getAbsoluteHash()
  {
    if(blob_ == null)
      throw new IllegalStateException(UNENCRYPTED);
    
    return blob_.getAbsoluteHash();
  }

  @Override
  public Hash getPrevHash()
  {
    if(blob_ == null)
      throw new IllegalStateException(UNENCRYPTED);
    
    return blob_.getPrevHash();
  }

  @Override
  public Hash getBaseHash()
  {
    if(blob_ == null)
      throw new IllegalStateException(UNENCRYPTED);
    
    return blob_.getBaseHash();
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