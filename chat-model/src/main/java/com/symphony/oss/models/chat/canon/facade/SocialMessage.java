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
 *  At                  2019-08-03 18:20:01 GMT-07:00
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.chat.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.models.chat.canon.SocialMessageEntity;

/**
 * Facade for Object ObjectSchema(SocialMessage)
 * Generated from ObjectSchema(SocialMessage) at #/components/schemas/SocialMessage
 */
@Immutable
public class SocialMessage extends SocialMessageEntity implements ISocialMessage
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public SocialMessage(AbstractSocialMessageBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SocialMessage(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SocialMessage(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public SocialMessage(ISocialMessage other)
  {
    super(other);
  }

//  /**
//   * Return the ID object for the sequence of messages belonging to the given message.
//   * This is the sequence onto which DeliveryReceipts, ReadReceipts and ObjectStatus messages
//   * are added.
//   * 
//   * @param messageId The ID of the stream.
//   * 
//   * @return The ID object for the sequence of messages belonging to the given thread (stream).
//   */
//  public static IFundamentalId getMessageContentSequenceId(IMessageIdObject messageId)
//  {
//    return getMessageSequenceId(messageId, LiveCurrentMessage.TYPE_ID);
//  }
//
//  private static IFundamentalId getMessageSequenceId(IMessageIdObject messageId, String contentType)
//  {
//    return new ContentIdObject.Builder()
//        .withSubjectHash(messageId.getAbsoluteHash())
//        .withSubjectType(Stream.TYPE_ID)
//        .withContentType(contentType)
//        .withIdType(ContentIdType.ABSOLUTE_SEQUENCE)
//        .build();
//  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */