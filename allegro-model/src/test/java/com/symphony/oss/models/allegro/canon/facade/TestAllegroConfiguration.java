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
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.core.canon.facade.UserId;
import com.symphony.oss.models.crypto.cipher.CipherSuiteUtils;
import com.symphony.s2.authc.canon.facade.NewPrincipalCredential;

@SuppressWarnings("javadoc")
public class TestAllegroConfiguration
{
  private static String SELF_SIGNED_TEST_CERT = "-----BEGIN CERTIFICATE-----\n" + 
      "MIICpDCCAYwCCQCpYxaSYm0hzDANBgkqhkiG9w0BAQsFADAUMRIwEAYDVQQDDAls\n" + 
      "b2NhbGhvc3QwHhcNMjEwMTE1MDkwNTAyWhcNMjIwMTE1MDkwNTAyWjAUMRIwEAYD\n" + 
      "VQQDDAlsb2NhbGhvc3QwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC4\n" + 
      "a9a2dSmpa7877TlzV6g7TitlCJ/qSkWQtyw76+fi29d4JjzZEVs6lEY+Pz2Z5YXs\n" + 
      "fMdODZwSIcHREbqDsCPWdvZpkwN8fhh1ZwBOWrT0tlJY1VXMfCdvnkoRnEt7uiSK\n" + 
      "q8Vi3Qgr3LCIKj+uZus0NLPfUrUgj3LWahnZdIWyg8Vg0EAZUdrJwct3WOKQsBbi\n" + 
      "5sU+Y+Yf9xW4UtUT2g8356HhJz1UW/maK6K2aCa4iT2JdWwkeeQXtgYpRgGUcqss\n" + 
      "WKfel9YmtCW6Kn/M4Tz2qQx7fIn9PYkV+AuBUAnbC1VmWKW38coorE+v1SQTE6RW\n" + 
      "991UGwhPrhfBitQD71fHAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAKTUPUejskc7\n" + 
      "jlhczocO3Nw++Ehyctwff5ip7hEnW65hj8pxEivaYrAI4HjJd1bvfDhleeiqb1FX\n" + 
      "y/aGDIF2QNz+XRoDKLQMOylI6SoBO2BJ+h0tmKPkXVzYmH2BcBHk+cSeylPOqkSt\n" + 
      "ah0/AzcMhJ0QBQghFqof6m1wyUgHsqifiyujy594nQqf43Vi+QRZ88CAxSqQkvdp\n" + 
      "uRPKJYbqItw10mn6xGQ4PU2EDVn4b8iYxPZBfBrVlVut9W6hykXfvimPaYCcT9Jy\n" + 
      "QeSoPtTjKxFm4D6pg3hrbqwkN5+3bZhqP+xPuYoeCC+H90PD+UXrLJqb7d+Ky+8G\n" + 
      "XrjIhky6dac=\n" + 
      "-----END CERTIFICATE-----";
  
  private static String SELF_SIGNED_TEST_CERT_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----\n" + 
      "MIIEpAIBAAKCAQEAuGvWtnUpqWu/O+05c1eoO04rZQif6kpFkLcsO+vn4tvXeCY8\n" + 
      "2RFbOpRGPj89meWF7HzHTg2cEiHB0RG6g7Aj1nb2aZMDfH4YdWcATlq09LZSWNVV\n" + 
      "zHwnb55KEZxLe7okiqvFYt0IK9ywiCo/rmbrNDSz31K1II9y1moZ2XSFsoPFYNBA\n" + 
      "GVHaycHLd1jikLAW4ubFPmPmH/cVuFLVE9oPN+eh4Sc9VFv5miuitmgmuIk9iXVs\n" + 
      "JHnkF7YGKUYBlHKrLFin3pfWJrQluip/zOE89qkMe3yJ/T2JFfgLgVAJ2wtVZlil\n" + 
      "t/HKKKxPr9UkExOkVvfdVBsIT64XwYrUA+9XxwIDAQABAoIBAQChABQcicBrVdNH\n" + 
      "f9xN49LMo3ui4pqpMVn18tO9JD/2fzJmhAtO/aYFR/ji0tb2ibgXMv/UFnGQKsRH\n" + 
      "vxBzcsx2qLiKhnverT6xD9Dmlq2vjZPqVaRntmO/QBMtuKL1SxYnsbnKi2hc0neT\n" + 
      "CD88BEGF/BSj/FKAMHty0IHso5sssfzxNGIi9IEO/ki12mxGKKcKmphH8V9bIUAm\n" + 
      "oCQQXeyDwAA0bJsdoz06kdZK/jXEbUEoo1sXrF5qNjFvpLjOMGYP9rVM/uJUcws+\n" + 
      "d8k+sXTiebrVjF7yiK+5nriTisNNydd0Jp9EeaqX8nSHVgwLjQczHr2DjvHOu8t6\n" + 
      "o/SRo5nxAoGBAOeUREQKSoF95uQdTjZzDwf1EwySu5kSThF6ghXJK9fitfdqoBHJ\n" + 
      "2BD8tAYDCtv+h5uSkve/MPt9jSyse9BbjhqKZZsVgvFhMXIG+DNHFrQfhYEYhsCR\n" + 
      "vkw+0IUgGY37yH3+6jJLaI8k9EbpGjAOIE3ntYWM0YqWSue0xYmV+RsLAoGBAMve\n" + 
      "fgb/Rp3/uudnoUNd4wGLew+iFW0vvBlH4a8wwXhbTcP3IOCzX8mEyBFDx2Lb2vdt\n" + 
      "LWAvPHFEPSWrea1pEW702SMosL20K6PPGnXfhvRjWMjJtJwroXKvMhxMfX+MMVKs\n" + 
      "sf2SUF+nI2RAoQpCUSdz0sZN8ov9dpQ0HMT1iUu1AoGBAOIv29mFH66I1VrbEMh3\n" + 
      "6yd/hKKUMu1Yhq3ltWcn/U24XrkRCNU3C3XUDNWOZS3o1nTqXefjcylAQQJwWeJx\n" + 
      "ekOMB5/JDANNRik0i3oyJge6fgBQf1VZRMBd3fcZGjymvvXpZL/JDZA6rOkRxoVH\n" + 
      "VuAgwBh4KTYug2z7ELHIrO1vAoGAFX3+MUSmMC/uh0iwgoaaUOZgcdR3h8w5ezzN\n" + 
      "uQddzg9qEleyOp4OYhNCeDd6BJ0C54S1/Obd617zR05dhH+IG/dqEL/Qah9dXmaN\n" + 
      "9pE7C/aRPJGvHaun2IxJrxwNOu4PoRSdqwrKuFrJa1842vkEzzDxroA3KrTs1FDu\n" + 
      "G4F7GOUCgYB3uV9q24jf5Okmi0lWzC3qTbFvsMSgBiqnsOrX/sG4/Ym2DhkVKNT0\n" + 
      "zq2kr/zSFrrYMu6ixM1i/bJtx3SZJ5IqyVC0fhBAGfL0mhcUGOYaZdTfIUMuGNPl\n" + 
      "iUJvoZ97ztVYhsA9DcDv4ssGyhTmQmYWEU5J5AdYjHTqaMfsw8XSJw==\n" + 
      "-----END RSA PRIVATE KEY-----";
  @Test
  public void testRedact()
  {
    IAllegroConfiguration config = new AllegroConfiguration.Builder()
      .withPodUrl("https://pod.url")
      .withApiUrl("https://object.store.url")
      .withUserName("serviceAccountUserName")
      .withRsaPemCredentialFile("RsaPemCredentialFile")
      .withApiConnectionSettings(new ConnectionSettings.Builder()
          .withSslTrustStrategy(SslTrustStrategy.TRUST_ALL_CERTS)
          .build())
      .build();
    
    assertEquals("{\n" + 
        "  \"_type\":\"com.symphony.s2.model.allegro.AllegroConfiguration\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"apiConnectionSettings\":{\n" + 
        "    \"_type\":\"com.symphony.s2.model.allegro.ConnectionSettings\",\n" + 
        "    \"_version\":\"1.0\",\n" + 
        "    \"maxHttpConnections\":200,\n" + 
        "    \"sslTrustStrategy\":\"TRUST_ALL_CERTS\",\n" + 
        "    \"trustedCertResources\":[],\n" + 
        "    \"trustedCerts\":[]\n" + 
        "  },\n" + 
        "  \"apiUrl\":\"https://object.store.url\",\n" + 
        "  \"podUrl\":\"https://pod.url\",\n" + 
        "  \"rsaPemCredentialFile\":\"RsaPemCredentialFile\",\n" + 
        "  \"userName\":\"serviceAccountUserName\"\n" + 
        "}\n",  config.getRedacted().toString());
    
    
    config = new AllegroConfiguration.Builder()
        .withPodUrl("https://pod.url")
        .withApiUrl("https://object.store.url")
        .withUserName("serviceAccountUserName")
        .withAuthCertFile("AuthCertFile")
        .withAuthCertFilePassword("SUPER SECRET CERT PASSWORD")
        .withApiConnectionSettings(new ConnectionSettings.Builder()
            .withSslTrustStrategy(SslTrustStrategy.TRUST_ALL_CERTS)
            .withProxyUrl("https://proxy.url")
            .withProxyUsername("proxyUserame")
            .withProxyPassword("SUPER SECRET PROXY PASSWORD")
            .build())
        .build();
          
      assertEquals("{\n" + 
          "  \"_type\":\"com.symphony.s2.model.allegro.AllegroConfiguration\",\n" + 
          "  \"_version\":\"1.0\",\n" + 
          "  \"apiConnectionSettings\":{\n" + 
          "    \"_type\":\"com.symphony.s2.model.allegro.ConnectionSettings\",\n" + 
          "    \"_version\":\"1.0\",\n" + 
          "    \"maxHttpConnections\":200,\n" + 
          "    \"proxyPassword\":\"**REDACTED**\",\n" + 
          "    \"proxyUrl\":\"https://proxy.url\",\n" + 
          "    \"proxyUsername\":\"proxyUserame\",\n" + 
          "    \"sslTrustStrategy\":\"TRUST_ALL_CERTS\",\n" + 
          "    \"trustedCertResources\":[],\n" + 
          "    \"trustedCerts\":[]\n" + 
          "  },\n" + 
          "  \"apiUrl\":\"https://object.store.url\",\n" + 
          "  \"authCertFile\":\"AuthCertFile\",\n" + 
          "  \"authCertFilePassword\":\"**REDACTED**\",\n" + 
          "  \"podUrl\":\"https://pod.url\",\n" + 
          "  \"userName\":\"serviceAccountUserName\"\n" + 
          "}\n",  config.getRedacted().toString());
      
      
      PodAndUserId          podAndUserId = PodAndUserId.newBuilder().build(PodId.newBuilder().build(123), UserId.newBuilder().build(1L));
      
      NewPrincipalCredential newCredential = new NewPrincipalCredential.Builder()
          .withUserId(podAndUserId)
          .build();
      
      config = new AllegroConfiguration.Builder()
          .withPodUrl("https://pod.url")
          .withApiUrl("https://object.store.url")
          .withUserName("serviceAccountUserName")
          .withRsaPemCredential(CipherSuiteUtils.privateKeyToPem(newCredential.getPrivateKey()))
          .withApiConnectionSettings(new ConnectionSettings.Builder()
              .withSslTrustStrategy(SslTrustStrategy.TRUST_ALL_CERTS)
              .withProxyUrl("https://proxy.url")
              .withProxyUsername("proxyUserame")
              .withProxyPassword("SUPER SECRET PROXY PASSWORD")
              .build())
          .build();
        
      assertEquals("**REDACTED**",  config.getRedacted().getRequiredString("rsaPemCredential"));

      config = new AllegroConfiguration.Builder()
          .withPodUrl("https://pod.url")
          .withApiUrl("https://object.store.url")
          .withUserName("serviceAccountUserName")
          .withAuthCert(SELF_SIGNED_TEST_CERT)
          .withAuthCertPrivateKey(SELF_SIGNED_TEST_CERT_PRIVATE_KEY)
          .withApiConnectionSettings(new ConnectionSettings.Builder()
              .withSslTrustStrategy(SslTrustStrategy.TRUST_ALL_CERTS)
              .withProxyUrl("https://proxy.url")
              .withProxyUsername("proxyUserame")
              .withProxyPassword("SUPER SECRET PROXY PASSWORD")
              .build())
          .build();
        
      assertEquals("**REDACTED**",  config.getRedacted().getRequiredString("authCertPrivateKey"));
  }
}
