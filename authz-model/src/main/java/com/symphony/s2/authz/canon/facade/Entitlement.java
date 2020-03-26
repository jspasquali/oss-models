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
 *  At                  2020-02-04 15:19:57 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.s2.authz.canon.facade;

import java.time.Instant;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.immutable.ImmutableByteArray;
import org.symphonyoss.s2.fugue.kv.IKvPartitionKey;
import org.symphonyoss.s2.fugue.kv.IKvPartitionSortKeyProvider;
import org.symphonyoss.s2.fugue.kv.IKvSortKey;
import org.symphonyoss.s2.fugue.kv.KvPartitionKey;
import org.symphonyoss.s2.fugue.kv.KvPartitionSortKeyProvider;
import org.symphonyoss.s2.fugue.kv.KvSortKey;
import org.symphonyoss.s2.fugue.store.IFuguePodId;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;


import com.symphony.s2.authz.canon.EntitlementEntity;
import com.symphony.s2.authz.canon.IEntitlementEntity;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.s2.authz.canon.AuthzModel;

/**
 * Facade for Object ObjectSchema(Entitlement)
 *
 * An entitlement definition.
 * Generated from ObjectSchema(Entitlement) at #/components/schemas/Entitlement
 */
@Immutable
@SuppressWarnings("unused")
public class Entitlement extends EntitlementEntity implements IEntitlement
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Entitlement(AbstractEntitlementBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Entitlement(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Entitlement(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Entitlement(IEntitlement other)
  {
    super(other);
  }
  
  @Override
  public IKvPartitionKey getPartitionKey()
  {
    return getPartitionKeyFor(getId().getHash());
  }
  
  /**
   * Get the partition key for Entitlement object for the given hash.
   * 
   * @param hash The entitlement hash for the required entitlement.
   * 
   * @return The partition key for Entitlement object for the given hash.
   */
  public static IKvPartitionKey getPartitionKeyFor(Hash hash)
  {
    return new KvPartitionKey("E#" + hash);
  }
  
  /**
   * Get the partition key for Entitlement object for the given hash.
   * 
   * @param hash The entitlement hash for the required entitlement.
   * 
   * @return The partition key for Entitlement object for the given hash.
   */
  public static IKvPartitionSortKeyProvider getPartitionSortKeyFor(Hash hash)
  {
    return new KvPartitionSortKeyProvider(getPartitionKeyFor(hash), new KvSortKey("E#"));
  }

  @Override
  public IKvSortKey getSortKey()
  {
    return new KvSortKey("E#");
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

  @Override
  public Instant getPurgeDate()
  {
    return null;
  }

  @Override
  public IFuguePodId getPodId()
  {
    return getId().getUserId().getPodId();
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */