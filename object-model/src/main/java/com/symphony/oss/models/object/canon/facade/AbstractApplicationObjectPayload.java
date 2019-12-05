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
 *  At                  2019-12-02 20:02:19 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import com.symphony.oss.models.object.canon.AbstractApplicationObjectPayloadEntity;
import com.symphony.oss.models.object.canon.IAbstractApplicationObjectPayloadEntity;

/**
 * Facade for Object ObjectSchema(AbstractApplicationObjectPayload)
 *
 * Base type for header or encrypted payloads in the object store.
 * Generated from ObjectSchema(AbstractApplicationObjectPayload) at #/components/schemas/AbstractApplicationObjectPayload
 */
public class AbstractApplicationObjectPayload extends AbstractApplicationObjectPayloadEntity implements IAbstractApplicationObjectPayload
{
  private IStoredApplicationObject storedApplicationObject_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public AbstractApplicationObjectPayload(AbstractAbstractApplicationObjectPayloadBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AbstractApplicationObjectPayload(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AbstractApplicationObjectPayload(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public AbstractApplicationObjectPayload(IAbstractApplicationObjectPayload other)
  {
    super(other);
  }
  
  @Override
  public IStoredApplicationObject getStoredApplicationObject()
  {
    return storedApplicationObject_;
  }

  public void setStoredApplicationObject(IStoredApplicationObject storedApplicationObject)
  {
    storedApplicationObject_ = storedApplicationObject;
  }

  /**
   * Abstract builder for AbstractApplicationObjectPayload. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractAbstractApplicationObjectPayloadBuilder<B extends AbstractAbstractApplicationObjectPayloadBuilder<B,T>, T extends IAbstractApplicationObjectPayloadEntity> extends AbstractAbstractApplicationObjectPayloadEntityBuilder<B,T>
  {
    private IStoredApplicationObject storedApplicationObject_;
    
    protected AbstractAbstractApplicationObjectPayloadBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractAbstractApplicationObjectPayloadBuilder(Class<B> type, IAbstractApplicationObjectPayloadEntity initial)
    {
      super(type, initial);
    }
    
    /**
     * Set the StoredApplicationObject.
     * 
     * @param storedApplicationObject The StoredApplicationObject.
     * 
     * @return This (fluent method)
     */
    public B withStoredApplicationObject(IStoredApplicationObject storedApplicationObject)
    {
      storedApplicationObject_ = storedApplicationObject;
      
      return self();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */