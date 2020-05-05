/*
 * Copyright 2018 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.models.core;

import com.symphony.oss.fugue.IFugueApplication;

public enum Application implements IFugueApplication
{
  Init(7901)
  ;

  private final int httpPort_;
  
  private Application(int httpPort)
  {
    httpPort_ = httpPort;
  }

  @Override
  public String getName()
  {
    return toString();
  }

  @Override
  public int getHttpPort()
  {
    return httpPort_;
  }

}
