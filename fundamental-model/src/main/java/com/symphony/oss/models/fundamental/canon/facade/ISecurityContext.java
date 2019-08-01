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
 * limitations under the License..
 *
 *----------------------------------------------------------------------------------------------------
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/I_.java.ftl
 *		Template version	   1.0
 *  At                  2018-05-13 13:48:35 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.security.PublicKey;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.exception.NoSuchObjectException;

import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.ISimpleSecurityContext;
import com.symphony.oss.models.fundmental.canon.ISecurityContextEntity;

/**
 * Facade for Object ObjectSchema(SecurityContext)
 * Generated from ObjectSchema(SecurityContext) at #/components/schemas/SecurityContext
 */
@Immutable
public interface ISecurityContext
  extends ISimpleSecurityContext, ISecurityContextEntity
{
  /**
   * Open this SecurityContext.
   * 
   * @param principalCredential  The principal credential to use to open the security context. 
   * @return An IOpenSecurityContext including the private and secret keys.
   * 
   * @throws NoSuchObjectException If the given principalCredential does not represent a principal
   * who is a member of this SecurityContext.
   */
  IOpenSecurityContext openSecurityContext(IOpenPrincipalCredential principalCredential) throws NoSuchObjectException;


  /**
   * 
   * @return The public key exchange key.
   */
  PublicKey getExchangeKey();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */