/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authc.model;

/**
 * 
 * An authentication and authorization context for some process.
 * 
 * An instance of this type is passed to service methods to indicate the identity and permissions of the service caller.
 * 
 * @author Geremia Longobardo
 *
 */
public interface ISupportContext
{

  /**
   * 
   * @return true if the Support Portral user is enabled to perform an action
   */
  boolean hasEntitlements();
  /**
   * 
   * @return the email
   */
  String  getEmail();
}