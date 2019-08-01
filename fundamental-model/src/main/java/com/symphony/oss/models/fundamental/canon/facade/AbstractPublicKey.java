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
 *  At                  2018-05-15 18:25:31 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.security.PublicKey;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;

import com.symphony.oss.models.fundamental.canon.facade.IAbstractPublicKey;
import com.symphony.oss.models.fundamental.crypto.cipher.CipherSuite;
import com.symphony.oss.models.fundamental.crypto.cipher.ICipherSuite;
import com.symphony.oss.models.fundmental.canon.AbstractPublicKeyEntity;

/**
 * Facade for Object ObjectSchema(AbstractPublicKey)
 * Generated from ObjectSchema(AbstractPublicKey) at #/components/schemas/AbstractPublicKey
 */
@Immutable
public class AbstractPublicKey extends AbstractPublicKeyEntity implements IAbstractPublicKey
{
  private final ICipherSuite cipherSuite_;
  private final PublicKey    publicKey_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public AbstractPublicKey(AbstractAbstractPublicKeyBuilder<?,?> builder)
  {
    super(builder);
    
    cipherSuite_ = CipherSuite.get(getCipherSuiteId());
    publicKey_ = cipherSuite_.publicKeyFromPem(getEncodedPublicKey());
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AbstractPublicKey(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    cipherSuite_ = CipherSuite.get(getCipherSuiteId());
    publicKey_ = cipherSuite_.publicKeyFromPem(getEncodedPublicKey());
  }
  
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public AbstractPublicKey(IAbstractPublicKey other)
  {
    super(other);
    
    cipherSuite_ = other.getCipherSuite();
    publicKey_ = other.getPublicKey();
  }
  
  @Override
  public ICipherSuite getCipherSuite()
  {
    return cipherSuite_;
  }

  @Override
  public PublicKey getPublicKey()
  {
    return publicKey_;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */