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
 *  At                  2019-11-25 12:58:22 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import java.time.Instant;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.immutable.ImmutableByteArray;
import org.symphonyoss.s2.fugue.kv.IKvPartitionKey;
import org.symphonyoss.s2.fugue.kv.KvPartitionKey;
import org.symphonyoss.s2.fugue.store.IFuguePodId;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;

import org.symphonyoss.s2.common.immutable.ImmutableByteArray;

import com.symphony.oss.models.object.canon.PartitionThreadEntity;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.object.canon.IPartitionThreadEntity;
import com.symphony.oss.models.object.canon.ObjectModel;

/**
 * Facade for Object ObjectSchema(PartitionThread)
 *
 * A partition in the object store.
 * Generated from ObjectSchema(PartitionThread) at #/components/schemas/PartitionThread
 */
@Immutable
@SuppressWarnings("unused")
public class PartitionThread extends PartitionThreadEntity implements IPartitionThread
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public PartitionThread(AbstractPartitionThreadBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PartitionThread(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PartitionThread(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public PartitionThread(IPartitionThread other)
  {
    super(other);
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */