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
 *  At                  2019-12-02 20:02:19 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.core.canon.IApplicationPayload;
import com.symphony.oss.models.object.canon.IAbstractApplicationObjectPayloadEntity;

/**
 * Facade for Object ObjectSchema(AbstractApplicationObjectPayload)
 *
 * Base type for header or encrypted payloads in the object store.
 * Generated from ObjectSchema(AbstractApplicationObjectPayload) at #/components/schemas/AbstractApplicationObjectPayload
 */
@Immutable
public interface IAbstractApplicationObjectPayload
  extends IApplicationPayload, IAbstractApplicationObjectPayloadEntity
{
  /**
   * 
   * @return The stored application object of which this is a part.
   */
  IStoredApplicationObject getStoredApplicationObject();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */