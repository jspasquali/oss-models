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

import javax.crypto.SecretKey;

import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.common.immutable.ImmutableByteArray;

import com.symphony.oss.models.crypto.canon.EncryptedData;

public class OpenSimpleSecurityContext extends SimpleSecurityContext implements IOpenSimpleSecurityContext
{
  private final SecretKey    secretKey_;

  public OpenSimpleSecurityContext(ISimpleSecurityContext securityContext, SecretKey secretKey)
  {
    super(securityContext);
    
    setPayloadContainer(securityContext.getPayloadContainer());
    
    secretKey_ = secretKey;
  }

  @Override
  public SecretKey getSecretKey()
  {
    return secretKey_;
  }

  @Override
  public EncryptedData encrypt(IEntity entity)
  {
    return getCipherSuite().encrypt(getSecretKey(), entity.getJsonObject().serialize());
  }

  @Override
  public ImmutableByteArray decrypt(EncryptedData cipherText)
  {
    return getCipherSuite().decrypt(getSecretKey(), cipherText);
  }
}
