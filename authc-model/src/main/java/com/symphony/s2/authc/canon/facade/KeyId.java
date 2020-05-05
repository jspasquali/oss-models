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
 *  At                  2019-11-11 12:35:22 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.s2.authc.canon.facade;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.apache.commons.codec.binary.Base64;

import com.symphony.oss.commons.fault.CodingFault;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.s2.authc.canon.KeyIdTypeDef;

/**
 * Facade for
 * A key identifier (SHA-1 hash), encoded as Base64.
 * Formatted as Base64 encoded bytes
 * Generated from TypeDef(KeyId,type=String) at #/components/schemas/KeyId
 */
@Immutable
public class KeyId extends KeyIdTypeDef
{
  private static Builder theBuilder = new Builder();
  
  private KeyId(@Nonnull ImmutableByteArray value)
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
    return getValue().toBase64String();
  }
  
  /**
   * Builder for KeyId.
   */
  public static class Builder extends KeyIdTypeDef.Builder
  {
    private Builder()
    {
    }
    
    @Override
    public KeyId build(@Nonnull ImmutableByteArray value)
    {
      if(value == null)
        throw new NullPointerException("value is required.");
      
      if(value.length() != 20)
        throw new NullPointerException("A 160 bit SHA1 hash value is required.");
      
      return new KeyId(value);
    }
    
    /**
     * Construct from a base64 encoded string.
     * 
     * @param value A BAse64 encoded SHA-1 hash value.
     * 
     * @return A KeyId made from the given value.
     */
    public KeyId build(@Nonnull String value)
    {
      if(value == null)
        throw new NullPointerException("value is required.");
      
      byte[] bytes = Base64.decodeBase64(value);
      
      if(bytes.length != 20)
        throw new NullPointerException("A 160 bit SHA1 hash value is required.");
      
      return new KeyId(ImmutableByteArray.newInstance(bytes));
    }
    
    /**
     * Construct from a base64 encoded string.
     * 
     * @param key A PublicKey whose ID is to be returned.
     * 
     * @return A KeyId made from the given value.
     */
    public KeyId build(@Nonnull PublicKey key)
    {
      if(key == null)
        throw new NullPointerException("key is required.");
      
      try
      {
        byte[] bytes = MessageDigest.getInstance("SHA-1").digest(key.getEncoded());
        
        return new KeyId(ImmutableByteArray.newInstance(bytes));
      }
      catch(NoSuchAlgorithmException e)
      {
        throw new CodingFault(e);
      }
    }
    
    @Override
    public ImmutableByteArray toValue(KeyId instance)
    {
      return instance.getValue();
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */