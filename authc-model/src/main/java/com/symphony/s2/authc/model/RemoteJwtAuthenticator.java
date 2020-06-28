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
import com.symphony.s2.authc.canon.KeysKeyIdGetHttpRequest;
import com.symphony.s2.authc.canon.UsersUserIdKeysFirstGetHttpRequest;
import com.symphony.s2.authc.canon.UsersUserIdKeysKeyIdGetHttpRequest;
import com.symphony.s2.authc.canon.facade.IJwk;
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
  
  @Override
  protected PublicKey fetchPublicKey(PodAndUserId userId, KeyId keyId)
  {
    IPrincipalAuthcKey principalAuthKey = null;
    
    try
    {
      if(userId == null && keyId != null)
      {
        KeysKeyIdGetHttpRequest request = authcClient_.newKeysKeyIdGetHttpRequestBuilder()
            .withKeyId(keyId)
            .build();
        
        IJwk jwk = request.execute(httpclient_);
        
        return jwk.getPublicKey();
      }
      else if(keyId == null)
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
