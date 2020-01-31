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
 *		Template name		   proforma/java/TypeDef/_Builder.java.ftl
 *		Template version	   1.0
 *  At                  2020-01-30 16:33:52 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.core.canon.facade;

import java.math.BigDecimal;
/**
 * A builder for BigDecimal values.
 * 
 * This is just so Canon generated code can use BigDecimal in the same way as a generated typedef.
 * 
 * @author Bruce Skingle
 *
 */
public class BigDecimalBuilder
{
  /**
   * Parse a BigDecimal from the given string value.
   * 
   * @param value A BigDecimal value in string form.
   * 
   * @return A BigDecimal Value
   */
  public static BigDecimal build(String value)
  {
    return new BigDecimal(value);
  }
  
  /**
   * Serialize the given BigDecimal value.
   * 
   * @param instance A BigDecimal value.
   * 
   * @return The serialized (string) form of the given value.
   */
  public static String toString(BigDecimal instance)
  {
    return instance.toString();
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_Builder.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */