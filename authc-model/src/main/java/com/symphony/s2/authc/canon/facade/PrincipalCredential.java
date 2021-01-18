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
 *  At                  2019-11-20 10:52:22 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.s2.authc.canon.facade;

import java.security.PrivateKey;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.fugue.kv.IKvPartitionKey;
import com.symphony.oss.fugue.kv.IKvSortKey;
import com.symphony.oss.fugue.kv.KvSortKey;
import com.symphony.oss.models.crypto.canon.PemPrivateKey;
import com.symphony.oss.models.crypto.cipher.CipherSuite;
import com.symphony.s2.authc.canon.IPrincipalCredentialEntity;
import com.symphony.s2.authc.canon.PrincipalCredentialEntity;

/**
 * Facade for Object ObjectSchema(Credential)
 *
 * A secret credential.
 * Generated from ObjectSchema(Credential) at #/components/schemas/Credential
 */
@Immutable
public class PrincipalCredential extends PrincipalCredentialEntity implements IPrincipalCredential
{
  static final String REDACTED = "**REDACTED**";
  static final String ENCODED_PRIVATE_KEY = "encodedPrivateKey";
  
  private final PrivateKey           privateKey_;
  private ImmutableJsonObject redacted_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public PrincipalCredential(AbstractPrincipalCredentialBuilder<?,?> builder)
  {
    super(builder);
    
    privateKey_ = CipherSuite.getDefault().privateKeyFromPem(getEncodedPrivateKey());
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PrincipalCredential(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    privateKey_ = CipherSuite.getDefault().privateKeyFromPem(getEncodedPrivateKey());
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PrincipalCredential(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    privateKey_ = CipherSuite.getDefault().privateKeyFromPem(getEncodedPrivateKey());
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public PrincipalCredential(IPrincipalCredential other)
  {
    super(other);

    privateKey_ = other.getPrivateKey();
  }

  @Override
  public PrivateKey getPrivateKey()
  {
    return privateKey_;
  }

  @Override
  public IKvSortKey getSortKey()
  {
    return new KvSortKey(getKeyId().toString());
  }

  @Override
  public IKvPartitionKey getPartitionKey()
  {
    if(getUserId() == null)
      return PrincipalAuthcKey.getPartitionKeyFor(getPodId());
    
    return PrincipalAuthcKey.getPartitionKeyFor(getUserId());
  }
  
  @Override
  public synchronized ImmutableJsonObject getRedacted()
  {
    if(redacted_ == null)
    {
      MutableJsonObject jsonObject = getJsonObject().mutify();
      
      redactJsonObject(jsonObject);
      
      redacted_ = jsonObject.immutify();
    }
    
    return redacted_;
  }

  protected void redactJsonObject(MutableJsonObject jsonObject)
  {
    if(getEncodedPrivateKey() != null)
    {
        jsonObject.addIfNotNull(ENCODED_PRIVATE_KEY, REDACTED);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */