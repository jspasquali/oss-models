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
 *    Template groupId     org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *    Template name      proforma/java/Object/_.java.ftl
 *    Template version     1.0
 *  At                  2019-03-12 11:45:33 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.system.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;

import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseReadOnly;
import com.symphony.oss.models.system.canon.EnvironmentEntity;
import com.symphony.oss.models.system.canon.EnvironmentIdObject;
import com.symphony.oss.models.system.canon.IEnvironmentEntity;
import com.symphony.oss.models.system.canon.IEnvironmentIdObject;

/**
 * Facade for Object ObjectSchema(Environment)
 *
 * The environment object, the root of the config structure, base hash is always InferredHashFactory.environment(). The contentSequence of this object is the list of realms in this environment.
 * Generated from ObjectSchema(Environment) at #/components/schemas/Environment
 */
@Immutable
public class Environment extends EnvironmentEntity implements IEnvironment
{
  /** The ID for the one and only Environment object. */
  public static final IEnvironmentIdObject ID = new EnvironmentIdObject.Builder().build();
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Environment(AbstractEnvironmentBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Environment(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Environment(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Environment(IEnvironment other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for Environment. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractEnvironmentBuilder<B extends AbstractEnvironmentBuilder<B,T>, T extends IEnvironmentEntity> extends AbstractEnvironmentEntityBuilder<B,T>
  {
    protected AbstractEnvironmentBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractEnvironmentBuilder(Class<B> type, IEnvironmentEntity initial)
    {
      super(type, initial);
    }
  }
  
  public static IEnvironment fetchInstance(IFundamentalDatabaseReadOnly fundamentalDatabase, IOpenPrincipalCredential credential) throws NoSuchObjectException
  {
    return fundamentalDatabase.fetchCurrentById(ID, credential, IEnvironment.class);
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */