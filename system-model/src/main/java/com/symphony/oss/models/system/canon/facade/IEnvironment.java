/**
 * Copyright 2018 Symphony Communication Services, LLC.
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
 *  At                  2018-06-08 14:41:27 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.system.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.fundamental.canon.facade.IApplicationObject;
import com.symphony.oss.models.system.canon.IEnvironmentEntity;

/**
 * Facade for Object ObjectSchema(Environment)
 *
 * The environment object, the root of the config structure, base hash is always InferredHashFactory.environment(). The contentSequence of this object is the list of realms in this environment.
 * Generated from ObjectSchema(Environment) at #/components/schemas/Environment
 */
@Immutable
public interface IEnvironment
  extends IApplicationObject, IEnvironmentEntity
{
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */