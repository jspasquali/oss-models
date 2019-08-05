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

import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;

import org.junit.Test;
import org.symphonyoss.s2.canon.runtime.Entity;
import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.ModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.jackson.JacksonAdaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestEntityObject
{
  @Test
  public void testFundamentalId() throws IOException
  {
    String jsonString = "{ \"name\": \"An arbitrary JSON Object\" }";
    String jsonStringWithType = "{ \"_type\": \"An arbitrary JSON Object\" }";
    
    ObjectNode json = (ObjectNode)new ObjectMapper().readTree(jsonString);
    ImmutableJsonObject immutableJson = JacksonAdaptor.adaptObject(json).immutify();
    Instant now = Instant.now();
    
    IEntity payload = new Entity(immutableJson);
    IEntity payload2 = new Entity(json);
    
    assertEquals(payload, payload2);
    
    IClob clob = new Clob.Builder()
        .withCreatedDate(now)
        .withPayload(payload)
        .build();
      
    IFundamentalObject entityObject = new FundamentalObject.EntityObjectBuilder()
        .withCreatedDate(now)
      .withPayload(payload)
      .build();
    
    System.out.println("clob  = " + clob);
    System.out.println("entityObject  = " + entityObject);
    System.out.println("entityObject.getPayload()  = " + entityObject.getPayload());
    
    assertEquals(clob, entityObject.getPayload());
    
    ModelRegistry modelRegistry = new ModelRegistry();
    
    IEntity parsedObject = modelRegistry.parseOne(new StringReader(jsonString));
    
    assertEquals(payload, parsedObject);
    
    Entity typedPayload = new Entity((ObjectNode)new ObjectMapper().readTree(jsonStringWithType));
    IEntity parsedTypedPayload = modelRegistry.parseOne(new StringReader(jsonStringWithType));
    
    assertEquals(typedPayload, parsedTypedPayload);
  }
}
