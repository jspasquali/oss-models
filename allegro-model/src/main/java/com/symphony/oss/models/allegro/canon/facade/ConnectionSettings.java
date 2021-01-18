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
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2020-06-23 10:14:41 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.allegro.canon.facade;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.models.allegro.canon.ConnectionSettingsEntity;
import com.symphony.oss.models.allegro.canon.IConnectionSettingsEntity;
import com.symphony.oss.models.crypto.cipher.CipherSuiteUtils;

/**
 * Facade for Object ObjectSchema(ConnectionSettings)
 * Generated from ObjectSchema(ConnectionSettings) at #/components/schemas/ConnectionSettings
 */
@Immutable
public class ConnectionSettings extends ConnectionSettingsEntity implements IConnectionSettings
{
  static final String       REDACTED = "**REDACTED**";
  static final String       PROXY_PASSWORD = "proxyPassword";

  final TrustStrategy       trustStrategy_;

  private ImmutableJsonObject redacted_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ConnectionSettings(AbstractConnectionSettingsBuilder<?,?> builder)
  {
    super(builder);
    
    trustStrategy_ = initTrustStrategy();
  }
  
  private TrustStrategy initTrustStrategy()
  {
    if(getSslTrustStrategy() != null)
    {
      switch(getSslTrustStrategy())
      {
        case TRUST_ALL_CERTS:
          return new TrustAllStrategy(); 
          
        case TRUST_SELF_SIGNED_CERTS:
          return new TrustSelfSignedStrategy();
      }
    }
    
    return null;
  }

  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ConnectionSettings(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    trustStrategy_ = initTrustStrategy();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ConnectionSettings(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    trustStrategy_ = initTrustStrategy();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ConnectionSettings(IConnectionSettings other)
  {
    super(other);
    
    trustStrategy_ = other.getTrustStrategy();
    redacted_ = other.getRedacted();
  }
  
  /**
   * Abstract builder for ConnectionSettings. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractConnectionSettingsBuilder<B extends AbstractConnectionSettingsBuilder<B,T>, T extends IConnectionSettingsEntity> extends AbstractConnectionSettingsEntityBuilder<B,T>
  {
    protected AbstractConnectionSettingsBuilder(Class<B> type)
    {
      super(type);
      
      _maxHttpConnections_ = 200;
    }
    
    protected AbstractConnectionSettingsBuilder(Class<B> type, IConnectionSettingsEntity initial)
    {
      super(type, initial);
      
      if(_maxHttpConnections_ == null)
        _maxHttpConnections_ = 200;
    }
  }
  
  @Override
  public synchronized ImmutableJsonObject getRedacted()
  {
    if(redacted_ == null)
    {
      MutableJsonObject jsonObject = getJsonObject().mutify();
      
      redactJsonObject(jsonObject);
      
      redacted_ = jsonObject.immutify();
    }
    
    return redacted_;
  }

  protected void redactJsonObject(MutableJsonObject jsonObject)
  {
    if(getProxyPassword() != null)
    {
        jsonObject.addIfNotNull(PROXY_PASSWORD, REDACTED);
    }
  }
  
  @Override
  public TrustStrategy getTrustStrategy()
  {
    return trustStrategy_;
  }

  @Override
  public CloseableHttpClient createHttpClient(CookieStore cookieStore)
  {
    return createHttpClient(cookieStore, null);
  }
  
  @Override
  public CloseableHttpClient createHttpClient(CookieStore cookieStore, @Nullable SSLContextBuilder sslContextBuilder)
  {
    
    List<X509Certificate> trustedCerts = new ArrayList<>(getTrustedCertResources() == null ? 0 : getTrustedCertResources().size() 
//        + configuredCerts_.size()
        );
    
//    trustedCerts.addAll(configuredCerts_);
    
    if(getTrustedCertResources() != null)
    {
      for(String resourceName : getTrustedCertResources())
      {
        trustedCerts.add(CipherSuiteUtils.certificateFromPemResource(getClass(), resourceName));
      }
    }
    
    HttpClientBuilder httpBuilder = HttpClients.custom()
        .setDefaultCookieStore(cookieStore)
        .setConnectionManager(createConnectionManager(trustedCerts, sslContextBuilder));
        
    
    if(getProxyUrl() != null)
    {
      
      HttpHost proxy = new HttpHost(getProxyUrl().getHost(), getProxyUrl().getPort(), getProxyUrl().getProtocol());
      DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            
      if(getProxyUsername() != null && getProxyPassword() != null )
      {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        
        credsProvider.setCredentials(
                new AuthScope(getProxyUrl().getHost(), getProxyUrl().getPort()),
                new UsernamePasswordCredentials(getProxyUsername(), getProxyPassword()));
        
        AuthCache authCache = new BasicAuthCache();
         
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(proxy, basicAuth);
        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);
        
        httpBuilder.setDefaultCredentialsProvider(credsProvider);      
        
      }
      
      httpBuilder.setRoutePlanner(routePlanner);
         // .setProxy(proxy);
    }
    
    CloseableHttpClient httpclient = httpBuilder
        .setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy())     
        .build();

    return httpclient;
  }
  
  private PoolingHttpClientConnectionManager createConnectionManager(List<X509Certificate> trustedCerts, @Nullable SSLContextBuilder sslContextBuilder)
  {
    
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    
    
    if(!trustedCerts.isEmpty() || getTrustStrategy() != null)
    {
      sslContextBuilder = configureTrust(trustedCerts, sslContextBuilder);
    }
    
    if(sslContextBuilder != null)
    {
      
      SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
          build(sslContextBuilder),
          null,
          null,
          (HostnameVerifier)null);
  
      //httpBuilder.setSSLSocketFactory(sslsf);
      
      Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
          .register("http", new PlainConnectionSocketFactory())
          .register("https", sslsf)
          .build();
      
      connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
      
    }
    else
    {
      connectionManager = new PoolingHttpClientConnectionManager();
    }
    
    connectionManager.setDefaultMaxPerRoute(getMaxHttpConnections());
    connectionManager.setMaxTotal(getMaxHttpConnections());
    
    return connectionManager;
  }

  private SSLContext build(SSLContextBuilder sslContextBuilder)
  {
    try
    {
      return sslContextBuilder.build();
    }
    catch (KeyManagementException | NoSuchAlgorithmException e)
    {
      throw new IllegalStateException("Failed to configure SSL trust", e);
    }
  }

  private SSLContextBuilder configureTrust(List<X509Certificate> trustedCerts, @Nullable SSLContextBuilder sslContextBuilder)
  {
    try
    {
      if(sslContextBuilder == null)
        sslContextBuilder = SSLContexts.custom();
        
      KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());
      trustStore.load(null);
      
      int n=1;
      
      for(X509Certificate trustedCert : trustedCerts)
      {
        trustStore.setCertificateEntry("cert" + n++, trustedCert);
      }
      
      sslContextBuilder.loadTrustMaterial(trustStore, getTrustStrategy());

      return sslContextBuilder;
    }
    catch(GeneralSecurityException | IOException e)
    {
      throw new IllegalStateException("Failed to configure SSL trust", e);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */