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
 *		Template name		   proforma/java/Object/I_.java.ftl
 *		Template version	   1.0
 *  At                  2019-02-24 07:56:25 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.fundmental.canon.IFundamentalIdEntity;

/**
 * Facade for Object ObjectSchema(FundamentalId)
 *
 * An ID object, applications define IDs as a sub-type of this. The absolute hash of an ID Fundamental Object is calculated from this object only.
 * Generated from ObjectSchema(FundamentalId) at #/components/schemas/FundamentalId
 */
@Immutable
public interface IFundamentalId
  extends IFundamentalIdEntity
{
  /**
   * 
   * @return the absolute hash of this ID.
   */
  Hash getAbsoluteHash();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */