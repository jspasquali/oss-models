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
 *  At                  2019-11-07 08:33:44 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.core.canon.facade;

import javax.annotation.concurrent.Immutable;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import com.symphony.oss.models.core.canon.EnvelopeEntity;
import com.symphony.oss.models.core.canon.IEnvelopeEntity;
import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.fugue.pubsub.IPubSubMessage;
import com.symphony.oss.models.core.canon.CoreModel;

/**
 * Facade for Object ObjectSchema(Envelope)
 *
 * This is not a persisted object it exists only on a pub sub topic.
 * Generated from ObjectSchema(Envelope) at #/components/schemas/Envelope
 */
@Immutable
@SuppressWarnings("unused")
public class Envelope extends EnvelopeEntity implements IEnvelope
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Envelope(AbstractEnvelopeBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Envelope(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Envelope(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Envelope(IEnvelope other)
  {
    super(other);
  }
  
  @Override
  public Map<String, Object> getAttributes()
  {
    Map<String, Object> attributes = new HashMap<>();
    
    if(getPodId() != null)
      attributes.put(IPubSubMessage.POD_ID_ATTRIBUTE,     getPodId());
    
    attributes.put(IPubSubMessage.PAYLOAD_TYPE_ATTRIBUTE, getPayloadType());
    //attributes.put(IPubSubMessage.FINAL_ATTRIBUTE,        getFinal().toString());
    
    return attributes;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */