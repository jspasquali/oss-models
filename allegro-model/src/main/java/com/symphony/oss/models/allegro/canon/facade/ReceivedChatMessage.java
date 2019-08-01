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

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.symphony.messageml.elements.MessageML;

import com.symphony.oss.models.allegro.canon.IReceivedChatMessageEntity;
import com.symphony.oss.models.allegro.canon.ReceivedChatMessageEntity;

/**
 * Facade for Object ObjectSchema(ReceivedChatMessage)
 * Generated from ObjectSchema(ReceivedChatMessage) at #/components/schemas/ReceivedChatMessage
 */
@Immutable
public class ReceivedChatMessage extends ReceivedChatMessageEntity implements IReceivedChatMessage
{
  private final MessageML messageML_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ReceivedChatMessage(AbstractReceivedChatMessageBuilder<?,?> builder)
  {
    super(builder);
    
    messageML_ = builder.messageML_;
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ReceivedChatMessage(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    messageML_ = null;
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ReceivedChatMessage(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    messageML_ = initMessageML();
  }
  
  private MessageML initMessageML()
  {
    return null;
    
    // This does not work, at-mentions result in an NPE with a null dataProvider
//    try
//    {
//      MessageMLContext context = new MessageMLContext(null);
//      
//      String version = null;
//      
//      context.parseMessageML(getPresentationML().getValue(), getEntityJson().toString(), version);
//      
//      return context.getMessageML();
//    }
//    catch (InvalidInputException | ProcessingException | IOException e)
//    {
//      throw new IllegalArgumentException("Unable parse messageML", e);
//    }
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ReceivedChatMessage(IReceivedChatMessage other)
  {
    super(other);
    
    messageML_ = other.getMessageML();
  }
  
  @Override
  public MessageML getMessageML()
  {
    return messageML_;
  }

  /**
   * Abstract builder for ReceivedChatMessage. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractReceivedChatMessageBuilder<B extends AbstractReceivedChatMessageBuilder<B,T>, T extends IReceivedChatMessageEntity> extends AbstractReceivedChatMessageEntityBuilder<B,T>
  {
    protected MessageML messageML_;
    
    protected AbstractReceivedChatMessageBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractReceivedChatMessageBuilder(Class<B> type, IReceivedChatMessageEntity initial)
    {
      super(type, initial);
    }
    
    /**
     * Set the messageML for the message.
     * 
     * @param messageML The messageML for the message.
     * 
     * @return this (fluent method).
     */
    public B withMessageML(MessageML messageML)
    {
      messageML_ = messageML;
      
      return self();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */