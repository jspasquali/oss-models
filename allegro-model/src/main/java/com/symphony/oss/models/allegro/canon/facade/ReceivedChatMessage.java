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

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.models.allegro.canon.IReceivedChatMessageEntity;
import com.symphony.oss.models.allegro.canon.ReceivedChatMessageEntity;

/**
 * This class represents a message which has been received from Symphony and decrypted.
 * Messages are not persisted in this format and this class lacks the constructors needed to
 * deserialize an instance from JSON because the MessageML cannot be reconstructed without
 * an IDataProvider
 * 
 * Facade for Object ObjectSchema(ReceivedChatMessage)
 * Generated from ObjectSchema(ReceivedChatMessage) at #/components/schemas/ReceivedChatMessage
 */
@Immutable
public class ReceivedChatMessage extends ReceivedChatMessageEntity implements IReceivedChatMessage
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ReceivedChatMessage(AbstractReceivedChatMessageBuilder<?,?> builder)
  {
    super(builder);
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
  }
  
  
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ReceivedChatMessage(IReceivedChatMessage other)
  {
    super(other);
  }

  /**
   * Abstract builder for ReceivedChatMessage. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractReceivedChatMessageBuilder<B extends AbstractReceivedChatMessageBuilder<B,T>, T extends IReceivedChatMessageEntity> extends AbstractReceivedChatMessageEntityBuilder<B,T>
  {
    protected AbstractReceivedChatMessageBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractReceivedChatMessageBuilder(Class<B> type, IReceivedChatMessageEntity initial)
    {
      super(type, initial);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */