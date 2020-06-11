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
 *  At                  2020-06-05 08:59:16 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.s2.authc.canon.facade;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.fault.CodingFault;
import com.symphony.s2.authc.canon.JwkEntity;

/**
 * Facade for Object ObjectSchema(Jwk)
 *
 * A Json Web Key.
 * Generated from ObjectSchema(Jwk) at #/components/schemas/Jwk
 */
@Immutable
public class Jwk extends JwkEntity implements IJwk
{
  private static final Logger log_ = LoggerFactory.getLogger(Jwk.class);
  
  private final PublicKey publicKey_;
  private final KeyId     keyId_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Jwk(AbstractJwkBuilder<?,?> builder)
  {
    super(builder);
    publicKey_ = initPublicKey();
    keyId_ = initKeyId();
  }
  
  private KeyId initKeyId()
  {
    //return KeyId.newBuilder().build(publicKey_);
    
    return KeyId.newBuilder().build(getKid());
  }

  private PublicKey initPublicKey()
  {
    if(getE() == null || getN() == null)
      return null;
    
    byte[] publicExponentBytes = Base64.decodeBase64(getE());
    byte[] modulusBytes = Base64.decodeBase64(getN());
    
    BigInteger publicExponent = new BigInteger(1, publicExponentBytes);
    BigInteger modulus = new BigInteger(1, modulusBytes);
    
    RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent );
    
    try
    {
      KeyFactory factory = KeyFactory.getInstance("RSA");
      PublicKey publicKey = factory.generatePublic(spec);
      
      return publicKey;
    }
    catch(NoSuchAlgorithmException e)
    {
      throw new CodingFault(e);
    }
    catch (InvalidKeySpecException e)
    {
      log_.error("Invalid RSA key", e);
    }
    
    return null;
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Jwk(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    publicKey_ = initPublicKey();
    keyId_ = initKeyId();
    
//    System.err.println("given keyId " + KeyId.newBuilder().build(getKid()));
//    System.err.println("computed keyId " + KeyId.newBuilder().build(publicKey_));
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Jwk(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    publicKey_ = initPublicKey();
    keyId_ = initKeyId();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Jwk(IJwk other)
  {
    super(other);
    publicKey_ = other.getPublicKey();
    keyId_ = initKeyId();
  }

  
  @Override
  public PublicKey getPublicKey()
  {
    return publicKey_;
  }

  @Override
  public KeyId getKeyId()
  {
    return keyId_;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */