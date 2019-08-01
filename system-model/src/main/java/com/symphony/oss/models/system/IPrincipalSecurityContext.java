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

package com.symphony.oss.models.system;

import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.fundamental.ICompoundSecurityContext;

/**
 * A (Simple) Security Context which uses an SBE user account key.
 * 
 * @author Bruce Skingle
 *
 */
public interface IPrincipalSecurityContext extends ICompoundSecurityContext
{
  /**
   * 
   * @return The principal hash for the owner of this security context.
   */
  Hash getPrincipalBaseHash();
}
