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
 *  At                  2019-11-25 13:29:56 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import java.time.Instant;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.fugue.store.IFuguePodId;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.object.canon.PartitionEntity;

/**
 * Facade for Object ObjectSchema(Partition)
 *
 * A partition in the object store.
 * Generated from ObjectSchema(Partition) at #/components/schemas/Partition
 */
@Immutable
public class Partition extends PartitionEntity implements IPartition
{
  private static SortKey  sortKey_ = SortKey.newBuilder().build("P#");
  
  private final PartitionKey partitionKey_ = PartitionKey.newBuilder().build("P#" + getId().getHash());
  private final PartitionKey objectKey_ = PartitionKey.newBuilder().build("O#" + getId().getHash());
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Partition(AbstractPartitionBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Partition(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Partition(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Partition(IPartition other)
  {
    super(other);
  }

  @Override
  public PartitionKey getPartitionKey()
  {
    return partitionKey_;
  }

  @Override
  public SortKey getSortKey()
  {
    return sortKey_;
  }

  @Override
  public PartitionKey getObjectKey()
  {
    return objectKey_;
  }

  @Override
  public PodAndUserId getOwner()
  {
    return getId().getUserId();
  }

  @Override
  public Instant getPurgeDate()
  {
    return null;
  }

  @Override
  public String getJson()
  {
    return super.toString();
  }

  @Override
  public String getType()
  {
    return super.getCanonType();
  }

  @Override
  public IFuguePodId getPodId()
  {
    return getId().getUserId().getPodId();
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */