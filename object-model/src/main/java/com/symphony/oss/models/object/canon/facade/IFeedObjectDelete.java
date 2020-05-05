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
 *		Template name		   proforma/java/Object/I_.java.ftl
 *		Template version	   1.0
 *  At                  2019-12-13 15:48:08 GMT-08:00
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.fugue.pubsub.IQueueMessageDelete;
import com.symphony.oss.models.object.canon.IFeedObjectDeleteEntity;

/**
 * Facade for Object ObjectSchema(FeedObjectDelete)
 *
 * Request to delete (acknowledge) a message from a feed.
 * Generated from ObjectSchema(FeedObjectDelete) at #/components/schemas/FeedObjectDelete
 */
@Immutable
public interface IFeedObjectDelete
  extends IFeedObjectDeleteEntity, IQueueMessageDelete
{
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */