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

package com.symphony.oss.models.fundamental.secret;

import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.ModelRegistry;
import org.symphonyoss.s2.common.dom.json.IImmutableJsonDomNode;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.fugue.naming.CredentialName;
import org.symphonyoss.s2.fugue.secret.ISecretManager;

import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.PrincipalCredential;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseReadOnly;

public class FundamentalSecretManager
{
  private final ISecretManager               secretManager_;
  private final IFundamentalDatabaseReadOnly fundamentalDatabase_;
  private final ModelRegistry                modelRegistry_;

  public FundamentalSecretManager(ISecretManager secretManager, IFundamentalDatabaseReadOnly fundamentalDatabase,
      ModelRegistry modelRegistry)
  {
    secretManager_ = secretManager;
    fundamentalDatabase_ = fundamentalDatabase;
    modelRegistry_ = modelRegistry;
  }

  public IOpenPrincipalCredential getCredential(CredentialName name) throws NoSuchObjectException
  {
    IImmutableJsonDomNode s = secretManager_.getSecret(name);
    return new PrincipalCredential((ImmutableJsonObject)s, modelRegistry_).open(fundamentalDatabase_, modelRegistry_);
  }

  public void putSecret(CredentialName name, IEntity entity)
  {
    secretManager_.putSecret(name, entity.getJsonObject());
  }

  @SuppressWarnings("unchecked")
  public <E extends IEntity> E getSecret(CredentialName secretName, Class<E> type) throws NoSuchObjectException
  {
    IImmutableJsonDomNode s =  secretManager_.getSecret(secretName);
    
    if(s instanceof ImmutableJsonObject)
    {
      IEntity existingInstance = modelRegistry_.newInstance((ImmutableJsonObject)s);
      
      if(type.isInstance(existingInstance))
        return (E) existingInstance;
      
      throw new IllegalStateException("Retrieved object is a " + existingInstance.getCanonType() + ", not an instance of " + type.getName());
    }
    throw new IllegalStateException("Retrieved object is a " + s.getClass().getName() + ", not a JSON object");
  }

}
