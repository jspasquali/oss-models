/**
 * Copyright 2018 Symphony Communication Services, LLC.
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
 *  At                  2018-05-13 13:48:35 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.annotation.concurrent.Immutable;
import javax.crypto.SecretKey;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;

import com.symphony.oss.models.fundmental.canon.ISecurityContextEntity;
import com.symphony.oss.models.fundmental.canon.ISecurityContextMemberKeys;
import com.symphony.oss.models.fundmental.canon.MembershipStatus;
import com.symphony.oss.models.fundmental.canon.SecurityContextEntity;

/**
 * Facade for Object ObjectSchema(SecurityContext)
 * Generated from ObjectSchema(SecurityContext) at #/components/schemas/SecurityContext
 */
@Immutable
public class SecurityContext extends SecurityContextEntity implements ISecurityContext
{
  private final PublicKey                                  exchangeKey_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public SecurityContext(AbstractSecurityContextBuilder<?,?> builder)
  {
    super(builder);
    
    exchangeKey_ = getCipherSuite().publicKeyFromPem(getEncodedExchangeKey());
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SecurityContext(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    exchangeKey_ = getCipherSuite().publicKeyFromPem(getEncodedExchangeKey());
  }
  
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public SecurityContext(ISecurityContext other)
  {
    super(other);
    
    exchangeKey_ = other.getExchangeKey();
  }
  
  /**
   * Abstract builder for SecurityContext. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractSecurityContextBuilder<B extends AbstractSecurityContextBuilder<B,T>, T extends ISecurityContextEntity> extends AbstractSecurityContextEntityBuilder<B,T>
  {
    protected AbstractSecurityContextBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractSecurityContextBuilder(Class<B> type, ISecurityContextEntity initial)
    {
      super(type, initial);
    }
  }
  
  @Override
  public PublicKey getExchangeKey()
  {
    return exchangeKey_;
  }
  
  @Override
  public IOpenSimpleSecurityContext openSimpleSecurityContext(IOpenPrincipalCredential credential)
      throws NoSuchObjectException
  {
    return openSecurityContext(credential);
  }

  @Override
  public IOpenSecurityContext openSecurityContext(IOpenPrincipalCredential credential) throws NoSuchObjectException
  {
    ISecurityContextMember member;
    
    try
    {
      member = credential.getDatabase().fetchSecurityContextMemberFor(getParentHash(),
              credential.getPrincipalBaseHash(), credential);
    }
    catch(NoSuchObjectException e)
    {
      throw new NoSuchObjectException("Principal " + credential.getPrincipalBaseHash() +
          " is not a member of security context " + getParentHash(), e);
    }
           
    if(member.getMembershipStatus() == MembershipStatus.NONE)
      throw new NoSuchObjectException("Principal is no longer a member of this security context");
    
    ISecurityContextMemberKeys memberKeys = 
        SecurityContextMember.fetchMemberKeysFor(getBaseHash(),
            credential.getPrincipalBaseHash(), credential.getDatabase(), credential);
        
    SecretKey secretKey = getCipherSuite().unwrap(memberKeys.getWrappedKey(), credential.getExchangeKey().getPrivateKey());

    PrivateKey privateKey = getCipherSuite().unwrap(getEncryptedPrivateKey(), secretKey);
    
    return new OpenSecurityContext(this, privateKey, secretKey);
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */