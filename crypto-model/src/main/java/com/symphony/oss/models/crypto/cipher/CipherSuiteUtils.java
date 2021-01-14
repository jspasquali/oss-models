/*
 *
 *
 * Copyright 2021 Symphony Communication Services, LLC.
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
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import com.symphony.oss.models.crypto.canon.PemCertificate;
import com.symphony.oss.models.crypto.canon.PemPrivateKey;
import com.symphony.oss.models.crypto.canon.PemPublicKey;

/**
 * Utilities for PrivateKey objects.
 * 
 * @author Bruce Skingle
 *
 */
public class CipherSuiteUtils
{
  static
  {
    if(Security.getProvider("BC") == null)
    {
      Security.addProvider(new BouncyCastleProvider());
    }
  }
  
  private static      JcaX509CertificateConverter   x509Converter_  = new JcaX509CertificateConverter().setProvider(AbstractPublicKeyCipherSuite.PROVIDER);
  
  /**
   * Return the PEM format of the given certificate.
   * 
   * @param x509Certificate A certificate.
   * @return The PEM format of the given certificate.
   * 
   * @throws NullPointerException      If the given certificate is null.
   * @throws IllegalArgumentException  If the given certificate is invalid.
   */
  public static PemCertificate certificateToPem(X509Certificate x509Certificate)
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

  /**
   * Decode the given PEM format certificate.
   * 
   * @param pem A PEM encoded certificate.
   * @return The unencoded certificate.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  public static X509Certificate certificateFromPem(PemCertificate pem)
  {
    try(Reader in = new StringReader(pem.getValue()))
    {
      return certificateFromPem(in);
    }
    catch (IOException e)
    {
      throw new IllegalArgumentException("Failed to decode certificate", e);
    }
  }

  /**
   * Decode the given PEM format certificate.
   * 
   * @param in A reader containing a PEM encoded certificate.
   * @return The unencoded certificate.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  public static X509Certificate certificateFromPem(Reader in)
  {
    try( PEMParser pemReader = new PEMParser(in) )
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

  /**
   * Decode the given PEM format certificate.
   * 
   * @param clazz        The class to use to load the resource.
   * @param resourceName The name of a Java resource containing a PEM encoded certificate.
   * @return The unencoded certificate.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  public static X509Certificate certificateFromPemResource(Class<?> clazz, String resourceName)
  {

    InputStream is = clazz.getResourceAsStream(resourceName);
    
    try(Reader in = new InputStreamReader(is))
    {
      return certificateFromPem(in);
    }
    catch (IOException e)
    {
      throw new IllegalArgumentException("Failed to decode certificate", e);
    }
  }
  
  /**
   * Return the PEM format of the given key.
   * 
   * @param key A key.
   * @return The PEM format of the given key.
   * 
   * @throws NullPointerException      If the given key is null.
   * @throws IllegalArgumentException  If the given key is invalid.
   */
  public static PemPrivateKey privateKeyToPem(PrivateKey key)
  {
    try
    (
        ByteArrayOutputStream bos       = new ByteArrayOutputStream();
        JcaPEMWriter          pemWriter = new JcaPEMWriter(new OutputStreamWriter(bos));
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
  
  /**
   * Decode the given PEM format key.
   * 
   * @param pem A PEM encoded key.
   * @return The unencoded key.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  public static PrivateKey    privateKeyFromPem(PemPrivateKey pem)
  {
    try(StringReader reader = new StringReader(pem.getValue()))
    {
      return privateKeyFromPem(reader);
    }
  }

  /**
   * Decode the given PEM format key.
   * 
   * @param reader A Reader containing a PEM encoded key.
   * @return The unencoded key.
   * 
   * @throws NullPointerException      If the given reader is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  public static PrivateKey   privateKeyFromPem(Reader reader)
  {
    try(PEMParser pemParser = new PEMParser(reader))
    {
      Object o = pemParser.readObject();
      
      if (o == null || !(o instanceof PEMKeyPair))
      {
        throw new IllegalArgumentException("Not a PEM key");
      }
      
      KeyPair kp = new JcaPEMKeyConverter().setProvider(AbstractPublicKeyCipherSuite.PROVIDER).getKeyPair((PEMKeyPair)o);
          return kp.getPrivate();
    }
    catch (IOException e)
    {
      throw new IllegalArgumentException(e);
    }
  }
  /**
   * Return the PEM format of the given key.
   * 
   * @param key A key.
   * @return The PEM format of the given key.
   * 
   * @throws NullPointerException      If the given key is null.
   * @throws IllegalArgumentException  If the given key is invalid.
   */
  public static PemPublicKey publicKeyToPem(PublicKey key)
  {
    try
    (
        ByteArrayOutputStream bos       = new ByteArrayOutputStream();
        JcaPEMWriter          pemWriter = new JcaPEMWriter(new OutputStreamWriter(bos));
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
  
  /**
   * Decode the given PEM format key.
   * 
   * @param pem A PEM encoded key.
   * @return The unencoded key.
   * 
   * @throws NullPointerException      If the given encoding is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  public static PublicKey     publicKeyFromPem(PemPublicKey pem)
  {
    try(StringReader reader = new StringReader(pem.getValue()))
    {
      return publicKeyFromPem(reader);
    }
  }
  
  /**
   * Decode the given PEM format key.
   * 
   * @param reader A Reader containing a PEM encoded key.
   * @return The unencoded key.
   * 
   * @throws NullPointerException      If the given reader is null.
   * @throws IllegalArgumentException  If the given encoding is invalid.
   */
  public static PublicKey     publicKeyFromPem(Reader reader)
  {
    try(PEMParser       pemParser = new PEMParser(reader))
    {
      Object o = pemParser.readObject();
      
      if (o == null || !(o instanceof SubjectPublicKeyInfo))
      {
        throw new IllegalArgumentException("Not a PEM public key");
      }
      
      return new JcaPEMKeyConverter().setProvider(AbstractPublicKeyCipherSuite.PROVIDER).getPublicKey((SubjectPublicKeyInfo)o);
    }
    catch (IOException e)
    {
      throw new IllegalArgumentException(e);
    }
  }
}
