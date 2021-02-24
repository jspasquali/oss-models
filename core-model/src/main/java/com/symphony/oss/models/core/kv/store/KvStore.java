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

import java.io.StringReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.fugue.kv.IKvItem;
import com.symphony.oss.fugue.kv.IKvPagination;
import com.symphony.oss.fugue.kv.IKvPartitionKeyProvider;
import com.symphony.oss.fugue.kv.IKvPartitionSortKeyProvider;
import com.symphony.oss.fugue.kv.KvCondition;
import com.symphony.oss.fugue.kv.table.IKvTable;
import com.symphony.oss.fugue.store.NoSuchObjectException;
import com.symphony.oss.fugue.trace.ITraceContext;

/**
 * Implementation of IKvStore.
 * 
 * @author Bruce Skingle
 *
 */
public class KvStore implements IKvStore
{
  private final IKvTable       kvTable_;
  private final IModelRegistry modelRegistry_;
  
  /**
   * Constructor.
   * 
   * @param kvTable         The low level storage.
   * @param modelRegistry   A model registry.
   */
  public KvStore(IKvTable kvTable, IModelRegistry modelRegistry)
  {
    kvTable_ = kvTable;
    modelRegistry_ = modelRegistry;
  }

  @Override
  public void store(Collection<IKvItem> kvItems, ITraceContext trace)
  {
    kvTable_.store(kvItems, trace);
  }

  @Override
  public void store(IKvItem kvItem, KvCondition condition, ITraceContext trace)
  {
    kvTable_.store(kvItem, condition, trace);
  }
  
  @Override
  public void storeEntitlementMapping(IKvItem kvItem, KvCondition effective, KvCondition entAction, String action, ITraceContext trace)
  {
    kvTable_.storeEntitlementMapping(kvItem, effective, entAction, action, trace);  
  }
  
  private <T extends IKvItem> T normalize(String json, Class<T> type)
  {
    IEntity entity = modelRegistry_.parseOne(new StringReader(json));
    
    if(type.isInstance(entity))
      return type.cast(entity);
    
    throw new IllegalStateException("Retrieved object is of type " + entity.getClass() + " not " + type);
  }

  @Override
  public <T extends IKvItem> T fetch(IKvPartitionSortKeyProvider partitionSortKey, Class<T> type, ITraceContext trace) throws NoSuchObjectException
  {
    return normalize(kvTable_.fetch(partitionSortKey, trace), type);
  }

  @Override
  public IKvItem fetch(IKvPartitionSortKeyProvider partitionSortKey, ITraceContext trace) throws NoSuchObjectException
  {
    return normalize(kvTable_.fetch(partitionSortKey, trace), IKvItem.class);
  }

  @Override
  public <T extends IKvItem> T fetchFirst(IKvPartitionKeyProvider partitionKey, Class<T> type, ITraceContext trace) throws NoSuchObjectException
  {
    return normalize(kvTable_.fetchFirst(partitionKey, trace), type);
  }

  @Override
  public IKvItem fetchFirst(IKvPartitionKeyProvider partitionKey, ITraceContext trace) throws NoSuchObjectException
  {
    return normalize(kvTable_.fetchFirst(partitionKey, trace), IKvItem.class);
  }

  @Override
  public IKvItem fetchLast(IKvPartitionKeyProvider partitionKey, ITraceContext trace) throws NoSuchObjectException
  {
    return normalize(kvTable_.fetchLast(partitionKey, trace), IKvItem.class);
  }

  @Override
  public <T extends IKvItem> T fetchLast(IKvPartitionKeyProvider partitionKey, Class<T> type, ITraceContext trace)
      throws NoSuchObjectException
  {
    return normalize(kvTable_.fetchLast(partitionKey, trace), type);
  }

  @Override
  public <T extends IKvItem> IKvPagination fetch(IKvPartitionKeyProvider partitionKey, boolean scanForwards, Integer limit, String after, String sortKeyPrefix,  
      @Nullable String sortKeyMin, @Nullable String sortKeyMax,
      Class<T> type, Map<String, Object> filterAttributes, Consumer<T> consumer, ITraceContext trace)
  {
    Consumer<String> stringConsumer = new Consumer<String>()
    {
      @Override
      public void accept(String json)
      {
        consumer.accept(normalize(json, type));
      }
    };
    
    return kvTable_.fetchPartitionObjects(partitionKey, scanForwards, limit, after, sortKeyPrefix,
        sortKeyMin,
        sortKeyMax, 
        filterAttributes, stringConsumer, trace);
  }

}
