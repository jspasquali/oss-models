/*
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

import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.models.crypto.canon.Base64SecretKey;
import com.symphony.oss.models.crypto.canon.CipherSuiteId;
import com.symphony.oss.models.crypto.canon.EncodedSignature;
import com.symphony.oss.models.crypto.canon.EncryptedData;
import com.symphony.oss.models.crypto.canon.PemCertificate;
import com.symphony.oss.models.crypto.canon.PemPrivateKey;
import com.symphony.oss.models.crypto.canon.PemPublicKey;
import com.symphony.oss.models.crypto.canon.facade.WrappedKey;
import com.symphony.oss.models.crypto.cipher.CipherSuite;
import com.symphony.oss.models.crypto.cipher.ICipherSuite;
import com.symphony.oss.models.crypto.cipher.SignatureVerificationException;

/**
 * A dummy CipherSuite which generates constant values for unit testing.
 * 
 * When the getInstance() method is called o this class it inserts itself as the implementation for the RSA1024_AES128 cipher suite.
 * 
 * THIS CLASS DOES NO ENCRYPTION OR DECRYPTION DO NOT EVER LET THIS CLASS FIND ITS WAY ONTO THE RUNTIME CLASSPATH
 * 
 * @author Bruce Skingle
 *
 */
public class DummyCipherSuite implements ICipherSuite
{
  private static final String DUMMY = "DUMMY";

  private static ICipherSuite instance_;
  
  public static synchronized ICipherSuite getInstance()
  {
    if(instance_ == null)
    {
      instance_ = new DummyCipherSuite();
      CipherSuite.register(instance_);
    }
    
    return instance_;
  }
  
  SecretKey secretKey = new DummySecretKey();
  KeyPair   keyPair   = new KeyPair(new DummyPublicKey(), new DummyPrivateKey());
  
  private DummyCipherSuite()
  {
  }
  
  @Override
  public String getSymmetricKeyAlgorithm()
  {
    return DUMMY;
  }

  @Override
  public int getSymmetricKeySize()
  {
    return 0;
  }

  @Override
  public SecretKey generateKey()
  {
    return secretKey;
  }

  @Override
  public EncryptedData encrypt(SecretKey secretKey, ImmutableByteArray data)
  {
    return EncryptedData.newBuilder().build(ImmutableByteArray.newInstance("ENCRYPTED:" + data));
  }

  @Override
  public ImmutableByteArray decrypt(SecretKey key, EncryptedData cipherText)
  {
    return ImmutableByteArray.newInstance(cipherText.toString().substring(10));
  }

  @Override
  public WrappedKey wrap(PrivateKey key, SecretKey userKey)
  {
    return WrappedKey.newBuilder().build(ImmutableByteArray.newInstance("WRAPPED(" + userKey + ")"));
  }

  @Override
  public int getKeySize(SecretKey key)
  {
    return 0;
  }

  @Override
  public Base64SecretKey secretKeyToBase64(SecretKey key)
  {
    return null;
  }

  @Override
  public SecretKey secretKeyFromBase64(Base64SecretKey encodedKey)
  {
    return null;
  }

  @Override
  public String getPublicKeyAlgorithm()
  {
    return DUMMY;
  }

  @Override
  public Cipher getPublicKeyEncryptCipher()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getPublicKeySize()
  {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void validateKey(KeyPair keyPair) throws InvalidKeyException
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void validateKey(PrivateKey key) throws InvalidKeyException
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void validateKey(PublicKey key) throws InvalidKeyException
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public String getPublicKeySignatureAlgorithm()
  {
    return DUMMY;
  }

  @Override
  public KeyPair generateKeyPair()
  {
    return keyPair;
  }

  @Override
  public EncodedSignature sign(ImmutableByteArray data, PrivateKey privateKey)
  {
    return EncodedSignature.newBuilder().build(ImmutableByteArray.newInstance("SIGNED(" + data + ")"));
  }

  @Override
  public void verifySignature(EncodedSignature encodedSignature, ImmutableByteArray data, PublicKey publicKey)
      throws SignatureVerificationException
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public WrappedKey wrap(SecretKey key, PublicKey userKey)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PemPublicKey publicKeyToPem(PublicKey key)
  {
    return PemPublicKey.newBuilder().build("PemPublicKey(" + new String(key.getEncoded()) + ")");
  }

  @Override
  public PublicKey publicKeyFromPem(PemPublicKey pem)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PemPrivateKey privateKeyToPem(PrivateKey key)
  {
    return PemPrivateKey.newBuilder().build("PemPrivateKey(" + new String(key.getEncoded()) + ")");
  }

  @Override
  public PrivateKey privateKeyFromPem(PemPrivateKey pem)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getKeySize(PublicKey key)
  {
    return 0;
  }

  @Override
  public int getKeySize(PrivateKey key)
  {
    return 0;
  }

  @Override
  public PemCertificate certificateToPem(X509Certificate x509Certificate)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public X509Certificate certificateFromPem(PemCertificate pem)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public X509Certificate certificateFromPem(Reader in)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public X509Certificate certificateFromPemResource(String resourceName)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PublicKey publicKeyFromPem(Reader reader)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PrivateKey privateKeyFromPem(Reader reader)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CipherSuiteId getId()
  {
    return CipherSuiteId.RSA1024_AES128;
  }

  @Override
  public SecretKey unwrap(WrappedKey wrappedKey, PrivateKey userPrivateKey)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PrivateKey unwrap(WrappedKey wrappedKey, SecretKey userKey)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  class DummySecretKey implements SecretKey
  {
    private static final long serialVersionUID = 1L;

    @Override
    public String getAlgorithm()
    {
      return DUMMY;
    }

    @Override
    public String getFormat()
    {
      return DUMMY;
    }

    @Override
    public byte[] getEncoded()
    {
      return "SecretKey".getBytes();
    }

  }

  class DummyPublicKey implements PublicKey
  {
    private static final long serialVersionUID = 1L;

    @Override
    public String getAlgorithm()
    {
      return DUMMY;
    }

    @Override
    public String getFormat()
    {
      return DUMMY;
    }

    @Override
    public byte[] getEncoded()
    {
      return "PublicKey".getBytes();
    }
    
  }

  class DummyPrivateKey implements PrivateKey
  {
    private static final long serialVersionUID = 1L;

    @Override
    public String getAlgorithm()
    {
      return DUMMY;
    }

    @Override
    public String getFormat()
    {
      return DUMMY;
    }

    @Override
    public byte[] getEncoded()
    {
      return "PrivateKey".getBytes();
    }
    
  }
}
