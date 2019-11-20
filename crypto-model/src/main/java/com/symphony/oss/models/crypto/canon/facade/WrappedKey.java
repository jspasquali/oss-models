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
 *		Template name		   proforma/java/TypeDef/_.java.ftl
 *		Template version	   1.0
 *  At                  2018-05-14 07:57:26 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.crypto.canon.facade;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.immutable.ImmutableByteArray;

import com.symphony.oss.models.crypto.canon.WrappedKeyTypeDef;

/**
 * Facade for
 * A wrapped (encrypted) key encoded as Base64
 * Formatted as Base64 encoded bytes
 * Generated from TypeDef(WrappedKey,type=String) at #/components/schemas/WrappedKey
 */
@Immutable
public class WrappedKey extends WrappedKeyTypeDef
{
  private static Builder theBuilder = new Builder();
  
  private WrappedKey(@Nonnull ImmutableByteArray value)
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
  
  /**
   * Builder for WrappedKey.
   */
  public static class Builder extends WrappedKeyTypeDef.Builder
  {
    private Builder()
    {
    }
    
    @Override
    public WrappedKey build(@Nonnull ImmutableByteArray value)
    {
      if(value == null)
        throw new NullPointerException("value is required.");
        
      return new WrappedKey(value);
    }
    
    @Override
    public ImmutableByteArray toValue(WrappedKey instance)
    {
      return instance.getValue();
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */