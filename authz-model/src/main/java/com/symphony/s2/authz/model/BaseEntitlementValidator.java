/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

import org.apache.http.impl.client.CloseableHttpClient;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.object.canon.NamedUserIdObject;
import com.symphony.s2.authc.model.IMultiTenantServiceRegistry;
import com.symphony.s2.authz.canon.AuthzHttpModelClient;
import com.symphony.s2.authz.canon.EntitlementRef;
import com.symphony.s2.authz.canon.EntitlementRequest;
import com.symphony.s2.authz.canon.IEntitlementRequest;

/**
 * Network implementation of IAuthzApi.
 * 
 * @author Bruce Skingle
 *
 */
public class BaseEntitlementValidator implements IEntitlementValidator
{
  private final CloseableHttpClient         httpClient_;
  private final AuthzHttpModelClient        authzApiClient_;
  private final IMultiTenantServiceRegistry serviceRegistry_;
  
  /**
   * Constructor.
   * 
   * @param httpClient        Http client to use for API calls.
   * @param authzApiClient    Authorization service client.
   * @param serviceRegistry   Registry to resolve MultiTenantServiceEntitlementSpecs
   */
  public BaseEntitlementValidator(CloseableHttpClient httpClient, AuthzHttpModelClient authzApiClient,
      IMultiTenantServiceRegistry serviceRegistry)
  {
    httpClient_ = httpClient;
    authzApiClient_ = authzApiClient;
    serviceRegistry_ = serviceRegistry;
  }

  @Override
  public void ensureUserHasAllEntitlements(PodAndUserId subjectUserId, ITraceContext trace, Hash... hashes)
  {
    EntitlementRequest.Builder builder = new EntitlementRequest.Builder();
    
    for(Hash hash : hashes)
    {
      builder.withEntitlementRefs(new EntitlementRef.Builder()
          .withEntitlementHash(hash)
          .build()
          );
    }
    
    ensureUserHasAllEntitlements(subjectUserId, builder.build());
  }

  @Override
  public void ensureUserHasAllEntitlements(PodAndUserId subjectUserId, ITraceContext trace,
      IEntitlementSpec... entitlementSpecs)
  {
    EntitlementRequest.Builder builder = new EntitlementRequest.Builder();
    
    for(IEntitlementSpec entitlementSpec : entitlementSpecs)
    {
      Hash hash = new NamedUserIdObject.Builder()
          .withUserId(entitlementSpec.getOwner())
          .withName(entitlementSpec.getName())
          .build()
          .getHash();

      builder.withEntitlementRefs(new EntitlementRef.Builder()
          .withEntitlementHash(hash)
          .withEntitlementType(entitlementSpec.getEntitlementType())
          .build()
          );
    }
    
    ensureUserHasAllEntitlements(subjectUserId, builder.build());
  }

  @Override
  public void ensureUserHasAllEntitlements(PodAndUserId subjectUserId, ITraceContext trace,
      IMultiTenantServiceEntitlementSpec... entitlementSpecs)
  {
    EntitlementRequest.Builder builder = new EntitlementRequest.Builder();
    
    for(IMultiTenantServiceEntitlementSpec entitlementSpec : entitlementSpecs)
    {
      Hash hash = new NamedUserIdObject.Builder()
          .withUserId(serviceRegistry_.fetchServiceInfo(entitlementSpec.getOwner()).getUserId())
          .withName(entitlementSpec.getName())
          .build()
          .getHash();

      builder.withEntitlementRefs(new EntitlementRef.Builder()
          .withEntitlementHash(hash)
          .withEntitlementType(entitlementSpec.getEntitlementType())
          .build()
          );
    }
    
    ensureUserHasAllEntitlements(subjectUserId, builder.build());
  }

  private void ensureUserHasAllEntitlements(PodAndUserId subjectUserId, IEntitlementRequest entitlementRequest)
  {
    authzApiClient_.newUsersUserIdEntitlementsHasAllPostHttpRequestBuilder()
      .withUserId(subjectUserId)
      .withCanonPayload(entitlementRequest)
      .build()
      .execute(httpClient_);
  }
}
