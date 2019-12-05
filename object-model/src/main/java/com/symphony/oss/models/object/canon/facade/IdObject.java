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
 *    Template groupId     org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *    Template name      proforma/java/Object/_.java.ftl
 *    Template version     1.0
 *  At                  2019-11-25 09:18:48 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.common.hash.HashProvider;

import com.symphony.oss.models.object.canon.IdObjectEntity;

/**
 * Facade for Object ObjectSchema(IdObject)
 *
 * An ID object, applications define IDs as a sub-type of this.
 * Generated from ObjectSchema(IdObject) at #/components/schemas/IdObject
 */
@Immutable
public class IdObject extends IdObjectEntity implements IIdObject
{
  private final Hash hash_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public IdObject(AbstractIdObjectBuilder<?,?> builder)
  {
    super(initHashType(builder));
    
    hash_ = generateHash();
  }
  
  private static AbstractIdObjectBuilder<?,?> initHashType(AbstractIdObjectBuilder<?,?> builder)
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
  public IdObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    hash_ = generateHash();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public IdObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    hash_ = generateHash();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public IdObject(IIdObject other)
  {
    super(other);
    
    hash_ = generateHash();
  }

  @Override
  public Hash getHash()
  {
    return hash_;
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