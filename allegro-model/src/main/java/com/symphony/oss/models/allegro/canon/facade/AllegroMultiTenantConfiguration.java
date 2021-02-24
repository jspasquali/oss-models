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
import com.symphony.oss.models.allegro.canon.AllegroMultiTenantConfigurationEntity;
import com.symphony.oss.models.allegro.canon.IAllegroMultiTenantConfigurationEntity;

/**
 * Facade for Object ObjectSchema(AllegroMultiTenantConfiguration)
 * Generated from ObjectSchema(AllegroMultiTenantConfiguration) at #/components/schemas/AllegroMultiTenantConfiguration
 */
@Immutable
public class AllegroMultiTenantConfiguration extends AllegroMultiTenantConfigurationEntity implements IAllegroMultiTenantConfiguration
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public AllegroMultiTenantConfiguration(AbstractAllegroMultiTenantConfigurationBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AllegroMultiTenantConfiguration(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AllegroMultiTenantConfiguration(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public AllegroMultiTenantConfiguration(IAllegroMultiTenantConfiguration other)
  {
    super(other);
  }

  /**
   * Abstract builder for AllegroMultiTenantConfiguration. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractAllegroMultiTenantConfigurationBuilder<B extends AbstractAllegroMultiTenantConfigurationBuilder<B,T>, T extends IAllegroMultiTenantConfigurationEntity> extends AbstractAllegroMultiTenantConfigurationEntityBuilder<B,T>
  {
    protected AbstractAllegroMultiTenantConfigurationBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractAllegroMultiTenantConfigurationBuilder(Class<B> type, IAllegroMultiTenantConfigurationEntity initial)
    {
      super(type, initial);
    }
  }
  
  @Override
  protected void redactJsonObject(MutableJsonObject jsonObject)
  {
    super.redactJsonObject(jsonObject);
    
    if(getPrincipalCredential() != null)
    {
      jsonObject.addIfNotNull("principalCredential", getPrincipalCredential().getRedacted());
    }
    
    if(getRsaPemCredential() != null)
    {
      jsonObject.addIfNotNull("rsaPemCredential", ConnectionSettings.REDACTED);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */