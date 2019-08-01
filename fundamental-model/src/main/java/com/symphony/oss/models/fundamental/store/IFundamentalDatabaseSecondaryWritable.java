/*
 * Copyright 2018 Symphony Communication Services, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.symphony.oss.models.fundamental.store;

import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

import com.symphony.oss.models.fundamental.canon.facade.INotification;

/**
 * A secondary storage writable object database for Fundamental Objects.
 * 
 * @author Bruce Skingle
 *
 */
public interface IFundamentalDatabaseSecondaryWritable extends IFundamentalDatabaseReadOnly
{
  /**
   * Perform secondary storage processing on the given message.
   * 
   * @param notification        A notification message for the object to be stored. 
   * @param payloadLimit        Max size of payload which will be written to primary storage.
   * @param validSequenceHashes Sequences to which the object should be added.
   * @param trace               A trace context.
   */
  void saveToSecondaryStorage(INotification notification, int payloadLimit, ValidSequenceHashes validSequenceHashes, ITraceContext trace);
}
