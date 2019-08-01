/*
 * Copyright 2016-2017  Symphony Communication Services, LLC.
 * 
 * Includes public domain material developed by Immutify Limited.
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
import com.symphony.oss.models.fundmental.canon.CipherSuiteId;

/**
 * The super-interface of symmetric and public key CipherSuites.
 * 
 * @author Bruce Skingle
 *
 */
public interface ICipherSuite extends ISymmetricCipherSuite, IPublicKeyCipherSuite
{
  /** The CipherSuiteId for SBE crypto. */
  public static final CipherSuiteId       ThreadSeurityContextCipherSuiteId = CipherSuiteId.RSA2048_AES256;
  
  /**
   * Get the unique ID for this cipher suite.
   * 
   * @return The unique ID of this cipher suite.
   * 
   */
  CipherSuiteId  getId();
  
  /**
   * Unwrap the given encrypted SecretKey
   * 
   * @param wrappedKey          Encrypted key to be unwrapped.
   * @param userPrivateKey      Wrapping key.
   * @return  The SecretKey.
   * 
   * @throws IllegalArgumentException
   */
  SecretKey unwrap(WrappedKey wrappedKey, PrivateKey userPrivateKey);
  
  /**
   * Unwrap the given encrypted PrivateKey
   * 
   * @param wrappedKey        Encrypted key to be unwrapped.
   * @param userKey         Wrapping key.
   * @return  The SecretKey.
   * 
   * @throws NullPointerException if any of the arguments are null.
   * @throws IllegalArgumentException if the key is invalid and encryption fails.
   */
  PrivateKey unwrap(WrappedKey wrappedKey, SecretKey userKey);
}
