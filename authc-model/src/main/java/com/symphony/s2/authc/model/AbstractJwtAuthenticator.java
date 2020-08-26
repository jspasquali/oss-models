/*
 * Copyright 2019 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authc.model;

import java.security.Key;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.symphony.oss.canon.runtime.exception.CanonException;
import com.symphony.oss.canon.runtime.exception.NotAuthenticatedException;
import com.symphony.oss.canon.runtime.exception.PermissionDeniedException;
import com.symphony.oss.canon.runtime.exception.ServerErrorException;
import com.symphony.oss.canon.runtime.http.IRequestAuthenticator;
import com.symphony.oss.canon.runtime.http.IRequestContext;
import com.symphony.oss.canon.runtime.jjwt.JwtBase;
import com.symphony.oss.commons.fault.CodingFault;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.crypto.canon.PemCertificate;
import com.symphony.oss.models.crypto.cipher.CipherSuite;
import com.symphony.oss.models.crypto.cipher.ICipherSuite;
import com.symphony.s2.authc.canon.facade.KeyId;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolverAdapter;

/**
 * 
 * A request authenticator which validates JWT tokens issued by SBE using pod certificates.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class AbstractJwtAuthenticator extends JwtBase implements IRequestAuthenticator<IAuthcContext>, IJwtAuthenticator
{
  private static final Logger log_ = LoggerFactory.getLogger(AbstractJwtAuthenticator.class);
  
  /** The issuer for API Gateway */
  private static final String                  CLAIM_API_GATEWAY_ISSUER = "symphony.com/api-gateway";
  
  /** A claim which only the common JWT has */
  private static final Object                  COMMON_JWT_CLAIM         = "ext_pod_id";

  private static final InvalidPodPublicKey     INVALID_POD              = new InvalidPodPublicKey();
  private static final Map<Integer, PublicKey> podKeyMap_               = new HashMap<>();
  private static final ICipherSuite            cipherSuite_             = CipherSuite.getDefault();

  
//  static
//  {
//    register(166, "-----BEGIN CERTIFICATE-----\nMIIEQDCCAyigAwIBAgIVAIOWgwlXTVSjTT9lJS3ztXAUJtrKMA0GCSqGSIb3DQEB\nCwUAMGQxJjAkBgNVBAMMHUlzc3VpbmcgQ2VydGlmaWNhdGUgQXV0aG9yaXR5MS0w\nKwYDVQQKDCRTeW1waG9ueSBDb21tdW5pY2F0aW9uIFNlcnZpY2VzIExMQy4xCzAJ\nBgNVBAYTAlVTMB4XDTE2MDgxOTAwNDU1NFoXDTM2MDgxOTAwNDU1NFowZDEmMCQG\nA1UEAwwdSXNzdWluZyBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkxLTArBgNVBAoMJFN5\nbXBob255IENvbW11bmljYXRpb24gU2VydmljZXMgTExDLjELMAkGA1UEBhMCVVMw\nggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC3C6LpX2Uo70lUe+HNscZA\nA6Dw9TSvoU4fmpN+tNV4JEHZozLwhPauyUNmWXjR9HMvUt/E5sAhvlTi9kw+nbgk\n27Vh9sn7l+LL70/CqG6PQE5QaHJ+x2NakL8kDwv6hqW9Fyf3wPSdkGc5RNq5wvw0\nTEijyYYbIvvGgDevGBWAHsv83OD0G3GNnoxin82B/rcCzWa3Bs9YJ56H9AkTeMhQ\nMxqpgeq/CHfop7iijHnEEiwD98NTn2B5DhOzz9WM/H5EIHLyl2Nx3xhMc2wHi+bZ\nmt4rziGSmUyhEwapmy6AuPN7oTqntyQWZPXCHQY5iBljHDjzdm8csjX91Q6lhJNl\nAgMBAAGjgegwgeUwDgYDVR0PAQH/BAQDAgGGMA8GA1UdEwEB/wQFMAMBAf8wHQYD\nVR0OBBYEFNwShQT4OjtJ0ncGNG7iVzieI0H9MIGiBgNVHSMEgZowgZeAFNwShQT4\nOjtJ0ncGNG7iVzieI0H9oWikZjBkMSYwJAYDVQQDDB1Jc3N1aW5nIENlcnRpZmlj\nYXRlIEF1dGhvcml0eTEtMCsGA1UECgwkU3ltcGhvbnkgQ29tbXVuaWNhdGlvbiBT\nZXJ2aWNlcyBMTEMuMQswCQYDVQQGEwJVU4IVAIOWgwlXTVSjTT9lJS3ztXAUJtrK\nMA0GCSqGSIb3DQEBCwUAA4IBAQBCXpzfEFkK2bsh9H/LbQ3X8hflsWLGWVVJ9qZF\nwv05RAmwnrLrmU4wEtUY4BDaafTGSkpA5J1KOQjvqXdJDjDjVytkMskI+8Urx5fF\ngmAzdKwXdFst69Iey0pmWzPSz6S5tPOd3ohSI9dAwSV6E7P+oMJTJa9iplqUCvUJ\ntvZgCFPNVwW83N3C8KX8gxQIo81Rsp0NZ5711NYiV4NzoqNoTZoxCZS9kzSdLScm\nepDhL2sKQEMdkG24fkA6klGyzVQcMUzUc/e4FeqrM7wSnSyYhHATbjYB6eNBtDsw\ngrs48sjsdMKasQDePedEOgvKVBHeGGouL2lVsfhifXW2Orrg\n-----END CERTIFICATE-----\n");
//  }
  

  static void register(int podId, String pemCert)
  {
    X509Certificate cert = cipherSuite_.certificateFromPem(PemCertificate.newBuilder().build(pemCert));
    
    podKeyMap_.put(podId, cert.getPublicKey());
  }
  
  private final LoadingCache<CacheKey, PublicKey>  podKeyCache_ = CacheBuilder.newBuilder()
      .maximumSize(1000)
      .expireAfterWrite(60, TimeUnit.MINUTES)
      .build(
          new CacheLoader<CacheKey,PublicKey>()
          {
            @Override
            public PublicKey load(CacheKey key)
            {
              PublicKey result = fetchPublicKey(key.userId_, key.keyId_);
              
              if(result == null)
                return INVALID_POD;
              
              return result;
            }
          });
  
  class CacheKey
  {
    final PodAndUserId userId_;
    final KeyId        keyId_;
    final String       value_;
    
    public CacheKey(PodAndUserId userId, KeyId keyId)
    {
      super();
      userId_ = userId;
      keyId_ = keyId;
      value_ = userId_ + ":" + keyId_;
    }

    @Override
    public boolean equals(Object anObject)
    {
      return anObject instanceof CacheKey && value_.equals(((CacheKey)anObject).value_);
    }

    @Override
    public int hashCode()
    {
      return value_.hashCode();
    }

    @Override
    public String toString()
    {
      return value_.toString();
    }
  }

  protected abstract PublicKey fetchPublicKey(PodAndUserId userId, KeyId keyId);
  
  @Override
  public IAuthcContext authenticate(IRequestContext context) throws CanonException
  {
    String token = getToken(context);
  
    return new AuthcContext(authenticate(token));
  }
  
  @Override
  public IAuthcContext authenticate(HttpServletRequest context) throws CanonException
  {
    String token = getToken(context);
    
    return new AuthcContext(authenticate(token));
  }

  @Override
  public PodAndUserId authenticate(String token) throws NotAuthenticatedException
  {
    
    try
    {

      Jws<Claims> parsedJwt = Jwts.parserBuilder().setSigningKeyResolver(new AuthcSigningKeyResolver()).build()
          .parseClaimsJws(token);
      
      if(!getAlgorithm(parsedJwt).toString().equals(parsedJwt.getHeader().getAlgorithm()))
        throw new NotAuthenticatedException("Invalid JWT Token, unacceptable signature algorithm");
      
      Claims claims = parsedJwt.getBody();
      
      PodAndUserId userId = getUserId(claims);
      
      log_.info("Request from " + userId + " (user " + userId.getUserId() + " in pod " + userId.getPodId() + ")");
      
      return userId;
    }
    catch(JwtException e)
    {
      throw new NotAuthenticatedException("Invalid JWT token: " + e.getLocalizedMessage());
    }
    catch(RuntimeException e)
    {
      throw new ServerErrorException("Unable to verify JWT token", e);
    }
    
  }

  private SignatureAlgorithm getAlgorithm(Jws<Claims> token)
  {
    if(CLAIM_API_GATEWAY_ISSUER.equals(token.getBody().getIssuer()))
      return SignatureAlgorithm.HS512;
    
    if(token.getBody().get(COMMON_JWT_CLAIM) != null)
      return SignatureAlgorithm.RS256;
    
    return SignatureAlgorithm.RS512;
  }
  
  class AuthcSigningKeyResolver extends SigningKeyResolverAdapter
  {
    @Override
    public Key resolveSigningKey(@SuppressWarnings("rawtypes") JwsHeader header, Claims claims)
    {
      try
      {
        if(CLAIM_API_GATEWAY_ISSUER.equals(claims.getIssuer()))
        {
          // An API Gateway token
          throw new NotAuthenticatedException("Invalid JWT token (legacy API Gateway unsupported)");
        }
        else if(claims.get(COMMON_JWT_CLAIM) != null)
        {
          // A new common JWT
          
          return geEnvironmentKey(header);
        }
        else
        {
          // we think its an auth (wrapped skey) token.

          return getPodKey(claims, getUserId(claims));
        }
      }
      catch(NullPointerException | NumberFormatException e)
      {
        throw new NotAuthenticatedException("Invalid JWT token", e);
      }
    }
  }

  private PodAndUserId getUserId(Claims claims)
  {
    Object userIdObject = claims.get("userId");
    
    if(userIdObject == null)
      userIdObject = claims.get("sub");
    
    PodAndUserId userId = userIdObject instanceof Long ? PodAndUserId.newBuilder().build((Long) userIdObject) : PodAndUserId.newBuilder().build(Long.parseLong(userIdObject.toString()));
    PodId        podId  = getPodId(claims, "podId");
    
    if(podId != null)
      userId = PodAndUserId.newBuilder().build(podId, userId.getUserId());
    
    return userId;
  }

//  private PodAndUserId getApuGwUserId(Claims claims)
//  {
//    Object userIdObject = claims.get("sub");
//    
//    PodAndUserId userId = userIdObject instanceof Long ? PodAndUserId.newBuilder().build((Long) userIdObject) : PodAndUserId.newBuilder().build(Long.parseLong(userIdObject.toString()));
//    PodId        podId  = getPodId(claims, "ext_pod_id");
//    
//    if(podId != null)
//      userId = PodAndUserId.newBuilder().build(podId, userId.getUserId());
//    
//    return userId;
//  }

  private PodId getPodId(Claims claims, String name)
  {
    Object podIdObject = claims.get(name);
    
    if(podIdObject == null)
      return null;
    
    if(podIdObject instanceof Number)
      return PodId.newBuilder().build((Integer) podIdObject);
    
    return PodId.newBuilder().build(Integer.parseInt(podIdObject.toString()));
  }
  
  protected PublicKey getPodKey(Claims claims, PodAndUserId userId)
  {
    String keyIdStr = claims.get("kid", String.class);
    KeyId  keyId    = keyIdStr == null ? null : KeyId.newBuilder().build(keyIdStr);
    
    try
    {
      PublicKey result = podKeyCache_.get(new CacheKey(userId, keyId));
      
      if(result == INVALID_POD)
        throw new PermissionDeniedException("Invalid JWT token: Unknwon podId " + userId.getPodId());
      
      return result;
    }
    catch (ExecutionException e)
    {
      if(e.getCause() instanceof RuntimeException)
        throw (RuntimeException)e.getCause();
      
      throw new CodingFault(e);
    }
    catch(UncheckedExecutionException e)
    {
      if(e.getCause() instanceof RuntimeException)
        throw (RuntimeException)e.getCause();
      
      throw e;
    }
  }
  
  protected PublicKey geEnvironmentKey(JwsHeader<?> header)
  {
    String keyIdStr = header.getKeyId();
    KeyId  keyId    = keyIdStr == null ? null : KeyId.newBuilder().build(keyIdStr);
    
    try
    {
      PublicKey result = podKeyCache_.get(new CacheKey(null, keyId));
      
      if(result == INVALID_POD)
        throw new PermissionDeniedException("Invalid JWT token: Unknwon key " + keyId);
      
      return result;
    }
    catch (ExecutionException e)
    {
      if(e.getCause() instanceof RuntimeException)
        throw (RuntimeException)e.getCause();
      
      throw new CodingFault(e);
    }
    catch(UncheckedExecutionException e)
    {
      if(e.getCause() instanceof RuntimeException)
        throw (RuntimeException)e.getCause();
      
      throw e;
    }
  }

  static class InvalidPodPublicKey implements PublicKey
  {
    private static final long serialVersionUID = 1L;

    @Override
    public String getAlgorithm()
    {
      return null;
    }

    @Override
    public String getFormat()
    {
      return null;
    }

    @Override
    public byte[] getEncoded()
    {
      return null;
    }
  }
}
