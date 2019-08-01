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
 *		Template name		   proforma/java/TypeDef/_.java.ftl
 *		Template version	   1.0
 *  At                  2019-02-05 11:10:58 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.chat.canon.facade;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.immutable.ImmutableByteArray;

import com.symphony.oss.models.chat.canon.MessageIdTypeDef;

/**
 * Facade for
 * Formatted as Base64 encoded bytes
 * Generated from TypeDef(MessageId,type=String) at #/components/schemas/MessageId
 */
@Immutable
public class MessageId extends MessageIdTypeDef
{
  private static Builder theBuilder = new Builder();
  
  private MessageId(@Nonnull ImmutableByteArray value)
  {
    super(value);
  }
  
  @Override
  public String toString()
  {
    return toBase64String();
  }
  
  /**
   * 
   * @return The value of this messageId as a Base64 string.
   */
  public String toBase64String()
  {
    return getValue().toBase64String();
  }
  
  /**
   * 
   * @return The value of this messageId as a URL safe Base64 string.
   */
  public String toBase64UrlSafeString()
  {
    return getValue().toBase64UrlSafeString();
  }
  
  /**
   * Return a new Builder.
   *
   * The generated version of this builder is stateless and so it is safe to return a 
   * reference to a shared instance, if you add functionality to this builder which is
   * not thread safe then you need to change this to return new Builder();
   *
   * @return A new Builder.
   */
  public static Builder newBuilder()
  {
    return theBuilder;
  }
  
  /**
   * Builder for MessageId.
   */
  public static class Builder extends MessageIdTypeDef.Builder
  {
    private Builder()
    {
    }
    
    /**
     * Convenience method to construct from byte array.
     * 
     * @param value message ID as byte[]
     * @return A MessageId for the given byte representation.
     * 
     * @throws NullPointerException If the value is null.
     */
    public MessageId build(@Nonnull byte[] value)
    {
      return new MessageId(ImmutableByteArray.newInstance(value));
    }
    
    @Override
    public MessageId build(@Nonnull ImmutableByteArray value)
    {
      return new MessageId(value);
    }
    
    @Override
    public ImmutableByteArray toValue(MessageId instance)
    {
      return instance.getValue();
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */