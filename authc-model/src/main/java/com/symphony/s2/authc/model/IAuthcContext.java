/*
 * Copyright 2019 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authc.model;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;

/**
 * 
 * An authentication and authorization context for some process.
 * 
 * An instance of this type is passed to service methods to indicate the identity and permissions of the service caller.
 * 
 * @author Bruce Skingle
 *
 */
public interface IAuthcContext
{
  /**
   * 
   * @return the podId of the caller.
   */
  PodId  getPodId();

  /**
   * 
   * @return The SBE user ID of the caller.
   */
  PodAndUserId getUserId();
}
