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
 *		Template name		   proforma/java/Object/I_.java.ftl
 *		Template version	   1.0
 *  At                  2018-05-20 15:11:40 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.fugue.store.IFugueObjectPayload;

import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundmental.canon.IFundamentalPayloadEntity;

/**
 * Facade for Object ObjectSchema(FundamentalPayload)
 * Generated from ObjectSchema(FundamentalPayload) at #/components/schemas/FundamentalPayload
 */
@Immutable
public interface IFundamentalPayload
  extends IFundamentalPayloadEntity, IFugueObjectPayload
{
  
  /**
   * Return a short textual description of this object.
   * 
   * Open objects should indicate their internal type, for example an OpenBlob should indicate the
   * type of the enclosed application payload.
   * 
   * @return a short textual description of this object.
   */
  @Override
  String getDescription();
  
  /**
   * Generate the binary range key for this payload.
   * 
   * This method is intended to be called from FundamentalObject only.
   * 
   * @param absoluteHash  The absolute hash of the object of which this payload is a part.
   * 
   * @return The binary range key used for storage of the object.
   */
  String generateRangeKey(Hash absoluteHash);
  
  IFundamentalObject getPayloadContainer();
  void setPayloadContainer(IFundamentalObject container);

  Hash getAbsoluteHash();

  String getRangeKey();
  
  /**
   * 
   * @return The pod which owns this object, if any.
   */
  @Override
  @Nullable PodId getPodId();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */