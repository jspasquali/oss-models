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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import com.symphony.oss.commons.fault.CodingFault;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.models.crypto.canon.EncodedSignature;
import com.symphony.oss.models.crypto.canon.PemCertificate;
import com.symphony.oss.models.crypto.canon.PemPrivateKey;
import com.symphony.oss.models.crypto.canon.PemPublicKey;

/* package */ abstract class AbstractPublicKeyCipherSuite implements IPublicKeyCipherSuite
{
  private static final String   PROVIDER = "BC";
  private static      JcaX509CertificateConverter   x509Converter_  = new JcaX509CertificateConverter().setProvider(PROVIDER);

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
	  try
	  (
    		ByteArrayOutputStream	bos       = new ByteArrayOutputStream();
    		JcaPEMWriter				    pemWriter = new JcaPEMWriter(new OutputStreamWriter(bos));
		)
	  {
    		pemWriter.writeObject(key);
    		
    		pemWriter.close();
    		
    		return PemPublicKey.newBuilder().build(new String(bos.toByteArray()));
	  }
    catch (IOException e)
    {
      throw new IllegalArgumentException(e);
    }
	}
  
  @Override
  public PublicKey    publicKeyFromPem(PemPublicKey pem)
  {
    try(StringReader reader = new StringReader(pem.getValue()))
    {
      return publicKeyFromPem(reader);
    }
  }
  
  @Override
  public PublicKey    publicKeyFromPem(Reader reader)
  {
    try(PEMParser       pemParser = new PEMParser(reader))
    {
      Object o = pemParser.readObject();
      
      if (o == null || !(o instanceof SubjectPublicKeyInfo))
      {
        throw new IllegalArgumentException("Not a PEM public key");
      }
      
      return new JcaPEMKeyConverter().setProvider(PROVIDER).getPublicKey((SubjectPublicKeyInfo)o);
    }
    catch (IOException e)
    {
      throw new IllegalArgumentException(e);
    }
  }
	
	@Override
  public PemPrivateKey		privateKeyToPem(PrivateKey key)
	{
	  try
	  (
    		ByteArrayOutputStream	bos       = new ByteArrayOutputStream();
    		JcaPEMWriter				  pemWriter = new JcaPEMWriter(new OutputStreamWriter(bos));
		)
	  {
    		pemWriter.writeObject(key);
    		
    		pemWriter.close();
    		
    		return PemPrivateKey.newBuilder().build(new String(bos.toByteArray()));
	  }
    catch (IOException e)
    {
      throw new IllegalArgumentException(e);
    }
	}
	
	@Override
  public PrivateKey		privateKeyFromPem(PemPrivateKey pem)
	{
	  try(StringReader reader = new StringReader(pem.getValue()))
    {
      return privateKeyFromPem(reader);
    }
	}
  
  @Override
  public PrivateKey   privateKeyFromPem(Reader reader)
  {
	  try(PEMParser pemParser = new PEMParser(reader))
		{
			Object o = pemParser.readObject();
			
			if (o == null || !(o instanceof PEMKeyPair))
      {
      	throw new IllegalArgumentException("Not a PEM key");
      }
			
			KeyPair kp = new JcaPEMKeyConverter().setProvider(PROVIDER).getKeyPair((PEMKeyPair)o);
	        return kp.getPrivate();
		}
    catch (IOException e)
    {
      throw new IllegalArgumentException(e);
    }
	}
	
	@Override
	public PemCertificate certificateToPem(X509Certificate x509Certificate)
  {
    try
    {
      ByteArrayOutputStream bos       = new ByteArrayOutputStream();
      try(JcaPEMWriter pemWriter = new JcaPEMWriter(new OutputStreamWriter(bos)))
      {
        pemWriter.writeObject(x509Certificate);
      }

      return PemCertificate.newBuilder().build(bos.toString(StandardCharsets.UTF_8.name()));
    }
    catch(IOException e)
    {
      throw new IllegalArgumentException("Failed to encode certificate", e);
    }
}
  
  @Override
  public X509Certificate  certificateFromPemResource(String resourceName)
  {
    InputStream is = getClass().getResourceAsStream(resourceName);
    
    try(Reader in = new InputStreamReader(is))
    {
      return certificateFromPem(in);
    }
    catch (IOException e)
    {
      throw new IllegalArgumentException("Failed to decode certificate", e);
    }
  }
  
  @Override
  public X509Certificate  certificateFromPem(PemCertificate certData)
  {
    try(Reader in = new StringReader(certData.getValue()))
    {
      return certificateFromPem(in);
    }
    catch (IOException e)
    {
      throw new IllegalArgumentException("Failed to decode certificate", e);
    }
  }
  
  @Override
  public X509Certificate  certificateFromPem(Reader certData)
  {
    try( PEMParser pemReader = new PEMParser(certData) )
    {
      Object                certificate;
      
      certificate = pemReader.readObject();
      
      if(certificate == null)
        throw new IllegalArgumentException("Input is empty");
      
      if(certificate instanceof X509Certificate)
        return (X509Certificate)certificate;
      
      if(certificate instanceof X509CertificateHolder)
      {
        synchronized(x509Converter_)
        {
          return x509Converter_.getCertificate((X509CertificateHolder)certificate);
        }
      }
      
      throw new IllegalArgumentException("Certificate decode resulted in " + certificate.getClass());

    }
    catch(IOException e)
    {
      throw new IllegalArgumentException("Failed to decode certificate", e);
    }
    catch(CertificateException e)
    {
      throw new IllegalArgumentException("Failed to decode certificate", e);
    }
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
