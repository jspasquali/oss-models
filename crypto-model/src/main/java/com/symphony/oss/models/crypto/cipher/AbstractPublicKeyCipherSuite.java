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

package com.symphony.oss.models.crypto.cipher;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;

import com.symphony.oss.commons.fault.CodingFault;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.models.crypto.canon.EncodedSignature;
import com.symphony.oss.models.crypto.canon.PemCertificate;
import com.symphony.oss.models.crypto.canon.PemPrivateKey;
import com.symphony.oss.models.crypto.canon.PemPublicKey;

/* package */ abstract class AbstractPublicKeyCipherSuite implements IPublicKeyCipherSuite
{
  static final String   PROVIDER = "BC";

	@Override
	public EncodedSignature	sign(ImmutableByteArray data, PrivateKey privateKey)
	{
	  try
	  {
  		  Signature	signature = Signature.getInstance(getPublicKeySignatureAlgorithm());
  		
    		signature.initSign(privateKey);
    		
    		for(byte b : data)
    		  signature.update(b);
    
    		return EncodedSignature.newBuilder().build(ImmutableByteArray.newInstance(signature.sign()));
	  }
    catch(NoSuchAlgorithmException | SignatureException e)
    {
      throw new CodingFault(e);
    }
    catch (InvalidKeyException e)
    {
      throw new IllegalArgumentException(e);
    }
  }	
	
	@Override
  public void	verifySignature(EncodedSignature encodedSignature, ImmutableByteArray data, PublicKey publicKey) throws SignatureVerificationException
	{
		try
		{
			Signature	signature = Signature.getInstance(getPublicKeySignatureAlgorithm());
			
			signature.initVerify(publicKey);
			
			for(byte b : data)
        signature.update(b);
			
			if(!signature.verify(encodedSignature.asImmutableByteArray().toByteArray()))
				throw new SignatureVerificationException("Signature verification failed");
		}
		catch(NoSuchAlgorithmException | InvalidKeyException | SignatureException e)
		{
			throw new SignatureVerificationException("Signature verification failed", e);
		}
	}
	
	@Override
  public PemPublicKey		publicKeyToPem(PublicKey key)
	{
	  return CipherSuiteUtils.publicKeyToPem(key);
	}
  
  @Override
  public PublicKey    publicKeyFromPem(PemPublicKey pem)
  {
    return CipherSuiteUtils.publicKeyFromPem(pem);
  }
  
  @Override
  public PublicKey    publicKeyFromPem(Reader reader)
  {
    return CipherSuiteUtils.publicKeyFromPem(reader);
  }
	
	@Override
  public PemPrivateKey		privateKeyToPem(PrivateKey key)
	{
	  return CipherSuiteUtils.privateKeyToPem(key);
	}
	
	@Override
  public PrivateKey		privateKeyFromPem(PemPrivateKey pem)
	{
	  return CipherSuiteUtils.privateKeyFromPem(pem);
	}
  
  @Override
  public PrivateKey   privateKeyFromPem(Reader reader)
  {
    return CipherSuiteUtils.privateKeyFromPem(reader);
	}
	
	@Override
	public PemCertificate certificateToPem(X509Certificate x509Certificate)
  {
	  return CipherSuiteUtils.certificateToPem(x509Certificate);
  }
  
  @Override
  public X509Certificate  certificateFromPemResource(String resourceName)
  {
    return CipherSuiteUtils.certificateFromPemResource(getClass(), resourceName);
  }
  
  @Override
  public X509Certificate  certificateFromPem(PemCertificate certData)
  {
    return CipherSuiteUtils.certificateFromPem(certData);
  }
  
  @Override
  public X509Certificate  certificateFromPem(Reader in)
  {
    return CipherSuiteUtils.certificateFromPem(in);
  }


  @Override
  public void validateKey(KeyPair keyPair) throws InvalidKeyException
  {
    validateKey(keyPair.getPublic());
  }

  @Override
  public void validateKey(PrivateKey key) throws InvalidKeyException
  {
    if(getKeySize(key) != getPublicKeySize())
      throw new InvalidKeyException("Incorrect key size");
  }

  @Override
  public void validateKey(PublicKey key) throws InvalidKeyException
  {
    if(getKeySize(key) != getPublicKeySize())
      throw new InvalidKeyException("Incorrect key size");
  }
}
