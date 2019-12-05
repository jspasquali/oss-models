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
 *  At                  2019-11-25 09:18:48 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.object.canon.facade;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.symphony.oss.models.object.canon.SortKeyTypeDef;

/**
 * Facade for
 * A sort key.
 * Generated from TypeDef(SortKey,type=String) at #/components/schemas/SortKey
 */
@Immutable
public class SortKey extends SortKeyTypeDef implements org.symphonyoss.s2.fugue.kv.IKvSortKey
{
  private static Builder theBuilder = new Builder();
  
  private SortKey(@Nonnull String value)
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
   * Builder for SortKey.
   */
  public static class Builder extends SortKeyTypeDef.Builder
  {
    private static final String PROHIBITED_CHARS = "#/&$+,;=";

    private Builder()
    {
    }
    
    @Override
    public SortKey build(@Nonnull String value)
    {
      if(value == null)
        throw new NullPointerException("value is required.");
      
      for(char c : PROHIBITED_CHARS.toCharArray())
      {
        if(value.indexOf(c) != -1)
          throw new IllegalArgumentException("Sort Keys may not contain any of " + PROHIBITED_CHARS);
      }
        
      return new SortKey(value);
    }
    
    @Override
    public String toValue(SortKey instance)
    {
      return instance.getValue();
    }
  }
}

/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/TypeDef/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */