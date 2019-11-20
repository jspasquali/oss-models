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
 *  At                  2018-05-14 14:37:44 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.core.canon.IApplicationPayload;
import com.symphony.oss.models.fundmental.canon.IApplicationObjectEntity;

/**
 * Facade for Object ObjectSchema(ApplicationObject)
 *
 * Generated from ObjectSchema(ApplicationObject) at #/components/schemas/ApplicationObject
 */
@Immutable
public interface IApplicationObject
  extends IApplicationPayload, IApplicationObjectEntity
{
  Hash getAbsoluteHash();

  Hash getPrevHash();

  Hash getBaseHash();

  /**
   * 
   * @return The container of this ApplicationObject.
   * @deprecated Use getContainer()
   */
  @Deprecated
  IBlob getBlob();
  
  /**
   * 
   * @return The container of this ApplicationObject.
   */
  IVersionedObject getContainer();
  
  /**
   * Set the container of this ApplicationObject.
   * 
   * @param blob The container. 
   * 
   * @deprecated Use setContainer()
   */
  @Deprecated
  void setBlob(IBlob blob);

  /**
   * Set the container of this ApplicationObject.
   * 
   * @param container The container. 
   */
  void setContainer(IVersionedObject container);

  /**
   * 
   * @return The FundamentalObject containing this ApplicationObject.
   */
  IFundamentalObject getFundamentalObject();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */