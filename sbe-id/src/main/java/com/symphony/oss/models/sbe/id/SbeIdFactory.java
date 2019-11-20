/*
 *
 *
 * Copyright 2017 Symphony Communication Services, LLC.
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

package com.symphony.oss.models.sbe.id;

import javax.annotation.Nullable;

import org.symphonyoss.s2.common.hash.Hash;

import com.symphony.oss.models.chat.canon.AttachmentMessageIdObject;
import com.symphony.oss.models.chat.canon.Bookmark;
import com.symphony.oss.models.chat.canon.DeliveryReceipt;
import com.symphony.oss.models.chat.canon.IAttachmentMessageIdObject;
import com.symphony.oss.models.chat.canon.IMessageIdObject;
import com.symphony.oss.models.chat.canon.IThreadIdObject;
import com.symphony.oss.models.chat.canon.IUserMessageIdObject;
import com.symphony.oss.models.chat.canon.LikedEvent;
import com.symphony.oss.models.chat.canon.MaestroMessage;
import com.symphony.oss.models.chat.canon.MentionNotification;
import com.symphony.oss.models.chat.canon.MessageIdObject;
import com.symphony.oss.models.chat.canon.ObjectStatusMessage;
import com.symphony.oss.models.chat.canon.OfflineNotice;
import com.symphony.oss.models.chat.canon.PushedSignal;
import com.symphony.oss.models.chat.canon.ReadReceipt;
import com.symphony.oss.models.chat.canon.Signal;
import com.symphony.oss.models.chat.canon.SignalNotification;
import com.symphony.oss.models.chat.canon.ThreadIdObject;
import com.symphony.oss.models.chat.canon.TypingNotification;
import com.symphony.oss.models.chat.canon.UserMessageIdObject;
import com.symphony.oss.models.chat.canon.WallPostNotification;
import com.symphony.oss.models.chat.canon.facade.MessageId;
import com.symphony.oss.models.chat.canon.facade.PresenceChangeMessage;
import com.symphony.oss.models.chat.canon.facade.SocialMessage;
import com.symphony.oss.models.chat.canon.facade.ThreadId;
import com.symphony.oss.models.chat.canon.facade.User;
import com.symphony.oss.models.core.canon.facade.IUserIdFunction;
import com.symphony.oss.models.core.canon.facade.PodAndUserId;
import com.symphony.oss.models.core.canon.facade.PodId;
import com.symphony.oss.models.core.canon.facade.UserId;
import com.symphony.oss.models.fundamental.canon.facade.IFundamentalId;
import com.symphony.oss.models.fundamental.canon.facade.IUserIdObject;
import com.symphony.oss.models.fundamental.canon.facade.RotationId;
import com.symphony.oss.models.fundamental.canon.facade.SecurityContextMember;
import com.symphony.oss.models.fundamental.canon.facade.UserIdObject;
import com.symphony.oss.models.fundmental.canon.IMemberIdObject;
import com.symphony.oss.models.fundmental.canon.ISecurityContextRotationId;
import com.symphony.oss.models.fundmental.canon.SecurityContextRotationId;
import com.symphony.oss.models.fundmental.canon.SecurityContextRotationIdType;
import com.symphony.oss.models.system.IPrincipalSecurityContext;
import com.symphony.oss.models.system.PrincipalSecurityContext;

/**
 * BREAKING CHANGE
 * 
 * This class used to return Hashes, but now returns the ID objects. In calling code which
 * used to work and is now broken, which expects a Hash, simply call .getAbsoluteHash() on
 * the returned ID object.
 * 
 * A factory to create ID objects which can be used as inferred Hash values each of which is
 * a 2.0 ID which acts as a mirror for an object in the Symphony1.5 space.
 *
 * Many methods take a podId which it uses to generate a globally unique ID for each
 * object across all pods. This means that if the same threadId happens to exist in 2
 * separate pods and we create a 2.0 Sequence to represent those two threads that the
 * 2.0 IDs will not collide in a multi tenant environment.
 *
 * This also means that where the same message goes xpod that events about the same message
 * originating from different pods will have different event IDs. In the comments below
 * where it says "originating pod" it means the pod where the event originates which may
 * be different from the pod where the message originated.
 * 
 * @author Bruce Skingle
 *
 */
public class SbeIdFactory
{
  private final IUserIdFunction userIdFunction_;
  
  /**
   * Constructor.
   * 
   * @param internalPodId The pod's internal pod ID
   * @param externalPodId The pod's external pod ID.
   */
  public SbeIdFactory(long internalPodId, long externalPodId)
  {
    userIdFunction_ = UserId.getUserIdFunction(internalPodId, externalPodId);
  }

  /**
   * Return the externalUserId for the given userId which may be internal or external.
   * 
   * @param internalOrExternalUserId A userId which may be internal or external.
   * 
   * @return The externalUserId for the given internalOrExternalUserId.
   */
  public PodAndUserId toExternalUserId(PodAndUserId internalOrExternalUserId)
  {
    return userIdFunction_.map(internalOrExternalUserId);
  }
  
  /**
   * Create a 2.0 Hash (ID) for the given userId.
   *
   * The hash of an IUserIdObject is a principalBaseHash and the object stored with that base hash should be an IPrincipal.
   * 
   * @param internalOrExternalUserId An SBE userId
   * 
   * @return            The 2.0 object ID for the mirror of the given ID.
   */
  public IUserIdObject principalId(PodAndUserId internalOrExternalUserId)
  {
    return new UserIdObject.Builder().withPodAndUserId(
        toExternalUserId(internalOrExternalUserId)).build();
  }

  /**
   * Create a 2.0 Hash (ID) for the given signal messageId.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param messageId   A messageId
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   * 
   * @deprecated Signal messages are no longer forwarded they are superseded by TypingNotification messages.
   */
  @Deprecated
  public IMessageIdObject signalId(PodId podId, MessageId messageId)
  {
    return new MessageIdObject.Builder()
        .withPodId(podId)
        .withMessageId(messageId)
        .withMessageType(Signal.TYPE_ID)
        .build()
        ;
  }

  /**
   * Create a 2.0 Hash (ID) for the given presence change message.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param messageId   A messageId
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   */
  public IMessageIdObject presenceChangeId(PodId podId, MessageId messageId)
  {
    return new MessageIdObject.Builder()
        .withPodId(podId)
        .withMessageId(messageId)
        .withMessageType(PresenceChangeMessage.TYPE_ID)
        .build()
        ;
  }

  /**
   * Create a 2.0 Hash (ID) for the given bookmark event.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param messageId   A messageId
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   */
  public IMessageIdObject bookmarkId(PodId podId, MessageId messageId)
  {
    return new MessageIdObject.Builder()
      .withPodId(podId)
      .withMessageId(messageId)
      .withMessageType(Bookmark.TYPE_ID)
      .build()
      ;
  }
  
  /**
   * Create a 2.0 Hash (ID) for the given messageId.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param messageId   A messageId
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   */
  public IMessageIdObject messageId(PodId podId, MessageId messageId)
  {
    return new MessageIdObject.Builder()
        .withPodId(podId)
        .withMessageId(messageId)
        .withMessageType(SocialMessage.TYPE_ID)
        .build()
        ;
  }

  /**
   * Create a 2.0 Hash (ID) for the given LiveCurrent messageId and type.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param messageId   A messageId
   * @param canonType   The specific type of the message.
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   */
  public IFundamentalId messageId(PodId podId, MessageId messageId, String canonType)
  {
    return new MessageIdObject.Builder()
        .withPodId(podId)
        .withMessageId(messageId)
        .withMessageType(canonType)
        .build()
        ;
  }

  /**
   * Create a 2.0 Hash (ID) for the given threadId.
   *
   * The hash of an IThreadIdObject is the base hash of a Stream object from the chat model.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param threadId    A threadId
   * 
   * @return            The 2.0 object ID for the mirror of the given ID.
   * 
   * @throws NullPointerException if any parameter is null.
   */
  public IThreadIdObject threadId(PodId podId, ThreadId threadId)
  {
    return new ThreadIdObject.Builder()
        .withPodId(podId)
        .withThreadId(threadId)
        .build()
        ;
  }
  
 /**
  * Create a 2.0 Hash (ID) for an object status of the given messageId for the given podId.
  *
  * @param podId       The pod ID of the pod where this event originated.
  * @param messageId   The id of the message read.
  * @param threadId    The threadId of the message read.
  * @return            The 2.0 object ID for the mirror of the given ID.
  * @throws NullPointerException if any parameter is null.
  */
 public IMessageIdObject objectStatusId(PodId podId, MessageId messageId, ThreadId threadId)
 {
   return new MessageIdObject.Builder()
       .withPodId(podId)
       .withMessageId(messageId)
       .withThreadId(threadId)
       .withMessageType(ObjectStatusMessage.TYPE_ID)
       .build()
       ;
 }

  /**
   * Create a 2.0 Hash (ID) for the read receipt of the given messageId for the given userId.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param internalOrExternalUserId The SBE userId of the user who read the message.
   * @param messageId   The id of the message read.
   * @param threadId    The threadId of the message read.
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   */
  public IUserMessageIdObject readReceiptId(PodId podId, PodAndUserId internalOrExternalUserId, MessageId messageId, ThreadId threadId)
  {
    return new UserMessageIdObject.Builder()
        .withUserId(internalOrExternalUserId)
        .withPodId(podId)
        .withMessageId(messageId)
        .withThreadId(threadId)
        .withMessageType(ReadReceipt.TYPE_ID)
        .build()
        ;
  }

  /**
   * Create a 2.0 Hash (ID) for the delivery receipt of the given messageId for the given userId.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param internalOrExternalUserId The SBE userId of the user who read the message.
   * @param messageId   The id of the message read.
   * @param threadId    The threadId of the message read.
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   */
  public IUserMessageIdObject deliveryReceiptId(PodId podId, PodAndUserId internalOrExternalUserId, MessageId messageId, ThreadId threadId)
  {
    return new UserMessageIdObject.Builder()
        .withUserId(internalOrExternalUserId)
        .withPodId(podId)
        .withMessageId(messageId)
        .withThreadId(threadId)
        .withMessageType(DeliveryReceipt.TYPE_ID)
        .build()
        ;
  }
  
  /**
  * Create a 2.0 Hash (ID) for a MaestroMessage.
  *
  * @param podId       The pod ID of the pod where this event originated.
  * @param messageId         The message ID.
  * @param threadId          The thread ID.
  * @return            The 2.0 object ID for the mirror of the given ID.
  * @throws NullPointerException if any parameter is null.
  */
   public IMessageIdObject maestroId(PodId podId, MessageId messageId, @Nullable ThreadId threadId)
   {
     return new MessageIdObject.Builder()
         .withPodId(podId)
         .withMessageId(messageId)
         .withThreadId(threadId)
         .withMessageType(MaestroMessage.TYPE_ID)
         .build()
         ;
   }

   /**
    * Create a 2.0 Hash (ID) for an offline notification event.
    *
    * @param podId       The pod ID of the pod where this event originated.
    * This event means that a user was offline when a message was delivered and an email
    * was sent to them.
    *
    * @param toUserId          The id of the offline user.
    * @param messageId         The message ID.
    * @return            The 2.0 object ID for the mirror of the given ID.
    * @throws NullPointerException if any parameter is null.
    */
   public IUserMessageIdObject offlineNoticeId(PodId podId, PodAndUserId toUserId, MessageId messageId)
   {
     return new UserMessageIdObject.Builder()
         .withUserId(toUserId)
         .withPodId(podId)
         .withMessageId(messageId)
         .withMessageType(OfflineNotice.TYPE_ID)
         .build()
         ;
   }

//   Delete events need to have the same ID as the message they delete
//   /**
//    * Create a 2.0 Hash (ID) for a DeleteEvent.
//    *
//    * @param podId             The pod ID of the pod where this event originated.
//    * @param requesterId       The id of the requesting user.
//    * @param deleteMessageId   The message ID of the deleted message.
//    * @return            The 2.0 object ID for the mirror of the given ID.
//    * @throws NullPointerException if any parameter is null.
//    */
//   public IUserMessageIdObject deleteEventId(PodId podId, PodAndUserId requesterId, MessageId deleteMessageId)
//   {
//     return new UserMessageIdObject.Builder()
//         .withUserId(requesterId)
//         .withPodId(podId)
//         .withMessageId(deleteMessageId)
//         .withMessageType(DeleteEvent.TYPE_ID)
//         .build()
//         ;
//   }

   /**
    * Create a 2.0 Hash (ID) for a DownloadAttachmentEvent.
    *
    * @param podId               The pod ID of the pod where this event originated.
    * @param downloadedByUserId  The user ID of the user downloading the attachment.
    * @param messageId           The message ID of the attachment.
    * @param fileId              The file ID of the attachment.
    * @return            The 2.0 object ID for the mirror of the given ID.
    * @throws NullPointerException if any parameter is null.
    */
   public IAttachmentMessageIdObject downloadAttachmentEventId(PodId podId, PodAndUserId downloadedByUserId, MessageId messageId, String fileId)
   {
     return new AttachmentMessageIdObject.Builder()
         .withFileId(fileId)
         .withUserId(downloadedByUserId)
         .withPodId(podId)
         .withMessageId(messageId)
         .withMessageType(Signal.TYPE_ID)
         .build()
         ;
   }

   /**
    * Create a 2.0 Hash (ID) for a LikeEvent.
    *
    * @param podId               The pod ID of the pod where this event originated.
    * @param liker               The user ID of the user sending the like.
    * @param likedMessageId      The message ID of liked message.
    * @return            The 2.0 object ID for the mirror of the given ID.
    * @throws NullPointerException if any parameter is null.
    */
   public IMessageIdObject likeEventId(PodId podId, PodAndUserId liker, MessageId likedMessageId)
   {
     return new MessageIdObject.Builder()
         .withPodId(podId)
         .withMessageId(likedMessageId)
         .withMessageType(LikedEvent.TYPE_ID)
         .build()
         ;
   }


   /**
    * Create a 2.0 Hash (ID) for the given TypingNotification messageId.
    *
    * @param podId       The pod ID of the pod where this event originated.
    * @param messageId   A messageId
    * @return            The 2.0 object ID for the mirror of the given ID.
    * @throws NullPointerException if any parameter is null.
    */
  public IMessageIdObject typingNotificationId(PodId podId, MessageId messageId)
  {
    return new MessageIdObject.Builder()
        .withPodId(podId)
        .withMessageId(messageId)
        .withMessageType(TypingNotification.TYPE_ID)
        .build()
        ;
  }
  
  /**
   * Create a 2.0 Hash (ID) for the given SignalNotification messageId.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param messageId   A messageId
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   */
  public IMessageIdObject signalNotificationId(PodId podId, MessageId messageId)
  {
    return new MessageIdObject.Builder()
        .withPodId(podId)
        .withMessageId(messageId)
        .withMessageType(SignalNotification.TYPE_ID)
        .build()
        ;
  }

  /**
   * Create a 2.0 Hash (ID) for the given MentionNotification messageId.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param messageId   A messageId
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   */
  public IMessageIdObject mentionNotificationId(PodId podId, MessageId messageId)
  {
    return new MessageIdObject.Builder()
        .withPodId(podId)
        .withMessageId(messageId)
        .withMessageType(MentionNotification.TYPE_ID)
        .build()
        ;
  }

  /**
   * Create a 2.0 Hash (ID) for the given MentionNotification messageId.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param messageId   A messageId
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   */
  public IMessageIdObject wallPostNotificationId(PodId podId, MessageId messageId)
  {
    return new MessageIdObject.Builder()
        .withPodId(podId)
        .withMessageId(messageId)
        .withMessageType(WallPostNotification.TYPE_ID)
        .build()
        ;
  }

  /**
   * Create a 2.0 Hash (ID) for the given MentionNotification messageId.
   *
   * @param podId       The pod ID of the pod where this event originated.
   * @param messageId   A messageId
   * @return            The 2.0 object ID for the mirror of the given ID.
   * @throws NullPointerException if any parameter is null.
   */
  public IMessageIdObject pushedSignalId(PodId podId, MessageId messageId)
  {
    return new MessageIdObject.Builder()
        .withPodId(podId)
        .withMessageId(messageId)
        .withMessageType(PushedSignal.TYPE_ID)
        .build()
        ;
  }
  
  /**
   * Create the ID Object for the wrapped keys for the given user's PrincipalSecurityContext.
   * 
   * This maps to the user's SBE account key.
   * 
   * @param userId      The user ID.
   * @param rotationId  The rotation ID of the key.
   * 
   * @return The ID object for the wrapped keys (base hash of the ISecurityContextMemberKeys object).
   */
  public IMemberIdObject getPrincipalSecurityContextKeysId(PodAndUserId userId, RotationId rotationId)
  {

    IUserIdObject userIdObject = principalId(userId);
    Hash principalBaseHash = userIdObject.getAbsoluteHash();
    
    IPrincipalSecurityContext principalSecurityContext = new PrincipalSecurityContext(principalBaseHash);
    
    ISecurityContextRotationId  securityContextRotationId = new SecurityContextRotationId.Builder()
        .withType(SecurityContextRotationIdType.SECURITY_CONTEXT)
        .withSecurityContextBaseHash(principalSecurityContext.getBaseHash())
        .withRotationId(rotationId)
        .build();
    
    return SecurityContextMember.getMemberKeysIdFor(securityContextRotationId.getAbsoluteHash(), principalBaseHash);
  }

  /**
   * Create the ID Object for the wrapped keys for the given user and SBE thread.
   * 
   * This maps to a user's SBE content for the given thread.
   * 
   * @param userId      The user ID.
   * @param podId       The podId whose view of the thread we are dealing with.
   * @param threadId    The thread for which the key is required.
   * @param rotationId  The rotation ID of the key.
   * 
   * @return The ID object for the wrapped keys (base hash of the ISecurityContextMemberKeys object).
   */
  public IMemberIdObject getThreadSecurityContextKeysId(PodAndUserId userId, PodId podId, ThreadId threadId, RotationId rotationId)
  {
    IUserIdObject userIdObject = principalId(userId);
    Hash principalBaseHash = userIdObject.getAbsoluteHash();
    
    IThreadIdObject threadIdObject = new ThreadIdObject.Builder()
        .withPodId(podId)
        .withThreadId(threadId)
        .build();
    
    ISecurityContextRotationId  securityContextRotationId = new SecurityContextRotationId.Builder()
        .withType(SecurityContextRotationIdType.SECURITY_CONTEXT)
        .withSecurityContextBaseHash(threadIdObject.getAbsoluteHash())
        .withRotationId(rotationId)
        .build();
    
    Hash securityContextHash = securityContextRotationId.getAbsoluteHash();
    
    return SecurityContextMember.getMemberKeysIdFor(securityContextHash, principalBaseHash);
  }

  /**
   * Return the ID object for the base user info for the given user.
   * 
   * @param userId A UserId.
   * 
   * @return The ID objects whose absolute hash is the base hash of the IUser info object for the given user.
   */
  public IFundamentalId getUserId(PodAndUserId userId)
  {
    IUserIdObject userIdObject = principalId(userId);
    Hash principalBaseHash = userIdObject.getAbsoluteHash();
    
    return User.getBaseUserId(principalBaseHash, userIdObject.getPodId());
    
  }
}
