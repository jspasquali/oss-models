/**
 * Copyright 2020 Symphony Communication Services, LLC.
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
 *  At                  2020-01-27 13:01:50 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.allegro.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.symphony.messageml.elements.MessageML;

import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.models.allegro.canon.AbstractReceivedChatMessageEntity;
import com.symphony.oss.models.allegro.canon.IAbstractReceivedChatMessageEntity;
import com.symphony.oss.models.chat.canon.ILiveCurrentMessage;
import com.symphony.oss.models.allegro.canon.AllegroModel;

/**
 * Facade for Object ObjectSchema(AbstractReceivedChatMessage)
 * Generated from ObjectSchema(AbstractReceivedChatMessage) at #/components/schemas/AbstractReceivedChatMessage
 */
@Immutable
@SuppressWarnings("unused")
public class AbstractReceivedChatMessage extends AbstractReceivedChatMessageEntity implements IAbstractReceivedChatMessage
{
  private final MessageML           messageML_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public AbstractReceivedChatMessage(AbstractAbstractReceivedChatMessageBuilder<?,?> builder)
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
  public AbstractReceivedChatMessage(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    messageML_ = initMessageML();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AbstractReceivedChatMessage(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    messageML_ = initMessageML();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public AbstractReceivedChatMessage(IAbstractReceivedChatMessage other)
  {
    super(other);
    
    messageML_ = other.getMessageML();
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
  
  @Override
  public MessageML getMessageML()
  {
    return messageML_;
  }
  
  @Override
  public ILiveCurrentMessage getLiveCurrentMessage()
  {
    throw new AbstractMethodError("If canon allowed it, thiswould be an abstract class....");
  }



  /**
   * Abstract builder for AbstractReceivedChatMessage. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractAbstractReceivedChatMessageBuilder<B extends AbstractAbstractReceivedChatMessageBuilder<B,T>, T extends IAbstractReceivedChatMessageEntity> extends AbstractAbstractReceivedChatMessageEntityBuilder<B,T>
  {
    protected MessageML messageML_;
    
    protected AbstractAbstractReceivedChatMessageBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractAbstractReceivedChatMessageBuilder(Class<B> type, IAbstractReceivedChatMessageEntity initial)
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