/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authc.model;

import com.symphony.s2.authc.canon.IServiceInfo;

/**
 * A service to look up information about multi tenant services.
 * 
 * @author Bruce Skingle
 *
 */
public interface IMultiTenantServiceRegistry
{
  /**
   * Return service information for the given service.
   * 
   * @param service The service for which info should be returned.
   * 
   * @return Service information for the given service.
   */
  IServiceInfo fetchServiceInfo(IMultiTenantService service);

}
