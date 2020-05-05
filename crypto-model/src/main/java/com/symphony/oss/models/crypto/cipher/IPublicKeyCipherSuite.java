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

package com.symphony.oss.models.crypto.cipher;

import java.io.Reader;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import com.symphony.oss.models.crypto.canon.facade.WrappedKey;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.models.crypto.canon.EncodedSignature;
import com.symphony.oss.models.crypto.canon.PemCertificate;
import com.symphony.oss.models.crypto.canon.PemPrivateKey;
import com.symphony.oss.models.crypto.canon.PemPublicKey;

/**
 * A public key CipherSuite.
 * 
 * @author Bruce Skingle
 *
 */
public interface IPublicKeyCipherSuite
{
  /**
   * Return the name of the KeyAlgorithm used by this CipherSuite.
   * @return The name of the KeyAlgorithm used by this CipherSuite.
   */
  String getPublicKeyAlgorithm();

  /**
   * Return a new instance of the Cipher object implementing this algorithm.
   * 
   * @return a new instance of the Cipher object implementing this algorithm.
   */
  Cipher getPublicKeyEncryptCipher();

  /**
   * Return the size of the keys used by this CipherSuite, in bits.
   * 
   * @return The size of the keys used by this CipherSuite, in bits.
   */
  int getPublicKeySize();
  
  /**
   * Validate the given KeyPair and throw an exception if it is not suitable for
   * use with this CipherSuite.
   * 
   * @param keyPair A KeyPair to validate.
   * @throws InvalidKeyException If the given KeyPair is incompatible with the
   * current CipherSuite.
   */
  void validateKey(KeyPair keyPair) throws InvalidKeyException;
  
  /**
   * Validate the given key and throw an exception if it is not suitable for
   * use with this CipherSuite.
   * 
   * @param key A key to validate.
   * @throws InvalidKeyException If the given KeyPair is incompatible with the
   * current CipherSuite.
   */
  void validateKey(PrivateKey key) throws InvalidKeyException;
  
  /**
   * Validate the given key and throw an exception if it is not suitable for
   * use with this CipherSuite.
   * 
   * @param key A key to validate.
   * @throws InvalidKeyException If the given KeyPair is incompatible with the
   * current CipherSuite.
   */
  void validateKey(PublicKey key) throws InvalidKeyException;
  
  /**
   * Return the name of the SignatureAlgorithm used by this CipherSuite.
   * @return The name of the SignatureAlgorithm used by this CipherSuite.
   */
	String getPublicKeySignatureAlgorithm();
	
	/**
	 * Generate a new key pair.
	 * 
	 * @return a new key pair.
	 */
	KeyPair generateKeyPair();
	
	/**
   * Sign the given data.
   * 
   * @param data
   *          Data to be signed
   * @param privateKey
   *          Signing key.
   * @return Encoded bytes of signature
	 * @throws IllegalArgumentException  If the given keys are incompatible with this CipherSuite. 
   */
	EncodedSignature	sign(ImmutableByteArray data, PrivateKey privateKey);
	
	/**
	 * Verify a signature.
	 * 
	 * @param encodedSignature The signature
	 * @param data             The signed data
	 * @param publicKey        The key with which the signature was created.
	 * 
	 * @throws SignatureVerificationException if it is not valid.
	 */
	void verifySignature(EncodedSignature encodedSignature, ImmutableByteArray data, PublicKey publicKey) throws SignatureVerificationException;


	/**
	 * Wrap the given symmetric key by encrypting with the given user's public key.
	 * 
	 * @param key		Key to be wrapped.
	 * @param userKey	Public key of user for whom it is wrapped. Only they can unwrap using their secret key.
	 * 
	 * @return	 wrapped key.
	 * @throws IllegalArgumentException if the given key is invalid 
	 */
	WrappedKey wrap(SecretKey key, PublicKey userKey);

	/**
	 * Return the PEM format of the given key.
	 * 
	 * @param key A key.
	 * @return The PEM format of the given key.
	 * 
	 * @throws NullPointerException      If the given key is null.
	 * @throws IllegalArgumentException  If the given key is invalid.
	 */
	PemPublicKey	publicKeyToPem(PublicKey key);
  
  /**
   * Decode the given PEM format key.
   * 
   * @param pem A PEM encoded key.
   * @return The unencoded key.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  PublicKey     publicKeyFromPem(PemPublicKey pem);
  
  /**
   * Decode the given PEM format key.
   * 
   * @param reader A Reader containing a PEM encoded key.
   * @return The unencoded key.
   * 
   * @throws NullPointerException      If the given reader is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  PublicKey     publicKeyFromPem(Reader reader);
	
	/**
   * Return the PEM format of the given key.
   * 
   * @param key A key.
   * @return The PEM format of the given key.
   * 
   * @throws NullPointerException      If the given key is null.
   * @throws IllegalArgumentException  If the given key is invalid.
   */
	PemPrivateKey	privateKeyToPem(PrivateKey key);
	
	/**
   * Decode the given PEM format key.
   * 
   * @param pem A PEM encoded key.
   * @return The unencoded key.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  PrivateKey		privateKeyFromPem(PemPrivateKey pem);

  /**
   * Decode the given PEM format key.
   * 
   * @param reader A Reader containing a PEM encoded key.
   * @return The unencoded key.
   * 
   * @throws NullPointerException      If the given reader is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  PrivateKey   privateKeyFromPem(Reader reader);
  
  /**
   * Return the size of the given key in bits.
   * 
   * @param key A public key.
   * 
   * @return the size of the given key in bits.
   * 
   * @throws NullPointerException if the input is null
   * @throws IllegalArgumentException if the given key is invalid
   */
  int getKeySize(PublicKey key);
  
  /**
   * Return the size of the given key in bits.
   * 
   * @param key A private key.
   * 
   * @return the size of the given key in bits.
   * 
   * @throws NullPointerException if the input is null
   * @throws IllegalArgumentException if the given key is invalid
   */
  int getKeySize(PrivateKey key);

  /**
   * Return the PEM format of the given certificate.
   * 
   * @param x509Certificate A certificate.
   * @return The PEM format of the given certificate.
   * 
   * @throws NullPointerException      If the given certificate is null.
   * @throws IllegalArgumentException  If the given certificate is invalid.
   */
  PemCertificate certificateToPem(X509Certificate x509Certificate);

  /**
   * Decode the given PEM format certificate.
   * 
   * @param pem A PEM encoded certificate.
   * @return The unencoded certificate.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  X509Certificate certificateFromPem(PemCertificate pem);

  /**
   * Decode the given PEM format certificate.
   * 
   * @param in A reader containing a PEM encoded certificate.
   * @return The unencoded certificate.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  X509Certificate certificateFromPem(Reader in);

  /**
   * Decode the given PEM format certificate.
   * 
   * @param resourceName The name of a Java resource containing a PEM encoded certificate.
   * @return The unencoded certificate.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  X509Certificate certificateFromPemResource(String resourceName);
}
