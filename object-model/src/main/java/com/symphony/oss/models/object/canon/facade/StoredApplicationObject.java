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
 *  At                  2019-11-29 11:39:41 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import java.time.Instant;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.hash.Hash;
import com.symphony.oss.models.object.canon.IStoredApplicationObjectEntity;
import com.symphony.oss.models.object.canon.StoredApplicationObjectEntity;

/**
 * Facade for Object ObjectSchema(StoredApplicationObject)
 *
 * Base type for application objects in the object store.
 * Generated from ObjectSchema(StoredApplicationObject) at #/components/schemas/StoredApplicationObject
 */
@Immutable
public class StoredApplicationObject extends StoredApplicationObjectEntity implements IStoredApplicationObject
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public StoredApplicationObject(AbstractStoredApplicationObjectBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public StoredApplicationObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public StoredApplicationObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public StoredApplicationObject(IStoredApplicationObject other)
  {
    super(other);
  }

  @Override
  public Hash getBaseHash()
  {
    if(super.getBaseHash() == null)
      return getAbsoluteHash();
    
    return super.getBaseHash();
  }
  
  /**
   * 
   * @return True iff this is a base object (the initial version of an object for a given baseHash)
   */
  public boolean isBaseObject()
  {
    return super.getBaseHash() == null;
  }

  /**
   * Abstract builder for StoredApplicationObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractStoredApplicationObjectBuilder<B extends AbstractStoredApplicationObjectBuilder<B,T>, T extends IStoredApplicationObjectEntity> extends AbstractStoredApplicationObjectEntityBuilder<B,T>
  {
    protected AbstractStoredApplicationObjectBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractStoredApplicationObjectBuilder(Class<B> type, IStoredApplicationObjectEntity initial)
    {
      super(type, initial);
    }

    @Override
    public Instant getCreatedDate()
    {
      if(super.getCreatedDate() == null)
         return Instant.now();
      
      return super.getCreatedDate();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */