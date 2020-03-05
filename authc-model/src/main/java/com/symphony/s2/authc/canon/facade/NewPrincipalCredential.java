/*
 * Copyright 2019 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.s2.authc.canon.facade;

import java.security.KeyPair;

import com.symphony.oss.models.crypto.cipher.CipherSuite;
import com.symphony.oss.models.crypto.cipher.ICipherSuite;

/**
 * A newly generated PrincipalCredential including a PrincipalAuthcKey.
 * 
 * @author Bruce Skingle
 *
 */
public class NewPrincipalCredential extends PrincipalCredential
{
  private final IPrincipalAuthcKey principalAuthcKey_;
  
  private NewPrincipalCredential(Builder builder)
  {
    super(builder);
    
    principalAuthcKey_ = builder.principalAuthcKey_;
  }
  
  /**
   * 
   * @return The IPrincipalAuthcKey containing the public half of the generated KeyPair.
   */
  public IPrincipalAuthcKey getPrincipalAuthcKey()
  {
    return principalAuthcKey_;
  }

  /**
   * Builder.
   * 
   * @author Bruce Skingle
   *
   */
  public static class Builder extends PrincipalCredential.AbstractPrincipalCredentialBuilder<Builder, NewPrincipalCredential>
  {
    private IPrincipalAuthcKey principalAuthcKey_;

    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    @Override
    protected void validate()
    {
      require(_userId_, "userId");
      
      ICipherSuite cipherSuite = CipherSuite.getDefault();
      
      KeyPair keyPair = cipherSuite.generateKeyPair();
      
      principalAuthcKey_ = new PrincipalAuthcKey.Builder()
          .withPodId(_userId_.getPodId())
          .withUserId(_userId_)
          .withEncodedPublicKey(cipherSuite.publicKeyToPem(keyPair.getPublic()))
          .build();
      
      withPodId(_userId_.getPodId())
          .withKeyId(principalAuthcKey_.getKeyId())
          .withEncodedPrivateKey(cipherSuite.privateKeyToPem(keyPair.getPrivate()))
      ;
      
      super.validate();
    }

    @Override
    protected NewPrincipalCredential construct()
    {
      return new NewPrincipalCredential(this);
    }
  }
}
