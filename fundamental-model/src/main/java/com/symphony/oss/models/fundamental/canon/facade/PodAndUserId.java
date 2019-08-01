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
 *  At                  2019-02-19 15:51:30 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.fundmental.canon.PodAndUserIdTypeDef;

/**
 * Facade for
 * Generated from TypeDef(PodAndUserId,type=Integer) at #/components/schemas/PodAndUserId
 */
@Immutable
public class PodAndUserId extends PodAndUserIdTypeDef
{
  private static Builder theBuilder = new Builder();

  private final PodId podId_;
  private final UserId userId_;
  
  private PodAndUserId(@Nonnull Long value)
  {
    super(value);
    
    podId_ = PodId.newBuilder().build(UserId.extractPodId(value));
    userId_ = UserId.newBuilder().build(value);
  }
  
  /**
   * 
   * @return The pod ID.
   */
  public PodId getPodId()
  {
    return podId_;
  }

  /**
   * 
   * @return The local user ID.
   */
  public UserId getUserId()
  {
    return userId_;
  }
  
  /**
   * 
   * @return The value of this PodAndUserId as a long.
   */
  public long longValue()
  {
    return getValue().longValue();
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
   * Builder for PodAndUserId.
   */
  public static class Builder extends PodAndUserIdTypeDef.Builder
  {
    private Builder()
    {
    }
    
    @Override
    public PodAndUserId build(@Nonnull Long value)
    {
      if(value == null)
        throw new NullPointerException("value is required.");
        
      return new PodAndUserId(value);
    }
    
    @Override
    public Long toValue(PodAndUserId instance)
    {
      return instance.getValue();
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */