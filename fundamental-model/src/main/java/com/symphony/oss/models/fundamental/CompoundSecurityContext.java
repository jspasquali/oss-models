/*
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
 */

package com.symphony.oss.models.fundamental;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

import com.symphony.oss.models.fundamental.canon.facade.FundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IOpenPrincipalCredential;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.ISimpleSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.RotationId;
import com.symphony.oss.models.fundamental.canon.facade.SecurityContextMember;
import com.symphony.oss.models.fundamental.canon.facade.SimpleSecurityContext;
import com.symphony.oss.models.fundamental.canon.facade.WrappedKey;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseReadOnly;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseWritable;
import com.symphony.oss.models.fundmental.canon.CipherSuiteId;
import com.symphony.oss.models.fundmental.canon.ISequence;
import com.symphony.oss.models.fundmental.canon.MembershipStatus;
import com.symphony.oss.models.fundmental.canon.SecurityContextPermission;
import com.symphony.oss.models.fundmental.canon.Sequence;
import com.symphony.oss.models.fundmental.canon.SequenceType;

/**
 * The parent for a set of SimpleSecurityContexts (1 per rotation) driven from an external ID
 * such as a userId or threadId.
 * 
 * @author Bruce Skingle
 *
 */
public abstract class CompoundSecurityContext implements ICompoundSecurityContext
{
  private final Map<RotationId, ISimpleSecurityContext> contextMap_ = new HashMap<>();
  
  private final IFundamentalId  id_;
  
  /**
   * Constructor.
   * 
   * @param id The ID for this compound security context which will be the parentHash of each SimpleSecurityContext 
   * representing a single rotation.
   */
  public CompoundSecurityContext(IFundamentalId id)
  {
    id_ = id;
  }

  @Override
  public IFundamentalId getId()
  {
    return id_;
  }

  @Override
  public Hash getBaseHash()
  {
    return id_.getAbsoluteHash();
  }

  @Override
  public List<IFundamentalObject> createObjects(RotationId rotationId,
      IOpenSigningKey openSigningKey, CipherSuiteId cipherSuiteId)
  {
    List<IFundamentalObject>  result = new LinkedList<>();
    
    IFundamentalId membersSequenceId = SimpleSecurityContext.getMembersSequenceId(getBaseHash());
    
    result.add(createMembersSequenceObject(membersSequenceId, openSigningKey).getPayloadContainer());
    result.add(SimpleSecurityContext.createRotationObject(openSigningKey, getBaseHash(), rotationId, cipherSuiteId));
    
    return result;
  }
  
  
  @Override
  public synchronized ISimpleSecurityContext fetchOrCreate(RotationId rotationId,
      IOpenSigningKey openSigningKey, CipherSuiteId cipherSuiteId,
      IFundamentalDatabaseWritable fundamentalDatabase,
      ITraceContext trace)
  {
    fetchOrCreateMembersSequence(openSigningKey, fundamentalDatabase, trace);

    ISimpleSecurityContext securityContext = contextMap_.get(rotationId);
    
    if(securityContext == null)
    {
        securityContext = SimpleSecurityContext.fetchOrCreateRotation(openSigningKey, getBaseHash(), rotationId,
            cipherSuiteId, fundamentalDatabase, trace);
      
      contextMap_.put(rotationId, securityContext);
    }
    
    return securityContext;
  }
  
  private synchronized ISequence fetchOrCreateMembersSequence(
      IOpenSigningKey openSigningKey,
      IFundamentalDatabaseWritable fundamentalDatabase,
      ITraceContext trace)
  {
    IFundamentalId membersSequenceId = SimpleSecurityContext.getMembersSequenceId(getBaseHash());
    ISequence membersSequence;
    
    try
    {
      membersSequence = fundamentalDatabase.fetchCurrent(membersSequenceId.getAbsoluteHash(), ISequence.class);
    }
    catch(NoSuchObjectException e)
    {
      membersSequence = createMembersSequenceObject(membersSequenceId, openSigningKey);
      
      fundamentalDatabase.save(membersSequenceId, trace);
      fundamentalDatabase.save(membersSequence.getPayloadContainer(), trace);
    }
    
    return membersSequence;
  }
  
  private synchronized ISequence createMembersSequenceObject(
      IFundamentalId membersSequenceId,
      IOpenSigningKey openSigningKey)
  {
    ISequence membersSequence = new Sequence.Builder()
        .withType(SequenceType.CURRENT)
        .withSecurityContextHash(getBaseHash())
        .withBaseHash(membersSequenceId.getAbsoluteHash())
        .withPrevHash(membersSequenceId.getAbsoluteHash())
        .withSigningKeyHash(openSigningKey.getAbsoluteHash())
        .build();
    
    new FundamentalObject.ObjectBuilder()
        .withPayload(membersSequence)
        .withSigningKey(openSigningKey)
        .build();
    
    return membersSequence;
  }
  
  @Override
  public synchronized ISimpleSecurityContext saveWrappedKeys(
      RotationId rotationId,
      Hash principalHash, 
      CipherSuiteId cipherSuiteId,
      WrappedKey wrappedKey,
      Hash exchangeKeyHash,
      WrappedKey encryptedKey,
      IOpenPrincipalCredential credential,
      IFundamentalDatabaseWritable fundamentalDatabase,
      ITraceContext trace)
  {
    ISimpleSecurityContext securityContext = contextMap_.get(rotationId);
    
    if(securityContext == null)
    {
      securityContext = SimpleSecurityContext.saveWrappedKeys(
          getBaseHash(), rotationId,
          principalHash, cipherSuiteId, wrappedKey, exchangeKeyHash, encryptedKey,
          credential,
          fundamentalDatabase,
          trace);
      
      contextMap_.put(rotationId, securityContext);
    }
    else
    {
      securityContext.saveWrappedKeys(
          principalHash, cipherSuiteId, wrappedKey, exchangeKeyHash, encryptedKey,
          credential,
          fundamentalDatabase, 
          trace);
    }
    
    return securityContext;
  }
  

  @Override
  public synchronized ISimpleSecurityContext fetch(RotationId rotationId,
      IFundamentalDatabaseReadOnly fundamentalDatabase) throws NoSuchObjectException
  {
    ISimpleSecurityContext securityContext = contextMap_.get(rotationId);
    
    if(securityContext == null)
    {
      securityContext = SimpleSecurityContext.fetchRotation(getBaseHash(), rotationId, fundamentalDatabase);
      
      securityContext = contextMap_.put(rotationId, securityContext);
    }
    
    return securityContext;
  }

  @Override
  public void upsertMember(Hash principalBaseHash, MembershipStatus status,
      SecurityContextPermission permission,
      IOpenSigningKey signingKey, IFundamentalDatabaseWritable fundamentalDatabase, ITraceContext trace, Instant timeStamp)
  {
    fetchOrCreateMembersSequence(signingKey, fundamentalDatabase, trace);
    
    SecurityContextMember.Builder builder = new SecurityContextMember.Builder()
        .withFundamentalDatabase(fundamentalDatabase)
        .withTrace(trace)
        .withGroupBaseHash(getBaseHash())
        .withMemberBaseHash(principalBaseHash)
        .withPermission(permission)
        .withMembershipStatus(status)
        .withSigningKey(signingKey)
        .withCreatedDate(timeStamp)
        ;
      
    builder.build();
  }
}
