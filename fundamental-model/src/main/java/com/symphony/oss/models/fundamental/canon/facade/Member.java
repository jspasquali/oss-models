/**
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
 *
 *----------------------------------------------------------------------------------------------------
 * Proforma generated from
 *		Template groupId		 org.symphonyoss.s2.canon
 *           artifactId canon-template-java
 *		Template name		   proforma/java/Object/_.java.ftl
 *		Template version	   1.0
 *  At                  2019-04-15 07:28:12 GMT-07:00
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.fundamental.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.common.immutable.ImmutableByteArray;
import org.symphonyoss.s2.fugue.core.trace.ITraceContext;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.exception.NoSuchObjectException;
import org.symphonyoss.s2.canon.runtime.IEntity;
import org.symphonyoss.s2.canon.runtime.IModelRegistry;

import java.util.List;
import org.symphonyoss.s2.common.immutable.ImmutableByteArray;
import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.fundmental.canon.MemberEntity;
import com.symphony.oss.models.fundmental.canon.MemberIdObject;
import com.symphony.oss.models.fundmental.canon.MembershipStatus;
import com.symphony.oss.models.fundmental.canon.SecurityContextMemberKeys;
import com.symphony.oss.models.fundmental.canon.IMemberEntity;
import com.symphony.oss.models.fundmental.canon.IMemberIdObject;
import com.symphony.oss.models.fundmental.canon.IMemberEntity;
import com.symphony.oss.models.fundamental.canon.facade.Member.AbstractMemberBuilder;
import com.symphony.oss.models.fundamental.canon.facade.Member.Builder;
import com.symphony.oss.models.fundamental.store.IFundamentalDatabaseWritable;
import com.symphony.oss.models.fundamental.canon.facade.FundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalObject;
import com.symphony.oss.models.fundamental.canon.facade.IMember;
import com.symphony.oss.models.fundamental.canon.facade.IOpenSigningKey;
import com.symphony.oss.models.fundamental.canon.facade.Member;
import com.symphony.oss.models.fundamental.canon.facade.SecurityContextMember;
import com.symphony.oss.models.fundamental.canon.facade.SimpleSecurityContext;
import com.symphony.oss.models.fundmental.canon.ContentIdType;
import com.symphony.oss.models.fundmental.canon.FundamentalModel;

/**
 * Facade for Object ObjectSchema(Member)
 *
 * A generic membership record.
 * Generated from ObjectSchema(Member) at #/components/schemas/Member
 */
@Immutable
@SuppressWarnings("unused")
public class Member extends MemberEntity implements IMember
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public Member(AbstractMemberBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Member(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public Member(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public Member(IMember other)
  {
    super(other);
  }

  public static IMemberIdObject getMemberIdFor(Hash securityContextBaseHash, Hash principalBaseHash)
  {
    // Arguably should be type Member but we did security context first and we use that.....
    return getMembershipId(SecurityContextMember.TYPE_ID, securityContextBaseHash, principalBaseHash);
  }

  public static IMemberIdObject getMemberKeysIdFor(Hash securityContextBaseHash, Hash principalBaseHash)
  {
    return getMembershipId(SecurityContextMemberKeys.TYPE_ID, securityContextBaseHash, principalBaseHash);
  }
 
  private static IMemberIdObject getMembershipId(String contentTypeId, Hash securityContextBaseHash, Hash principalBaseHash)
  {
    return new MemberIdObject.Builder()
        .withSubjectHash(securityContextBaseHash)
        .withSubjectType(SimpleSecurityContext.TYPE_ID)
        .withContentHash(principalBaseHash)
        .withContentType(contentTypeId)
        .withIdType(ContentIdType.ATTRIBUTE)
        .build()
        ;
  }
  
  /**
   * Abstract builder for Member. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractMemberBuilder<B extends AbstractMemberBuilder<B,T>, T extends IMemberEntity> extends AbstractMemberEntityBuilder<B,T>
  {
    protected IFundamentalDatabaseWritable fundamentalDatabase_;
    protected ITraceContext                trace_;
    protected IOpenSigningKey              signingKey_;
    protected IMemberIdObject              memberId_;
    
    protected AbstractMemberBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractMemberBuilder(Class<B> type, IMemberEntity initial)
    {
      super(type, initial);
    }

    @Override
    protected void validate()
    {
      require(getGroupBaseHash(),   "Group Base Hash");
      require(fundamentalDatabase_, "Fundamental Database");
      require(trace_,               "Trace Context");
      require(signingKey_,          "Signing Key");
      require(getMemberBaseHash(),  "Member Base Hash");
      
      if(getSigningKeyHash() == null)
      {
        withSigningKeyHash(signingKey_.getAbsoluteHash());
      }
      else if(!getSigningKeyHash().equals(signingKey_.getAbsoluteHash()))
      {
        throw new IllegalStateException("Builder indicates signing key hash " + getSigningKeyHash() + ", but signing key is " + signingKey_.getAbsoluteHash() + ", (you don't actually need to call withSigningKeyHash()).");
      }

      if(getMembershipStatus() == null)
        withMembershipStatus(MembershipStatus.MEMBER);
      
      withMemberId(getMemberIdFor(getGroupBaseHash(), getMemberBaseHash()));
      
    }
    
    
    protected void preConstruct()
    {
      try
      {
        IMember existingMember = fundamentalDatabase_.fetchCurrentById(memberId_, IMember.class);
        
        withPrevHash(existingMember.getAbsoluteHash());
      }
      catch(NoSuchObjectException e)
      {
        withPrevHash(memberId_.getAbsoluteHash());
        fundamentalDatabase_.save(memberId_, trace_);
      }
      
      withBaseHash(memberId_.getAbsoluteHash());
    }

    /**
     * Set the Fundamental Object Database to save objects to.
     * 
     * @param fundamentalDatabase The Fundamental Object Database to save objects to.
     * 
     * @return This (fluent method).
     */
    public B withFundamentalDatabase(IFundamentalDatabaseWritable fundamentalDatabase)
    {
      fundamentalDatabase_ = fundamentalDatabase;
      
      return self();
    }
    
    /**
     * Set the trace context to use when saving objects.
     * 
     * @param trace The trace context to use when saving objects.
     * 
     * @return This (fluent method).
     */
    public B withTrace(ITraceContext trace)
    {
      trace_ = trace;
      
      return self();
    }

    /**
     * Set the signing key.
     * 
     * @param signingKey The open signing key with which the object is to be signed.
     * 
     * @return This (fluent method)
     */
    public B withSigningKey(IOpenSigningKey signingKey)
    {
      signingKey_ = signingKey;
      
      return self();
    }

    /**
     * Set the membership ID.
     * 
     * @param memberId  The membership ID
     * 
     * @return This (fluent method)
     */
    public B withMemberId(IMemberIdObject memberId)
    {
      memberId_ = memberId;
      
      return self();
    }
  }
  
  /**
   * Builder for Member
   *
   */
  public static class Builder extends AbstractMemberBuilder<Builder, IMember>
  {
    /**
     * Constructor.
     */
    public Builder()
    {
      super(Builder.class);
    }

    /**
     * Constructor initialised from another object instance.
     * 
     * @param initial
     *          An instance of the built type from which values are to be
     *          initialised.
     */
    public Builder(IMemberEntity initial)
    {
      super(Builder.class, initial);
    }

    @Override
    protected IMember construct()
    {
      preConstruct();
  
      Member  member        = new Member(this);
      IFundamentalObject     memberObject  = new FundamentalObject.ObjectBuilder()
           .withSigningKey(signingKey_)
           .withPayload(member)
           .build()
           ;
       
      fundamentalDatabase_.save(memberObject, trace_);
       
      return member;
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */