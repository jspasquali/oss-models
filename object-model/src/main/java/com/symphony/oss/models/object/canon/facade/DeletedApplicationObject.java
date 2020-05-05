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
 *  At                  2019-12-04 07:16:22 GMT-08:00
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import java.time.Instant;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.models.object.canon.DeletedApplicationObjectEntity;
import com.symphony.oss.models.object.canon.IDeletedApplicationObjectEntity;

/**
 * Facade for Object ObjectSchema(DeletedApplicationObject)
 *
 * A tombstone marker for an object which was deleted.
 * Generated from ObjectSchema(DeletedApplicationObject) at #/components/schemas/DeletedApplicationObject
 */
@Immutable
public class DeletedApplicationObject extends DeletedApplicationObjectEntity implements IDeletedApplicationObject
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public DeletedApplicationObject(AbstractDeletedApplicationObjectBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public DeletedApplicationObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public DeletedApplicationObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public DeletedApplicationObject(IDeletedApplicationObject other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for DeletedApplicationObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractDeletedApplicationObjectBuilder<B extends AbstractDeletedApplicationObjectBuilder<B,T>, T extends IDeletedApplicationObjectEntity> extends AbstractDeletedApplicationObjectEntityBuilder<B,T>
  {
    protected AbstractDeletedApplicationObjectBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractDeletedApplicationObjectBuilder(Class<B> type, IDeletedApplicationObjectEntity initial)
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