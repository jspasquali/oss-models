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
 *  At                  2018-02-23 15:21:01 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.store.IFugueObject;

import com.symphony.oss.models.fundamental.canon.facade.ISigningKey;
import com.symphony.oss.models.fundamental.crypto.cipher.SignatureVerificationException;
import com.symphony.oss.models.fundmental.canon.IFundamentalObjectEntity;

/**
 * Facade for Object ObjectSchema(FundamentalObject)
 * Generated from ObjectSchema(FundamentalObject) at #/components/schemas/FundamentalObject
 */
@Immutable
public interface IFundamentalObject
  extends IFundamentalObjectEntity, IFugueObject
{
  /**
   * Return the absolute hash for this object.
   * 
   * This will be a hash of the type which was current when the object was created, i.e. using the hash type
   * which is encoded in the object, not the current default hash type.
   * 
   * @return the absolute hash for this object.
   */
  @Override
  Hash getAbsoluteHash();
  
  /**
   * Return the range key for this object, which consists of the create timestamp concatenated with the hash.
   * 
   * @return The range key for this object.
   */
  @Override
  String  getRangeKey();

  void trace(ITraceContext trace, String operationId);

  /**
   * Return a short textual description of this object including its type and absoluteHash.
   * 
   * Open objects should indicate their internal type, for example an OpenBlob should indicate the
   * type of the enclosed application payload.
   * @return a short textual description of this object.
   */
  @Override
  String getDescription();

  /**
   * Verify the signature on this object using the given signingKey.
   * 
   * @param signingKey  The key to use for verification.
   * 
   * @throws SignatureVerificationException If the signature is not verified.
   */
  void verifySignature(ISigningKey signingKey) throws SignatureVerificationException;
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */