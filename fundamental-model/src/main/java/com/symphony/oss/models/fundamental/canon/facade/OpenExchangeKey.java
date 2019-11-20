/*
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
 */

package com.symphony.oss.models.fundamental.canon.facade;

import java.security.PrivateKey;

import javax.crypto.SecretKey;

import com.symphony.oss.models.crypto.canon.PemPrivateKey;
import com.symphony.oss.models.crypto.canon.facade.WrappedKey;
import com.symphony.oss.models.crypto.cipher.ISymmetricCipherSuite;

/**
 * An open exchange key.
 * 
 * This class contains static factory methods.
 * 
 * @author Bruce Skingle
 *
 */
public class OpenExchangeKey extends ExchangeKey implements IOpenExchangeKey
{
  private final PrivateKey privateKey_;

  private OpenExchangeKey(IExchangeKey exchangeKey, PrivateKey privateKey)
  {
    super(exchangeKey);
    
    setPayloadContainer(exchangeKey.getPayloadContainer());
    privateKey_ = privateKey;
  }
  
  /**
   * Builder for an Open Exchange Key.
   * 
   * To generate a new exchange key use:
   * <code>

     new OpenExchangeKey.Builder()
        .withPrincipalHash(initialPrinicpalObject.getAbsoluteHash())
        .withCipherSuiteId(cipherSuite_.getId())
        .withEncodedPublicKey(exchangePublicKey)
        .withPrivateKey(exchangeKeyPair.getPrivate())
        .withSigningKey(signingKey_)
        .build()
        ;
        
   * </code> 
   * To wrap an existing closed exchange key (publicKey) use:
   * <code>
     
     new OpenExchangeKey.Builder()
        .withPublicKey(publicKey)
        .withPrivateKey(exchangeKeyPair.getPrivate())
        .build()
        
   * </code>
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends AbstractExchangeKeyBuilder<Builder, IOpenExchangeKey>
  {
    private IOpenSigningKey    signingKey_;
    private PrivateKey         privateKey_;
    private IFundamentalObject publicKey_;
    
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }
    
    /**
     * Set the private key.
     * 
     * @param privateKey The private key.
     * 
     * @return This (fluent method).
     */
    public Builder withPrivateKey(PrivateKey privateKey)
    {
      privateKey_ = privateKey;
      
      return self();
    }

    /**
     * Set the signing key with which to sign the created key.
     * 
     * @param signingKey The signing key with which to sign the created key.
     * 
     * @return This (fluent method).
     */
    public Builder withSigningKey(IOpenSigningKey signingKey)
    {
      signingKey_ = signingKey;
      
      return self();
    }

    /**
     * Set the public key object.
     * 
     * If this is set then the builder simply wraps this object with the open facade.
     * 
     * @param publicKey The public key object.
     * 
     * @return This (fluent method).
     */
    public Builder withPublicKey(IFundamentalObject publicKey)
    {
      publicKey_ = publicKey;
      
      return self();
    }
    
    @Override
    protected IOpenExchangeKey construct()
    {
      if(publicKey_ == null)
      {
        if(signingKey_ != null)
          withSigningKeyHash(signingKey_.getAbsoluteHash());
        
        publicKey_ = new FundamentalObject.ObjectBuilder()
            .withSigningKey(signingKey_)
            .withPayload(new ExchangeKey(this))
            .build();
      }
      
      return new OpenExchangeKey((IExchangeKey)publicKey_.getPayload(), privateKey_);
    }
  }

  @Override
  public PrivateKey getPrivateKey()
  {
    return privateKey_;
  }
  
  @Override
  public PemPrivateKey getEncodedPrivateKey()
  {
    return getCipherSuite().privateKeyToPem(privateKey_);
  }

  @Override
  public SecretKey unwrap(WrappedKey wrappedKey, ISymmetricCipherSuite wrappedKeyCipherSuite)
  {
    return getCipherSuite().unwrap(wrappedKey, getPrivateKey());
  }

  public static IOpenExchangeKey deserialize(IExchangeKey exchangeKey, PrivateKey privateKey)
  {
    return new OpenExchangeKey(exchangeKey, privateKey);
  }
}
