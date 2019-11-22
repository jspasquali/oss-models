/*
 * Copyright 2019 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.models.core.canon.facade;

public class SplitUserId
{
  public static void main(String argv[])
  {
    PodAndUserId podAndUserId = PodAndUserId.newBuilder().build(68719539845L);
    
    System.out.println("User " + podAndUserId + " is local user " + podAndUserId.getUserId() + " in pod " + podAndUserId.getPodId());
  }
}
