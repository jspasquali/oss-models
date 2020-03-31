/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.s2.authc.model.MultiTenantService;
import com.symphony.s2.authz.canon.facade.IEntitlementId;

/**
 * Specification of a service owned user entitlement to be used to link entitlements to code.
 * 
 * @author Bruce Skingle
 *
 */
public interface IServiceEntitlementSpec extends IGeneralEntitlementSpec
{
  /**
   * 
   * @return The service owning the entitlement.
   */
  MultiTenantService getOwner();

  /**
   * Return the is of this entitlement given the external userId for the owner.
   * 
   * @param ownerId The external userId for the owner
   * 
   * @return The entitlement ID.
   */
  IEntitlementId getEntitlementId(PodAndUserId ownerId);
}
