/**
 * GENERATED CODE - DO NOT EDIT OR CHECK IN TO SOURCE CODE CONTROL
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
 *
 *----------------------------------------------------------------------------------------------------
 * Generated from
 *    Input source         file:/Users/geremia.longobardo/workspace/oss-models/authc-model/src/main/resources/canon/authc.json
 *    Template groupId     org.symphonyoss.s2.canon
 *           artifactId    canon-template-java
 *    Template name        template/java/Path/I_PathHandler.java.ftl
 *    At                   2020-12-16 14:53:52 CET
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.core.canon.facade;

import javax.annotation.Nonnull;

import com.symphony.oss.canon.runtime.IEntityHandler;
import com.symphony.oss.canon.runtime.exception.CanonException;
import com.symphony.oss.fugue.trace.ITraceContext;

/**
 * Path name=Version
 *
 * Path					/version
 * Bind Path			version
 */
public interface IServiceVersionPathHandler<T> extends IEntityHandler
{
  /**
   * get /version
   * Fetch build version of the service
   * @param canonAuth An authentication token.
   * @param canonTrace A trace context.
   * @return A IPlainText
   * @throws CanonException                    If the method cannot be called
   */
  @Nonnull IPlainText handleGet(
              T canonAuth, 
              ITraceContext             canonTrace
	
    ) throws CanonException;
    
}
/*----------------------------------------------------------------------------------------------------
 * End of template template/java/Path/I_PathHandler.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */