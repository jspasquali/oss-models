/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

import com.symphony.s2.authz.canon.facade.IEntitlementId;

/**
 * Specification of an entitlement to be used to link entitlements to code.
 * 
 * @author Bruce Skingle
 *
 */
public interface IEntitlementIdProvider extends IServiceEntitlementSpecOrIdProvider
{
  /**
   * 
   * @return The ID of the entitlement, this includes the owning userId since IEntitlementId extends INamedUserIdObject.
   */
  IEntitlementId          getEntitlementId();
}
