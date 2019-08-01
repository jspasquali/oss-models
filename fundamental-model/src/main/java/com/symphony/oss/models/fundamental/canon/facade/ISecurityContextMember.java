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
 *  At                  2019-03-13 17:09:22 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.fundamental.canon.facade.IMember;
import com.symphony.oss.models.fundmental.canon.ISecurityContextMemberEntity;

/**
 * Facade for Object ObjectSchema(SecurityContextMember)
 *
 * wrappedKey is the SecurityContext SecretKey wrapped by the member's Public ExchangeKey. encryptedKey is the SecurityContext SecretKey encrypted with the member's AccountSecretKey. This may be added as an update by the member as a performance optimization.
 * Generated from ObjectSchema(SecurityContextMember) at #/components/schemas/SecurityContextMember
 */
@Immutable
public interface ISecurityContextMember
  extends IMember, ISecurityContextMemberEntity
{
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */