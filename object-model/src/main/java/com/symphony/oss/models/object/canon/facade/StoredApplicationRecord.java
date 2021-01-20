/**
 * Copyright 2021 Symphony Communication Services, LLC.
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
 *  At                  2021-01-20 08:25:55 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.commons.immutable.ImmutableByteArray;

import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;

import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;

import com.symphony.oss.commons.immutable.ImmutableByteArray;

import com.symphony.oss.models.object.canon.StoredApplicationRecordEntity;
import com.symphony.oss.models.object.canon.IStoredApplicationRecordEntity;
import com.symphony.oss.models.object.canon.ObjectModel;

/**
 * Facade for Object ObjectSchema(StoredApplicationRecord)
 *
 * A stored application object in an external database.
 * Generated from ObjectSchema(StoredApplicationRecord) at #/components/schemas/StoredApplicationRecord
 */
@Immutable
@SuppressWarnings("unused")
public class StoredApplicationRecord extends StoredApplicationRecordEntity implements IStoredApplicationRecord
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public StoredApplicationRecord(AbstractStoredApplicationRecordBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public StoredApplicationRecord(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public StoredApplicationRecord(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public StoredApplicationRecord(IStoredApplicationRecord other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for StoredApplicationRecord. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractStoredApplicationRecordBuilder<B extends AbstractStoredApplicationRecordBuilder<B,T>, T extends IStoredApplicationRecordEntity> extends AbstractStoredApplicationRecordEntityBuilder<B,T>
  {
    protected AbstractStoredApplicationRecordBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractStoredApplicationRecordBuilder(Class<B> type, IStoredApplicationRecordEntity initial)
    {
      super(type, initial);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */