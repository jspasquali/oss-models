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

package com.symphony.oss.models.fundamental;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.IEntityFactory;
import org.symphonyoss.s2.canon.runtime.ModelRegistry;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.common.fault.CodingFault;

import com.symphony.oss.models.fundamental.canon.facade.IBlob;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalPayload;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSimpleSecurityContext;

/**
 * Inpolementation of ModelRegistry which opens FundamentalObjects if possible.
 * 
 * @author Bruce Skingle
 *
 */
public class FundamentalModelRegistry extends ModelRegistry
{
  private static final Logger log_ = LoggerFactory.getLogger(FundamentalModelRegistry.class);

  @Override
  public FundamentalModelRegistry withFactories(IEntityFactory<?, ?, ?>... factories)
  {
    super.withFactories(factories);
    
    return this;
  }

  /**
   * Return a new entity instance parsed from the given input and decrypted if possible using the given credential.
   * 
   * @param reader A Reader containing the serialized form of an entity.
   * @param credential A credential with which to decrypt the payload.
   * 
   * @return The deserialized entity.
   * 
   * @throws IOException If the payload cannot be read from the given Reader. 
   * 
   * @throws NullPointerException if the value is null.
   * @throws IllegalArgumentException if the value is not of the expected type or is otherwise invalid.
   * This may be the case if the schema defines limits on the magnitude of the value, or if a facade
   * has been written for the type.
   */
  public IEntity parseOne(Reader reader, @Nullable IOpenPrincipalCredential credential) throws IOException
  {
    IEntity existingInstance = parseOne(reader);
    
    
    if(existingInstance instanceof IFundamentalObject)
    {
      return open((IFundamentalObject) existingInstance, credential);
      
    }
    
    return existingInstance;
  }
  
  /**
   * Return the opened payload of the given object.
   * 
   * @param existingInstance  A FundamentalObject to be opened.
   * @param credential A credential with which to decrypt the payload.
   * 
   * @return The opened payload of the given object.
   */
  public IEntity open(IFundamentalObject existingInstance, @Nullable IOpenPrincipalCredential credential)
  {
    if(existingInstance.getPayload() == null)
      return existingInstance;
    
    IEntity payload = existingInstance.getPayload();
    
    if(credential != null && payload instanceof IBlob)
    {
      try
      {
        return ((IBlob)payload).open(credential);
      }
      catch(IllegalArgumentException | NoSuchObjectException e)
      {
        log_.warn("Unable to open blob", e);
        return payload;
      }
    }
    
    return payload;
  }
  
  /**
   * Return the opened payload of the given object.
   * 
   * @param existingInstance  A FundamentalObject to be opened.
   * @param securityContext An open security context with which to decrypt the payload.
   * 
   * @return The opened payload of the given object.
   */
  public IEntity open(IFundamentalObject existingInstance, IOpenSimpleSecurityContext securityContext)
  {
    if(existingInstance.getPayload() == null)
      return existingInstance;
    
    try
    {
      IEntity payload = newInstance(existingInstance.getPayload().getJsonObject());
      
      if(payload instanceof IFundamentalPayload)
        ((IFundamentalPayload) payload).setPayloadContainer(existingInstance);
      
      if(payload instanceof IBlob)
      {
        return ((IBlob)payload).open(securityContext, this);
      }
      
      return payload;
    }
    catch(IllegalArgumentException e)
    {
      throw new IllegalStateException(e);
    }
  }

  /**
   * Return the opened payload of the given object.
   * 
   * @param existing  A FundamentalObject to be opened.
   * @param credential A credential with which to decrypt the payload.
   * 
   * @return The opened payload of the given object.
   */
  public IEntity parseOne(String existing, @Nullable IOpenPrincipalCredential credential)
  {
    try(
        StringReader reader = new StringReader(existing)
        )
    {
      return parseOne(reader, credential);
    }
    catch (IOException e)
    {
      throw new CodingFault("This can't happen because the IO is all in-memory", e);
    }
  }
}
