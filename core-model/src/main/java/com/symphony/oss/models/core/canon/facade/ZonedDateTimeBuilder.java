/**
 * Copyright 2018 Symphony Communication Services, LLC.
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
 *		Template name		   proforma/java/TypeDef/_Builder.java.ftl
 *		Template version	   1.0
 *  At                  2018-02-14 19:07:41 PST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.core.canon.facade;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A builder for ZonedDateTime.
 * 
 * This is just so Canon generated code can use ZonedDateTime in the same way as a generated typedef.
 * 
 * @author Bruno Biasetti1
 */
public class ZonedDateTimeBuilder
{
  /**
   * Parse an ZonedDateTime from the given string value.
   * 
   * @param value An instant in the ISO format.
   * 
   * @return A ZonedDateTime value.
   */
  public static ZonedDateTime build(String value)
  {
    return ZonedDateTime.parse(value);
  }
  
  /**
   * Return the string (ISO) encoding of the given ZonedDateTime value.
   * 
   * @param instance An ZonedDateTime value.
   * 
   * @return The string (ISO) encoding of the given ZonedDateTime value.
   */
  public static String toString(ZonedDateTime instance)
  {
    return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(instance);
  }

}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_Builder.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */