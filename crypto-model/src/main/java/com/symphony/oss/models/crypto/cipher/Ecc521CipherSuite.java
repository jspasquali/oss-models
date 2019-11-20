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

import java.io.IOException;
import java.io.StringReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.symphonyoss.s2.common.fault.CodingFault;
import org.symphonyoss.s2.common.immutable.ImmutableByteArray;

import com.symphony.oss.models.crypto.canon.facade.WrappedKey;
import com.symphony.oss.models.crypto.canon.PemPrivateKey;
import com.symphony.oss.models.crypto.canon.PemPublicKey;

/* package */ class Ecc521CipherSuite extends AbstractPublicKeyCipherSuite implements IPublicKeyCipherSuiteImpl
{
  private static final String KEY_ALGORITHM        = "EC";
  private static final String SIGNATURE_ALGORITHM  = "SHA512withECDSA";
  private static final String PROVIDER             = "BC";
  private static final String CURVE_NAME           = "secp521r1";
  private static final String WRAPPING_CIPHER_SPEC = "ECIESwithAES";

  private ECGenParameterSpec  ecGenSpec;
  private KeyPairGenerator    keyGen_;
  private SecureRandom        rand_;
	
	public Ecc521CipherSuite()
	{
	  try
	  {
    		rand_		= new SecureRandom();
    		ecGenSpec	= new ECGenParameterSpec(CURVE_NAME);
    		keyGen_		= KeyPairGenerator.getInstance(KEY_ALGORITHM, PROVIDER);
    		
    		keyGen_.initialize(ecGenSpec, rand_);
    }
    catch(NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e)
    {
      throw new CodingFault(e);
    }
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
      return  Cipher.getInstance(WRAPPING_CIPHER_SPEC, PROVIDER);
    }
    catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e)
    {
      throw new CodingFault(e);
    }
	}

	@Override
	public int getPublicKeySize()
	{
		return 521;
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
  		Cipher cipher = getPublicKeyEncryptCipher();
          
      cipher.init(Cipher.ENCRYPT_MODE, userKey, rand_);
      
      return WrappedKey.newBuilder().build(ImmutableByteArray.newInstance(cipher.doFinal(key.getEncoded())));
	  }
    catch (InvalidKeyException e)
    {
      throw new IllegalArgumentException(e);
    }
    catch (IllegalBlockSizeException | BadPaddingException e)
    {
      throw new CodingFault(e);
    }
	}

	@Override
	public SecretKey unwrap(WrappedKey wrappedKey, PrivateKey userPrivateKey, ISymmetricCipherSuite symmetricCipherSuite)
	{
	  try
	  {
  		Cipher cipher = getPublicKeyEncryptCipher();
          
      cipher.init(Cipher.DECRYPT_MODE, userPrivateKey, rand_);
      
      byte[] encoded = cipher.doFinal(wrappedKey.getValue().toByteArray());
      return new SecretKeySpec(encoded, symmetricCipherSuite.getSymmetricKeyAlgorithm());
	  }
    catch (InvalidKeyException e)
    {
      throw new IllegalArgumentException(e);
    }
    catch (IllegalBlockSizeException | BadPaddingException e)
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
  public PublicKey		publicKeyFromPem(PemPublicKey pem)
	{
	  try(
        StringReader    reader = new StringReader(pem.getValue());
        PEMParser       pemParser = new PEMParser(reader);
    )
		{
			Object o = pemParser.readObject();
			
			if (o == null || !(o instanceof SubjectPublicKeyInfo))
      {
      	throw new IllegalArgumentException("Not a PEM public key");
      }
			
			return  KeyFactory.getInstance("EC", "BC").generatePublic(new X509EncodedKeySpec(((SubjectPublicKeyInfo)o).getEncoded()));
		}
    catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchProviderException e)
    {
      throw new IllegalArgumentException(e);
    }
	}
	
	@Override
  public PrivateKey		privateKeyFromPem(PemPrivateKey pem)
	{
		try(
		    StringReader    reader = new StringReader(pem.getValue());
		    PEMParser       pemParser = new PEMParser(reader);
		)
		{
			Object o = pemParser.readObject();
			
			if (o == null || !(o instanceof PEMKeyPair))
      {
      	throw new IllegalArgumentException("Not a PEM key");
      }
			return  KeyFactory.getInstance("EC", "BC").generatePrivate(new PKCS8EncodedKeySpec(((PEMKeyPair)o).getPrivateKeyInfo().getEncoded()));
		}
    catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchProviderException e)
    {
      throw new IllegalArgumentException(e);
    }
	}

  @Override
  public int getKeySize(PublicKey key)
  {
    if(key instanceof ECPublicKey)
    {
      ECParameterSpec spec = ((ECPublicKey)key).getParams();
      
      if(spec == null)
        throw new IllegalArgumentException("Key has null parameter spec");
      
      return spec.getOrder().bitLength();
    }
    
    throw new IllegalArgumentException("Not an Elliptic Curve Key");
  }

  @Override
  public int getKeySize(PrivateKey key)
  {
    if(key instanceof ECPrivateKey)
    {
      ECParameterSpec spec = ((ECPrivateKey)key).getParams();
      
      if(spec == null)
        throw new IllegalArgumentException("Key has null parameter spec");
      
      return spec.getOrder().bitLength();
    }
    
    throw new IllegalArgumentException("Not an Elliptic Curve Key");
  }
  
}
