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
 *  At                  2020-03-28 17:43:30 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.s2.authz.canon.facade;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import com.symphony.s2.authz.canon.UserStatusEntity;
import com.symphony.s2.authz.canon.IUserStatusEntity;
import com.google.common.collect.ImmutableMap;
import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.hash.Hash;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.fugue.kv.IKvPartitionKey;
import com.symphony.oss.fugue.kv.IKvPartitionSortKeyProvider;
import com.symphony.oss.fugue.kv.IKvSortKey;
import com.symphony.oss.fugue.kv.KvPartitionKey;
import com.symphony.oss.fugue.kv.KvPartitionSortKeyProvider;
import com.symphony.oss.fugue.kv.KvSortKey;
import com.symphony.oss.fugue.store.IFuguePodId;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.s2.authz.canon.AuthzModel;

/**
 * Facade for Object ObjectSchema(UserStatus)
 *
 * The status of a user account.
 * Generated from ObjectSchema(UserStatus) at #/components/schemas/UserStatus
 */
@Immutable
@SuppressWarnings("unused")
public class UserStatus extends UserStatusEntity implements IUserStatus
{
  /** Additional attribute name for the effective date of the entitlement mapping */
  public static final String EFFECTIVE_DATE_ATTRIBUTE_NAME = "effective";
  
  private final Map<String, Object> additionalAttributes_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public UserStatus(AbstractUserStatusBuilder<?,?> builder)
  {
    super(builder);
    
    additionalAttributes_ = initAdditionalAttributes();
  }
  
  private Map<String, Object> initAdditionalAttributes()
  {
    Map<String, Object> map = new HashMap<>();
    
    return new ImmutableMap.Builder<String, Object>()
        .put(EFFECTIVE_DATE_ATTRIBUTE_NAME, 
            getEffectiveDate()==null ? 
                Instant.EPOCH : 
                  getEffectiveDate().toString())
        .build();
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public UserStatus(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
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
  public UserStatus(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    additionalAttributes_ = initAdditionalAttributes();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public UserStatus(IUserStatus other)
  {
    super(other);
    
    additionalAttributes_ = other.getAdditionalAttributes();
  }
  
  @Override
  public IKvPartitionKey getPartitionKey()
  {
    return getPartitionKeyFor(getUserId());
  }
  
  /**
   * Get the partition key for Entitlement object for the given hash.
   * 
   * @param userId The ID of the user to whom this status relates.
   * 
   * @return The partition key for Entitlement object for the given hash.
   */
  public static KvPartitionKey getPartitionKeyFor(PodAndUserId userId)
  {
    return new KvPartitionKey("US#" + userId);
  }
  
  /**
   * Get the partition key for Entitlement object for the given hash.
   * 
   * @param userId The ID of the user to whom this status relates.
   * 
   * @return The partition key for Entitlement object for the given hash.
   */
  public static KvPartitionSortKeyProvider getPartitionSortKeyFor(PodAndUserId userId)
  {
    return new KvPartitionSortKeyProvider(getPartitionKeyFor(userId), new KvSortKey("US#"));
  }

  @Override
  public IKvSortKey getSortKey()
  {
    return new KvSortKey("US#");
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
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */