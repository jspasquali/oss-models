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
 *  At                  2019-11-20 10:52:22 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.s2.authc.canon.facade;

import java.security.PrivateKey;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.fugue.kv.IKvPartitionSortKeyProvider;
import com.symphony.s2.authc.canon.IPrincipalCredentialEntity;

/**
 * Facade for Object ObjectSchema(Credential)
 *
 * A secret credential.
 * Generated from ObjectSchema(Credential) at #/components/schemas/Credential
 */
@Immutable
public interface IPrincipalCredential
  extends IPrincipalCredentialEntity, IKvPartitionSortKeyProvider
{
  /**
   * 
   * @return The PrivateKey.
   */
  PrivateKey getPrivateKey();
  
  /**
   * Return a copy of this object with the private key redacted.
   * 
   * The returned object can safely be logged.
   * 
   * @return a copy of this object with the private key redacted.
   */
  ImmutableJsonObject getRedacted();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */