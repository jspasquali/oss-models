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
 *  At                  2020-06-23 10:20:24 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.core.canon.facade;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A builder for URLs.
 * 
 * This is just so Canon generated code can use URL in the same way as a generated typedef.
 * 
 * @author Bruce Skingle
 *
 */
public class UrlBuilder
{
  /**
   * Parse a URL from the given string value.
   * 
   * @param value A URL as a String.
   * 
   * @return A URL value.
   */
  public static URL build(String value)
  {
    if(value == null)
      return null;
    
    try
    {
      return new URL(value);
    }
    catch (MalformedURLException e)
    {
      throw new IllegalArgumentException(e);
    }
  }
  
  /**
   * Return the string encoding of the given URL value.
   * 
   * @param instance A URL value.
   * 
   * @return The string encoding of the given URL value.
   */
  public static String toString(URL instance)
  {
    return instance.toString();
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_Builder.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */