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
 *  At                  2019-04-27 11:09:39 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.system.canon.facade;

import java.security.PrivateKey;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import com.symphony.oss.models.fundamental.crypto.cipher.CipherSuite;
import com.symphony.oss.models.system.canon.AuthenticationCredentialEntity;

/**
 * Facade for Object ObjectSchema(AuthenticationCredential)
 *
 * A secret credential which can be used to authenticate to a Pod public API.
 * Generated from ObjectSchema(AuthenticationCredential) at #/components/schemas/AuthenticationCredential
 */
@Immutable
public class AuthenticationCredential extends AuthenticationCredentialEntity implements IAuthenticationCredential
{
  private PrivateKey privateKey_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public AuthenticationCredential(AbstractAuthenticationCredentialBuilder<?,?> builder)
  {
    super(builder);
    privateKey_ = initPrivateKey();
  }
  
  private PrivateKey initPrivateKey()
  {
    return CipherSuite.get(getCipherSuiteId()).privateKeyFromPem(getEncodedPrivateKey());
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AuthenticationCredential(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    privateKey_ = initPrivateKey();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public AuthenticationCredential(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    privateKey_ = initPrivateKey();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public AuthenticationCredential(IAuthenticationCredential other)
  {
    super(other);
    privateKey_ = other.getPrivateKey();
  }

  @Override
  public PrivateKey getPrivateKey()
  {
    return privateKey_;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */