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
 *    Template groupId     org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *    Template name      proforma/java/Object/I_.java.ftl
 *    Template version     1.0
 *  At                  2019-11-25 09:18:48 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.commons.hash.Hash;
import com.symphony.oss.models.core.canon.IApplicationPayload;
import com.symphony.oss.models.object.canon.IIdObjectEntity;

/**
 * Facade for Object ObjectSchema(IdObject)
 *
 * An ID object, applications define IDs as a sub-type of this.
 * Generated from ObjectSchema(IdObject) at #/components/schemas/IdObject
 */
@Immutable
public interface IIdObject
  extends IApplicationPayload, IIdObjectEntity
{
  /**
   * 
   * @return the hash of this ID.
   */
  Hash getHash();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */