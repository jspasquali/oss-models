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

import com.symphony.oss.commons.immutable.ImmutableByteArray;

import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;

import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;

import java.net.URL;
import com.symphony.oss.models.core.canon.facade.UrlBuilder;

import com.symphony.oss.models.allegro.canon.AllegroConfigurationEntity;
import com.symphony.oss.models.allegro.canon.IAllegroConfigurationEntity;
import com.symphony.oss.models.allegro.canon.AllegroModel;

/**
 * Facade for Object ObjectSchema(AllegroConfiguration)
 * Generated from ObjectSchema(AllegroConfiguration) at #/components/schemas/AllegroConfiguration
 */
@Immutable
@SuppressWarnings("unused")
public class AllegroConfiguration extends AllegroConfigurationEntity implements IAllegroConfiguration
{
  final IAllegroConfiguration redacted_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public AllegroConfiguration(AbstractAllegroConfigurationBuilder<?,?> builder)
  {
    super(builder);
    redacted_ = initRedacted();
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AllegroConfiguration(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    redacted_ = initRedacted();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AllegroConfiguration(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    redacted_ = initRedacted();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public AllegroConfiguration(IAllegroConfiguration other)
  {
    super(other);
    redacted_ = other.getRedacted();
  }
  
  private IAllegroConfiguration initRedacted()
  {
    AllegroConfiguration.Builder builder = null;
    
    if(getApiConnectionSettings() != null && getApiConnectionSettings() != getApiConnectionSettings().getRedacted())
    {
      builder = new AllegroConfiguration.Builder(this);
      
      builder.withApiConnectionSettings(getApiConnectionSettings().getRedacted());
    }
    
    if(getPodConnectionSettings() != null && getPodConnectionSettings() != getPodConnectionSettings().getRedacted())
    {
      if(builder == null)
        builder = new AllegroConfiguration.Builder(this);
      
      builder.withPodConnectionSettings(getPodConnectionSettings().getRedacted());
    }
    
    if(getKeyManagerConnectionSettings() != null && getKeyManagerConnectionSettings() != getKeyManagerConnectionSettings().getRedacted())
    {
      if(builder == null)
        builder = new AllegroConfiguration.Builder(this);
      
      builder.withKeyManagerConnectionSettings(getKeyManagerConnectionSettings().getRedacted());
    }
    
    if(getCertSessionAuthConnectionSettings() != null && getCertSessionAuthConnectionSettings() != getCertSessionAuthConnectionSettings().getRedacted())
    {
      if(builder == null)
        builder = new AllegroConfiguration.Builder(this);
      
      builder.withCertSessionAuthConnectionSettings(getCertSessionAuthConnectionSettings().getRedacted());
    }
    
    if(getCertKeyAuthConnectionSettings() != null && getCertKeyAuthConnectionSettings() != getCertKeyAuthConnectionSettings().getRedacted())
    {
      if(builder == null)
        builder = new AllegroConfiguration.Builder(this);
      
      builder.withCertKeyAuthConnectionSettings(getCertKeyAuthConnectionSettings().getRedacted());
    }
    
    if(getAuthCertFilePassword() != null && !ConnectionSettings.REDACTED.equals(getAuthCertFilePassword()))
    {
      if(builder == null)
        builder = new AllegroConfiguration.Builder(this);
      
      builder.withAuthCertFilePassword(ConnectionSettings.REDACTED);
    }
    
    if(getAuthCertPrivateKey() != null && !ConnectionSettings.REDACTED.equals(getAuthCertPrivateKey().asString()))
    {
      if(builder == null)
        builder = new AllegroConfiguration.Builder(this);
      
      builder.withAuthCertPrivateKey(ConnectionSettings.REDACTED);
    }
    
    if(builder == null)
      return this;
    
    return builder.build();
  }
  
  /**
   * Abstract builder for AllegroConfiguration. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractAllegroConfigurationBuilder<B extends AbstractAllegroConfigurationBuilder<B,T>, T extends IAllegroConfigurationEntity> extends AbstractAllegroConfigurationEntityBuilder<B,T>
  {
    protected AbstractAllegroConfigurationBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractAllegroConfigurationBuilder(Class<B> type, IAllegroConfigurationEntity initial)
    {
      super(type, initial);
    }
  }
  
  @Override
  public IAllegroConfiguration getRedacted()
  {
    return redacted_;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */