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
 *  At                  2019-07-26 08:19:34 GMT-07:00
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.IJsonDomNode;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import com.symphony.oss.models.fundmental.canon.ClobEntity;
import com.symphony.oss.models.fundmental.canon.IClobEntity;

/**
 * Facade for Object ObjectSchema(Clob)
 *
 * An unencrypted JSON payload
 * Generated from ObjectSchema(Clob) at #/components/schemas/Clob
 */
@Immutable
public class Clob extends ClobEntity implements IClob
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Clob(AbstractClobBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Clob(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Clob(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Clob(IClob other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for Clob. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractClobBuilder<B extends AbstractClobBuilder<B,T>, T extends IClobEntity> extends AbstractClobEntityBuilder<B,T>
  {
    protected IJsonDomNode jsonPayload_;
    
    protected AbstractClobBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractClobBuilder(Class<B> type, IClobEntity initial)
    {
      super(type, initial);
    }
    
    /**
     * Set the JSON payload.
     * 
     * @param jsonPayload The payload.
     * 
     * @return this (fluent method)
     */
    public B withPayload(IJsonDomNode jsonPayload)
    {
      jsonPayload_ = jsonPayload;
      
      return self();
    }
    
    @Override 
    public void getJsonObject(MutableJsonObject jsonObject)
    {
      super.getJsonObject(jsonObject);
  
      if(jsonPayload_ != null)
      {
        jsonObject.addIfNotNull("payload", jsonPayload_);
      }
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */