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
 *  At                  2019-02-13 15:17:26 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.internal.pod.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import com.symphony.oss.models.fundamental.canon.facade.PodAndUserId;
import com.symphony.oss.models.internal.pod.canon.AccountInfoEntity;

/**
 * Facade for Object ObjectSchema(AccountInfo)
 * Generated from ObjectSchema(AccountInfo) at #/components/schemas/AccountInfo
 */
@Immutable
public class AccountInfo extends AccountInfoEntity implements IAccountInfo
{
  private final PodAndUserId userId_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public AccountInfo(AbstractAccountInfoBuilder<?,?> builder)
  {
    super(builder);
    
    userId_ = PodAndUserId.newBuilder().build(getUserName());
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AccountInfo(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    userId_ = PodAndUserId.newBuilder().build(getUserName());
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AccountInfo(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    userId_ = PodAndUserId.newBuilder().build(getUserName());
  }

  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public AccountInfo(IAccountInfo other)
  {
    super(other);
    
    userId_ = other.getUserId();
  }

  @Override
  public PodAndUserId getUserId()
  {
    return userId_;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */