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
 *  At                  2019-11-28 11:12:37 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.core.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.hash.Hash;
import com.symphony.oss.commons.hash.HashProvider;
import com.symphony.oss.models.core.canon.HashType;
import com.symphony.oss.models.core.canon.ISystemObjectEntity;
import com.symphony.oss.models.core.canon.SystemObjectEntity;

/**
 * Facade for Object ObjectSchema(SystemObject)
 *
 * Base type for system objects in the object store.
 * Generated from ObjectSchema(SystemObject) at #/components/schemas/SystemObject
 */
@Immutable
public class SystemObject extends SystemObjectEntity implements ISystemObject
{
  /** Trace subject type for object trace records. */
  public static final String TRACE_SUBJECT_TYPE = "OBJECT";
  
  private final Hash            absoluteHash_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public SystemObject(AbstractSystemObjectBuilder<?,?> builder)
  {
    super(builder);
    absoluteHash_ = generateHash();
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SystemObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    absoluteHash_ = generateHash();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SystemObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    absoluteHash_ = generateHash();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public SystemObject(ISystemObject other)
  {
    super(other);
    absoluteHash_ = other.getAbsoluteHash();
  }
  
  protected Hash generateHash()
  {
    return HashProvider.getHashOf(getHashType().asInteger(), serialize());
  }
  
  @Override
  public Hash getAbsoluteHash()
  {
    return absoluteHash_;
  }

  @Override
  public boolean isSaveToSecondaryStorage()
  {
    return true;
  }
  
  @Override
  public String getTraceSubjectType()
  {
    return TRACE_SUBJECT_TYPE;
  }

  @Override
  public String getTraceSubjectId()
  {
    return absoluteHash_.toStringBase64();
  }

  /**
   * Abstract builder for SystemObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractSystemObjectBuilder<B extends AbstractSystemObjectBuilder<B,T>, T extends ISystemObjectEntity> extends AbstractSystemObjectEntityBuilder<B,T>
  {
    protected AbstractSystemObjectBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractSystemObjectBuilder(Class<B> type, ISystemObjectEntity initial)
    {
      super(type, initial);
    }

    @Override
    public void validate()
    {
      if(getHashType() == null)
        withHashType(HashType.newBuilder().build(Hash.getDefaultHashTypeId()));
      
      super.validate();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */