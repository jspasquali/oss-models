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
 *  At                  2019-04-27 11:09:39 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.system.canon.facade;

import java.security.PrivateKey;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.system.canon.IAuthenticationCredentialEntity;

/**
 * Facade for Object ObjectSchema(AuthenticationCredential)
 *
 * A secret credential which can be used to authenticate to a Pod public API.
 * Generated from ObjectSchema(AuthenticationCredential) at #/components/schemas/AuthenticationCredential
 */
@Immutable
public interface IAuthenticationCredential
  extends IAuthenticationCredentialEntity
{
  /**
   * 
   * @return The private key for this credential.
   */
  PrivateKey getPrivateKey();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */