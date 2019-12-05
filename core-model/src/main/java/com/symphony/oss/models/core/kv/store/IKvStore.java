/*
 *
 *
 * Copyright 2019 Symphony Communication Services, LLC.
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.symphony.oss.models.core.kv.store;

import java.util.Collection;

import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.kv.IKvItem;
import org.symphonyoss.s2.fugue.kv.IKvPartitionKeyProvider;
import org.symphonyoss.s2.fugue.kv.IKvPartitionSortKeyProvider;

/**
 * A Key Value Store.
 * 
 * @author Bruce Skingle
 *
 */
public interface IKvStore
{
  /**
   * Store the given items, in as few batches as possible.
   * 
   * @param kvItems Items to be stored.
   * @param trace   Trace context.
   */
  void store(Collection<IKvItem> kvItems, ITraceContext trace);

  /**
   * Fetch an item given a partition key and sort key.
   * 
   * @param partitionSortKey    A key containing the podId, partition key and sort key for the item.
   * @param trace               Trace context.
   * 
   * @return The required item.
   * 
   * @throws NoSuchObjectException If the item does not exist.
   */
  IKvItem fetch(IKvPartitionSortKeyProvider partitionSortKey, ITraceContext trace) throws NoSuchObjectException;
  
  /**
   * Fetch an item given a partition key. If there is more than one row in the partition the first one will
   * be returned.
   * 
   * @param partitionKey    A key containing the podId and partition key for the item.
   * @param trace           Trace context.
   * 
   * @return The required item.
   * 
   * @throws NoSuchObjectException If the item does not exist.
   */
  IKvItem fetchFirst(IKvPartitionKeyProvider partitionKey, ITraceContext trace) throws NoSuchObjectException;
  
  /**
   * Fetch an item given a partition key. If there is more than one row in the partition the last one will
   * be returned.
   * 
   * @param partitionKey    A key containing the podId and partition key for the item.
   * @param trace           Trace context.
   * 
   * @return The required item.
   * 
   * @throws NoSuchObjectException If the item does not exist.
   */
  IKvItem fetchLast(IKvPartitionKeyProvider partitionKey, ITraceContext trace) throws NoSuchObjectException;

  /**
   * Fetch an item given a partition key and sort key.
   * @param <T> 
   * 
   * @param partitionSortKey    A key containing the podId, partition key and sort key for the item.
   * @param type                The type of the object to be returned.
   * @param trace               Trace context.
   * 
   * @return The required item.
   * 
   * @throws NoSuchObjectException If the item does not exist.
   * @throws IllegalStateException If the object exists but is of an incompatible type to that passed.
   */
  <T extends IKvItem> T fetch(IKvPartitionSortKeyProvider partitionSortKey, Class<T> type, ITraceContext trace) throws NoSuchObjectException;
  
  /**
   * Fetch an item given a partition key. If there is more than one row in the partition the first one will
   * be returned.
   * 
   * @param partitionKey    A key containing the podId and partition key for the item.
   * @param type                The type of the object to be returned.
   * @param trace           Trace context.
   * 
   * @return The required item.
   * 
   * @throws NoSuchObjectException If the item does not exist.
   * @throws IllegalStateException If the object exists but is of an incompatible type to that passed.
   */
  <T extends IKvItem> T fetchFirst(IKvPartitionKeyProvider partitionKey, Class<T> type, ITraceContext trace) throws NoSuchObjectException;
  
  /**
   * Fetch an item given a partition key. If there is more than one row in the partition the last one will
   * be returned.
   * 
   * @param partitionKey    A key containing the podId and partition key for the item.
   * @param type                The type of the object to be returned.
   * @param trace           Trace context.
   * 
   * @return The required item.
   * 
   * @throws NoSuchObjectException If the item does not exist.
   * @throws IllegalStateException If the object exists but is of an incompatible type to that passed.
   */
  <T extends IKvItem> T fetchLast(IKvPartitionKeyProvider partitionKey, Class<T> type, ITraceContext trace) throws NoSuchObjectException;

}
