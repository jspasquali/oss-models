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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.crypto.SecretKey;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.canon.runtime.exception.PermissionDeniedException;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.common.hash.Hash;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;

import com.symphony.oss.models.crypto.canon.CipherSuiteId;
import com.symphony.oss.models.crypto.canon.facade.WrappedKey;
import com.symphony.oss.models.crypto.cipher.CipherSuite;
import com.symphony.oss.models.crypto.cipher.ICipherSuite;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseReadOnly;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseWritable;
import com.symphony.oss.models.fundmental.canon.ContentIdObject;
import com.symphony.oss.models.fundmental.canon.ContentIdType;
import com.symphony.oss.models.fundmental.canon.ISecurityContextMemberKeys;
import com.symphony.oss.models.fundmental.canon.ISecurityContextRotationId;
import com.symphony.oss.models.fundmental.canon.MembershipStatus;
import com.symphony.oss.models.fundmental.canon.SecurityContextRotationId;
import com.symphony.oss.models.fundmental.canon.SecurityContextRotationIdType;
import com.symphony.oss.models.fundmental.canon.SimpleSecurityContextEntity;

/**
 * Facade for Object ObjectSchema(SimpleSecurityContext)
 * Generated from ObjectSchema(SimpleSecurityContext) at #/components/schemas/SimpleSecurityContext
 */
@Immutable
public class SimpleSecurityContext extends SimpleSecurityContextEntity implements ISimpleSecurityContext
{
  private final ICipherSuite cipherSuite_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public SimpleSecurityContext(AbstractSimpleSecurityContextBuilder<?,?> builder)
  {
    super(builder);
    
    cipherSuite_ = CipherSuite.get(getCipherSuiteId());
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public SimpleSecurityContext(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    cipherSuite_ = CipherSuite.get(getCipherSuiteId());
  }
  
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public SimpleSecurityContext(ISimpleSecurityContext other)
  {
    super(other);
    
    cipherSuite_ = other.getCipherSuite();
  }

  @Override
  public ICipherSuite getCipherSuite()
  {
    return cipherSuite_;
  }

  @Override
  public Hash getParentHash()
  {
    Hash parentHash = super.getParentHash();
    
    if(parentHash == null)
      return getBaseHash();
    
    return parentHash;
  }

  private static ISecurityContextRotationId getSecurityContextRotationId(Hash parentBaseHash,
      RotationId rotationId)
  {
    return  new SecurityContextRotationId.Builder()
        .withType(SecurityContextRotationIdType.SECURITY_CONTEXT)
        .withSecurityContextBaseHash(parentBaseHash)
        .withRotationId(rotationId)
        .build();
  }
  /**
   * Fetch the security context for the given rotation.
   * 
   * @param parentBaseHash      The baseHash of the security context for which the rotation is required.
   * @param rotationId          The rotation to which this security context relates.
   * @param fundamentalDatabase A database to read the security context from.
   * 
   * @return  A new or existing security context for the given thread.
   * 
   * @throws NoSuchObjectException If the requested rotation context does not exist. 
   */
  public static ISimpleSecurityContext fetchRotation(
      Hash parentBaseHash,
      RotationId rotationId,
      IFundamentalDatabaseReadOnly fundamentalDatabase
      ) throws NoSuchObjectException
  {
    ISecurityContextRotationId  securityContextRotationId = getSecurityContextRotationId(parentBaseHash, rotationId);
    
    return fundamentalDatabase.fetchCurrentById(securityContextRotationId, ISimpleSecurityContext.class);
  }
  
  /**
   * Fetch or create the security context for the given rotation.
   * 
   * This method calls a conditional transactional write method on the database to ensure that only one security context is created
   * for any given rotation.
   * 
   * @param openSigningKey      The signing key with which to sign the security context if it is created. 
   * @param parentBaseHash      The baseHash of the security context for which the rotation is required.
   * @param rotationId          The rotation to which this security context relates.
   * @param cipherSuiteId       The cipher suite ID for the security context.
   * @param fundamentalDatabase A database to store the security context in.
   * @param trace               A trace context to trace any write operations.
   * 
   * @return  A new or existing security context for the given rotation.
   */
  public static ISimpleSecurityContext fetchOrCreateRotation(IOpenSigningKey openSigningKey,
      Hash parentBaseHash, RotationId rotationId, CipherSuiteId cipherSuiteId,
      IFundamentalDatabaseWritable fundamentalDatabase, ITraceContext trace)
  {
    ISecurityContextRotationId  securityContextRotationId = getSecurityContextRotationId(parentBaseHash, rotationId);
    
    try
    {
      return fundamentalDatabase.fetchCurrentById(securityContextRotationId, ISimpleSecurityContext.class);
    }
    catch(NoSuchObjectException e)
    { 
      ISimpleSecurityContext securityContext = createRotation(openSigningKey, securityContextRotationId, parentBaseHash, rotationId, cipherSuiteId);
      
      fundamentalDatabase.save(securityContextRotationId, trace);
      fundamentalDatabase.save(securityContext.getPayloadContainer(), trace);
      
      return securityContext;
    }
  }
  
  /**
   * Create the object for a rotation. This method does not persist the objects, in a server context call fetchOrCreateRotation
   * 
   * @param openSigningKey      The signing key with which to sign the security context if it is created. 
   * @param parentBaseHash      The baseHash of the security context for which the rotation is required.
   * @param rotationId          The rotation to which this security context relates.
   * @param cipherSuiteId       The cipher suite ID for the security context.
   * 
   * @return The security context object.
   */
  public static IFundamentalObject createRotationObject(IOpenSigningKey openSigningKey,
      Hash parentBaseHash, RotationId rotationId, CipherSuiteId cipherSuiteId)
  {
    return createRotation(openSigningKey, getSecurityContextRotationId(parentBaseHash, rotationId),
        parentBaseHash, rotationId, cipherSuiteId).getPayloadContainer();
  }
  
  private static ISimpleSecurityContext createRotation(IOpenSigningKey openSigningKey,
      ISecurityContextRotationId  securityContextRotationId,
      Hash parentBaseHash, RotationId rotationId, CipherSuiteId cipherSuiteId)
  {
 
      ISimpleSecurityContext securityContext = new SimpleSecurityContext.Builder()
          .withBaseHash(securityContextRotationId.getAbsoluteHash())
          .withPrevHash(securityContextRotationId.getAbsoluteHash())
          .withParentHash(parentBaseHash)
          .withRotationId(rotationId)
          .withCipherSuiteId(cipherSuiteId)
          .withSigningKeyHash(openSigningKey.getAbsoluteHash())
          .build()
          ;
      
      new FundamentalObject.ObjectBuilder()
          .withPayload(securityContext)
          .withSigningKey(openSigningKey)
          .build()
          ;
      
      return securityContext;
  }

  @Override
  public @Nonnull RotationId getRotationId()
  {
    RotationId rotationId = super.getRotationId();
    
    if(rotationId == null)
      return RotationId.ZERO;
    
    return rotationId;
  }

  /**
   * Special implementation of generateRangeKey to ensure that the latest version of a security context is the one with the highest
   * rotationId.
   * 
   * This method is intended to be called from FundamentalObject only.
   * 
   * @param absoluteHash  The absolute hash of the object of which this payload is a part.
   * 
   * @return The range key used for storage of the object.
   */
  @Override
  public String generateRangeKey(Hash absoluteHash)
  {
    return getRotationId() + "#" + getCreatedDate() + "#" + absoluteHash.toStringBase64();
  }

  @Override
  public void saveWrappedKeys(
      Hash principalBaseHash, 
      CipherSuiteId cipherSuiteId,
      WrappedKey wrappedKey,
      Hash exchangeKeyHash,
      WrappedKey encryptedKey,
      IOpenPrincipalCredential credential,
      IFundamentalDatabaseWritable fundamentalDatabase, 
      ITraceContext trace)
  {
    saveWrappedKeys(this, getParentHash(), getRotationId(),
        principalBaseHash, cipherSuiteId, wrappedKey, exchangeKeyHash, encryptedKey, credential,
        fundamentalDatabase,
        trace);
  }

  /**
   * Save the given wrapped keys for a security context.
   * 
   * @param securityContextBaseHash The security context which the keys are for.
   * @param rotationId              The rotation which the keys are for.
   * 
   * @param principalBaseHash       The principal to whom the keys belong.
   * @param cipherSuiteId           The CipherSuiteId (type) of the wrapped key
   * @param wrappedKey              The key wrapped in the user's public exchange key.
   * @param exchangeKeyHash         The ID of the key which was used to wrap the wrapped key.
   * @param encryptedKey            The key wrapped in the users AES account key as per SBE key wrapping.
   * @param credential              An open credential to use to store the key.
   * @param fundamentalDatabase     A database in which to store the keys.
   * @param trace                   A trace context.
   * 
   * @return The security context object.
   */
  public static ISimpleSecurityContext saveWrappedKeys(
      Hash securityContextBaseHash,
      RotationId rotationId,
      Hash principalBaseHash, 
      CipherSuiteId cipherSuiteId,
      WrappedKey wrappedKey,
      Hash exchangeKeyHash,
      WrappedKey encryptedKey,
      
      IOpenPrincipalCredential credential,
      IFundamentalDatabaseWritable fundamentalDatabase, 
      ITraceContext trace)
  {
    return saveWrappedKeys(null, securityContextBaseHash, rotationId,
        principalBaseHash, cipherSuiteId, wrappedKey, exchangeKeyHash, encryptedKey,
        credential,
        fundamentalDatabase, trace);
  }

  private static ISimpleSecurityContext saveWrappedKeys(
      @Nullable ISimpleSecurityContext securityContext,
      Hash securityContextBaseHash,
      RotationId rotationId,
      Hash principalBaseHash, 
      CipherSuiteId cipherSuiteId,
      WrappedKey wrappedKey,
      Hash exchangeKeyHash,
      WrappedKey encryptedKey,
      IOpenPrincipalCredential credential,
      IFundamentalDatabaseWritable fundamentalDatabase, 
      ITraceContext trace)
  {
    try
    {
      IOpenSigningKey openSigningKey = credential.getSigningKey();
      
      ISecurityContextMember member = 
          fundamentalDatabase.fetchSecurityContextMemberFor(securityContextBaseHash, principalBaseHash, credential);
          
          
         // fundamentalDatabase.fetchCurrent(getMemberBaseHash(SecurityContextMember.TYPE_ID, securityContextBaseHash, principalBaseHash), ISecurityContextMember.class);
      
      if(member.getMembershipStatus() != MembershipStatus.MEMBER)
        throw new PermissionDeniedException("No longer a member of this security context");
      
      if(securityContext == null)
        securityContext = fetchOrCreateRotation(openSigningKey, securityContextBaseHash, rotationId, cipherSuiteId, fundamentalDatabase, trace);
      
      try
      {
        // We are just checking that the already exist here
        ISecurityContextMemberKeys keys = SecurityContextMember.fetchMemberKeysFor(securityContext.getAbsoluteHash(), principalBaseHash, fundamentalDatabase, credential);
        
        System.err.println(keys.getBaseHash());
        System.err.println(keys.getAbsoluteHash());
        System.err.println(keys);

      }
      catch (NoSuchObjectException e)
      {
        SecurityContextMember.createMemberKeysFor(
            securityContext.getAbsoluteHash(), principalBaseHash,
            exchangeKeyHash, wrappedKey,
            encryptedKey, fundamentalDatabase, credential.getSigningKey(), trace);        
      }
      
      return securityContext;
    }
    catch (NoSuchObjectException e)
    {
      throw new PermissionDeniedException("Not a member of this security context");
    }
  }

  @Override
  public IOpenSimpleSecurityContext open(SecretKey secretKey)
  {
    return new OpenSimpleSecurityContext(this, secretKey);
  }
  
  @Override
  public IOpenSimpleSecurityContext openSimpleSecurityContext(IOpenPrincipalCredential credential) throws NoSuchObjectException
  {
    ISecurityContextMember member = 
        credential.getDatabase().fetchSecurityContextMemberFor(getParentHash(),
            credential.getPrincipalBaseHash(), credential);
  
    if(member.getMembershipStatus() == MembershipStatus.NONE)
      throw new NoSuchObjectException("Principal is no longer a member of this security context");
    
    ISecurityContextMemberKeys memberKeys = 
        SecurityContextMember.fetchMemberKeysFor(getBaseHash(),
            credential.getPrincipalBaseHash(), credential.getDatabase(), credential);
    
    SecretKey secretKey = getCipherSuite().unwrap(memberKeys.getWrappedKey(), credential.getExchangeKey().getPrivateKey());
    
    return new OpenSimpleSecurityContext(this, secretKey);

  }
  
  @Override
  public IFundamentalId getMembersSequenceId()
  {
    return getMembersSequenceId(getBaseHash());
  }

  /**
   * Return the ID object for the sequence of members of the given security context.
   * 
   * @param securityContextBaseHash The base hash of the security context.
   * 
   * @return The ID object for the sequence of members of the given security context.
   */
  public static IFundamentalId getMembersSequenceId(Hash securityContextBaseHash)
  {
    return getSequenceId(securityContextBaseHash, Member.TYPE_ID);
  }
  
  private static IFundamentalId getSequenceId(Hash securityContextBaseHash, String contentType)
  {
    return new ContentIdObject.Builder()
        .withSubjectHash(securityContextBaseHash)
        .withSubjectType(SimpleSecurityContext.TYPE_ID)
        .withContentType(contentType)
        .withIdType(ContentIdType.CURRENT_SEQUENCE)
        .build();
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */