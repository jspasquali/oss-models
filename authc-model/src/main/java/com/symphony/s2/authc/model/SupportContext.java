/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An authentication and authorization context for support portal.
 * 
 * @author Geremia Longobardo
 *
 */
public class SupportContext implements ISupportContext
{
  private List<String> entitlements_ = new ArrayList<>();
  private String       email_;

  /**
   * Constructor.
   * 
   * See the interface class comment for details.
   * 
   * @param email                 Email of PMP user
   * @param entitlements          List of groups in the JWT
   */
  public SupportContext(String email, List<String> entitlements )
  {
    email_        = email;
    entitlements_ = entitlements;
  }

  @Override
  public boolean hasEntitlements()
  {
    return true;
  }

  @Override
  public String getEmail()
  {
    return email_;
  }

}
