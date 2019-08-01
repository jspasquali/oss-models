/*
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
 */

package com.symphony.oss.models.fundamental;

import java.time.Instant;
import java.util.List;

import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.ISimpleSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.RotationId;
import com.symphony.oss.models.fundamental.canon.facade.WrappedKey;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseReadOnly;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseWritable;
import com.symphony.oss.models.fundmental.canon.CipherSuiteId;
import com.symphony.oss.models.fundmental.canon.MembershipStatus;
import com.symphony.oss.models.fundmental.canon.SecurityContextPermission;

/**
 * The parent for a set of SimpleSecurityContexts (1 per rotation) driven from an external ID
 * such as a userId or threadId.
 * 
 * @author Bruce Skingle
 *
 */
public interface ICompoundSecurityContext
{
  /** 
   * 
   * @return The ID for this compound security context which will be the parentHash of each SimpleSecurityContext 
   * representing a single rotation.
   */
  IFundamentalId getId();

  /** 
   * 
   * @return The ID for this compound security context which will be the parentHash of each SimpleSecurityContext 
   * representing a single rotation.
   */
  Hash getBaseHash();
  
  /**
   * Save the given wrapped keys for a security context.
   * 
   * @param rotationId              The rotation which the keys are for.
   * @param principalBaseHash       The principal to whom the keys belong.
   * @param cipherSuiteId           The CipherSuiteId (type) of the wrapped key
   * @param wrappedKey              The key wrapped in the user's public exchange key.
   * @param exchangeKeyHash         The ID of the key which was used to wrap the wrapped key.
   * @param encryptedKey            The key wrapped in the users AES account key as per SBE key wrapping.
   * @param credential              An open credential to use to store the key.
   * @param fundamentalDatabase     A database in which to store the keys.
   * @param trace                   A trace context.
   * 
   * @return The security context object.
   */
  ISimpleSecurityContext saveWrappedKeys(RotationId rotationId, Hash principalBaseHash, CipherSuiteId cipherSuiteId,
      WrappedKey wrappedKey, Hash exchangeKeyHash,
      WrappedKey encryptedKey, IOpenPrincipalCredential credential,
      IFundamentalDatabaseWritable fundamentalDatabase, ITraceContext trace);
  
  /**
   * Insert or update the status of the given principal as a member of this security context.
   * 
   * @param principalBaseHash   The ID of the principal.
   * @param status              The status, MEMBER or NONE
   * @param permission          The permission, OWNER, MEMBER or NONE
   * @param signingKey          The key with which to sign the membership record.
   * @param fundamentalDatabase The DB in which to store the record.
   * @param trace               A trace context.
   * @param timeStamp           The time from which this membership status takes effect.
   */
  void upsertMember(Hash principalBaseHash, MembershipStatus status,
      SecurityContextPermission permission,
      IOpenSigningKey signingKey, IFundamentalDatabaseWritable fundamentalDatabase, ITraceContext trace, Instant timeStamp);
   
  /**
   * Fetch the security context for the given rotation.
   * 
   * This method calls a conditional transactional write method on the database to ensure that only one security context is created
   * for any given thread.
   * 
   * @param rotationId          The rotation to which this security context relates.
   * @param fundamentalDatabase A database to store the security context in.
   * 
   * @return  A new or existing security context for the given thread.
   * 
   * @throws NoSuchObjectException If the requested context does not exist. 
   */
  ISimpleSecurityContext fetch(RotationId rotationId,
      IFundamentalDatabaseReadOnly fundamentalDatabase) throws NoSuchObjectException;

  /**
   * Fetch or create the security context objects for the given rotation.
   * 
   * This method calls a does not persist the objects, in a server context call fetchOrCreate()
   * 
   * @param rotationId          The rotation to which this security context relates.
   * @param openSigningKey      The signing key with which to sign the security context if it is created. 
   * @param cipherSuiteId       The ID of the cipher suite for the security context in case it is created.
   * 
   * @return  New security context objects.
   */
   List<IFundamentalObject> createObjects(RotationId rotationId, IOpenSigningKey openSigningKey,
      CipherSuiteId cipherSuiteId);

   /**
    * Fetch or create the security context for the given rotation.
    * 
    * This method calls a conditional transactional write method on the database to ensure that only one security context is created
    * for any given thread.
    * 
    * @param rotationId          The rotation to which this security context relates.
    * @param openSigningKey      The signing key with which to sign the security context if it is created. 
    * @param cipherSuiteId       The ID of the cipher suite for the security context in case it is created.
    * @param fundamentalDatabase A database to store the security context in.
    * @param trace               A trace context to trace any write operations.
    * 
    * @return  A new or existing security context for the given rotation.
    */
   ISimpleSecurityContext fetchOrCreate(RotationId rotationId, IOpenSigningKey openSigningKey,
       CipherSuiteId cipherSuiteId, IFundamentalDatabaseWritable fundamentalDatabase, ITraceContext trace);
}
