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
 *  At                  2019-02-09 06:47:46 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.internal.km.canon.facade;

import java.security.KeyPair;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.internal.km.canon.IUserKeysEntity;

/**
 * Facade for Object ObjectSchema(UserKeys)
 * Generated from ObjectSchema(UserKeys) at #/components/schemas/UserKeys
 */
@Immutable
public interface IUserKeys
  extends IUserKeysEntity
{
  /**
   * 
   * @return The decoded public and private keys as a Java KeyPair.
   */
  KeyPair getKeyPair();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */