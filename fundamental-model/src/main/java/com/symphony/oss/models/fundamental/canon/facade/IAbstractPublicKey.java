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
 *  At                  2018-05-15 18:25:31 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.security.PublicKey;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.crypto.cipher.ICipherSuite;
import com.symphony.oss.models.fundamental.canon.facade.IVersionedObject;
import com.symphony.oss.models.fundmental.canon.IAbstractPublicKeyEntity;

/**
 * A public key.
 * 
 * Facade for Object ObjectSchema(AbstractPublicKey)
 * Generated from ObjectSchema(AbstractPublicKey) at #/components/schemas/AbstractPublicKey
 */
@Immutable
public interface IAbstractPublicKey
  extends IVersionedObject, IAbstractPublicKeyEntity
{
  /**
   * Return the CipherSuite to which the key belongs.
   * 
   * @return The CipherSuite to which the key belongs.
   */
  ICipherSuite getCipherSuite();

  /**
   * Return the public key object.
   * 
   * @return The public key object.
   */
  PublicKey getPublicKey();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */