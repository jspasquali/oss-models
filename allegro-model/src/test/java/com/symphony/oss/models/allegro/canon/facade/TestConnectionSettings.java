/*
 *
 *
 * Copyright 2021 Symphony Communication Services, LLC.
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

package com.symphony.oss.models.allegro.canon.facade;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.symphony.oss.models.allegro.canon.SslTrustStrategy;

@SuppressWarnings("javadoc")
public class TestConnectionSettings
{
  @Test
  public void testRedacted()
  {
    IConnectionSettings connectionSettings = new ConnectionSettings.Builder()
        .withSslTrustStrategy(SslTrustStrategy.TRUST_ALL_CERTS)
        .withProxyUrl("https://proxy.url")
        .withProxyUsername("proxyUserame")
        .withProxyPassword("SUPER SECRET PROXY PASSWORD")
        .build();
    
    assertEquals("{\n" + 
        "  \"_type\":\"com.symphony.s2.model.allegro.ConnectionSettings\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"maxHttpConnections\":200,\n" + 
        "  \"proxyPassword\":\"**REDACTED**\",\n" + 
        "  \"proxyUrl\":\"https://proxy.url\",\n" + 
        "  \"proxyUsername\":\"proxyUserame\",\n" + 
        "  \"sslTrustStrategy\":\"TRUST_ALL_CERTS\",\n" + 
        "  \"trustedCertResources\":[],\n" + 
        "  \"trustedCerts\":[]\n" + 
        "}\n", connectionSettings.getRedacted().toString());
  }
}
