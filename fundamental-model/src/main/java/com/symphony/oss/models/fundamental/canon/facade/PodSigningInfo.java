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
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2019-03-22 09:19:04 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.security.PrivateKey;

import javax.annotation.concurrent.Immutable;
import javax.crypto.SecretKey;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.canon.runtime.ModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import com.symphony.oss.models.fundamental.canon.facade.FundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalPayload;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.IPodSigningInfo;
import com.symphony.oss.models.fundamental.canon.facade.OpenSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.OpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.SecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.SigningKey;
import com.symphony.oss.models.fundmental.canon.FundamentalModel;
import com.symphony.oss.models.fundmental.canon.PodSigningInfoEntity;

/**
 * Facade for Object ObjectSchema(PodSigningInfo)
 * Generated from ObjectSchema(PodSigningInfo) at #/components/schemas/PodSigningInfo
 */
@Immutable
public class PodSigningInfo extends PodSigningInfoEntity implements IPodSigningInfo
{
  private static final IModelRegistry modelRegistry_ = new ModelRegistry().withFactories(FundamentalModel.FACTORIES);
  
  private IOpenSigningKey      openSigningKey_;
  private IOpenSecurityContext openSecurityContext_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public PodSigningInfo(AbstractPodSigningInfoBuilder<?,?> builder)
  {
    super(builder);
    init();
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PodSigningInfo(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    init();
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public PodSigningInfo(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    init();
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public PodSigningInfo(IPodSigningInfo other)
  {
    super(other);
    init();
  }

  private void init()
  {
    initSigningKey();
    initSecurityContext();
  }

  private void initSigningKey()
  {
    ImmutableJsonObject json = ModelRegistry.parseOneJsonObject(getSigningKey().getReader());
    
    FundamentalObject fundamentalObject = new FundamentalObject(json, modelRegistry_);
    IFundamentalPayload payload = fundamentalObject.getPayload();
    
    SigningKey signingKey = new SigningKey(payload.getJsonObject(), modelRegistry_);
    
    signingKey.setPayloadContainer(fundamentalObject);
    
    PrivateKey privateKey = signingKey.getCipherSuite().privateKeyFromPem(getEncodedPrivateSigningKey());
    
    openSigningKey_ = OpenSigningKey.deserialize(signingKey, privateKey);
  }

  private void initSecurityContext()
  {
    ImmutableJsonObject json = ModelRegistry.parseOneJsonObject(getSecurityContext().getReader());
    
    FundamentalObject fundamentalObject = new FundamentalObject(json, modelRegistry_);
    IFundamentalPayload payload = fundamentalObject.getPayload();
    
    SecurityContext securityContext = new SecurityContext(payload.getJsonObject(), modelRegistry_);
    
    securityContext.setPayloadContainer(fundamentalObject);
    
    PrivateKey privateKey = securityContext.getCipherSuite().privateKeyFromPem(getEncodedPrivateContextKey());
    SecretKey secretKey = securityContext.getCipherSuite().secretKeyFromBase64(getEncodedSecretContextKey());
    
    openSecurityContext_ = new OpenSecurityContext(securityContext, privateKey, secretKey);
  }

  @Override
  public IOpenSigningKey getOpenSigningKey()
  {
    return openSigningKey_;
  }

  @Override
  public IOpenSecurityContext getOpenSecurityContext()
  {
    return openSecurityContext_;
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */