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
 *  At                  2019-03-05 11:05:29 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.allegro.canon.facade;

import java.io.IOException;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.symphony.messageml.MessageMLContext;
import org.symphonyoss.symphony.messageml.exceptions.InvalidInputException;
import org.symphonyoss.symphony.messageml.exceptions.ProcessingException;
import org.symphonyoss.symphony.messageml.util.IDataProvider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.dom.json.jackson.JacksonAdaptor;
import com.symphony.oss.models.allegro.canon.ChatMessageEntity;
import com.symphony.oss.models.allegro.canon.EntityJson;
import com.symphony.oss.models.allegro.canon.IChatMessageEntity;

/**
 * Facade for Object ObjectSchema(ChatMessage)
 * Generated from ObjectSchema(ChatMessage) at #/components/schemas/ChatMessage
 */
@Immutable
public class ChatMessage extends ChatMessageEntity implements IChatMessage
{
  private static final String FORMAT_MESSAGEMLV2 = "com.symphony.messageml.v2";
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ChatMessage(AbstractChatMessageBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ChatMessage(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ChatMessage(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ChatMessage(IChatMessage other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for ChatMessage. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractChatMessageBuilder<B extends AbstractChatMessageBuilder<B,T>, T extends IChatMessageEntity> extends AbstractChatMessageEntityBuilder<B,T>
  {
    private IDataProvider  dataProvider_;
    private IModelRegistry modelRegistry_;
    private String messageML_;
    private String entityJson_;

    protected AbstractChatMessageBuilder(Class<B> type)
    {
      super(type);
    }

    protected AbstractChatMessageBuilder(Class<B> type, IChatMessageEntity initial)
    {
      super(type, initial);
    }
    
    /**
     * Set the modelRegistry and dataProvider for this builder.
     * 
     * This method is called by the Allegro API implementation and need not be called by client applications.
     * 
     * @param modelRegistry An IModelRegistry for deserialization.
     * @param dataProvider  An IDataProvider to provide user presentations.
     * 
     * @return This (fluent method)
     */
    public B withRegistry(IModelRegistry modelRegistry, IDataProvider dataProvider)
    {
      modelRegistry_ = modelRegistry;
      dataProvider_ = dataProvider;
      
      return self();
    }
    
    /**
     * Set the EntityJSON for the message.
     * 
     * @param entityJson  EntityJSON as a String.
     * 
     * @return This (fluent method)
     */
    public B withEntityJson(String entityJson)
    {
      entityJson_ = entityJson;
      
      JsonNode json;
      try
      {
        json = OBJECT_MAPPER.readTree(entityJson);
        
        withEntityJson(new EntityJson((MutableJsonObject) JacksonAdaptor.adapt(json), modelRegistry_));
        
        return self();
      }
      catch (IOException e)
      {
        throw new IllegalArgumentException("Invalid JSON", e);
      }
    }
    
    /**
     * Set the MessageML for the message.
     * 
     * @param messageML   MessageML as a String.
     * 
     * @return This (fluent method)
     */
    public B withMessageML(String messageML)
    {
      messageML_ = messageML;
      
      return self();
    }
    
    @Override
    protected void validate()
    {
      if(messageML_ != null)
      {
        try
        {
          MessageMLContext context = new MessageMLContext(dataProvider_);
          
          context.parseMessageML(messageML_, entityJson_, FORMAT_MESSAGEMLV2);
          
          withPresentationML(context.getPresentationML());
          withEntityJson(new EntityJson((MutableJsonObject) JacksonAdaptor.adapt(context.getEntityJson()), modelRegistry_));
        }
        catch (InvalidInputException | ProcessingException | IOException e)
        {
          throw new IllegalArgumentException("Invalid MessageML", e);
        }
      }
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */