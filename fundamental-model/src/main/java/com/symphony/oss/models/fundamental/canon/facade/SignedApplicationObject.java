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
 *  At                  2019-05-01 10:09:49 BST
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.time.Instant;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;

import com.symphony.oss.models.fundmental.canon.ISignedApplicationObjectEntity;
import com.symphony.oss.models.fundmental.canon.SignedApplicationObjectEntity;

/**
 * Facade for Object ObjectSchema(SignedApplicationObject)
 *
 * An unencrypted but signed payload. Sometimes we need to have a client sign a request which the sever may need to read, this might then be encrypted by an operating principal and become the payload of an encrypted blob.
 * Generated from ObjectSchema(SignedApplicationObject) at #/components/schemas/SignedApplicationObject
 */
@Immutable
public class SignedApplicationObject extends SignedApplicationObjectEntity implements ISignedApplicationObject
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public SignedApplicationObject(AbstractSignedApplicationObjectBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SignedApplicationObject(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SignedApplicationObject(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public SignedApplicationObject(ISignedApplicationObject other)
  {
    super(other);
  }
  
  /**
   * Abstract builder for SignedApplicationObject. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractSignedApplicationObjectBuilder<B extends AbstractSignedApplicationObjectBuilder<B,T>, T extends ISignedApplicationObjectEntity> extends AbstractSignedApplicationObjectEntityBuilder<B,T>
  {
    protected IOpenSigningKey signingKey_;
    protected IFundamentalPayload payload_;
    
    protected AbstractSignedApplicationObjectBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractSignedApplicationObjectBuilder(Class<B> type, ISignedApplicationObjectEntity initial)
    {
      super(type, initial);
    }

    /**
     * Generate a signed object.
     * 
     * @param signingKey The open signing key with which the object is to be signed.
     * 
     * @return This (fluent method)
     */
    public B withSigningKey(IOpenSigningKey signingKey)
    {
      signingKey_ = signingKey;
      
      return self();
    }

    /**
     * Set the unsigned payload.
     * 
     * @param payload The payload, to be signed.
     * 
     * @return This (fluent method)
     */
    public B withPayload(IFundamentalPayload payload)
    {
      payload_ = payload;
      
      return self();
    }
    
    @Override
    protected void validate()
    {
      if(payload_ == null)
        throw new IllegalStateException("Payload is required.");
      
      if(signingKey_ == null)
        throw new IllegalStateException("Signing key is required.");
      
      SignedPayload.Builder builder = new SignedPayload.Builder()
          .withSigningKeyHash(signingKey_.getAbsoluteHash())
          .withPayload(payload_)
          .withCreatedDate(Instant.now())
          ;
      
      ISignedPayload signedPayload = builder.build();
      
      super.withPayload(signedPayload);
      
      withSignature(signingKey_.sign(signedPayload));
      
      super.validate();
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */