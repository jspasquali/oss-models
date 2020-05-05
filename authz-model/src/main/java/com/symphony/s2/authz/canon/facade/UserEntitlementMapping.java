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
import com.symphony.oss.fugue.kv.IKvPartitionKeyProvider;
import com.symphony.oss.fugue.kv.IKvPartitionSortKeyProvider;
import com.symphony.oss.fugue.kv.IKvSortKey;
import com.symphony.oss.fugue.kv.KvPartitionKey;
import com.symphony.oss.fugue.kv.KvPartitionKeyProvider;
import com.symphony.oss.fugue.kv.KvPartitionSortKeyProvider;
import com.symphony.oss.fugue.kv.KvSortKey;
import com.symphony.oss.fugue.store.IFuguePodId;
import com.symphony.oss.fugue.trace.ITraceContext;
import com.symphony.oss.fugue.trace.NoOpTraceContext;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.core.kv.store.IKvStore;
import com.symphony.s2.authz.canon.EntitlementAction;
import com.symphony.s2.authz.canon.EntitlementRequest;
import com.symphony.s2.authz.canon.IEntitlementRequest;
import com.symphony.s2.authz.canon.IUserEntitlementMappingEntity;
import com.symphony.s2.authz.canon.UserEntitlementMappingEntity;

/**
 * Facade for Object ObjectSchema(UserEntitlementMapping)
 *
 * A user entitlement setting.
 * Generated from ObjectSchema(UserEntitlementMapping) at #/components/schemas/UserEntitlementMapping
 */
@Immutable
@SuppressWarnings("unused")
public class UserEntitlementMapping extends UserEntitlementMappingEntity implements IUserEntitlementMapping
{
  /** Additional attribute name for the ID of the entitlement owner */
  public static final String OWNER_ID_ATTRIBUTE_NAME = "ownerId";
  /** Additional attribute name for the effective date of the entitlement mapping */
  public static final String EFFECTIVE_DATE_ATTRIBUTE_NAME = "effective";
  
  private final Map<String, Object> additionalAttributes_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public UserEntitlementMapping(AbstractUserEntitlementMappingBuilder<?,?> builder)
  {
    super(builder);
    
    additionalAttributes_ = initAdditionalAttributes();
  }
  
  private Map<String, Object> initAdditionalAttributes()
  {
    Map<String, Object> map = new HashMap<>();
    
    return new ImmutableMap.Builder<String, Object>()
        .put(OWNER_ID_ATTRIBUTE_NAME, getEntitlementId().getUserId().getValue())
        .put(EFFECTIVE_DATE_ATTRIBUTE_NAME, getEffectiveDate().toString())
        .build();
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public UserEntitlementMapping(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
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
  public UserEntitlementMapping(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    additionalAttributes_ = initAdditionalAttributes();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public UserEntitlementMapping(IUserEntitlementMapping other)
  {
    super(other);
    
    additionalAttributes_ = other.getAdditionalAttributes();
  }
  
  /**
   * Return the partition key for the mappings of the given user.
   * 
   * @param userId The ID of the required user.
   * 
   * @return The partition key for the mapping of the given user.
   */
  public static KvPartitionKey getPartitionKeyFor(PodAndUserId userId)
  {
    return new KvPartitionKey("UE#" + userId);
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
   * Return the partition and sort key for the mapping of the given entitlement to the given user.
   * 
   * @param userId The ID of the required user.
   * @param entitlementHash The ID of the required entitlement.
   * 
   * @return The partition and sort key for the mapping of the given entitlement to the given user.
   */
  public static IKvPartitionSortKeyProvider getPartitionSortKeyFor(PodAndUserId userId, Hash entitlementHash)
  {
    return new KvPartitionSortKeyProvider(getPartitionKeyFor(userId), getSortKeyFor(entitlementHash));
  }
  
  @Override
  public IKvPartitionKey getPartitionKey()
  {
    return getPartitionKeyFor(getUserId());
  }

  @Override
  public IKvSortKey getSortKey()
  {
    return getSortKeyFor(getEntitlementId().getHash());
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
    return getUserId().getPodId();
  }
  
  @Override
  public Map<String, Object> getAdditionalAttributes()
  {
    return additionalAttributes_;
  }
  
  /**
   * Abstract builder for UserEntitlementMapping. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractUserEntitlementMappingBuilder<B extends AbstractUserEntitlementMappingBuilder<B,T>, T extends IUserEntitlementMappingEntity> extends AbstractUserEntitlementMappingEntityBuilder<B,T>
  {
    protected AbstractUserEntitlementMappingBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractUserEntitlementMappingBuilder(Class<B> type, IUserEntitlementMappingEntity initial)
    {
      super(type, initial);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */