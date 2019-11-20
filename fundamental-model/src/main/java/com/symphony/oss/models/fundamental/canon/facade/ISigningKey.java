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
 *  At                  2018-05-15 16:17:26 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.immutable.ImmutableByteArray;

import com.symphony.oss.models.crypto.canon.EncodedSignature;
import com.symphony.oss.models.crypto.cipher.SignatureVerificationException;
import com.symphony.oss.models.fundmental.canon.ISigningKeyEntity;

/**
 * A signing key which can be used to verify signatures created by the owner of the current key.
 * 
 * Facade for Object ObjectSchema(SigningKey)
 * Generated from ObjectSchema(SigningKey) at #/components/schemas/SigningKey
 */
@Immutable
public interface ISigningKey
  extends IAbstractPublicKey, ISigningKeyEntity
{
  /**
   * Verify that the given signature is a valid signature over the given data.
   * 
   * If the signature is not valid an exception is thrown.
   * 
   * @param encodedSignature  The signature to be verified.
   * @param data              The data which the signature is expected to cover.
   * 
   * @throws SignatureVerificationException If the signature is invalid for any reason.
   */
  void verifySignature(EncodedSignature encodedSignature, ImmutableByteArray data) throws SignatureVerificationException;
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */