/**
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
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2020-03-20 11:50:40 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.s2.authz.canon.facade;

import java.util.Collection;
import java.util.TreeSet;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.s2.authz.canon.EntitlementResponseEntity;
import com.symphony.s2.authz.canon.IEntitlementResponseEntity;

/**
 * Facade for Object ObjectSchema(EntitlementResponse)
 *
 * A user ID and the set of entitlements valid for a particular user at the current moment.
 * Generated from ObjectSchema(EntitlementResponse) at #/components/schemas/EntitlementResponse
 */
@Immutable
public class EntitlementResponse extends EntitlementResponseEntity implements IEntitlementResponse
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public EntitlementResponse(AbstractEntitlementResponseBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public EntitlementResponse(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public EntitlementResponse(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public EntitlementResponse(IEntitlementResponse other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for EntitlementResponse. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractEntitlementResponseBuilder<B extends AbstractEntitlementResponseBuilder<B,T>, T extends IEntitlementResponseEntity> extends AbstractEntitlementResponseEntityBuilder<B,T>
  {
    protected TreeSet<Hash> entitlementHashes_ = new TreeSet<>();
    
    protected AbstractEntitlementResponseBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractEntitlementResponseBuilder(Class<B> type, IEntitlementResponseEntity initial)
    {
      super(type, initial);
    }
    
    protected B withEntitlementHash(Hash hash)
    {
      entitlementHashes_.add(hash);
      
      return self();
    }
    
    protected B withEntitlementHashes(Collection<Hash> hashes)
    {
      entitlementHashes_.addAll(hashes);
      
      return self();
    }

    @Override
    protected void validate()
    {
      // TODO Auto-generated method stub
      super.validate();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */