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
 *  At                  2019-02-09 06:47:46 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.internal.km.canon.facade;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import com.symphony.oss.models.crypto.cipher.IPublicKeyCipherSuite;
import com.symphony.oss.models.internal.km.canon.UserKeysEntity;

/**
 * Facade for Object ObjectSchema(UserKeys)
 * Generated from ObjectSchema(UserKeys) at #/components/schemas/UserKeys
 */
@Immutable
public class UserKeys extends UserKeysEntity implements IUserKeys
{
  private final KeyPair    keyPair_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public UserKeys(AbstractUserKeysBuilder<?,?> builder)
  {
    super(builder);

    keyPair_ = createKeyPair();
  }
  
  private KeyPair createKeyPair()
  {
    IPublicKeyCipherSuite cipherSuite = getCipherSuite();
    
    PublicKey publicKey = cipherSuite.publicKeyFromPem(getPublicKey());
    PrivateKey privateKey = cipherSuite.privateKeyFromPem(getPrivateKey());
    return new KeyPair(publicKey, privateKey);
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public UserKeys(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);

    keyPair_ = createKeyPair();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public UserKeys(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);

    keyPair_ = createKeyPair();
  }

  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public UserKeys(IUserKeys other)
  {
    super(other);
    
    keyPair_ = other.getKeyPair();
  }

  @Override
  public KeyPair getKeyPair()
  {
    return keyPair_;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */