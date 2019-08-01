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

import org.symphonyoss.s2.canon.runtime.IModelRegistry;

import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseReadOnly;
import com.symphony.oss.models.fundamental.canon.facade.IOpenExchangeKey;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.IPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.PrincipalCredential;

public class OpenPrincipalCredential extends PrincipalCredential implements IOpenPrincipalCredential
{
  private final IOpenExchangeKey                   exchangeKey_;
  private final IOpenSigningKey                    signingKey_;
  private final IFundamentalDatabaseReadOnly database_;
  private final IModelRegistry                     modelRegistry_;

  public OpenPrincipalCredential(IPrincipalCredential principalCredential, IOpenExchangeKey exchangeKey,
      IOpenSigningKey signingKey,
      IFundamentalDatabaseReadOnly database, IModelRegistry modelRegistry)
  {
    super(principalCredential);
    
    exchangeKey_ = exchangeKey;
    signingKey_ = signingKey;
    database_ = database;
    modelRegistry_ = modelRegistry;
  }

  @Override
  public IOpenExchangeKey getExchangeKey()
  {
    return exchangeKey_;
  }

  @Override
  public IOpenSigningKey getSigningKey()
  {
    return signingKey_;
  }

  @Override
  public IFundamentalDatabaseReadOnly getDatabase()
  {
    return database_;
  }

  @Override
  public IModelRegistry getModelRegistry()
  {
    return modelRegistry_;
  }
}
