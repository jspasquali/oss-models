/**
 * Copyright 2020 Symphony Communication Services, LLC.
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
 *  At                  2020-06-23 13:24:02 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.allegro.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.models.allegro.canon.AllegroBaseConfigurationEntity;
import com.symphony.oss.models.allegro.canon.IAllegroBaseConfigurationEntity;

/**
 * Facade for Object ObjectSchema(AllegroBaseConfiguration)
 * Generated from ObjectSchema(AllegroBaseConfiguration) at #/components/schemas/AllegroBaseConfiguration
 */
@Immutable
public class AllegroBaseConfiguration extends AllegroBaseConfigurationEntity implements IAllegroBaseConfiguration
{
  private ImmutableJsonObject redacted_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public AllegroBaseConfiguration(AbstractAllegroBaseConfigurationBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AllegroBaseConfiguration(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AllegroBaseConfiguration(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public AllegroBaseConfiguration(IAllegroBaseConfiguration other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for AllegroBaseConfiguration. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractAllegroBaseConfigurationBuilder<B extends AbstractAllegroBaseConfigurationBuilder<B,T>, T extends IAllegroBaseConfigurationEntity> extends AbstractAllegroBaseConfigurationEntityBuilder<B,T>
  {
    protected AbstractAllegroBaseConfigurationBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractAllegroBaseConfigurationBuilder(Class<B> type, IAllegroBaseConfigurationEntity initial)
    {
      super(type, initial);
    }

    /**
     * Set the configuration settings to be used for any connection which does not specify its own.
     * 
     * @param connectionSettings the configuration settings to be used for any connection which does not specify its own.
     * 
     * @return This (fluent method).
     */
    public B withDefaultConnectionSettings(IConnectionSettings connectionSettings)
    {
      return withApiConnectionSettings(connectionSettings);
    }
  }
  
  @Override
  public IConnectionSettings getDefaultConnectionSettings()
  {
    return getApiConnectionSettings();
  }
  
  @Override
  public synchronized ImmutableJsonObject getRedacted()
  {
    if(redacted_ == null)
    {
      MutableJsonObject jsonObject = getJsonObject().mutify();
      
      redactJsonObject(jsonObject);
      
      redacted_ = jsonObject.immutify();
    }
    
    return redacted_;
  }

  protected void redactJsonObject(MutableJsonObject jsonObject)
  {
    if(getApiConnectionSettings() != null)
    {
        jsonObject.addIfNotNull("apiConnectionSettings", getApiConnectionSettings().getRedacted());
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */