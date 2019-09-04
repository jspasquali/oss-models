/*
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
 */

package com.symphony.oss.models.fundamental.canon.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import org.junit.Test;
import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.ModelRegistry;

import com.symphony.oss.models.fundmental.canon.FundamentalModel;

public class TestApplicationObject
{
  @Test
  public void testJsonObject()
  {
    String jsonString = "{ \"name\": \"An arbitrary JSON Object\" }";
    String expectedJsonString = "{\n" + 
        "  \"_type\":\"com.symphony.s2.model.fundamental.ApplicationObject\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"name\":\"An arbitrary JSON Object\"\n" + 
        "}\n";
    
    IApplicationObject payload = new ApplicationObject(jsonString);

    assertTrue(payload.toString().equals(expectedJsonString));
    
    ModelRegistry modelRegistry = new ModelRegistry().withFactories(FundamentalModel.FACTORIES);
    
    IEntity parsedPayload = modelRegistry.parseOne(new StringReader(payload.toString()));
    
    assertEquals(parsedPayload, payload);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testInvalidType()
  {
    String jsonString = "{ \"name\": \"An arbitrary JSON Object\", \"_type\": 7 }";
    
    new ApplicationObject(jsonString);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testInvalidJson()
  {
    String jsonString = "{This is not valid json";
    
    new ApplicationObject(jsonString);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testNotObject()
  {
    String jsonString = "[\"This is valid json\", \"but its not an object\"]";
    
    new ApplicationObject(jsonString);
  }
}
