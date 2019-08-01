/*
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
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.security.PrivateKey;

import com.symphony.oss.models.fundamental.canon.facade.IAbstractPublicKey;
import com.symphony.oss.models.fundmental.canon.PemPrivateKey;

/**
 * An open public key, i.e. an object containing the matching private key for the public key contained
 * in the super-class.
 * 
 * @author Bruce Skingle
 *
 */
public interface IOpenPublicKey extends IAbstractPublicKey
{
  /**
   * Return the private key for this public key.
   * 
   * @return The private key for this public key.
   */
  PrivateKey getPrivateKey();

  /**
   * 
   * @return The private key in PEM format.
   */
  PemPrivateKey getEncodedPrivateKey();
}
