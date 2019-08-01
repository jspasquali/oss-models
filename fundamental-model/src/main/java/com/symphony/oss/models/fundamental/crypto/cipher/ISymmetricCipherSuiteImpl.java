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

package com.symphony.oss.models.fundamental.crypto.cipher;

import java.security.PrivateKey;

import javax.crypto.SecretKey;

import com.symphony.oss.models.fundamental.canon.facade.WrappedKey;

/**
 * Implementation of a symmetric key cipher, these methods are not inherited by ICipherSuite.
 * 
 * @author bruce.skingle
 *
 */
public interface ISymmetricCipherSuiteImpl extends ISymmetricCipherSuite
{
  /**
   * Unwrap the given encrypted PrivateKey
   * 
   * @param wrappedKey        Encrypted key to be unwrapped.
   * @param userKey         Wrapping key.
   * @param cipherSuite       Wrapping cipherSuite
   * @return  The SecretKey.
   * 
   * @throws NullPointerException if any of the arguments are null.
   * @throws IllegalArgumentException if the key is invalid and encryption fails.
   */
  PrivateKey unwrap(WrappedKey wrappedKey, SecretKey userKey, IPublicKeyCipherSuite cipherSuite);
}
