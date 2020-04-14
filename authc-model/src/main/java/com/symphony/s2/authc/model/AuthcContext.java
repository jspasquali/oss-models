/*
 * Copyright 2019 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authc.model;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;

/**
 * An authentication and authorization context for some process.
 * 
 * @author Bruce Skingle
 *
 */
public class AuthcContext implements IAuthcContext
{
  private final PodAndUserId     userId_;
 
  /**
   * Constructor.
   * 
   * See the interface class comment for details.
   * 
   * @param userId          The SBE userId
   */
  public AuthcContext(PodAndUserId userId)
  {
    userId_ = userId;
  }

  @Override
  public PodId getPodId()
  {
    return userId_.getPodId();
  }

  @Override
  public PodAndUserId getUserId()
  {
    return userId_;
  }
}
