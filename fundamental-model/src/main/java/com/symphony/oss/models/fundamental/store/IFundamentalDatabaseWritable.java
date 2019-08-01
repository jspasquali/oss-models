/*
 * Copyright 2018-2019 Symphony Communication Services, LLC.
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

import java.util.List;

import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.fugue.pipeline.IThreadSafeConsumer;
import org.symphonyoss.s2.fugue.store.ObjectExistsException;

import com.symphony.oss.models.fundamental.canon.facade.IApplicationObject;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.INotification;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSimpleSecurityContext;

/**
 * A writable object database for Fundamental Objects.
 * 
 * Implementations of this interface are responsible for storing and publishing objects as they are created.
 * 
 * @author Bruce Skingle
 *
 */
public interface IFundamentalDatabaseWritable extends IFundamentalDatabaseReadOnly, IThreadSafeConsumer<IFundamentalObject>
{
  /**
   * Store the given fundamental object, and publish a default notification on pubsub.
   * 
   * @param instance  An object to be stored.
   * @param trace     A trace context.
   */
  void save(IFundamentalObject instance, ITraceContext trace);
  

  /**
   * Store the given fundamental object, and publish the given notification on pubsub.
   * 
   * @param instance      An object to be stored.
   * @param trace         A trace context.
   * @param notifications A list of notifications to be published.
   */
  void save(IFundamentalObject instance, ITraceContext trace, List<INotification> notifications);

  /**
   * Store the given fundamental IS, and publish a default notification on pubsub.
   * 
   * @param id        An ID object to be stored.
   * @param trace     A trace context.
   * 
   * @return The generated FundamentalObject wrapper for the ID.
   */
  IFundamentalObject save(IFundamentalId id, ITraceContext trace);

  /**
   * Create a new version of the given existingVersion containing the values in the given updatedVersion.
   * 
   * @param existingVersion A versioned object to be updated.
   * @param updatedVersion  The new version.
   * @param securityContext The opened security context for (both) objects.
   * @param signingKey      The key with which to sign the object.
   * @param trace           A trace context.
   * 
   * @return The generated FundamentalObject wrapper for the updted object.
   */
  <T extends IApplicationObject> IFundamentalObject update(T existingVersion, T updatedVersion, IOpenSimpleSecurityContext securityContext, IOpenSigningKey signingKey,
     ITraceContext trace);

  /**
   * Publish the given notification on pubsub.
   * 
   * @param trace         A trace context.
   * @param notifications A list of notifications to be published.
   */
  void publishNotifications(ITraceContext trace, List<INotification> notifications);

  /**
   * Save the given object iff its ID object, also given, does not exist.
   * 
   * @param id                    An ID object.
   * @param fundamentalObject     A VersionedObject whose baseHash is the absoluteHash of the given ID.
   * @param trace                 A trace context.
   * 
   * @throws ObjectExistsException if the given idObject already exists, and no change has been made to the object store.
   */
  void saveIfNotExists(IFundamentalId id, IFundamentalObject fundamentalObject, ITraceContext trace) throws ObjectExistsException;

  /**
   * Save the given object iff its ID object, also given, does not exist.
   * 
   * @param id                    An ID object.
   * @param fundamentalObject     A VersionedObject whose baseHash is the absoluteHash of the given ID.
   * @param trace                 A trace context.
   * @param objectNotifications   One or more Notifications for the object, a notification for the ID will be added if it is created.
   * 
   * @throws ObjectExistsException if the given idObject already exists, and no change has been made to the object store.
   */
  void saveIfNotExists(IFundamentalId id, IFundamentalObject fundamentalObject, ITraceContext trace,
      List<INotification> objectNotifications) throws ObjectExistsException;


}
