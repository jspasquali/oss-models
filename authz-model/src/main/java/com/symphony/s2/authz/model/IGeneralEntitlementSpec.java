/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

/**
 * Super interface of IEntitlementSpec and IServiceEntitlementSpec.
 * 
 * @author Bruce Skingle
 *
 */
public interface IGeneralEntitlementSpec extends IServiceEntitlementSpecOrIdProvider
{
  /**
   * 
   * @return A description of the semantics of the entitlement.
   */
  String          getDescription();
}
