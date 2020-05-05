/*
 *
 *
 * Copyright 2020 Symphony Communication Services, LLC.
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

package com.symphony.oss.models.object;

import com.symphony.oss.canon.runtime.ModelRegistry;
import com.symphony.oss.models.object.canon.facade.IStoredApplicationObject;

/**
 * Specialisation of ModelRegistry to allow ApplicationObjectPayload to take a reference to its containing StoredApplicationObject.
 * 
 * @author Bruce Skingle
 *
 */
public class ObjectModelRegistry extends ModelRegistry
{
  private final IStoredApplicationObject storedApplicationObject_;
  
  /**
   * Constructor.
   * 
   * @param modelRegistry             A base model registry which is to be cloned.
   * @param storedApplicationObject   The StoredApplicationObject from which an ApplicationObjectPayload will be decrypted.
   */
  public ObjectModelRegistry(ModelRegistry modelRegistry, IStoredApplicationObject storedApplicationObject)
  {
    super(modelRegistry);
    storedApplicationObject_ = storedApplicationObject;
  }

  /**
   * 
   * @return The StoredApplicationObject.
   */
  public IStoredApplicationObject getStoredApplicationObject()
  {
    return storedApplicationObject_;
  }
}
