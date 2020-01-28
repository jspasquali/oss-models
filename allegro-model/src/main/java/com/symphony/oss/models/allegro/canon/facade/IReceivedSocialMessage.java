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

import java.util.Map;
import java.util.Set;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.allegro.canon.IReceivedSocialMessageEntity;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;

/**
 * Facade for Object ObjectSchema(ReceivedSocialMessage)
 * Generated from ObjectSchema(ReceivedSocialMessage) at #/components/schemas/ReceivedSocialMessage
 */
@Immutable
public interface IReceivedSocialMessage
  extends IAbstractReceivedChatMessage, IReceivedChatMessage, IReceivedSocialMessageEntity
{ 
  /**
   * Return true iff the given user ID is mentioned in this message.
   * 
   * @param userId A PodAndUserId
   * 
   * @return true iff the given user ID is mentioned in this message.
   */
  boolean isMentioned(PodAndUserId userId);

  /**
   * Return all at-mentions in this message.
   * 
   * The return value is a map whose key is a PodAndUserId and whose value is the screen name of the user.
   * The PodAndUserId is the integer user ID used internally (the calling user's PodAndUserId is returned 
   * by the method IAllegroApi.getUserId()) and the screen name is the text which appears after the @ sign 
   * when the message is displayed.
   * 
   * @return All at-mentions in this message.
   */
  Map<PodAndUserId, String> getMentions();

  /**
   * Return all hash tags in the message.
   * 
   * @return All hash tags in the message.
   */
  Set<String> getHashTags();
  
  /**
   * Return all cash tags in the message.
   * 
   * @return All cash tags in the message.
   */
  Set<String> getCashTags();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */