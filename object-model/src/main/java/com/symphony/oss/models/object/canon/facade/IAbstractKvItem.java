/*
 * Copyright 2019 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.models.object.canon.facade;

import com.symphony.oss.models.core.canon.facade.PodAndUserId;

/**
 * Interface for object store types which are KvItems with derived partition and sort keys.
 * 
 * @author Bruce Skingle
 *
 */
public interface IAbstractKvItem extends org.symphonyoss.s2.fugue.kv.IKvItem
{
  /**
   * 
   * @return The owner of this object.
   */
  PodAndUserId getOwner();
}
