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
 *		Template name		   proforma/java/TypeDef/_.java.ftl
 *		Template version	   1.0
 *  At                  2019-02-09 07:41:41 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.fundmental.canon.RotationIdTypeDef;

/**
 * Facade for
 * Generated from TypeDef(RotationId,type=Integer) at #/components/schemas/RotationId
 */
@Immutable
public class RotationId extends RotationIdTypeDef
{
  private static Builder theBuilder = new Builder();
  
  /** RotationId zero */
  public static final RotationId ZERO = RotationId.newBuilder().build(0L);
  
  
  private RotationId(@Nonnull Long value)
  {
    super(value);
  }
  
  /**
   * Return a new Builder.
   *
   * The generated version of this builder is stateless and so it is safe to return a 
   * reference to a shared instance, if you add functionality to this builder which is
   * not thread safe then you need to change this to return new Builder();
   *
   * @return A new Builder.
   */
  public static Builder newBuilder()
  {
    return theBuilder;
  }
  
  /**
   * Builder for RotationId.
   */
  public static class Builder extends RotationIdTypeDef.Builder
  {
    private Builder()
    {
    }
    
    @Override
    public RotationId build(@Nonnull Long value)
    {
      return new RotationId(value);
    }
    
    @Override
    public Long toValue(RotationId instance)
    {
      return instance.getValue();
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */