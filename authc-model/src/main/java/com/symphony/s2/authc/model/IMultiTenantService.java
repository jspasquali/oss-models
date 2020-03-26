/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authc.model;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;

/**
 * A descriptor for a Multi Tenant Service.
 * 
 * @author Bruce Skingle
 *
 */
public interface IMultiTenantService
{
  /**
   * 
   * @return the service name.
   */
  String  getName();
  
  /**
   * Return the service user ID in the given admin pod.
   * 
   * @param adminPodId The PodId of the admin Pod.
   * 
   * @return The service user ID in the given admin pod.
   */
  PodAndUserId getUserId(PodId adminPodId);

}
