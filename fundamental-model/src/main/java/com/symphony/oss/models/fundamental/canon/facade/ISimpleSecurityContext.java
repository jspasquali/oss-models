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
 *  At                  2018-05-13 13:48:35 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.crypto.SecretKey;

import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseWritable;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSimpleSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.IVersionedObject;
import com.symphony.oss.models.fundamental.canon.facade.RotationId;
import com.symphony.oss.models.fundamental.canon.facade.WrappedKey;
import com.symphony.oss.models.fundamental.crypto.cipher.ICipherSuite;
import com.symphony.oss.models.fundmental.canon.CipherSuiteId;
import com.symphony.oss.models.fundmental.canon.ISimpleSecurityContextEntity;

/**
 * Facade for Object ObjectSchema(SimpleSecurityContext)
 * Generated from ObjectSchema(SimpleSecurityContext) at #/components/schemas/SimpleSecurityContext
 */
@Immutable
public interface ISimpleSecurityContext
  extends IVersionedObject, ISimpleSecurityContextEntity
{
  /**
   * This method returns the zero rotation ID if the actual value in the object is null.
   * 
   * @return The rotationID for this security context.
   */
  @Override
  @Nonnull RotationId getRotationId();

  /**
   * Save the given wrapped keys for a security context.
   * 
   * @param principalBaseHash   The principal to whom the keys belong.
   * @param cipherSuiteId       The CipherSuiteId (type) of the wrapped key
   * @param wrappedKey          The key wrapped in the user's public exchange key.
   * @param exchangeKeyHash     The ID of the key which was used to wrap the wrapped key.
   * @param encryptedKey        The key wrapped in the users AES account key as per SBE key wrapping.
   * @param credential          An open credential to use to store the key.
   * @param fundamentalDatabase A database in which to store the keys.
   * @param trace               A trace context.
   */
  void saveWrappedKeys(
      Hash principalBaseHash, CipherSuiteId cipherSuiteId, WrappedKey wrappedKey, Hash exchangeKeyHash, WrappedKey encryptedKey,
      IOpenPrincipalCredential credential,
      IFundamentalDatabaseWritable fundamentalDatabase,
      ITraceContext trace);

  /**
   * Open the security context using the given secret key.
   * 
   * Note this method does no validation it simply provides an IOpenSecurityContext with the given secret key.
   * 
   * @param secretKey     The secret key.
   * 
   * @return An IOpenSecurityContext representing this security context with the given secret key.
   */
  IOpenSimpleSecurityContext open(SecretKey secretKey);

  IOpenSimpleSecurityContext openSimpleSecurityContext(IOpenPrincipalCredential credential) throws NoSuchObjectException;

  /**
   * 
   * @return The CipherSuite for this security context.
   */
  ICipherSuite getCipherSuite();

  /**
   * 
   * @return The ID object for the sequence of members of this security context.
   */
  IFundamentalId getMembersSequenceId();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */