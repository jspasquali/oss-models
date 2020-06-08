/**
 * Copyright 2020 Symphony Communication Services, LLC.
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
 *  At                  2020-06-05 10:07:40 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.s2.authc.canon.facade;

import java.security.PublicKey;
import java.time.Instant;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.canon.runtime.exception.NotAuthenticatedException;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.fugue.kv.IKvPartitionKey;
import com.symphony.oss.fugue.kv.IKvSortKey;
import com.symphony.oss.fugue.kv.KvPartitionKey;
import com.symphony.oss.fugue.kv.KvPartitionSortKeyProvider;
import com.symphony.oss.fugue.kv.KvSortKey;
import com.symphony.oss.fugue.store.IFuguePodId;
import com.symphony.oss.fugue.store.NoSuchObjectException;
import com.symphony.oss.fugue.trace.ITraceContext;
import com.symphony.oss.models.core.kv.store.IKvStore;
import com.symphony.s2.authc.canon.EnvironmentAuthcKeyEntity;
import com.symphony.s2.authc.canon.IEnvironmentAuthcKeyEntity;

/**
 * Facade for Object ObjectSchema(EnvironmentAuthcKey)
 *
 * An RSA Public Key for an environment, this is the key the new standard JWTs are signed with.
 * Generated from ObjectSchema(EnvironmentAuthcKey) at #/components/schemas/EnvironmentAuthcKey
 */
@Immutable
public class EnvironmentAuthcKey extends EnvironmentAuthcKeyEntity implements IEnvironmentAuthcKey
{
  /** The partition key for environment level keys */
  public static final KvPartitionKey PARTITION_KEY = new KvPartitionKey("E#");
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public EnvironmentAuthcKey(AbstractEnvironmentAuthcKeyBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public EnvironmentAuthcKey(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public EnvironmentAuthcKey(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public EnvironmentAuthcKey(IEnvironmentAuthcKey other)
  {
    super(other);
  }
  
  @Override
  public IFuguePodId getPodId()
  {
    // Environment keys don't belong to any pod.
    return null;
  }

  @Override
  public Instant getPurgeDate()
  {
    return null;
  }

  @Override
  public PublicKey getPublicKey()
  {
    return getJwk().getPublicKey();
  }

  @Override
  public KeyId getKeyId()
  {
    return getJwk().getKeyId();
  }

  @Override
  public IKvPartitionKey getPartitionKey()
  {
    return PARTITION_KEY;
  }

  @Override
  public IKvSortKey getSortKey()
  {
    return new KvSortKey(getKeyId().toString());
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
   * Fetch the best signing key for the given user and (possibly) key ID.
   * 
   * If the keyId is non-null then only that key will be found. If a user key exists for the user then it will be returned,
   * otherwise if a pod key exists then it will be returned, otherwise this method returns null.
   * 
   * @param kvStore The Authc service kv store.
   * @param keyId   The key id named in the JWT, if there is one.
   * @param trace   A trace context.
   * 
   * @return The key, if it exists, or null.
   * 
   * @throws NotAuthenticatedException if the key cannot be found.
   */
  public static IJwk fetchPublicKey(IKvStore kvStore, KeyId keyId, ITraceContext trace)
  {
    try
    {
      IEnvironmentAuthcKey environmentKey = kvStore.fetch(new KvPartitionSortKeyProvider(PARTITION_KEY, keyId.toString()), IEnvironmentAuthcKey.class, trace);

      return environmentKey.getJwk();
    }
    catch (NoSuchObjectException e)
    {
      throw new NotAuthenticatedException("Unable to locate keyid " + keyId , e);
    }
  }
  
  /**
   * Abstract builder for EnvironmentAuthcKey. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractEnvironmentAuthcKeyBuilder<B extends AbstractEnvironmentAuthcKeyBuilder<B,T>, T extends IEnvironmentAuthcKeyEntity> extends AbstractEnvironmentAuthcKeyEntityBuilder<B,T>
  {
    protected AbstractEnvironmentAuthcKeyBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractEnvironmentAuthcKeyBuilder(Class<B> type, IEnvironmentAuthcKeyEntity initial)
    {
      super(type, initial);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */