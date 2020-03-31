/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.s2.authz.canon.EntitlementType;
import com.symphony.s2.authz.canon.facade.EntitlementId;
import com.symphony.s2.authz.canon.facade.IEntitlementId;

/**
 * Specification of a service owned user entitlement with a simple name based ID.
 * 
 * This convenient interface provides a default implementation of getEntitlementId(PodAndUserId ownerId).
 * 
 * @author Bruce Skingle
 *
 */
public interface INamedServiceEntitlementSpec extends IServiceEntitlementSpec
{
  /**
   * 
   * @return The Name (ID) of the entitlement
   */
  String          getName();
  
  /**
   * 
   * @return The type of the entitlement, POD or USER.
   */
  EntitlementType getEntitlementType();

  @Override
  default IEntitlementId getEntitlementId(PodAndUserId ownerId)
  {
    return new EntitlementId.Builder()
        .withName(getName())
        .withEntitlementType(getEntitlementType())
        .withUserId(ownerId)
        .build();
  }
}
