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
 *  At                  2019-03-22 09:19:08 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.system.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.fundamental.canon.facade.IApplicationObject;
import com.symphony.oss.models.system.canon.IPodEntity;

/**
 * Facade for Object ObjectSchema(Pod)
 *
 * A pod. The operating security context contains system config objects, the environment principal has access to this. The root context gives access to all content for the pod, the public context is visible to all principals in the pod but not the environment operating principal, this is where the forwarder puts forwarded messages.
 * Generated from ObjectSchema(Pod) at #/components/schemas/Pod
 */
@Immutable
public interface IPod
  extends IApplicationObject, IPodEntity
{
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */