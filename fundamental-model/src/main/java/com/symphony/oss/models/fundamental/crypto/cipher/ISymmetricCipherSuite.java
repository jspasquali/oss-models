/*
 * Copyright 2016-2018  Symphony Communication Services, LLC.
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

import org.symphonyoss.s2.common.immutable.ImmutableByteArray;

import com.symphony.oss.models.fundamental.canon.facade.WrappedKey;
import com.symphony.oss.models.fundmental.canon.Base64SecretKey;
import com.symphony.oss.models.fundmental.canon.EncryptedData;

/**
 * An implementation of a symmetric (secret key) encryption algorithm.
 * 
 * @author Bruce Skingle
 *
 */
public interface ISymmetricCipherSuite
{
  /**
   * Return the name of the KeyAlgorithm used by this CipherSuite.
   * @return The name of the KeyAlgorithm used by this CipherSuite.
   */
  String getSymmetricKeyAlgorithm();

  /**
   * Return the size of the keys used by this CipherSuite, in bits.
   * 
   * @return The size of the keys used by this CipherSuite, in bits.
   */
  int getSymmetricKeySize();
  

  
  /**
   * Generate a new secret key.
   * 
   * @return a new secret key.
   */
	SecretKey generateKey();

	/**
	 * Encrypt the given binary data with the given key and return a Base64 
	 * encoded String containing the encrypted form.
	 * 
	 * @param secretKey	Encryption key
	 * @param data		Plain text.
	 * @return			  ciphertext
	 * 
	 * @throws NullPointerException if any of the arguments are null.
	 * @throws IllegalArgumentException if the key is invalid and encryption fails.
	 */
	EncryptedData encrypt(SecretKey secretKey, ImmutableByteArray data);

	/**
	 * Decrypt the given Base64 encoded cipher test using the given key.
	 * 
	 * @param key			Decryption key.
	 * @param cipherText	ciphertext.
	 * @return				Plaintext.
	 * 
	 * @throws NullPointerException if any of the arguments are null.
   * @throws IllegalArgumentException if the key is invalid and encryption fails.
	 */
	ImmutableByteArray decrypt(SecretKey key, EncryptedData cipherText);

	
	/**
	 * Wrap the given asymmetric key by encrypting with the given user's secret key.
	 * 
	 * @param key		Key to be wrapped.
	 * @param userKey	Secret key of user for whom it is wrapped.
	 * 
	 * @return	ciphertext of wrapped key.
	 * @throws NullPointerException if any of the arguments are null.
   * @throws IllegalArgumentException if the key is invalid and encryption fails.
	 */
	WrappedKey wrap(PrivateKey key, SecretKey userKey);

	/**
	 * Return the size of the given key in bits.
	 * 
	 * @param key A secret key.
	 * 
	 * @return the size of the given key in bits.
	 * 
	 * @throws NullPointerException if the input is null
	 * @throws IllegalArgumentException if the given key is invalid
	 */
  int getKeySize(SecretKey key);
  
  /**
   * Return the given key in base64.
   * 
   * @param key A key.
   * @return The Base64 format of the given key.
   * 
   * @throws NullPointerException      If the given key is null.
   * @throws IllegalArgumentException  If the given key is invalid.
   */
  Base64SecretKey secretKeyToBase64(SecretKey key);
  
  /**
   * Decode the given Base64 format key.
   * 
   * @param encodedKey A Base64 encoded key.
   * @return The unencoded key.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  SecretKey    secretKeyFromBase64(Base64SecretKey encodedKey);

}
