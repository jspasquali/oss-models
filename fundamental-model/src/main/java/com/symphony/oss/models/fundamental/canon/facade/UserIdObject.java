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
 *  At                  2019-03-22 12:15:36 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.fundmental.canon.IUserIdObjectEntity;
import com.symphony.oss.models.fundmental.canon.UserIdObjectEntity;

/**
 * Facade for Object ObjectSchema(UserIdObject)
 *
 * The ID object for an SBE user.
 * Generated from ObjectSchema(UserIdObject) at #/components/schemas/UserIdObject
 */
@Immutable
public class UserIdObject extends UserIdObjectEntity implements IUserIdObject
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public UserIdObject(AbstractUserIdObjectBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public UserIdObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public UserIdObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public UserIdObject(IUserIdObject other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for UserIdObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractUserIdObjectBuilder<B extends AbstractUserIdObjectBuilder<B,T>, T extends IUserIdObjectEntity> extends AbstractUserIdObjectEntityBuilder<B,T>
  {
    protected AbstractUserIdObjectBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractUserIdObjectBuilder(Class<B> type, IUserIdObjectEntity initial)
    {
      super(type, initial);
    }
    
    /**
     * Set both the pod and user IDs from the given combined value.
     * 
     * @param podAndUserId A PodAndUserId value;
     * 
     * @return This (fluent method).
     */
    public B withPodAndUserId(PodAndUserId podAndUserId)
    {
      withUserId(podAndUserId.getUserId());
      withPodId(podAndUserId.getPodId());
      
      return self();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */