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
 *    Template groupId     org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *    Template name      proforma/java/Object/_.java.ftl
 *    Template version     1.0
 *  At                  2020-03-18 11:36:51 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.s2.authz.canon.facade;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.hash.Hash;
import com.symphony.oss.fugue.kv.IKvPagination;
import com.symphony.oss.fugue.kv.IKvPartitionKey;
import com.symphony.oss.fugue.kv.IKvPartitionSortKeyProvider;
import com.symphony.oss.fugue.kv.IKvSortKey;
import com.symphony.oss.fugue.kv.KvPartitionKey;
import com.symphony.oss.fugue.kv.KvPartitionKeyProvider;
import com.symphony.oss.fugue.kv.KvPartitionSortKeyProvider;
import com.symphony.oss.fugue.kv.KvSortKey;
import com.symphony.oss.fugue.trace.ITraceContext;
import com.symphony.oss.fugue.trace.NoOpTraceContext;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.core.kv.store.IKvStore;
import com.symphony.s2.authz.canon.EntitlementAction;
import com.symphony.s2.authz.canon.IPodEntitlementMappingEntity;
import com.symphony.s2.authz.canon.PodEntitlementMappingEntity;

/**
 * Facade for Object ObjectSchema(PodEntitlementMapping)
 *
 * A pod entitlement setting.
 * Generated from ObjectSchema(PodEntitlementMapping) at #/components/schemas/PodEntitlementMapping
 */
@Immutable
@SuppressWarnings("unused")
public class PodEntitlementMapping extends PodEntitlementMappingEntity implements IPodEntitlementMapping
{
  /** Additional attribute name for the ID of the entitlement owner */
  public static final String OWNER_ID_ATTRIBUTE_NAME = "ownerId";
  /** Additional attribute name for the effective date of the entitlement mapping */
  public static final String EFFECTIVE_DATE_ATTRIBUTE_NAME = "effective";
  /** Additional attribute name for the action of the entitlement mapping */
  public static final String ACTION_ATTRIBUTE_NAME = "entAction";
  
  private final Map<String, Object> additionalAttributes_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public PodEntitlementMapping(AbstractPodEntitlementMappingBuilder<?,?> builder)
  {
    super(builder);
    
    additionalAttributes_ = initAdditionalAttributes();
  }
  
  private Map<String, Object> initAdditionalAttributes()
  {
    return new ImmutableMap.Builder<String, Object>()
        .put(OWNER_ID_ATTRIBUTE_NAME, getEntitlementId().getUserId().getValue())
        .put(EFFECTIVE_DATE_ATTRIBUTE_NAME, getEffectiveDate().toString())
        .put(ACTION_ATTRIBUTE_NAME, getAction().toString())
        .build();
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PodEntitlementMapping(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    additionalAttributes_ = initAdditionalAttributes();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PodEntitlementMapping(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    additionalAttributes_ = initAdditionalAttributes();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public PodEntitlementMapping(IPodEntitlementMapping other)
  {
    super(other);
    
    additionalAttributes_ = other.getAdditionalAttributes();
  }
  
  /**
   * Return the partition key for the mapping of the given pod.
   * 
   * @param podId The ID of the required pod.
   * 
   * @return The partition key for the mapping of the given pod.
   */
  public static KvPartitionKey getPartitionKeyFor(PodId podId)
  {
    return new KvPartitionKey("PE#" + podId);
  }

  /**
   * Return the sort key for the mapping of the given entitlement.
   * 
   * @param entitlementHash The ID of the required entitlement.
   * 
   * @return The sort key for the mapping of the given entitlement.
   */
  public static KvSortKey getSortKeyFor(Hash entitlementHash)
  {
    return new KvSortKey(entitlementHash.toStringBase64());
  }
  
  /**
   * Return the partition and sort key for the mapping of the given entitlement to the pod user.
   * 
   * @param podId The ID of the required pod.
   * @param entitlementHash The ID of the required entitlement.
   * 
   * @return The partition and sort key for the mapping of the given entitlement to the given pod.
   */
  public static KvPartitionSortKeyProvider getPartitionSortKeyFor(PodId podId, Hash entitlementHash)
  {
    return new KvPartitionSortKeyProvider(getPartitionKeyFor(podId), getSortKeyFor(entitlementHash));
  }
  
  @Override
  public IKvPartitionKey getPartitionKey()
  {
    return getPartitionKeyFor(getPodId());
  }
  
  @Override
  public IKvSortKey getSortKey()
  {
    return new KvSortKey(getEntitlementId().getHash().toStringBase64());
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
  public Map<String, Object> getAdditionalAttributes()
  {
    return additionalAttributes_;
  }

  /**
   * Abstract builder for PodEntitlementMapping. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractPodEntitlementMappingBuilder<B extends AbstractPodEntitlementMappingBuilder<B,T>, T extends IPodEntitlementMappingEntity> extends AbstractPodEntitlementMappingEntityBuilder<B,T>
  {
    protected AbstractPodEntitlementMappingBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractPodEntitlementMappingBuilder(Class<B> type, IPodEntitlementMappingEntity initial)
    {
      super(type, initial);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */