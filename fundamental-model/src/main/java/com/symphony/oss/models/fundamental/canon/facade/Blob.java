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
 *  At                  2018-05-15 14:21:52 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;

import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseReadOnly;
import com.symphony.oss.models.fundamental.canon.facade.IApplicationObject;
import com.symphony.oss.models.fundamental.canon.facade.IBlob;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSimpleSecurityContext;
import com.symphony.oss.models.fundmental.canon.BlobEntity;
import com.symphony.oss.models.fundmental.canon.IBlobEntity;

/**
 * Facade for Object ObjectSchema(Blob)
 *
 * If tenantId is present then the security context belongs to a tenant operating principal (i.e. this is a legacy wrapped object)
 * Generated from ObjectSchema(Blob) at #/components/schemas/Blob
 */
@Immutable
public class Blob extends BlobEntity implements IBlob
{
  private IApplicationObject  applicationObject_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Blob(AbstractBlobBuilder<?,?> builder)
  {
    super(builder);
    
    applicationObject_ = builder.getApplicationObject();
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Blob(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Blob(IBlob other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for Blob. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractBlobBuilder<B extends AbstractBlobBuilder<B,T>, T extends IBlobEntity> extends AbstractBlobEntityBuilder<B,T>
  {
    private IApplicationObject  applicationObject_;
    
    protected AbstractBlobBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractBlobBuilder(Class<B> type, IBlobEntity initial)
    {
      super(type, initial);
    }
    
    /**
     * Set the application object payload for the blob.
     * 
     * @param applicationObject The payload for the blob.
     * 
     * @return This (fluent method)
     */
    public B withApplicationObject(IApplicationObject applicationObject)
    {
      applicationObject_ = applicationObject;
      
      return self();
    }

    /**
     * 
     * @return The current application object payload.
     */
    public IApplicationObject getApplicationObject()
    {
      return applicationObject_;
    }
  }
  
  @Override
  public String getDescription()
  {
    if(applicationObject_ == null)
      return super.getDescription();
    
    return "OpenBlob(" + applicationObject_.getClass().getSimpleName() + ") " + getAbsoluteHash();
  }

  @Override
  public IApplicationObject open(IOpenPrincipalCredential principalContext) throws NoSuchObjectException
  {
    IFundamentalDatabaseReadOnly db = principalContext.getDatabase();
    IOpenSecurityContext securityContext = db.fetchOpenSecurityContext(getSecurityContextHash(), principalContext);
    
    try
    {
      applicationObject_ = (IApplicationObject) principalContext.getModelRegistry().parseOne(securityContext.decrypt(getEncryptedPayload()).getReader());
      applicationObject_.setBlob(this);
      
      return applicationObject_;
    }
    catch(ClassCastException e)
    {
      throw new IllegalArgumentException("Decrypted payload is not an ApplicationObject");
    }
  }

  @Override
  public IApplicationObject open(IOpenSimpleSecurityContext securityContext, IModelRegistry modelRegistry)
  {
//    if(!getSecurityContextHash().equals(securityContext.getAbsoluteHash()))
//      throw new IllegalArgumentException("Given security context is not for this object");
    
    try
    {
      IEntity entity = modelRegistry.parseOne(securityContext.decrypt(getEncryptedPayload()).getReader());
      
      applicationObject_ = (IApplicationObject) entity;
      applicationObject_.setBlob(this);
      
      return applicationObject_;
    }
    catch(ClassCastException  e)
    {
      throw new IllegalStateException("Decrypted payload is not an ApplicationObject");
    }
    catch(IllegalArgumentException e)
    {
      throw new IllegalStateException("Decrypted payload is of unknown type");
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */