/*
 * Copyright 2019 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authc.model;

import java.security.PublicKey;

import org.apache.http.impl.client.CloseableHttpClient;

import com.symphony.oss.canon.runtime.exception.NotFoundException;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.s2.authc.canon.AuthcHttpModelClient;
import com.symphony.s2.authc.canon.UsersUserIdKeysFirstGetHttpRequest;
import com.symphony.s2.authc.canon.UsersUserIdKeysKeyIdGetHttpRequest;
import com.symphony.s2.authc.canon.facade.IPrincipalAuthcKey;
import com.symphony.s2.authc.canon.facade.KeyId;

/**
 * A JWT authenticator which obtains valid signature verification keys from the authc service.
 * 
 * @author Bruce Skingle
 *
 */
public class RemoteJwtAuthenticator extends AbstractJwtAuthenticator
{
  private final AuthcHttpModelClient authcClient_;
  private final CloseableHttpClient  httpclient_;
  
  /**
   * Constructor.
   * 
   * @param authcClient Authc service API client.
   * @param httpclient  Http client to connect with.
   */
  public RemoteJwtAuthenticator(AuthcHttpModelClient authcClient, CloseableHttpClient httpclient)
  {
    authcClient_ = authcClient;
    httpclient_ = httpclient;
  }

//  /**
//   * Constructor.
//   * 
//   * Trusted Certs can be loaded from resources like this:
//   * 
//   * <code>CipherSuite.getDefault().certificateFromPemResource(SYMPHONY_DEV_QA_ROOT_CERT)</code>
//   * 
//   * @param config          Global config.
//   * @param modelRegistry   A model registry containing the Authc Model factories.
//   * @param jwtGenerator    A generator of JWTs to be used to authenticate the calling service to the authc service.
//   */
//  public RemoteJwtAuthenticator(IGlobalConfiguration config, IModelRegistry modelRegistry, IAuthenticationProvider jwtGenerator)
//  {
//    this(config.getEnvironmentType(), config.getEnvironmentId(), modelRegistry, jwtGenerator);
//  }
  
//  /**
//   * Constructor.
//   * 
//   * @param environmentType The environment type, dev, qa, stage, uat or prod
//   * @param environmentId   The (sub-) environment ID, usually master
//   * @param modelRegistry   A model registry containing the Authc Model factories.
//   * @param jwtGenerator    A generator of JWTs to be used to authenticate the calling service to the authc service.
//   */
//  public RemoteJwtAuthenticator(String environmentType, String environmentId, IModelRegistry modelRegistry,
//      IAuthenticationProvider jwtGenerator)
//  {
//    MultiTenantServiceHelper helper = new MultiTenantServiceHelper(environmentType, environmentId, MultiTenantService.AUTHC);
//    
//    url = helper.getUrl();
//    authcClient_ = new AuthcHttpModelClient(modelRegistry, url_, null, jwtGenerator);
//    
//    log_.info("Fetching validation keys from " + url_);
//
//    httpclient_ = helper.getHttpclient();
//
//  }

//  private void configureTrust(HttpClientBuilder httpBuilder, List<X509Certificate> trustedCerts, TrustStrategy sslTrustStrategy)
//  {
//    try
//    {
//      KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());
//      trustStore.load(null);
//      
//      if(trustedCerts != null)
//      {
//        int n=1;
//        
//        for(X509Certificate trustedCert : trustedCerts)
//        {
//          trustStore.setCertificateEntry("cert" + n++, trustedCert);
//        }
//      }
//      
//      // Trust own CA and all self-signed certs
//      SSLContext sslcontext = org.apache.http.ssl.SSLContexts.custom()
//              .loadTrustMaterial(trustStore, sslTrustStrategy)
//              .build();
//
//      SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//              sslcontext,
//              null,
//              null,
//              (HostnameVerifier)null);
//      
//      httpBuilder.setSSLSocketFactory(sslsf);
//    }
//    catch(GeneralSecurityException | IOException e)
//    {
//      throw new IllegalStateException("Failed to configure SSL trust", e);
//    }
//  }
  
  @Override
  protected PublicKey fetchPublicKey(PodAndUserId userId, KeyId keyId)
  {
    IPrincipalAuthcKey principalAuthKey = null;
    
    try
    {
      if(keyId == null)
      {
        UsersUserIdKeysFirstGetHttpRequest request = authcClient_.newUsersUserIdKeysFirstGetHttpRequestBuilder()
            .withUserId(userId)
            .build();
        
        principalAuthKey = request.execute(httpclient_);
      }
      else
      {
        UsersUserIdKeysKeyIdGetHttpRequest request = authcClient_.newUsersUserIdKeysKeyIdGetHttpRequestBuilder()
            .withUserId(userId)
            .withKeyId(keyId)
            .build();
        
        principalAuthKey = request.execute(httpclient_);
      }
      
      if(principalAuthKey == null)
        return null;
    }
    catch(NotFoundException e)
    {
      return null;
    }
    
    return principalAuthKey.getPublicKey();
  }
}
