/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authz.model;

import com.symphony.s2.authc.model.IMultiTenantServiceRegistry;
import com.symphony.s2.authz.canon.facade.IEntitlementId;

/**
 * Base class which provides utilities to extract an IEntitlementId from various types.
 * 
 * This allows the API to implement one method which can be passed any one of:
 *  - IEntitlementSpec
 *  - IServiceEntitlementSpec
 *  - IEntitlementIdProvider
 *  - IEntitlementId
 * 
 * @author Bruce Skingle
 *
 */
public class EntitlementSpecAdaptor
{
  private final IMultiTenantServiceRegistry serviceRegistry_;
  
  protected EntitlementSpecAdaptor(IMultiTenantServiceRegistry serviceRegistry)
  {
    serviceRegistry_ = serviceRegistry;
  }

  protected IEntitlementId getEntitlementId(IServiceEntitlementSpecOrIdProvider entitlement)
  {
    if(entitlement instanceof IServiceEntitlementSpec)
    {
      IServiceEntitlementSpec spec = (IServiceEntitlementSpec)entitlement;
      
      return spec.getEntitlementId(serviceRegistry_.fetchServiceInfo(spec.getOwner()).getUserId());
    }
    
    if(entitlement instanceof IEntitlementIdProvider)
    {
      return ((IEntitlementIdProvider) entitlement).getEntitlementId();
    }
    
    throw new IllegalArgumentException("Unknown sub-interface of IServiceEntitlementSpecOrIdProvider: " + entitlement.getClass());
  }
  


  protected IEntitlementId getEntitlementId(IGeneralEntitlementSpec entitlement)
  {
    if(entitlement instanceof IServiceEntitlementSpec)
    {
      IServiceEntitlementSpec spec = (IServiceEntitlementSpec)entitlement;
      
      return spec.getEntitlementId(serviceRegistry_.fetchServiceInfo(spec.getOwner()).getUserId());
    }
    
    if(entitlement instanceof IEntitlementIdProvider)
    {
      return ((IEntitlementIdProvider) entitlement).getEntitlementId();
    }
    
    throw new IllegalArgumentException("Unknown sub-interface of IServiceEntitlementSpecOrIdProvider: " + entitlement.getClass());
  }
}
