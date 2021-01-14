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
 *		Template name		   proforma/java/Object/I_.java.ftl
 *		Template version	   1.0
 *  At                  2020-06-23 10:14:41 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.allegro.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import com.symphony.oss.models.allegro.canon.IConnectionSettingsEntity;

/**
 * Facade for Object ObjectSchema(ConnectionSettings)
 * Generated from ObjectSchema(ConnectionSettings) at #/components/schemas/ConnectionSettings
 */
@Immutable
public interface IConnectionSettings
  extends IConnectionSettingsEntity
{
  /**
   * Return the TrustStrategy for server certificate validation.
   * 
   * @return The TrustStrategy for server certificate validation.
   */
  TrustStrategy getTrustStrategy();

  CloseableHttpClient createHttpClient(CookieStore cookieStore);

  CloseableHttpClient createHttpClient(CookieStore cookieStore, SSLContextBuilder sslContextBuilder);

  /**
   * Return a copy of this object with any passwords redacted.
   * 
   * The returned object can safely be logged.
   * 
   * @return a copy of this object with any passwords redacted.
   */
  IConnectionSettings getRedacted();
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/I_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */