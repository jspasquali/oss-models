/**
 * Copyright 2020 Symphony Communication Services, LLC.
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
 *  At                  2020-01-27 13:01:50 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.allegro.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.symphony.messageml.elements.CashTag;
import org.symphonyoss.symphony.messageml.elements.Element;
import org.symphonyoss.symphony.messageml.elements.HashTag;
import org.symphonyoss.symphony.messageml.elements.Mention;
import org.symphonyoss.symphony.messageml.util.IUserPresentation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.symphony.oss.models.allegro.canon.ReceivedSocialMessageEntity;
import com.symphony.oss.models.chat.canon.facade.ISocialMessage;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.allegro.canon.IReceivedSocialMessageEntity;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.symphony.oss.canon.runtime.IEntity;
import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.immutable.ImmutableByteArray;
import com.symphony.oss.models.allegro.canon.AllegroModel;

/**
 * Facade for Object ObjectSchema(ReceivedSocialMessage)
 * Generated from ObjectSchema(ReceivedSocialMessage) at #/components/schemas/ReceivedSocialMessage
 */
@Immutable
@SuppressWarnings("unused")
public class ReceivedSocialMessage extends ReceivedSocialMessageEntity implements IReceivedSocialMessage
{
  private Map<PodAndUserId, String> mentionMap_;
  private Set<String>               hashTagSet_;
  private Set<String>               cashTagSet_;
  
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public ReceivedSocialMessage(AbstractReceivedSocialMessageBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ReceivedSocialMessage(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public ReceivedSocialMessage(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public ReceivedSocialMessage(IReceivedSocialMessage other)
  {
    super(other);
  }
  
  @Override
  public boolean isMentioned(PodAndUserId userId)
  {
    return getMentions().containsKey(userId);
  }

  @Override
  public synchronized Map<PodAndUserId, String> getMentions()
  {
    if(mentionMap_ == null)
      processMessageML();
    
    return mentionMap_;
  }

  @Override
  public synchronized Set<String> getHashTags()
  {
    if(mentionMap_ == null)
      processMessageML();
    
    return hashTagSet_;
  }

  @Override
  public synchronized Set<String> getCashTags()
  {
    if(mentionMap_ == null)
      processMessageML();
    
    return cashTagSet_;
  }
  
  private void processMessageML()
  {
    if(getMessageML() == null)
    {
      mentionMap_ = ImmutableMap.of();
      hashTagSet_ = ImmutableSet.of();
      cashTagSet_ = ImmutableSet.of();
    }
    else
    {
      Map<PodAndUserId, String> mentionMap = new HashMap<>();
      Set<String>               hashTagSet = new HashSet<>();
      Set<String>               cashTagSet = new HashSet<>();
      
      processMessageML(getMessageML(), mentionMap, hashTagSet, cashTagSet);
      
      mentionMap_ = ImmutableMap.copyOf(mentionMap);
      hashTagSet_ = ImmutableSet.copyOf(hashTagSet);
      cashTagSet_ = ImmutableSet.copyOf(cashTagSet);
    }
  }

  private void processMessageML(Element element, Map<PodAndUserId, String> mentionMap, Set<String> hashTagSet, Set<String> cashTagSet)
  {
    if(element instanceof Mention)
    {
      IUserPresentation presentation = ((Mention) element).getUserPresentation();
      
      mentionMap.put(PodAndUserId.newBuilder().build(presentation.getId()), presentation.getPrettyName());
    }
    else if(element instanceof HashTag)
    {
      hashTagSet.add(((HashTag) element).getTag());
    }
    else if(element instanceof CashTag)
    {
      cashTagSet.add(((CashTag) element).getTag());
    }
    
    if(element.getChildren() != null)
    {
      for(Element child : element.getChildren())
        processMessageML(child, mentionMap, hashTagSet, cashTagSet);
    }
  }
  
  @Override
  public ISocialMessage getLiveCurrentMessage()
  {
    return getSocialMessage();
  }

  /**
   * Abstract builder for ReceivedSocialMessage. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
  public static abstract class AbstractReceivedSocialMessageBuilder<B extends AbstractReceivedSocialMessageBuilder<B,T>, T extends IReceivedSocialMessageEntity> extends AbstractReceivedSocialMessageEntityBuilder<B,T>
  {
    protected AbstractReceivedSocialMessageBuilder(Class<B> type)
    {
      super(type);
    }
    
    protected AbstractReceivedSocialMessageBuilder(Class<B> type, IReceivedSocialMessageEntity initial)
    {
      super(type, initial);
    }
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */