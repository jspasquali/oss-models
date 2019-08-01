/**
 * Copyright 2018 Symphony Communication Services, LLC.
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
 *  At                  2018-05-20 15:11:40 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.fundmental.canon.FundamentalPayloadEntity;

/**
 * Facade for Object ObjectSchema(FundamentalPayload)
 * Generated from ObjectSchema(FundamentalPayload) at #/components/schemas/FundamentalPayload
 */
@Immutable
public class FundamentalPayload extends FundamentalPayloadEntity implements IFundamentalPayload
{
  private IFundamentalObject payloadContainer_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public FundamentalPayload(AbstractFundamentalPayloadBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public FundamentalPayload(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
 /**
  * Copy constructor.
  * 
  * @param other Another instance from which all attributes are to be copied.
  */
 public FundamentalPayload(IFundamentalPayload other)
 {
   super(other);
   
   payloadContainer_ = other.getPayloadContainer();
 }
  
  @Override
  public IFundamentalObject getPayloadContainer()
  {
    return payloadContainer_;
  }

  @Override
  public void setPayloadContainer(IFundamentalObject payloadContainer)
  {
    payloadContainer_ = payloadContainer;
  }
  
  @Override
  public Hash getAbsoluteHash()
  {
    return payloadContainer_.getAbsoluteHash();
  }
  
  @Override
  public String getRangeKey()
  {
    return payloadContainer_.getRangeKey();
  }

  @Override
  public String generateRangeKey(Hash absoluteHash)
  {
    return null;
  }

  @Override
  public String getDescription()
  {
    return getClass().getSimpleName() + " absoluteHash=" + getAbsoluteHash();
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */