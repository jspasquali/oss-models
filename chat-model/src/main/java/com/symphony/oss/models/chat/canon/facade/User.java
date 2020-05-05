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
 *  At                  2019-10-30 08:40:35 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.chat.canon.facade;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.models.chat.canon.IEntitlement;
import com.symphony.oss.models.chat.canon.UserEntity;

/**
 * Facade for Object ObjectSchema(User)
 *
 * A user.
 * Generated from ObjectSchema(User) at #/components/schemas/User
 */
@Immutable
@SuppressWarnings("unused")
public class User extends UserEntity implements IUser
{
  private Map<String, Boolean>  entitlementMap_ = null;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public User(AbstractUserBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public User(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public User(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public User(IUser other)
  {
    super(other);
  }
  
  private synchronized Map<String, Boolean> getEntitlementMap()
  {
    if(entitlementMap_ == null)
    {
      entitlementMap_ = new HashMap<>();
      
      for(IEntitlement entitlement : getEntitlement())
      {
        entitlementMap_.put(entitlement.getName(), entitlement.getValue());
      }
    }
    
    return entitlementMap_;
  }
  
  @Override
  public boolean hasEntitlement(String entitlementId)
  {
    Boolean result = getEntitlementMap().get(entitlementId);
    
    return Boolean.TRUE == result;
  }
  
//  /**
//   * Return the ID object for the principal's BaseUser record as viewed from some pod.
//   * 
//   * @param principalBaseHash The base hash of the principal whose BaseUsert this ID represents.
//   * @param podId             The podId of the pod whose view this object represents.
//   * 
//   * In xpod scenarios the view of a user from the local and remote pods are different so we need to include
//   * the viewing pod's ID in this object.
//   * 
//   * @return The ID object for the principal's personal security context.
//   */
//  public static IFundamentalId getBaseUserId(Hash principalBaseHash, PodId podId)
//  {
//    return new PodContentIdObject.Builder()
//        .withSubjectHash(principalBaseHash)
//        .withSubjectType(Principal.TYPE_ID)
//        .withContentType(User.TYPE_ID)
//        .withIdType(ContentIdType.ATTRIBUTE)
//        .withPodId(podId)
//        .build();
//  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */