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
 *    Input source         file:/Users/geremia.longobardo/workspace/oss-models/object-model/src/main/resources/canon/object.json
 *    Template groupId     org.symphonyoss.s2.canon
 *           artifactId    canon-template-java
 *    Template name        template/java/Path/I_PathHandler.java.ftl
 *    At                   2020-11-24 18:32:48 CET
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import java.io.Writer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.symphony.oss.canon.runtime.IEntityHandler;
import com.symphony.oss.canon.runtime.exception.CanonException;
import com.symphony.oss.commons.hash.Hash;
import com.symphony.oss.fugue.trace.ITraceContext;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.object.canon.IPageOfStoredApplicationObject;

/**
 * Path name=PartitionsPartitionHashPage
 *
 * Path					/partitions/{partitionHash}/page
 * Bind Path			partitions/
 */
public interface IStreamingPartitionsPartitionHashPagePathHandler<T> extends IEntityHandler
{
  /**
   * get /partitions/{partitionHash}/page
   * List the contents of a partition
   * @param canonAuth An authentication token.
   * @param canonTrace A trace context.
   * @param sortKeyPrefix             No summary given.
   * @param limit                     No summary given.
   * @param scanForwards              No summary given.
   * @param partitionHash             No summary given.
   * @param after                     No summary given.
   * @return A IPageOfStoredApplicationObject
   * or <code>null</code>
   * @throws CanonException                    If the method cannot be called
   */
  @Nullable IPageOfStoredApplicationObject handleGet(
              T canonAuth, 
              ITraceContext             canonTrace,
    @Nullable String                    sortKeyPrefix,
    @Nullable Integer                   limit,
    @Nullable Boolean                   scanForwards,
    @Nonnull  Hash                      partitionHash,
    @Nullable String                    after,
              PodId xSymphonyExternalPodId,
              Writer               writer
              
    ) throws CanonException;
    
}
/*----------------------------------------------------------------------------------------------------
 * End of template template/java/Path/I_PathHandler.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */