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
 *  At                  2019-05-23 13:16:21 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.chat.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.chat.canon.BaseUserEntity;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundamental.canon.facade.PodId;
import com.symphony.oss.models.fundmental.canon.ContentIdType;
import com.symphony.oss.models.fundmental.canon.PodContentIdObject;
import com.symphony.oss.models.system.canon.facade.Principal;

/**
 * Facade for Object ObjectSchema(BaseUser)
 *
 * A subset representation of the Symphony Maestro User
 * Generated from ObjectSchema(BaseUser) at #/components/schemas/BaseUser
 */
@Immutable
public class BaseUser extends BaseUserEntity implements IBaseUser
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public BaseUser(AbstractBaseUserBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public BaseUser(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public BaseUser(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public BaseUser(IBaseUser other)
  {
    super(other);
  }
  
  /**
   * Return the ID object for the principal's BaseUser record as viewed from some pod.
   * 
   * @param principalBaseHash The base hash of the principal whose BaseUsert this ID represents.
   * @param podId             The podId of the pod whose view this object represents.
   * 
   * In xpod scenarios the view of a user from the local and remote pods are different so we need to include
   * the viewing pod's ID in this object.
   * 
   * @return The ID object for the principal's personal security context.
   */
  public static IFundamentalId getBaseUserId(Hash principalBaseHash, PodId podId)
  {
    return new PodContentIdObject.Builder()
        .withSubjectHash(principalBaseHash)
        .withSubjectType(Principal.TYPE_ID)
        .withContentType(BaseUser.TYPE_ID)
        .withIdType(ContentIdType.ATTRIBUTE)
        .withPodId(podId)
        .build();
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */