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
 *    Template name      proforma/java/TypeDef/_.java.ftl
 *    Template version     1.0
 *  At                  2019-02-05 11:16:49 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.core.canon.facade;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.apache.commons.codec.binary.Base64;

import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.models.core.canon.ThreadIdTypeDef;

/**
 * Facade for
 * Formatted as Base64 encoded bytes
 * Generated from TypeDef(ThreadId,type=String) at #/components/schemas/ThreadId
 */
@Immutable
public class ThreadId extends ThreadIdTypeDef
{
  private static Builder theBuilder = new Builder();
  
  private ThreadId(@Nonnull ImmutableByteArray value)
  {
    super(value);
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
  
  @Override
  public String toString()
  {
    return toBase64String();
  }

  /**
   * 
   * @return This thread ID as a Base64 string.
   */
  public String toBase64String()
  {
    return getValue().toBase64String();
  }
  
  /**
   * 
   * @return This threadId as a URL safe Base64 String.
   */
  public String toBase64UrlSafeString()
  {
    return getValue().toBase64UrlSafeString();
  }
  /**
   * Builder for ThreadId.
   */
  public static class Builder extends ThreadIdTypeDef.Builder
  {
    private Builder()
    {
    }
    
    /**
     * Convenience method to construct from byte array.
     * 
     * @param value thread ID as byte[]
     * @return A ThreadId for the given byte representation.
     * 
     * @throws NullPointerException If the value is null.
     */
    public ThreadId build(@Nonnull byte[] value)
    {
      return new ThreadId(ImmutableByteArray.newInstance(value));
    }
    
    @Override
    public ThreadId build(@Nonnull ImmutableByteArray value)
    {
      return new ThreadId(value);
    }
    
    @Override
    public ImmutableByteArray toValue(ThreadId instance)
    {
      return instance.getValue();
    }

    /**
     * Build a ThreadId from the given Base64 format string.
     * 
     * @param threadIdStr A thread ID value as a Base64 String.
     * 
     * @return A ThreadId representing the given value.
     */
    public ThreadId build(String threadIdStr)
    {
      return new ThreadId(ImmutableByteArray.newInstance(Base64.decodeBase64(threadIdStr)));
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */