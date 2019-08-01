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
 *  At                  2018-03-20 15:39:10 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.common.hash.HashProvider;
import org.symphonyoss.s2.fugue.IFugueServer;

import com.symphony.oss.models.fundamental.canon.facade.ITraceTransaction;
import com.symphony.oss.models.fundamental.canon.facade.TraceTransaction;
import com.symphony.oss.models.fundmental.canon.HashType;
import com.symphony.oss.models.fundmental.canon.TraceTransactionEntity;

/**
 * Facade for Object ObjectSchema(TraceTransaction)
 * Generated from ObjectSchema(TraceTransaction) at #/components/schemas/TraceTransaction
 */
@Immutable
public class TraceTransaction extends TraceTransactionEntity implements ITraceTransaction
{
  private static final String instanceId_ = IFugueServer.getInstanceId();
  
  private final Hash               absoluteHash_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public TraceTransaction(TraceTransaction.Builder builder)
  {
    super(forceHashType(builder)
        .withInstanceId(instanceId_));
    
    absoluteHash_ = generateHash();
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public TraceTransaction(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    absoluteHash_ = generateHash();
  }
  
  private static TraceTransaction.Builder forceHashType(TraceTransaction.Builder other)
  {
    return other
        .withHashType(HashType.newBuilder().build(Hash.getDefaultHashTypeId()));
  }
  
  private Hash generateHash()
  {
    return HashProvider.getHashOf(getHashType().asInteger(), serialize());
  }

  @Override
  public Hash getAbsoluteHash()
  {
    return absoluteHash_;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */