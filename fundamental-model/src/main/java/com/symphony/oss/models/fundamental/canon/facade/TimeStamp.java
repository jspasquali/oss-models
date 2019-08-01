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

import java.time.Instant;
import java.util.Date;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.fundmental.canon.TimeStampTypeDef;

/**
 * Facade for
 * Generated from TypeDef(TimeStamp,type=Integer) at #/components/schemas/TimeStamp
 */
@Immutable
public class TimeStamp extends TimeStampTypeDef
{
  private static Builder theBuilder = new Builder();

  private final Date     date_;
  private final Instant  instant_;
  
  private TimeStamp(@Nonnull Long value)
  {
    super(value);
    
    date_ = new Date(value);
    instant_ = InstantBuilder.build(value);
  }
  
  /**
   * 
   * @return the value of this TimeStamp as a java util Date.
   */
  public Date asDate()
  {
    return date_;
  }

  /**
   * 
   * @return the value of this TimeStamp as a java Instant.
   */
  public Instant asInstant()
  {
    return instant_;
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
   * Builder for TimeStamp.
   */
  public static class Builder extends TimeStampTypeDef.Builder
  {
    private Builder()
    {
    }
    
    @Override
    public TimeStamp build(@Nonnull Long value)
    {
      return new TimeStamp(value);
    }
    
    @Override
    public Long toValue(TimeStamp instance)
    {
      return instance.getValue();
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */