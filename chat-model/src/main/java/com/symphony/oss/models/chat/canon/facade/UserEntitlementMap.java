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
 *  At                  2019-11-05 08:41:07 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.chat.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.immutable.ImmutableByteArray;
import org.symphonyoss.s2.common.type.provider.IBooleanProvider;
import org.symphonyoss.s2.common.dom.json.IImmutableJsonDomNode;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;


import com.symphony.oss.models.chat.canon.UserEntitlementMapEntity;
import com.symphony.oss.models.chat.canon.IUserEntitlementMapEntity;
import com.symphony.oss.models.chat.canon.ChatModel;

/**
 * Facade for Object ObjectSchema(UserEntitlementMap)
 *
 * An user entitlement map as seen in IMaestroUser
 * Generated from ObjectSchema(UserEntitlementMap) at #/components/schemas/UserEntitlementMap
 */
@Immutable
@SuppressWarnings("unused")
public class UserEntitlementMap extends UserEntitlementMapEntity implements IUserEntitlementMap
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public UserEntitlementMap(AbstractUserEntitlementMapBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public UserEntitlementMap(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public UserEntitlementMap(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public UserEntitlementMap(IUserEntitlementMap other)
  {
    super(other);
  }

  @Override
  public boolean hasEntitlement(String entitlementId)
  {
    IImmutableJsonDomNode node = getJsonObject().get(entitlementId);
    
    if(node instanceof IBooleanProvider)
      return ((IBooleanProvider) node).asBoolean();
    
    return false;
  }
  
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */