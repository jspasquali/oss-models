/*
 * Copyright 2016-2017  Symphony Communication Services, LLC.
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

import java.io.Reader;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.symphonyoss.s2.common.fault.CodingFault;
import org.symphonyoss.s2.common.immutable.ImmutableByteArray;

import com.symphony.oss.models.fundmental.canon.CipherSuiteId;
import com.symphony.oss.models.fundmental.canon.EncodedSignature;
import com.symphony.oss.models.fundmental.canon.EncryptedData;
import com.symphony.oss.models.fundmental.canon.PemCertificate;
import com.symphony.oss.models.fundmental.canon.PemPrivateKey;
import com.symphony.oss.models.fundmental.canon.PemPublicKey;
import com.symphony.oss.models.fundamental.canon.facade.WrappedKey;
import com.symphony.oss.models.fundmental.canon.Base64SecretKey;


/**
 * Access point for CipherSuites.
 * 
 * @author Bruce Skingle
 */
public class CipherSuite implements ICipherSuite
{
  private static final Map<String, IPublicKeyCipherSuite>               publicAlgoMap_     = new HashMap<>();
  private static final Map<String, ISymmetricCipherSuite>               secretAlgoMap_     = new HashMap<>();
  private static final Map<String, Map<Integer, IPublicKeyCipherSuite>> publicAlgoSizeMap_ = new HashMap<>();
  private static final Map<String, Map<Integer, ISymmetricCipherSuite>> secretAlgoSizeMap_ = new HashMap<>();

  private static final Map<CipherSuiteId, ICipherSuite>                 cipherSuiteMap_    = new HashMap<>();
  private static final List<ICipherSuite>                               cipherSuiteList_;

  private static final ICipherSuite                                     defaultCipherSuite_;

  private static List<ICipherSuite>                                     buildList_         = new ArrayList<>();

  private final IPublicKeyCipherSuiteImpl                               publicKeyCipherSuite_;
  private final ISymmetricCipherSuiteImpl                               symmetricCipherSuite_;
  private final CipherSuiteId                                           id_;
  
  static
  {
    if(Security.getProvider("BC") == null)
    {
      Security.addProvider(new BouncyCastleProvider());
    }
  
    /*
     * The order of initialization is significant.
     * 
     * When calling get(SecretKey key) or get(PublicKey key), if there are multiple
     * implementations with the same key size then the first one registered will be
     * returned.
     */
    defaultCipherSuite_ = new CipherSuite(CipherSuiteId.RSA2048_AES256, new RsaCipherSuite(2048), new AesCipherSuite(256));
    
    register(defaultCipherSuite_);
    register(new CipherSuite(CipherSuiteId.RSA1024_AES128,  new RsaCipherSuite(2014), new AesCipherSuite(128)));
    register(new CipherSuite(CipherSuiteId.ECC521_AES256,   new Ecc521CipherSuite(),  new AesCipherSuite(256)));

    
    cipherSuiteList_ = Collections.unmodifiableList(buildList_);
  }
  
  CipherSuite(CipherSuiteId id, IPublicKeyCipherSuiteImpl publicKeyCipherSuite, ISymmetricCipherSuiteImpl symmetricCipherSuite)
  {
    id_ = id;
    publicKeyCipherSuite_ = publicKeyCipherSuite;
    symmetricCipherSuite_ = symmetricCipherSuite;
  }
  
  @Override
  public CipherSuiteId getId()
  {
    return id_;
  }

  static void register(ICipherSuite cipherSuite)
  {
    cipherSuiteMap_.put(cipherSuite.getId(), cipherSuite);
    
    publicAlgoMap_.put(cipherSuite.getPublicKeyAlgorithm(), cipherSuite);
    secretAlgoMap_.put(cipherSuite.getSymmetricKeyAlgorithm(), cipherSuite);
    
    Map<Integer, IPublicKeyCipherSuite> privateMap = publicAlgoSizeMap_.get(cipherSuite.getPublicKeyAlgorithm());
    
    if(privateMap == null)
    {
      privateMap = new HashMap<>();
      publicAlgoSizeMap_.put(cipherSuite.getPublicKeyAlgorithm(), privateMap);
    }
    
    if(!privateMap.containsKey(cipherSuite.getPublicKeySize()))
      privateMap.put(cipherSuite.getPublicKeySize(), cipherSuite);
    
    Map<Integer, ISymmetricCipherSuite> secretMap = secretAlgoSizeMap_.get(cipherSuite.getSymmetricKeyAlgorithm());
    
    if(secretMap == null)
    {
      secretMap = new HashMap<>();
      secretAlgoSizeMap_.put(cipherSuite.getSymmetricKeyAlgorithm(), secretMap);
    }
    
    if(!secretMap.containsKey(cipherSuite.getSymmetricKeySize()))
      secretMap.put(cipherSuite.getSymmetricKeySize(), cipherSuite);
    
    buildList_.add(cipherSuite);
  }

  /**
   * Return the cipher suite implementation for the given key.
   * 
   * @param key A PublicKey
   * @return The implementation of the required cipher suite.
   * @throws UnknownCipherSuiteException If the given key does not match any known CipherSuite
   */
  public static @Nonnull IPublicKeyCipherSuite get(PublicKey key) throws UnknownCipherSuiteException
  {
    IPublicKeyCipherSuite cipherSuite = publicAlgoMap_.get(key.getAlgorithm());
    
    if(cipherSuite == null)
      throw new UnknownCipherSuiteException("Unknown algorithm \"" + key.getAlgorithm() + "\"");
    
    int keySize;
    try
    {
      keySize = cipherSuite.getKeySize(key);
    }
    catch (IllegalArgumentException e)
    {
      throw new UnknownCipherSuiteException("Unsupported key size \"" + key.getAlgorithm() + "\"");

    }
    cipherSuite = publicAlgoSizeMap_.get(key.getAlgorithm()).get(keySize);
        
    if(cipherSuite == null)
      throw new UnknownCipherSuiteException("Unsupported key size \"" + key.getAlgorithm() + "\" " + keySize);
    
    return cipherSuite;
  }

  /**
   * Return the cipher suite implementation for the given ID.
   * 
   * @param key A SecretKey
   * @return The implementation of the required cipher suite.
   * @throws UnknownCipherSuiteException if the key does not match any cipher suite
   */
  public static @Nonnull ISymmetricCipherSuite get(SecretKey key) throws UnknownCipherSuiteException
  {
    ISymmetricCipherSuite cipherSuite = secretAlgoMap_.get(key.getAlgorithm());
    
    if(cipherSuite == null)
      throw new UnknownCipherSuiteException("Unknown algorithm \"" + key.getAlgorithm() + "\"");
    
    int keySize = cipherSuite.getKeySize(key);
    cipherSuite = secretAlgoSizeMap_.get(key.getAlgorithm()).get(keySize);
        
    if(cipherSuite == null)
      throw new UnknownCipherSuiteException("Unsupported key size \"" + key.getAlgorithm() + "\" " + keySize);
    
    return cipherSuite;
  }
  
  /**
   * Return the cipher suite implementation for the given ID.
   * 
   * @param id The ID of the required cipher suite.
   * @return The implementation of the required cipher suite.
   */
  public static @Nonnull ICipherSuite get(CipherSuiteId id)
  {
    if(!cipherSuiteMap_.containsKey(id))
      throw new CodingFault("No cipher suite for " + id);
    
    return cipherSuiteMap_.get(id);
  }
  
  /**
   * Return the default cipher suite.
   * 
   * @return the default cipher suite.
   */
  public static @Nonnull ICipherSuite getDefault()
  {
    return defaultCipherSuite_;
  }

  /**
   * 
   * @return an UnModifiableList of all CipherSuites
   */
  public static List<ICipherSuite> getAllCipherSuites()
  {
    return cipherSuiteList_;
  }

  @Override
  public String getPublicKeyAlgorithm()
  {
    return publicKeyCipherSuite_.getPublicKeyAlgorithm();
  }

  @Override
  public Cipher getPublicKeyEncryptCipher()
  {
    return publicKeyCipherSuite_.getPublicKeyEncryptCipher();
  }

  @Override
  public int getPublicKeySize()
  {
    return publicKeyCipherSuite_.getPublicKeySize();
  }

  @Override
  public void validateKey(KeyPair keyPair) throws InvalidKeyException
  {
    publicKeyCipherSuite_.validateKey(keyPair);
  }

  @Override
  public void validateKey(PrivateKey key) throws InvalidKeyException
  {
    publicKeyCipherSuite_.validateKey(key);
  }

  @Override
  public void validateKey(PublicKey key) throws InvalidKeyException
  {
    publicKeyCipherSuite_.validateKey(key);
  }

  @Override
  public String getPublicKeySignatureAlgorithm()
  {
    return publicKeyCipherSuite_.getPublicKeySignatureAlgorithm();
  }

  @Override
  public KeyPair generateKeyPair()
  {
    return publicKeyCipherSuite_.generateKeyPair();
  }

  @Override
  public EncodedSignature sign(ImmutableByteArray data, PrivateKey privateKey)
  {
    return publicKeyCipherSuite_.sign(data, privateKey);
  }

  @Override
  public void verifySignature(EncodedSignature encodedSignature, ImmutableByteArray data, PublicKey publicKey)
      throws SignatureVerificationException
  {
    publicKeyCipherSuite_.verifySignature(encodedSignature, data, publicKey);
  }

  @Override
  public WrappedKey wrap(SecretKey key, PublicKey userKey)
  {
    return publicKeyCipherSuite_.wrap(key, userKey);
  }

  @Override
  public SecretKey unwrap(WrappedKey wrappedKey, PrivateKey userPrivateKey)
  {
    return publicKeyCipherSuite_.unwrap(wrappedKey, userPrivateKey, symmetricCipherSuite_);
  }

  @Override
  public PemPublicKey publicKeyToPem(PublicKey key)
  {
    return publicKeyCipherSuite_.publicKeyToPem(key);
  }

  @Override
  public PublicKey publicKeyFromPem(PemPublicKey pem)
  {
    return publicKeyCipherSuite_.publicKeyFromPem(pem);
  }

  @Override
  public PublicKey publicKeyFromPem(Reader reader)
  {
    return publicKeyCipherSuite_.publicKeyFromPem(reader);
  }

  @Override
  public PemPrivateKey privateKeyToPem(PrivateKey key)
  {
    return publicKeyCipherSuite_.privateKeyToPem(key);
  }

  @Override
  public PrivateKey privateKeyFromPem(PemPrivateKey pem)
  {
    return publicKeyCipherSuite_.privateKeyFromPem(pem);
  }

  @Override
  public PrivateKey privateKeyFromPem(Reader reader)
  {
    return publicKeyCipherSuite_.privateKeyFromPem(reader);
  }

  @Override
  public Base64SecretKey secretKeyToBase64(SecretKey key)
  {
    return symmetricCipherSuite_.secretKeyToBase64(key);
  }

  @Override
  public SecretKey secretKeyFromBase64(Base64SecretKey encodedKey)
  {
    return symmetricCipherSuite_.secretKeyFromBase64(encodedKey);
  }

  @Override
  public PemCertificate certificateToPem(X509Certificate x509Certificate)
  {
    return publicKeyCipherSuite_.certificateToPem(x509Certificate);
  }

  @Override
  public X509Certificate certificateFromPem(PemCertificate pem)
  {
    return publicKeyCipherSuite_.certificateFromPem(pem);
  }

  @Override
  public X509Certificate certificateFromPem(Reader in)
  {
    return publicKeyCipherSuite_.certificateFromPem(in);
  }

  @Override
  public X509Certificate certificateFromPemResource(String resourceName)
  {
    return publicKeyCipherSuite_.certificateFromPemResource(resourceName);
  }

  @Override
  public int getKeySize(PublicKey key)
  {
    return publicKeyCipherSuite_.getKeySize(key);
  }

  @Override
  public int getKeySize(PrivateKey key)
  {
    return publicKeyCipherSuite_.getKeySize(key);
  }

  @Override
  public String getSymmetricKeyAlgorithm()
  {
    return symmetricCipherSuite_.getSymmetricKeyAlgorithm();
  }

  @Override
  public int getSymmetricKeySize()
  {
    return symmetricCipherSuite_.getSymmetricKeySize();
  }

  @Override
  public SecretKey generateKey()
  {
    return symmetricCipherSuite_.generateKey();
  }

  @Override
  public EncryptedData encrypt(SecretKey secretKey, ImmutableByteArray data)
  {
    return symmetricCipherSuite_.encrypt(secretKey, data);
  }

  @Override
  public ImmutableByteArray decrypt(SecretKey key, EncryptedData cipherText)
  {
    return symmetricCipherSuite_.decrypt(key, cipherText);
  }

  @Override
  public WrappedKey wrap(PrivateKey key, SecretKey userKey)
  {
    return symmetricCipherSuite_.wrap(key, userKey);
  }

  @Override
  public PrivateKey unwrap(WrappedKey wrappedKey, SecretKey userKey)
  {
    return symmetricCipherSuite_.unwrap(wrappedKey, userKey, publicKeyCipherSuite_);
  }

  @Override
  public int getKeySize(SecretKey key)
  {
    return symmetricCipherSuite_.getKeySize(key);
  }

  @Override
  public String toString()
  {
    return id_.toString();
  }
}
