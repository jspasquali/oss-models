/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

import com.symphony.s2.authz.canon.EntitlementType;

/**
 * Specification of an entitlement to be used to link entitlements to code.
 * 
 * @author Bruce Skingle
 *
 */
public interface IBaseEntitlementSpec
{
  /**
   * 
   * @return The Name (ID) of the entitlement
   */
  String          getName();
  
  /**
   * 
   * @return A description of the semantics of the entitlement.
   */
  String          getDescription();
  
  /**
   * 
   * @return The type of the entitlement, POD or USER.
   */
  EntitlementType getEntitlementType();
}
