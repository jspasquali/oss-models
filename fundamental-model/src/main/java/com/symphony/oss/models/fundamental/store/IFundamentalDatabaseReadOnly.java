/*
 * Copyright 2018-2019 Symphony Communication Services, LLC.
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

package com.symphony.oss.models.fundamental.store;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.common.fault.TransactionFault;
import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.ISecurityContextMember;
import com.symphony.oss.models.fundmental.canon.IPagination;

/**
 * A read only object database for Fundamental Objects.
 * 
 * Implementations of this interface may provide a cache (usually using WeakReferences).
 * 
 * @author Bruce Skingle
 *
 */
public interface IFundamentalDatabaseReadOnly
{
  /**
   * Fetch the object with the given absoluteHash.
   * 
   * @param absoluteHash  The ID of the required object.
   * @param type          The type of the required object.
   * @return              The required object.
   * 
   * @throws NoSuchObjectException  If there is no object with the given absoluteHash.
   * @throws TransactionFault       If the object exists but is not of the given type.
   */
  <E extends IEntity> E fetchAbsolute(Hash absoluteHash, Class<E> type) throws NoSuchObjectException;

  /**
   * Fetch the object with the given absoluteHash.
   * 
   * This method will return an IBlob for encrypted objects even if the object has previously been opened and is
   * in the cache.
   * 
   * @param absoluteHash  The ID of the required object.
   * @return              The required object.
   * 
   * @throws NoSuchObjectException  If there is no object with the given absoluteHash.
   * @throws TransactionFault       If the object exists but is not of the given type.
   */
  IEntity fetchAbsolute(Hash absoluteHash) throws NoSuchObjectException;

  /**
   * Fetch the object with the given absoluteHash.
   * 
   * If the object is an encrypted Blob then it will be opened.
   * 
   * @param absoluteHash  The ID of the required object.
   * @param credential    A credential to decrypt retrieved objects.
   * 
   * @return              The required object.
   * 
   * @throws NoSuchObjectException  If there is no object with the given absoluteHash.
   */
  IEntity fetchAbsolute(Hash absoluteHash, IOpenPrincipalCredential credential) throws NoSuchObjectException;

  /**
   * Fetch the object with the given absoluteHash.
   * 
   * If the object is an encrypted Blob then it will be opened, and cast to the required type.
   * 
   * @param absoluteHash  The ID of the required object.
   * @param credential    A credential to decrypt retrieved objects.
   * @param type          The type of the required object.
   * 
   * @return              The required object.
   * 
   * @throws NoSuchObjectException  If there is no object with the given absoluteHash.
   * @throws TransactionFault       If the object exists but is not of the given type.
   */
  <E extends IEntity> E fetchAbsolute(Hash absoluteHash, IOpenPrincipalCredential credential, Class<E> type) throws NoSuchObjectException;


  /**
   * Fetch the current (latest) version of the object with the given baseHash.
   * 
   * @param baseHash  The ID of the required object.
   * @return          The required object.
   * 
   * @throws NoSuchObjectException  If there is no object with the given baseHash.
   */
  IEntity fetchCurrent(Hash baseHash) throws NoSuchObjectException;

  /**
   * Fetch the current (latest) version of the object with the given baseHash.
   * 
   * @param baseHash  The ID of the required object.
   * @param type      The type of the required object.
   * @return          The required object.
   * 
   * @throws NoSuchObjectException  If there is no object with the given baseHash.
   * @throws TransactionFault       If the object exists but is not of the given type.
   */
  <E extends IEntity> E fetchCurrent(Hash baseHash, Class<E> type) throws NoSuchObjectException;

  /**
   * Fetch the current (latest) version of the object with the given baseHash.
   * 
   * If the object is an encrypted Blob then it will be opened, and cast to the required type.
   * 
   * @param baseHash      The ID of the required object.
   * @param credential    A credential to decrypt retrieved objects.
   * @param type          The type of the required object.
   * @return              The required object.
   * 
   * @throws NoSuchObjectException  If there is no object with the given baseHash.
   * @throws TransactionFault       If the object exists but is not of the given type.
   */
  <E extends IEntity> E fetchCurrent(Hash baseHash, IOpenPrincipalCredential credential, Class<E> type) throws NoSuchObjectException;

  /**
   * Return the FundamentalObject containing the given entity.
   * 
   * @param entity An IEntity.
   * 
   * @return The FundamentalObject containing the given entity, or the given entity if it is an instance of IFundamentalObject.
   * 
   * @throws IllegalArgumentException if the given entity cannot be resolved to a fundamental object.
   */
  IFundamentalObject resolveFundamentalObject(IEntity entity);

  /**
   * Fetch versions of the object with the given baseHash.
   * 
   * @param baseHash    The baseHash of the object required.
   * @param limit       The maximum number of versions to return.
   * @param after       A pagination token received from a previous call to continue the query.
   * @param consumer    A consumer to receive retrieved versions.
   * @param credential  A credential to decrypt retrieved objects.
   * @param type        The expected type of the object.
   * 
   * @return Pagination information to allow the query to be continued in a further call.
   */
  <E extends IEntity> IPagination fetchVersions(Hash baseHash, @Nullable Integer limit, String after, Consumer<E> consumer,
      @Nullable IOpenPrincipalCredential credential, Class<E> type);

  /**
   * Fetch opened objects from the given sequence in reverse chronological (most recent first) order.
   * 
   * @param sequenceHash    The baseHash of the object required.
   * @param limit           The maximum number of versions to return.
   * @param after           A pagination token received from a previous call to continue the query.
   * @param consumer        A consumer to receive retrieved versions.
   * @param credential      A credential to decrypt retrieved objects.
   * @param type            The expected type of the object.
   * 
   * @return Pagination information to allow the query to be continued in a further call.
   */
  <E extends IEntity> IPagination fetchSequenceRecentObjects(Hash sequenceHash, @Nullable Integer limit, String after, Consumer<E> consumer,
      IOpenPrincipalCredential credential, Class<E> type);

  /**
   * Fetch unopened objects from the given sequence in reverse chronological (most recent first) order.
   * 
   * @param sequenceHash    The baseHash of the object required.
   * @param limit           The maximum number of versions to return.
   * @param after           A pagination token received from a previous call to continue the query.
   * @param consumer        A consumer to receive retrieved versions.
   * 
   * @return Pagination information to allow the query to be continued in a further call.
   */
  IPagination fetchSequenceRecentObjects(Hash sequenceHash, @Nullable Integer limit, String after, Consumer<IEntity> consumer);

  /**
   * Fetch the current (latest) version of the object pointed to by the given id object.
   * This method verified that the given hash exists as an ID object in the database.
   * 
   * @param idObjectHash    The hash of an ID Object.
   * 
   * @return The current (latest) version of the object pointed to by the given id object.
   * 
   * @throws NoSuchObjectException If there is no such object
   */
  IEntity fetchCurrentById(Hash idObjectHash) throws NoSuchObjectException;
  
  /**
   * Fetch the current (latest) version of the object pointed to by the given id object.
   * 
   * @param idObject    The ID Object.
   * @param type        The type of the expected object.
   * 
   * @return The current (latest) version of the object pointed to by the given id object.
   * 
   * @throws NoSuchObjectException If there is no such object
   * @throws IllegalStateException If the object exists but has a different type.
   */
  <E extends IEntity> E fetchCurrentById(IFundamentalId idObject, Class<E> type) throws NoSuchObjectException;

  /**
   * Fetch the current (latest) version of the object pointed to by the given id object.
   * 
   * This method retrieves the object pointed to by an ID which was created by
   * <code>saveIdIfNotExists()</code>
   * 
   * @param idObject    The ID Object.
   * @param credential  An open credential with which to open the retrieved object.
   * @param type        The type of the expected object.
   * 
   * @return The current (latest) version of the object pointed to by the given id object.
   * 
   * @throws NoSuchObjectException If there is no such object
   * @throws IllegalStateException If the object exists but has a different type.
   */
  <E extends IEntity> E fetchCurrentById(IFundamentalId idObject, IOpenPrincipalCredential credential, Class<E> type) throws NoSuchObjectException;

  /**
   * Fetch and open the security context whose baseHash is given. This involves reading encrypted keys from the
   * database and unwrapping them.
   * 
   * The result is cached in an expiring cache so that repeated calls do not result in round trips to the database
   * in rapid succession. If the values are updated elsewhere then the old cached values will continue to be
   * returned by this call until they expire from the cache, which is sub-optimal but consistent with the 
   * eventually consistent model of the whole system.
   * 
   * @param securityContextBaseHash The baseHash of the required security context.
   * @param credential              A credential to unwrap keys.
   * 
   * @return The required, opened, security context with unwrapped keys.
   * 
   * @throws NoSuchObjectException If the security context does not exist.
   * @throws IllegalStateException If the given baseHash exists but is not a SecurityContext.
   */
  IOpenSecurityContext fetchOpenSecurityContext(Hash securityContextBaseHash, IOpenPrincipalCredential credential)
      throws NoSuchObjectException;

  /**
   * Fetch the security context membership for the given principal of the given security context.
   * 
   * The result is cached in an expiring cache so that repeated calls do not result in round trips to the database
   * in rapid succession. If the values are updated elsewhere then the old cached values will continue to be
   * returned by this call until they expire from the cache, which is sub-optimal but consistent with the 
   * eventually consistent model of the whole system.
   * 
   * If an object is returned it does not necessarily mean that the principal is a current member, if a principal
   * is removed from a security context then a new version of the membership with MembershipStatus.NONE is
   * written, callers must therefore check the membership status attribute.
   * 
   * @param securityContextBaseHash The baseHash of the required security context.
   * @param principalBaseHash       The baseHash of the principal.
   * @param credential              A credential to decrypt the membership object.
   * 
   * @return The required membership object.
   * 
   * @throws NoSuchObjectException If the given principal was never a member of the security context.
   */
  ISecurityContextMember fetchSecurityContextMemberFor(Hash securityContextBaseHash, Hash principalBaseHash,
      IOpenPrincipalCredential credential) throws NoSuchObjectException;

  /**
   * Invalidate all objects in the current version cache.
   */
  void flushCurrentVersionCache();

  /**
   * Invalidate the given object from the current version cache.
   * 
   * @param baseHash The baseHash of the object to evict from the cache.
   */
  void flushCurrentVersionCache(Hash baseHash);

  <E extends IEntity> E fetchMemberFor(Hash securityContextBaseHash, Hash principalBaseHash, IOpenPrincipalCredential credential,
      Class<E> type) throws NoSuchObjectException;
}
