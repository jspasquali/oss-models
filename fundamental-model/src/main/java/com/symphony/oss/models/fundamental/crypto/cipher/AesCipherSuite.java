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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.symphonyoss.s2.common.fault.CodingFault;
import org.symphonyoss.s2.common.immutable.ImmutableByteArray;

import com.symphony.oss.models.fundamental.canon.facade.WrappedKey;
import com.symphony.oss.models.fundmental.canon.Base64SecretKey;
import com.symphony.oss.models.fundmental.canon.EncryptedData;

/* package */ class AesCipherSuite implements ISymmetricCipherSuiteImpl
{
  private static final String   KEY_ALGORITHM = "AES";
  private static final String   PROVIDER      = "BC";

  protected static final String CIPHER_MODE   = "AES/GCM/NoPadding";
  protected static final String WRAP_MODE     = "AES/ECB/PKCS7Padding";

  private int                   keySize_;
  private KeyGenerator          keyGen_;
  protected SecureRandom        rand_         = new SecureRandom();

	public AesCipherSuite(int keySize)
	{
	  try
	  {
    		keySize_ = keySize;
    		keyGen_	= KeyGenerator.getInstance(KEY_ALGORITHM, PROVIDER);
    		keyGen_.init(keySize_, rand_);
	  }
	  catch(NoSuchAlgorithmException | NoSuchProviderException e)
	  {
	    throw new CodingFault(e);
	  }
	}

	@Override
	public String getSymmetricKeyAlgorithm()
	{
		return KEY_ALGORITHM;
	}

	@Override
	public int getSymmetricKeySize()
	{
		return keySize_;
	}

	Cipher getEncryptCipher()
	{
		return getCipher(CIPHER_MODE);
	}

	Cipher getWrapCipher()
  {
	  return getCipher(WRAP_MODE);
  }
	
	private Cipher getCipher(String cipherMode)
  {
    try
    {
      return Cipher.getInstance(cipherMode, PROVIDER);
    }
    catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e)
    {
      throw new CodingFault(e);
    }
  }
	
	@Override
  public EncryptedData encrypt(SecretKey secretKey, ImmutableByteArray data)
  {
    try
    {
      Cipher cipher = getEncryptCipher();
      byte[] iv = new byte[cipher.getBlockSize()];
      rand_.nextBytes(iv);
      GCMParameterSpec spec = new GCMParameterSpec(cipher.getBlockSize() * 8, iv);
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
      byte[] authData = new byte[cipher.getBlockSize()];
      rand_.nextBytes(authData);
      cipher.updateAAD(authData);
      byte[] encryptedData = cipher.doFinal(data.toByteArray());
      
      return EncryptedData.newBuilder().build(ImmutableByteArray.newInstance(iv, authData, encryptedData));
    }
    catch (InvalidKeyException e)
    {
      throw new IllegalArgumentException(e);
    }
    catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e)
    {
      throw new CodingFault(e);
    }
  }

  @Override
  public ImmutableByteArray decrypt(SecretKey key, EncryptedData cipherText)
  {
    try
    {
      Cipher cipher = getEncryptCipher();
      byte[] cipherBytes = cipherText.getValue().toByteArray();
      
      GCMParameterSpec spec = new GCMParameterSpec(cipher.getBlockSize() * 8, cipherBytes, 0, cipher.getBlockSize());
      cipher.init(Cipher.DECRYPT_MODE, key, spec);
      cipher.updateAAD(cipherBytes, cipher.getBlockSize(), cipher.getBlockSize());
  
      int twoBlocks = 2 * cipher.getBlockSize();
      
      byte[] clearText = cipher.doFinal(cipherBytes, twoBlocks, cipherBytes.length - twoBlocks);
  
      return ImmutableByteArray.newInstance(clearText);
    }
    catch (InvalidKeyException e)
    {
      throw new IllegalArgumentException(e);
    }
    catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e)
    {
      throw new CodingFault(e);
    }
  }
	
	@Override
  public WrappedKey wrap(PrivateKey key, SecretKey userKey)
  {
    try
    {
        Cipher cipher = getWrapCipher();
            
        cipher.init(Cipher.WRAP_MODE, userKey);
        
        return WrappedKey.newBuilder().build(ImmutableByteArray.newInstance(cipher.wrap(key)));
    }
    catch (InvalidKeyException e)
    {
      throw new IllegalArgumentException(e);
    }
    catch (IllegalBlockSizeException e)
    {
      throw new CodingFault(e);
    }
  }
  
  @Override
  public PrivateKey unwrap(WrappedKey cipherText, SecretKey userKey, IPublicKeyCipherSuite cipherSuite)
  {
    try
    {
      Cipher cipher = getWrapCipher();
      cipher.init(Cipher.UNWRAP_MODE, userKey);
  
      return (PrivateKey) cipher.unwrap(cipherText.getValue().toByteArray(), cipherSuite.getPublicKeyAlgorithm(), Cipher.PRIVATE_KEY);
    }
    catch (InvalidKeyException e)
    {
      throw new IllegalArgumentException(e);
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new CodingFault(e);
    }
  }

	@Override
	public SecretKey generateKey()
	{
		return keyGen_.generateKey();
	}

  @Override
  public int getKeySize(SecretKey key)
  {
    return key.getEncoded().length * 8;
  }

  @Override
  public Base64SecretKey secretKeyToBase64(SecretKey key)
  {
    return Base64SecretKey.newBuilder().build(Base64.encodeBase64String(key.getEncoded()));
  }

  @Override
  public SecretKey secretKeyFromBase64(Base64SecretKey encodedKey)
  {
    return new SecretKeySpec(Base64.decodeBase64(encodedKey.asString()), KEY_ALGORITHM);
  }
}
