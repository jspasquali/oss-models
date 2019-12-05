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
 *  At                  2019-12-02 20:02:19 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.immutable.ImmutableByteArray;

import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;


import com.symphony.oss.models.object.canon.ApplicationObjectHeaderEntity;
import com.symphony.oss.models.object.canon.IApplicationObjectHeaderEntity;
import com.symphony.oss.models.object.canon.ObjectModel;

/**
 * Facade for Object ObjectSchema(ApplicationObjectHeader)
 *
 * Base type for header or encrypted payloads in the object store.
 * Generated from ObjectSchema(ApplicationObjectHeader) at #/components/schemas/ApplicationObjectHeader
 */
@Immutable
@SuppressWarnings("unused")
public class ApplicationObjectHeader extends ApplicationObjectHeaderEntity implements IApplicationObjectHeader
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ApplicationObjectHeader(AbstractApplicationObjectHeaderBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ApplicationObjectHeader(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ApplicationObjectHeader(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ApplicationObjectHeader(IApplicationObjectHeader other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for ApplicationObjectHeader. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractApplicationObjectHeaderBuilder<B extends AbstractApplicationObjectHeaderBuilder<B,T>, T extends IApplicationObjectHeaderEntity> extends AbstractApplicationObjectHeaderEntityBuilder<B,T>
  {
    protected AbstractApplicationObjectHeaderBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractApplicationObjectHeaderBuilder(Class<B> type, IApplicationObjectHeaderEntity initial)
    {
      super(type, initial);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */