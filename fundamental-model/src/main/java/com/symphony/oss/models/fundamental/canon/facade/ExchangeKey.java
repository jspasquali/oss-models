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
 *  At                  2018-05-15 16:35:29 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;
import javax.crypto.SecretKey;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.crypto.canon.facade.WrappedKey;
import com.symphony.oss.models.fundamental.canon.facade.IExchangeKey;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundmental.canon.CertificateId;
import com.symphony.oss.models.fundmental.canon.ExchangeKeyEntity;
import com.symphony.oss.models.fundmental.canon.ExchangeKeyIdObject;

/**
 * Facade for Object ObjectSchema(ExchangeKey)
 * Generated from ObjectSchema(ExchangeKey) at #/components/schemas/ExchangeKey
 */
@Immutable
public class ExchangeKey extends ExchangeKeyEntity implements IExchangeKey
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ExchangeKey(AbstractExchangeKeyBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ExchangeKey(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ExchangeKey(IExchangeKey other)
  {
    super(other);
  }

  @Override
  public WrappedKey wrap(SecretKey key)
  {
    return getCipherSuite().wrap(key, getPublicKey());
  }

  /**
   * Return the ID object for the given user's ExchangeKey for the given certificate ID.
   * 
   * @param userId The external pod and user ID.
   * @param certId The certificate ID.
   * 
   * @return The ID object.
   */
  public static IFundamentalId getKeyId(PodAndUserId userId, CertificateId certId)
  {
    return new ExchangeKeyIdObject.Builder()
        .withPodId(userId.getPodId())
        .withUserId(userId.getUserId())
        .withCertId(certId)
        .build();
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */