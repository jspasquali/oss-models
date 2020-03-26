/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

import com.symphony.s2.authc.model.MultiTenantService;

/**
 * Specification of a multi tenant service entitlement to be used to link entitlements to code.
 * 
 * @author Bruce Skingle
 *
 */
public interface IMultiTenantServiceEntitlementSpec extends IBaseEntitlementSpec
{
  /**
   * 
   * @return The service owning the entitlement.
   */
  MultiTenantService getOwner();
}
