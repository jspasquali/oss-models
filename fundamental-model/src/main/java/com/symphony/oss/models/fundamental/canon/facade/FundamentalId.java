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
 *  At                  2019-02-24 07:56:25 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.common.hash.HashProvider;

import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundmental.canon.FundamentalIdEntity;

/**
 * Facade for Object ObjectSchema(FundamentalId)
 *
 * An ID object, applications define IDs as a sub-type of this. The absolute hash of an ID Fundamental Object is calculated from this object only.
 * Generated from ObjectSchema(FundamentalId) at #/components/schemas/FundamentalId
 */
@Immutable
public class FundamentalId extends FundamentalIdEntity implements IFundamentalId
{
  private final Hash absoluteHash_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public FundamentalId(AbstractFundamentalIdBuilder<?,?> builder)
  {
    super(initHashType(builder));
    
    absoluteHash_ = generateHash();
  }
  
  private static AbstractFundamentalIdBuilder<?,?> initHashType(AbstractFundamentalIdBuilder<?,?> builder)
  {
    if(builder.getHashType() == null)
    {
      for(Object value : builder.getCanonAllFields())
      {
        if(value instanceof Hash)
        {
          builder.withHashType(((Hash)value).getTypeId());
          break;
        }
      }
      
      if(builder.getHashType() == null)
      {
        // We will have to use type 1 forever with IDs containing no hashes.
        builder.withHashType(1);
      }
    }
    return builder;
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public FundamentalId(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    absoluteHash_ = generateHash();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public FundamentalId(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    absoluteHash_ = generateHash();
  }

  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public FundamentalId(IFundamentalId other)
  {
    super(other);
    
    absoluteHash_ = generateHash();
  }

  @Override
  public Hash getAbsoluteHash()
  {
    return absoluteHash_;
  }

  private Hash generateHash()
  {
    return HashProvider.getHashOf(getHashType().asInteger(), serialize());
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */