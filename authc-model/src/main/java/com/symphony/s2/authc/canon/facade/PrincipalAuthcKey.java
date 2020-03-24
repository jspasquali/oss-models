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
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2019-11-09 08:06:24 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.s2.authc.canon.facade;

import java.security.PublicKey;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.kv.IKvPartitionKey;
import org.symphonyoss.s2.fugue.kv.IKvSortKey;
import org.symphonyoss.s2.fugue.kv.KvPartitionKey;
import org.symphonyoss.s2.fugue.kv.KvPartitionKeyProvider;
import org.symphonyoss.s2.fugue.kv.KvPartitionSortKeyProvider;
import org.symphonyoss.s2.fugue.kv.KvSortKey;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.core.kv.store.IKvStore;
import com.symphony.oss.models.crypto.cipher.CipherSuite;
import com.symphony.s2.authc.canon.IPrincipalAuthcKeyEntity;
import com.symphony.s2.authc.canon.PrincipalAuthcKeyEntity;

/**
 * Facade for Object ObjectSchema(PrincipalAuthcKey)
 *
 * An RSA Public Key for a principal to authenticate with.
 * Generated from ObjectSchema(PrincipalAuthcKey) at #/components/schemas/PrincipalAuthcKey
 */
@Immutable
public class PrincipalAuthcKey extends PrincipalAuthcKeyEntity implements IPrincipalAuthcKey
{
  private static final Logger log_ = LoggerFactory.getLogger(PrincipalAuthcKey.class);
  
  private final PublicKey publicKey_;
  private final KeyId     keyId_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public PrincipalAuthcKey(AbstractPrincipalAuthcKeyBuilder<?,?> builder)
  {
    super(builder);
    publicKey_ = CipherSuite.getDefault().publicKeyFromPem(getEncodedPublicKey());
    keyId_ = KeyId.newBuilder().build(publicKey_);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PrincipalAuthcKey(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    publicKey_ = CipherSuite.getDefault().publicKeyFromPem(getEncodedPublicKey());
    keyId_ = KeyId.newBuilder().build(publicKey_);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PrincipalAuthcKey(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    publicKey_ = CipherSuite.getDefault().publicKeyFromPem(getEncodedPublicKey());
    keyId_ = KeyId.newBuilder().build(publicKey_);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public PrincipalAuthcKey(IPrincipalAuthcKey other)
  {
    super(other);
    publicKey_ = other.getPublicKey();
    keyId_ = KeyId.newBuilder().build(publicKey_);
  }
  
  @Override
  public PublicKey getPublicKey()
  {
    return publicKey_;
  }

  @Override
  public KeyId getKeyId()
  {
    return keyId_;
  }

  @Override
  public IKvPartitionKey getPartitionKey()
  {
    if(getUserId() == null)
      return getPartitionKeyFor(getPodId());
    
    return getPartitionKeyFor(getUserId());
  }
  
  /**
   * Get the partition key for authentication keys for the given User.
   * 
   * @param userId The user for whom the partition key is required.
   * 
   * @return The partition key for authentication keys for the given User.
   */
  public static IKvPartitionKey getPartitionKeyFor(PodAndUserId userId)
  {
    return new KvPartitionKey("U#" + userId);
  }
  
  /**
   * Get the partition key for authentication keys for the given Pod.
   * 
   * @param podId The pod for whom the partition key is required.
   * 
   * @return The partition key for authentication keys for the given Pod.
   */
  public static IKvPartitionKey getPartitionKeyFor(PodId podId)
  {
    return new KvPartitionKey("P#" + podId);
  }

  @Override
  public IKvSortKey getSortKey()
  {
    return new KvSortKey(keyId_.toString());
  }

  @Override
  public String getJson()
  {
    return super.toString();
  }

  @Override
  public String getType()
  {
    return getCanonType();
  }
  
  /**
   * Abstract builder for PrincipalAuthcKey. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractPrincipalAuthcKeyBuilder<B extends AbstractPrincipalAuthcKeyBuilder<B,T>, T extends IPrincipalAuthcKeyEntity> extends AbstractPrincipalAuthcKeyEntityBuilder<B,T>
  {
    protected AbstractPrincipalAuthcKeyBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractPrincipalAuthcKeyBuilder(Class<B> type, IPrincipalAuthcKeyEntity initial)
    {
      super(type, initial);
    }
  }

  /**
   * Fetch the best signing key for the given user and (possibly) key ID.
   * 
   * If the keyId is non-null then only that key will be found. If a user key exists for the user then it will be returned,
   * otherwise if a pod key exists then it will be returned, otherwise this method returns null.
   * 
   * @param kvStore The Authc service kv store.
   * @param userId  The user ID from a JWT to be verified,
   * @param keyId   The key id named in the JWT, if there is one.
   * @param trace   A trace context.
   * 
   * @return The key, if it exists, or null.
   */
  public static @Nullable IPrincipalAuthcKey fetchPublicKey(IKvStore kvStore, PodAndUserId userId, @Nullable KeyId keyId, ITraceContext trace)
  {
    PodId podId = userId.getPodId();
    IPrincipalAuthcKey key = null;
    
    log_.info("Fetch key for user " + userId + " podId=" + podId + " keyId=" + keyId);
    
    if(keyId != null)
    {
      key = fetchPublicKey(kvStore, getPartitionKeyFor(userId), keyId, trace);
      log_.info("User key = " + key);
    }
    
    if(key == null)
    {
      key = fetchPublicKey(kvStore, getPartitionKeyFor(podId), keyId, trace);
      log_.info("Pod key = " + key);
    }
    
    return key;
  }

  private static IPrincipalAuthcKey fetchPublicKey(IKvStore kvStore, IKvPartitionKey partitionKey,
      KeyId keyId, ITraceContext trace)
  {
    try
    {
      if(keyId != null)
        return kvStore.fetch(new KvPartitionSortKeyProvider(partitionKey, keyId.toString()), IPrincipalAuthcKey.class, trace);
    }
    catch (NoSuchObjectException e)
    {
      // Fall through
    }

    try
    {
      return kvStore.fetchFirst(new KvPartitionKeyProvider(partitionKey), IPrincipalAuthcKey.class, trace);
    }
    catch (NoSuchObjectException e2)
    {
      return null;
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */