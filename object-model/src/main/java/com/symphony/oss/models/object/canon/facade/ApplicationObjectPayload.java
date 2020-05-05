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

import java.io.IOException;

import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.symphony.oss.canon.runtime.CanonRuntime;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.IJsonDomNode;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.JsonString;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.dom.json.jackson.JacksonAdaptor;
import com.symphony.oss.models.object.ObjectModelRegistry;
import com.symphony.oss.models.object.canon.ApplicationObjectPayloadEntity;
import com.symphony.oss.models.object.canon.IApplicationObjectPayloadEntity;

/**
 * Facade for Object ObjectSchema(ApplicationObjectPayload)
 *
 * Base type for header or encrypted payloads in the object store.
 * Generated from ObjectSchema(ApplicationObjectPayload) at #/components/schemas/ApplicationObjectPayload
 */
@Immutable
public class ApplicationObjectPayload extends ApplicationObjectPayloadEntity implements IApplicationObjectPayload
{
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private final IStoredApplicationObject storedApplicationObject_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ApplicationObjectPayload(AbstractApplicationObjectPayloadBuilder<?,?> builder)
  {
    super(builder);
    
    storedApplicationObject_ = builder.storedApplicationObject_;
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ApplicationObjectPayload(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    storedApplicationObject_ = modelRegistry instanceof ObjectModelRegistry ? ((ObjectModelRegistry)modelRegistry).getStoredApplicationObject() : null;
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ApplicationObjectPayload(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    storedApplicationObject_ = modelRegistry instanceof ObjectModelRegistry ? ((ObjectModelRegistry)modelRegistry).getStoredApplicationObject() : null;
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ApplicationObjectPayload(IApplicationObjectPayload other)
  {
    super(other);
    
    storedApplicationObject_ = other.getStoredApplicationObject();
  }
  
  /**
   * Constructor from serialized form.
   * 
   * @param json The serialized form.
   */
  public ApplicationObjectPayload(String json)
  {
    this(parse(json));
  }

  /**
   * Constructor from serialized form.
   * 
   * @param jsonObject A Jackson parse tree of the serialized form.
   */
  public ApplicationObjectPayload(ObjectNode jsonObject)
  {
    // The modelRegistry parameter is required because of the shape of Canon generated code, but is not used in this case so we pass null.
    super(adapt(jsonObject), null);
    
    storedApplicationObject_ = null;
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
  public IStoredApplicationObject getStoredApplicationObject()
  {
    return storedApplicationObject_;
  }
  
  /**
   * Abstract builder for ApplicationObjectPayload. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractApplicationObjectPayloadBuilder<B extends AbstractApplicationObjectPayloadBuilder<B,T>, T extends IApplicationObjectPayloadEntity> extends AbstractApplicationObjectPayloadEntityBuilder<B,T>
  {
    private IStoredApplicationObject storedApplicationObject_;
    
    protected AbstractApplicationObjectPayloadBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractApplicationObjectPayloadBuilder(Class<B> type, IApplicationObjectPayloadEntity initial)
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