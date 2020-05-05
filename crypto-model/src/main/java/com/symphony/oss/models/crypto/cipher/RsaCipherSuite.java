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

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.symphony.oss.commons.fault.CodingFault;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.models.crypto.canon.facade.WrappedKey;



/* package */ class RsaCipherSuite extends AbstractPublicKeyCipherSuite implements IPublicKeyCipherSuiteImpl
{
  private static final String KEY_ALGORITHM        = "RSA";
  private static final String SIGNATURE_ALGORITHM  = "SHA256WithRSA";
  private static final String PROVIDER             = "BC";
  private static final String WRAPPING_CIPHER_SPEC = "RSA/NONE/OAEPWithSHA256AndMGF1Padding";

  private int                 keySize_;
  private KeyPairGenerator    keyGen_;
  private SecureRandom        rand_                = new SecureRandom();

	public RsaCipherSuite(int keySize)
	{
	  try
	  {
    		keySize_ = keySize;
    		keyGen_       = KeyPairGenerator.getInstance(KEY_ALGORITHM, PROVIDER);
    		keyGen_.initialize(getPublicKeySize(), rand_);
    }
    catch(NoSuchAlgorithmException | NoSuchProviderException e)
    {
      throw new CodingFault(e);
    }
	}

	@Override
	public String getPublicKeySignatureAlgorithm()
	{
		return SIGNATURE_ALGORITHM;
	}

	@Override
	public int getPublicKeySize()
	{
		return keySize_;
	}

	@Override
	public String getPublicKeyAlgorithm()
	{
		return KEY_ALGORITHM;
	}

	@Override
	public Cipher getPublicKeyEncryptCipher()
	{
		try
    {
      return Cipher.getInstance(WRAPPING_CIPHER_SPEC, PROVIDER);
    }
    catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e)
    {
      throw new CodingFault(e);
    }
	}

	@Override
	public KeyPair generateKeyPair()
	{
		return keyGen_.generateKeyPair();
	}

	@Override
	public WrappedKey wrap(SecretKey key, PublicKey userKey)
	{
	  try
	  {
  		Cipher	cipher = getPublicKeyEncryptCipher();
          
      cipher.init(Cipher.WRAP_MODE, userKey, rand_);
      
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
	public SecretKey	unwrap(WrappedKey wrappedKey, PrivateKey userPrivateKey, ISymmetricCipherSuite symmetricCipherSuite)
	{
	  try
	  {
  		Cipher	cipher =  getPublicKeyEncryptCipher();
          
      cipher.init(Cipher.UNWRAP_MODE, userPrivateKey, rand_);
      
      return (SecretKey)cipher.unwrap(wrappedKey.getValue().toByteArray(), symmetricCipherSuite.getSymmetricKeyAlgorithm(), Cipher.SECRET_KEY);
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
  public int getKeySize(PublicKey key)
  {
    if(key instanceof RSAPublicKey)
    {
      return ((RSAPublicKey)key).getModulus().bitLength();
    }
    
    throw new IllegalArgumentException("Not an RSA Key");
  }
  
  @Override
  public int getKeySize(PrivateKey key)
  {
    if(key instanceof RSAPrivateKey)
    {
      return ((RSAPrivateKey)key).getModulus().bitLength();
    }
    
    throw new IllegalArgumentException("Not an RSA Key");
  }
}
