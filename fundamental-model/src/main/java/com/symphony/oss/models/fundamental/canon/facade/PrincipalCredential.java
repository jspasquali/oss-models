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
 *  At                  2018-05-16 17:35:26 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.security.PrivateKey;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.canon.runtime.ModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;

import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseReadOnly;
import com.symphony.oss.models.fundamental.canon.facade.IExchangeKey;
import com.symphony.oss.models.fundamental.canon.facade.IPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.ISigningKey;
import com.symphony.oss.models.fundamental.canon.facade.OpenExchangeKey;
import com.symphony.oss.models.fundamental.canon.facade.OpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.OpenSigningKey;
import com.symphony.oss.models.fundmental.canon.PrincipalCredentialEntity;

/**
 * Facade for Object ObjectSchema(PrincipalCredential)
 *
 * A secret credential which a system principal needs to bootstrap.
 * Generated from ObjectSchema(PrincipalCredential) at #/components/schemas/PrincipalCredential
 */
@Immutable
public class PrincipalCredential extends PrincipalCredentialEntity implements IPrincipalCredential
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public PrincipalCredential(AbstractPrincipalCredentialBuilder<?,?> builder)
  {
    super(builder);
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
  }
  
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public PrincipalCredential(IPrincipalCredential other)
  {
    super(other);
  }

  public OpenPrincipalCredential  open(IFundamentalDatabaseReadOnly database, ModelRegistry modelRegistry) throws NoSuchObjectException
  {
    IExchangeKey  exchangeKey         = database.fetchAbsolute(getExchangeKeyHash(), IExchangeKey.class);
    PrivateKey    exchangePrivateKey  = exchangeKey.getCipherSuite().privateKeyFromPem(getEncodedExchangeKey());
    
    ISigningKey   signingKey          = database.fetchAbsolute(getSigningKeyHash(), ISigningKey.class);
    PrivateKey    signingPrivateKey   = signingKey.getCipherSuite().privateKeyFromPem(getEncodedSigningKey());

    return new OpenPrincipalCredential(this,
        OpenExchangeKey.deserialize(exchangeKey, exchangePrivateKey),
        OpenSigningKey.deserialize(signingKey, signingPrivateKey),
        database, modelRegistry);
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */