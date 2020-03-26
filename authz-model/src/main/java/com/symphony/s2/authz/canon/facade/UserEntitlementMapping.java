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

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.core.trace.NoOpTraceContext;
import org.symphonyoss.s2.fugue.kv.IKvPagination;
import org.symphonyoss.s2.fugue.kv.IKvPartitionKey;
import org.symphonyoss.s2.fugue.kv.IKvPartitionSortKeyProvider;
import org.symphonyoss.s2.fugue.kv.IKvSortKey;
import org.symphonyoss.s2.fugue.kv.KvPartitionKey;
import org.symphonyoss.s2.fugue.kv.KvPartitionKeyProvider;
import org.symphonyoss.s2.fugue.kv.KvPartitionSortKeyProvider;
import org.symphonyoss.s2.fugue.kv.KvSortKey;
import org.symphonyoss.s2.fugue.store.IFuguePodId;

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
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public UserEntitlementMapping(AbstractUserEntitlementMappingBuilder<?,?> builder)
  {
    super(builder);
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
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public UserEntitlementMapping(IUserEntitlementMapping other)
  {
    super(other);
  }
  
  /**
   * Return the partition key for the mapping of the given user.
   * 
   * @param userId The ID of the required user.
   * 
   * @return The partition key for the mapping of the given user.
   */
  public static IKvPartitionKey getPartitionKeyFor(PodAndUserId userId)
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
  public static IKvSortKey getSortKeyFor(Hash entitlementHash)
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
    return getSortKeyFor(getEntitlementHash());
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

//  public static IEntitlementResponse fetchEntitlements(IKvStore kvStore, PodAndUserId userId, Set<Hash> entitlementHashes)
//  {
//    return fetchEntitlements(kvStore, new EntitlementRequest.Builder()
//      .withUserId(userId)
//      .withEntitlementHashes(entitlementHashes)
//      .build());
//  }
//  
//
//
//  public static IEntitlementResponse fetchEntitlements(IKvStore kvStore, IEntitlementRequest request)
//  {
//    EntitlementResponse.Builder builder = new EntitlementResponse.Builder()
//        .withUserId(request.getUserId())
//        .withRequestHash(request.getAbsoluteHash())
//        ;
//    
//    return builder.build();
//  }
//
//  public static Map<Hash, EntitlementAction> fetchEntitlements(IKvStore kvStore, PodId podId, Set<Hash> entitlementHashes)
//  { 
//    Map<Hash, EntitlementAction>  result = new HashMap<>();
//    ITraceContext trace = NoOpTraceContext.INSTANCE;
//
//    
////    ;
//    
//    String after = null;
//    do
//    {
//      IKvPagination pagination = kvStore.fetch(new KvPartitionKeyProvider(podId, getPartitionKeyFor(podId)),
//          true, null, after, null, IPodEntitlementMapping.class, (item) ->
//          {
//            result.put(item.getEntitlementHash(), item.getAction());
//          }, trace);
//      
//      after = pagination.getAfter();
//    }while(after != null);
//    
//    return result;
//  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */