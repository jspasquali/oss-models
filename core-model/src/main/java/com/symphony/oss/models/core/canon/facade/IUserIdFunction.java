/*
 * Copyright 2019 Symphony Communication Services, LLC.
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

package com.symphony.oss.models.core.canon.facade;

/**
 * A function to map user IDs from internal to external.
 * 
 * @author Bruce Skingle
 *
 */
@FunctionalInterface
public interface IUserIdFunction
{
  /**
   * Map the given internal or external user ID to its external form.
   * 
   * @param userId A user ID
   * @return The external form of that user ID.
   */
  PodAndUserId map(PodAndUserId userId);
}
