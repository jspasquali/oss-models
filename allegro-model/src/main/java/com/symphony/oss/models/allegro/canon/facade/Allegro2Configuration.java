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
 *  At                  2021-02-03 10:52:53 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.allegro.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.models.allegro.canon.Allegro2ConfigurationEntity;

/**
 * Facade for Object ObjectSchema(Allegro2Configuration)
 * Generated from ObjectSchema(Allegro2Configuration) at #/components/schemas/Allegro2Configuration
 */
@Immutable
public class Allegro2Configuration extends Allegro2ConfigurationEntity implements IAllegro2Configuration
{
  private ImmutableJsonObject redacted_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Allegro2Configuration(AbstractAllegro2ConfigurationBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Allegro2Configuration(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Allegro2Configuration(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Allegro2Configuration(IAllegro2Configuration other)
  {
    super(other);
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
    if(getDefaultConnectionSettings() != null)
    {
      jsonObject.addIfNotNull("defaultConnectionSettings", getDefaultConnectionSettings().getRedacted());
    }
    
    if(getPodConnectionSettings() != null)
    {
      jsonObject.addIfNotNull("podConnectionSettings", getPodConnectionSettings().getRedacted());
    }
    
    if(getKeyManagerConnectionSettings() != null)
    {
      jsonObject.addIfNotNull("keyManagerConnectionSettings", getKeyManagerConnectionSettings().getRedacted());
    }
    
    if(getCertSessionAuthConnectionSettings() != null)
    {
      jsonObject.addIfNotNull("certSessionAuthConnectionSettings", getCertSessionAuthConnectionSettings().getRedacted());
    }
    
    if(getCertKeyAuthConnectionSettings() != null)
    {
      jsonObject.addIfNotNull("certKeyAuthConnectionSettings", getCertKeyAuthConnectionSettings().getRedacted());
    }
    
    if(getAuthCertFilePassword() != null)
    {
      jsonObject.addIfNotNull("authCertFilePassword", ConnectionSettings.REDACTED);
    }
    
    if(getAuthCertPrivateKey() != null)
    {
      jsonObject.addIfNotNull("authCertPrivateKey", ConnectionSettings.REDACTED);
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