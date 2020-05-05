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
 *  At                  2019-11-28 11:12:37 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.core.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.commons.hash.Hash;
import com.symphony.oss.fugue.trace.ITraceSubject;
import com.symphony.oss.models.core.canon.IApplicationPayload;
import com.symphony.oss.models.core.canon.ISystemObjectEntity;

/**
 * Facade for Object ObjectSchema(SystemObjectPayload)
 *
 * Base type for system objects in the object store.
 * Generated from ObjectSchema(SystemObjectPayload) at #/components/schemas/SystemObjectPayload
 */
@Immutable
public interface ISystemObject extends IApplicationPayload, ISystemObjectEntity, ITraceSubject
{
  /**
   * 
   * @return The absolute hash of this object.
   */
  Hash getAbsoluteHash();

  /**
   * In some cases we store multiple copies of the primary storage record for an object with different
   * partition and sort keys. In these cases it is only necessary to save the main instance to
   * secondary storage.
   * 
   * @return True if this object should be saved to secondary storage.
   */
  boolean isSaveToSecondaryStorage();
}