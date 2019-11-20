/**
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
 *
 *----------------------------------------------------------------------------------------------------
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2019-03-22 09:19:08 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.system.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.fundamental.canon.facade.FundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundamental.canon.facade.SimpleSecurityContext;
import com.symphony.oss.models.fundmental.canon.ContentIdObject;
import com.symphony.oss.models.fundmental.canon.ContentIdType;
import com.symphony.oss.models.fundmental.canon.IMemberIdObject;
import com.symphony.oss.models.fundmental.canon.MemberIdObject;
import com.symphony.oss.models.system.canon.IPodEntity;
import com.symphony.oss.models.system.canon.PodEntitlements;
import com.symphony.oss.models.system.canon.PodEntity;
import com.symphony.oss.models.system.canon.PodIdObject;

/**
 * Facade for Object ObjectSchema(Pod)
 *
 * A pod. The operating security context contains system config objects, the environment principal has access to this. The root context gives access to all content for the pod, the public context is visible to all principals in the pod but not the environment operating principal, this is where the forwarder puts forwarded messages.
 * Generated from ObjectSchema(Pod) at #/components/schemas/Pod
 */
@Immutable
public class Pod extends PodEntity implements IPod
{
  /**
   * Return the ID hash for the given podId.
   * 
   * @param podId The podId for which the ID hash is required.
   * 
   * @return The ID hash for the given podId.
   */
  public static Hash getIdHash(PodId podId)
  {
    return new PodIdObject.Builder().withPodId(podId).build().getAbsoluteHash();
  }
  
  /**
   * Return the hash for all principals sequence for the given podId.
   * 
   * @param podId The podId for which the ID hash is required.
   * 
   * @return The ID hash for the given podId.
   */
  public static IFundamentalId getPrincipalsSequenceHashId(PodId podId)
  {
    return new ContentIdObject.Builder()
        .withSubjectHash(getIdHash(podId))
        .withSubjectType(Pod.TYPE_ID)
        .withContentType(Principal.TYPE_ID)
        .withIdType(ContentIdType.CURRENT_SEQUENCE)
        .build();
  }
  
  /**
   * Return the ID for the all objects sequence for the given podId.
   * 
   * @param podId The podId for which the ID is required.
   * 
   * @return The ID for the all objects sequence for the given podId.
   */
  public static IFundamentalId getContentSequenceHashId(PodId podId)
  {
    return new ContentIdObject.Builder()
        .withSubjectHash(getIdHash(podId))
        .withSubjectType(Pod.TYPE_ID)
        .withContentType(FundamentalObject.TYPE_ID)
        .withIdType(ContentIdType.ABSOLUTE_SEQUENCE)
        .build();
  }
  
  /**
   * Return the ID for the PodEntitlements of given podId.
   * 
   * @param podId The podId for which the ID is required.
   * 
   * @return The ID for the PodEntitlements of given podId.
   */
  public static IFundamentalId getEntitlementsId(PodId podId)
  {
    return new ContentIdObject.Builder()
        .withSubjectHash(getIdHash(podId))
        .withSubjectType(Pod.TYPE_ID)
        .withContentType(PodEntitlements.TYPE_ID)
        .withIdType(ContentIdType.ATTRIBUTE)
        .build()
        ;
  }
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Pod(AbstractPodBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Pod(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Pod(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Pod(IPod other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for Pod. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractPodBuilder<B extends AbstractPodBuilder<B,T>, T extends IPodEntity> extends AbstractPodEntityBuilder<B,T>
  {
    protected AbstractPodBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractPodBuilder(Class<B> type, IPodEntity initial)
    {
      super(type, initial);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */