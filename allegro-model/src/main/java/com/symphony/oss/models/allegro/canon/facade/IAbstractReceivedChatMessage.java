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
 *		Template name		   proforma/java/Object/I_.java.ftl
 *		Template version	   1.0
 *  At                  2020-01-27 13:01:50 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.allegro.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.symphony.messageml.elements.MessageML;

import com.symphony.oss.models.allegro.canon.IAbstractChatMessage;
import com.symphony.oss.models.allegro.canon.IAbstractReceivedChatMessageEntity;
import com.symphony.oss.models.chat.canon.ILiveCurrentMessage;

/**
 * Facade for Object ObjectSchema(AbstractReceivedChatMessage)
 * Generated from ObjectSchema(AbstractReceivedChatMessage) at #/components/schemas/AbstractReceivedChatMessage
 */
@Immutable
public interface IAbstractReceivedChatMessage
  extends IAbstractChatMessage, IAbstractReceivedChatMessageEntity
{
  /**
   * 
   * @return The MessageML parse tree for the message.
   */
  MessageML getMessageML();
  
  /**
   * 
   * @return The LiveCurrentMessage which represents this message in the cloud.
   */
  ILiveCurrentMessage getLiveCurrentMessage();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */