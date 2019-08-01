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
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.IPrincipalCredential;

public interface IOpenPrincipalCredential extends IPrincipalCredential
{

  IOpenExchangeKey getExchangeKey();

  IOpenSigningKey getSigningKey();

  IFundamentalDatabaseReadOnly getDatabase();

  IModelRegistry getModelRegistry();

}
