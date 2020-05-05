/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

import org.apache.http.impl.client.CloseableHttpClient;

import com.symphony.oss.commons.hash.Hash;
import com.symphony.oss.fugue.trace.ITraceContext;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.s2.authc.model.IMultiTenantServiceRegistry;
import com.symphony.s2.authz.canon.AuthzHttpModelClient;
import com.symphony.s2.authz.canon.EntitlementRef;
import com.symphony.s2.authz.canon.EntitlementRequest;
import com.symphony.s2.authz.canon.IEntitlementRequest;
import com.symphony.s2.authz.canon.facade.IEntitlementId;

/**
 * Network implementation of IAuthzApi.
 * 
 * @author Bruce Skingle
 *
 */
public class BaseEntitlementValidator extends EntitlementSpecAdaptor implements IEntitlementValidator
{
  private final CloseableHttpClient         httpClient_;
  private final AuthzHttpModelClient        authzApiClient_;
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
    super(serviceRegistry);
    
    httpClient_ = httpClient;
    authzApiClient_ = authzApiClient;
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
      IServiceEntitlementSpecOrIdProvider... entitlements)
  {
    EntitlementRequest.Builder builder = new EntitlementRequest.Builder();
    
    for(IServiceEntitlementSpecOrIdProvider entitlement : entitlements)
    {
      IEntitlementId entitlementId = getEntitlementId(entitlement);

      builder.withEntitlementRefs(new EntitlementRef.Builder()
          .withEntitlementHash(entitlementId.getHash())
          .withEntitlementType(entitlementId.getEntitlementType())
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
