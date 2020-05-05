/*
 * Copyright 2020 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authc.model;

import java.util.HashMap;
import java.util.Map;

import com.symphony.oss.fugue.IFugueApplication;
import com.symphony.oss.fugue.store.NoSuchObjectException;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.core.canon.facade.UserId;

/**
 * Enumeration of multi-tenant services.
 * 
 * @author Bruce Skingle
 *
 */
public enum MultiTenantService implements IFugueApplication, IMultiTenantService
{
  /** Authentication service. */
  AUTHC("authc",    1,  7002),
  
  /** Object Store */
  OBJECT("object",  2,  7001),
  
  /** Market Solutions API */
  MSAPI("msapi",    3,  7004),
  
  /** Authorization Service */
  AUTHZ("authz",    4,  7003),
  
  /** SBE - Obviously not actually a multi tenant service but we need an Owner ID for entitlements and other SBE owned assets. */
  SBE("sbe",        5,  7005),
  
  /** The example service, for development and testing purposes. */
  EXAMPLE("example", 6, 7006)
  ;

  private static final Map<String, MultiTenantService> nameMap_ = new HashMap<>();
  
  static
  {
    for(MultiTenantService service : values())
      nameMap_.put(service.getName(), service);
  }
  
  /**
   * Return the service with the given name.
   * 
   * @param name The name of the  required service.
   * 
   * @return The service with the given name.
   * 
   * @throws NoSuchObjectException If the given name is not a valid service.
   */
  public static MultiTenantService getServiceByName(String name) throws NoSuchObjectException
  {
    MultiTenantService service = nameMap_.get(name);
    
    if(service == null)
      throw new NoSuchObjectException("Unknown service \"" + name + "\"");
    
    return service;
  }

  private final String               name_;
  private final UserId               localUserId_;
  private final int                  httpPort_;
  
  private MultiTenantService(String name, int localUserId, int httpPort)
  {
    name_ = name;
    localUserId_ = UserId.newBuilder().build((long)localUserId);
    httpPort_ = httpPort;
  }

  @Override
  public String getName()
  {
    return name_;
  }

  /**
   * 
   * @return The local userId for the service.
   */
  public UserId getLocalUserId()
  {
    return localUserId_;
  }
  
  @Override
  public PodAndUserId getUserId(PodId adminPodId)
  {
    return PodAndUserId.newBuilder().build(adminPodId, localUserId_);
  }

  @Override
  public int getHttpPort()
  {
    return httpPort_;
  }
}
