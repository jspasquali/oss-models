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
 *  At                  2019-05-20 08:14:54 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.chat.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.chat.canon.IThreadIdObject;
import com.symphony.oss.models.chat.canon.StreamEntity;
import com.symphony.oss.models.fundmental.canon.ContentIdObject;
import com.symphony.oss.models.fundmental.canon.ContentIdType;

/**
 * Facade for Object ObjectSchema(Stream)
 *
 * Stream object
 * Generated from ObjectSchema(Stream) at #/components/schemas/Stream
 */
@Immutable
public class Stream extends StreamEntity implements IStream
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Stream(AbstractStreamBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Stream(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Stream(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Stream(IStream other)
  {
    super(other);
  }

  /**
   * Return the ID object for the sequence of messages belonging to the given thread (stream).
   * 
   * @param threadId The ID of the stream.
   * 
   * @return The ID object for the sequence of messages belonging to the given thread (stream).
   */
  public static IFundamentalId getStreamContentSequenceId(IThreadIdObject threadId)
  {
    return getStreamSequenceId(threadId, LiveCurrentMessage.TYPE_ID);
  }

  /**
   * Return the ID object for the sequence of child streams whose parent is this stream.
   * 
   * @param threadId The ID of the stream.
   * 
   * @return The ID object for the sequence of messages belonging to the given thread (stream).
   */
  public static IFundamentalId getChildStreamSequenceId(IThreadIdObject threadId)
  {
    return getStreamSequenceId(threadId, Stream.TYPE_ID);
  }

  private static IFundamentalId getStreamSequenceId(IThreadIdObject threadId, String contentType)
  {
    return new ContentIdObject.Builder()
        .withSubjectHash(threadId.getAbsoluteHash())
        .withSubjectType(Stream.TYPE_ID)
        .withContentType(contentType)
        .withIdType(ContentIdType.CURRENT_SEQUENCE)
        .build();
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */