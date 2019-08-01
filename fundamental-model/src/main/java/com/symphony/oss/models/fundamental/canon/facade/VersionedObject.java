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
 *  At                  2018-02-27 13:30:31 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.fundmental.canon.IVersionedObjectEntity;
import com.symphony.oss.models.fundmental.canon.VersionedObjectEntity;

/**
 * Facade for Object ObjectSchema(VersionedObject)
 * Generated from ObjectSchema(VersionedObject) at #/components/schemas/VersionedObject
 */
@Immutable
public class VersionedObject extends VersionedObjectEntity implements IVersionedObject
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public VersionedObject(AbstractVersionedObjectBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public VersionedObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public VersionedObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
  
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public VersionedObject(IVersionedObject other)
  {
    super(other);
  }

  @Override
  public Hash getBaseHash()
  {
    Hash baseHash = super.getBaseHash();
    
    if(baseHash == null || Hash.NIL_HASH.equals(baseHash))
      return getAbsoluteHash();
    
    return baseHash;
  }
  
  @Override
  public Hash getPrevHash()
  {
    Hash prevHash = super.getPrevHash();
    
    if(prevHash == null)
      return Hash.NIL_HASH;

    return prevHash;
  }
  
  @Override
  public String getDescription()
  {
    return getClass().getSimpleName() + " absoluteHash=" + getAbsoluteHash() + " baseHash=" + super.getBaseHash();
  }

  /**
   * Abstract builder for VersionedObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractVersionedObjectBuilder<B extends AbstractVersionedObjectBuilder<B,T>, T extends IVersionedObjectEntity> extends AbstractVersionedObjectEntityBuilder<B,T>
  {
    protected AbstractVersionedObjectBuilder(Class<B> type)
    {
      super(type);
      
      withPrevHash(Hash.NIL_HASH);
      withBaseHash(Hash.NIL_HASH);
    }
    
    protected AbstractVersionedObjectBuilder(Class<B> type, IVersionedObjectEntity initial)
    {
      super(type, initial);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */