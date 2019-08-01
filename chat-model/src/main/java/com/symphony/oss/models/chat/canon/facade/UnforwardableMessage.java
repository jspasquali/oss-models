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
 *  At                  2018-06-21 14:00:20 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.chat.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.IJsonDomNode;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.dom.json.jackson.JacksonAdaptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.symphony.oss.models.chat.canon.IUnforwardableMessageEntity;
import com.symphony.oss.models.chat.canon.UnforwardableMessageEntity;

/**
 * Facade for Object ObjectSchema(UnforwardableMessage)
 *
 * A message from a 1.X pod which we can't process'.
 * Generated from ObjectSchema(UnforwardableMessage) at #/components/schemas/UnforwardableMessage
 */
@Immutable
public class UnforwardableMessage extends UnforwardableMessageEntity implements IUnforwardableMessage
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public UnforwardableMessage(AbstractUnforwardableMessageBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public UnforwardableMessage(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public UnforwardableMessage(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public UnforwardableMessage(IUnforwardableMessage other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for UnforwardableMessage. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractUnforwardableMessageBuilder<B extends AbstractUnforwardableMessageBuilder<B,T>, T extends IUnforwardableMessageEntity> extends AbstractUnforwardableMessageEntityBuilder<B,T>
  {
    private IJsonDomNode  jsonPayload_;
    
    protected AbstractUnforwardableMessageBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractUnforwardableMessageBuilder(Class<B> type, IUnforwardableMessageEntity initial)
    {
      super(type, initial);
    }
    
    /**
     * Set the legacy payload to the given JSON.
     * 
     * @param jsonPayload The legacy payload.
     * @return This (fluent method)
     */
    public B withJsonPayload(JsonNode jsonPayload)
    {
      if(jsonPayload == null)
      {
        jsonPayload_ =  null;
      }
      else
      {
        jsonPayload_ =  JacksonAdaptor.adapt(jsonPayload);
      }
      
      return self();
    }
    
    /**
     * Set the legacy payload to the given JSON.
     * 
     * @param jsonPayload The legacy payload.
     * @return This (fluent method)
     * @throws IllegalArgumentException If the payload is null.
     */
    public B withJsonPayload(IJsonDomNode jsonPayload)
    {
      if(jsonPayload == null)
      {
        throw new IllegalArgumentException("A legacy payload is required.");
      }
      
      jsonPayload_ =  jsonPayload;
      
      return self();
    }
    
    @Override 
    public void getJsonObject(MutableJsonObject jsonObject)
    {
      super.getJsonObject(jsonObject);
  
      if(jsonPayload_ != null)
      {
        jsonObject.addIfNotNull("jsonPayload", jsonPayload_);
      }
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */