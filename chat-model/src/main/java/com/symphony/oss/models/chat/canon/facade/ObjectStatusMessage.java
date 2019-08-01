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
 *  At                  2018-06-28 08:05:07 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.chat.canon.facade;

import java.io.IOException;
import java.io.StringReader;
import java.util.Base64;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.IJsonDomNode;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.dom.json.jackson.JacksonAdaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.symphony.protobuf.application.socialmessage.proto.SocialMessageProtos;
import com.symphony.oss.models.chat.canon.ObjectStatusMessageEntity;
import com.symphony.oss.models.chat.canon.ResponsePayloadEntity;

/**
 * Facade for Object ObjectSchema(ObjectStatusMessage)
 *
 * A notification of the change in ingestion status of a submitted message.
 * Generated from ObjectSchema(ObjectStatusMessage) at #/components/schemas/ObjectStatusMessage
 */
@Immutable
public class ObjectStatusMessage extends ObjectStatusMessageEntity implements IObjectStatusMessage
{
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ObjectStatusMessage(AbstractObjectStatusMessageBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ObjectStatusMessage(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ObjectStatusMessage(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(unwrapProto(mutableJsonObject), modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ObjectStatusMessage(IObjectStatusMessage other)
  {
    super(other);
  }
  
  private static MutableJsonObject unwrapProto(MutableJsonObject mutableJsonObject)
  {
    IJsonDomNode protobufNode = mutableJsonObject.get("protobuf");
    
    if(protobufNode != null)
    {
      /* This is a message in the internal SBE format with a protobuf payload */
      try
      {
        SocialMessageProtos.ObjectStatusMessage statusProto = SocialMessageProtos.ObjectStatusMessage.parseFrom(
            Base64.getDecoder().decode(protobufNode.toString()));
        
        mutableJsonObject.addIfNotNull("version", "OBJECTSTATUS");
        mutableJsonObject.addIfNotNull("messageId", statusProto.getMessageId());
        mutableJsonObject.addIfNotNull("threadId", statusProto.getThreadId());
        mutableJsonObject.addIfNotNull("ingestionDate", statusProto.getIngestionDate());
        mutableJsonObject.addIfNotNull("objectHash", statusProto.getObjectHash());
        mutableJsonObject.addIfNotNull("status", statusProto.getStatus().toString());
        mutableJsonObject.addIfNotNull("responseStatus", statusProto.getResponseStatus());
        
        String payload = statusProto.getResponsePayload();
        
        if(payload != null && payload.trim().length() > 0)
        {
          try(StringReader in = new StringReader(payload))
          {
            mutableJsonObject.add("responsePayload", ResponsePayloadEntity.setType((MutableJsonObject) JacksonAdaptor.adapt(OBJECT_MAPPER.readTree(in))));
          }
        }
      }
      catch (IOException | ClassCastException e)
      {
        throw new IllegalArgumentException(e);
      }
    }
    
    return mutableJsonObject;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */