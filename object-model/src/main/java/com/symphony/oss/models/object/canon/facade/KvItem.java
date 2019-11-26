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
 *  At                  2019-11-25 09:18:48 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.common.hash.HashProvider;
import org.symphonyoss.s2.fugue.store.IFuguePodId;

import com.symphony.oss.models.object.canon.KvItemEntity;

/**
 * Facade for Object ObjectSchema(KvItem)
 *
 * Base type for objects in the  object store.
 * Generated from ObjectSchema(KvItem) at #/components/schemas/KvItem
 */
@Immutable
public class KvItem extends KvItemEntity implements IKvItem
{
  private final Hash    hash_;
  private final SortKey uniqieSortKey_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public KvItem(AbstractKvItemBuilder<?,?> builder)
  {
    super(builder);
    hash_ = generateHash();
    uniqieSortKey_ = generateSortKey();
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public KvItem(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    hash_ = generateHash();
    uniqieSortKey_ = generateSortKey();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public KvItem(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    hash_ = generateHash();
    uniqieSortKey_ = generateSortKey();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public KvItem(IKvItem other)
  {
    super(other);
    
    hash_ = other.getHash();
    uniqieSortKey_ = other.getSortKey();
  }
  
  protected Hash generateHash()
  {
//    if(getId() != null)
//      return ((IdPayloadContainer)getPayload()).getId().getAbsoluteHash();
    
    return HashProvider.getHashOf(getHashType().asInteger(), serialize());
  }
  
  private SortKey generateSortKey()
  {
    return SortKey.newBuilder().build(super.getSortKey() + getHash().toStringBase64());
  }

  @Override
  public SortKey getSortKey()
  {
    return uniqieSortKey_;
  }

  @Override
  public String getJson()
  {
    return super.toString();
  }

  @Override
  public String getType()
  {
    return getCanonType();
  }

  @Override
  public IFuguePodId getPodId()
  {
    return getOwner().getPodId();
  }

  @Override
  public Hash getHash()
  {
    return hash_;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */