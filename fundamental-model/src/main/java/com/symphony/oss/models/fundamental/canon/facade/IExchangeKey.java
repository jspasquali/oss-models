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
 *		Template name		   proforma/java/Object/I_.java.ftl
 *		Template version	   1.0
 *  At                  2018-05-15 16:35:29 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;
import javax.crypto.SecretKey;

import com.symphony.oss.models.crypto.canon.facade.WrappedKey;
import com.symphony.oss.models.fundamental.canon.facade.IAbstractPublicKey;
import com.symphony.oss.models.fundmental.canon.IExchangeKeyEntity;

/**
 * A key exchange key, which can be used to wrap secret keys to make them available to the owner of
 * the current key.
 * 
 * Facade for Object ObjectSchema(ExchangeKey)
 * Generated from ObjectSchema(ExchangeKey) at #/components/schemas/ExchangeKey
 */
@Immutable
public interface IExchangeKey
  extends IAbstractPublicKey, IExchangeKeyEntity
{
  /**
   * Wrap the given secret key.
   * 
   * @param key A secret key.
   * 
   * @return The given key wrapped (encrypted) in this public key.
   */
  WrappedKey wrap(SecretKey key);
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */