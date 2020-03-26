/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;

/**
 * Specification of a user entitlement to be used to link entitlements to code.
 * 
 * @author Bruce Skingle
 *
 */
public interface IEntitlementSpec extends IBaseEntitlementSpec
{
  /**
   * 
   * @return The userId of the owner of the entitlement.
   */
  PodAndUserId getOwner();
}
