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
 *  At                  2018-05-08 09:42:51 PDT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.chat.canon.facade;

import javax.annotation.concurrent.Immutable;

import org.symphonyoss.s2.canon.runtime.IModelRegistry;
import org.symphonyoss.s2.common.dom.json.IJsonDomNode;
import org.symphonyoss.s2.common.dom.json.ImmutableJsonObject;
import org.symphonyoss.s2.common.dom.json.MutableJsonObject;
import org.symphonyoss.s2.common.type.provider.IStringProvider;

import com.symphony.oss.models.chat.canon.Bookmark;
import com.symphony.oss.models.chat.canon.DeleteEvent;
import com.symphony.oss.models.chat.canon.DeliveryReceipt;
import com.symphony.oss.models.chat.canon.DownloadAttachmentEvent;
import com.symphony.oss.models.chat.canon.HideStream;
import com.symphony.oss.models.chat.canon.KeepAlive;
import com.symphony.oss.models.chat.canon.LikedEvent;
import com.symphony.oss.models.chat.canon.LiveCurrentMessageEntity;
import com.symphony.oss.models.chat.canon.LiveCurrentMessageType;
import com.symphony.oss.models.chat.canon.MaestroMessage;
import com.symphony.oss.models.chat.canon.MaestroSynth;
import com.symphony.oss.models.chat.canon.MentionNotification;
import com.symphony.oss.models.chat.canon.MessageStatus;
import com.symphony.oss.models.chat.canon.Notification;
import com.symphony.oss.models.chat.canon.OfflineNotice;
import com.symphony.oss.models.chat.canon.PresenceChangeMessage;
import com.symphony.oss.models.chat.canon.PushedSignal;
import com.symphony.oss.models.chat.canon.ReadReceipt;
import com.symphony.oss.models.chat.canon.RemoveBadgeCount;
import com.symphony.oss.models.chat.canon.RemoveMentionBadgeCount;
import com.symphony.oss.models.chat.canon.StreamSettings;
import com.symphony.oss.models.chat.canon.Signal;
import com.symphony.oss.models.chat.canon.SignalNotification;
import com.symphony.oss.models.chat.canon.SocialMessage;
import com.symphony.oss.models.chat.canon.Statistic;
import com.symphony.oss.models.chat.canon.TypingNotification;
import com.symphony.oss.models.chat.canon.UserLastRead;
import com.symphony.oss.models.chat.canon.WallPostNotification;

/**
 * Facade for Object ObjectSchema(LiveCurrentMessage)
 *
 * A livecurrent message as persisted in the DB.
 * Generated from ObjectSchema(LiveCurrentMessage) at #/components/schemas/LiveCurrentMessage
 */
@Immutable
public class LiveCurrentMessage extends LiveCurrentMessageEntity implements ILiveCurrentMessage
{
  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public LiveCurrentMessage(AbstractLiveCurrentMessageBuilder<?,?> builder)
  {
    super(builder);
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public LiveCurrentMessage(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public LiveCurrentMessage(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
  }
   
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public LiveCurrentMessage(ILiveCurrentMessage other)
  {
    super(other);
  }

  public static ILiveCurrentMessage  newInstance(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
      IJsonDomNode versionNode = mutableJsonObject.get("version");
      
      if(versionNode instanceof IStringProvider)
      {
        String versionStr = ((IStringProvider) versionNode).asString();
        LiveCurrentMessageType type = LiveCurrentMessageType.valueOf(versionStr);
        
        if(type == null)
          throw new IllegalArgumentException("Unknown version present \"" + versionStr + "\"");
        
        switch(type)
        {
          case SOCIALMESSAGE:               return new SocialMessage(mutableJsonObject, modelRegistry);
          case EXTERNAL_SOCIALMESSAGE:      return new SocialMessage(mutableJsonObject, modelRegistry);
          case MAESTRO:                     return new MaestroMessage(mutableJsonObject, modelRegistry);
            
          case SIGNAL:                      return new Signal(mutableJsonObject, modelRegistry);
          case REMOVE_MENTION_BADGE_COUNT:  return new RemoveMentionBadgeCount(mutableJsonObject, modelRegistry);
          case READRECEIPT:                 return new ReadReceipt(mutableJsonObject, modelRegistry);
          case REMOVE_BADGE_COUNT:          return new RemoveBadgeCount(mutableJsonObject, modelRegistry);
          case KEEPALIVE:                   return new KeepAlive(mutableJsonObject, modelRegistry);
          case NOTIFICATION:                return new Notification(mutableJsonObject, modelRegistry);
          case DELETE_EVENT:                return new DeleteEvent(mutableJsonObject, modelRegistry); 
          case DOWNLOAD_EVENT:              return new DownloadAttachmentEvent(mutableJsonObject, modelRegistry);
          case Synth:                       return new MaestroSynth(mutableJsonObject, modelRegistry);
          case STREAM_HIDDEN:               return new HideStream(mutableJsonObject, modelRegistry);
          case STREAM_SETTINGS:             return new StreamSettings(mutableJsonObject, modelRegistry);
          case LASTREAD:                    return new UserLastRead(mutableJsonObject, modelRegistry);
          case LIKE_EVENT:                  return new LikedEvent(mutableJsonObject, modelRegistry);
          case STATISTIC:                   return new Statistic(mutableJsonObject, modelRegistry);
          case DELIVERYRECEIPT:             return new DeliveryReceipt(mutableJsonObject, modelRegistry);
          case MESSAGESTATUS:               return new MessageStatus(mutableJsonObject, modelRegistry);
          case OFFLINENOTICE:               return new OfflineNotice(mutableJsonObject, modelRegistry);
          case OBJECTSTATUS:                return new ObjectStatusMessage(mutableJsonObject, modelRegistry);
          case PRESENCE_CHANGE:             return new PresenceChangeMessage(mutableJsonObject, modelRegistry);
          case BOOKMARK:                    return new Bookmark(mutableJsonObject, modelRegistry);
          case SIGNAL_NOTIFICATION:         return new SignalNotification(mutableJsonObject, modelRegistry);
          case MENTION_NOTIFICATION:        return new MentionNotification(mutableJsonObject, modelRegistry);
          case WALLPOST_NOTIFICATION:       return new WallPostNotification(mutableJsonObject, modelRegistry);
          case TYPING:                      return new TypingNotification(mutableJsonObject, modelRegistry);
          case PUSHED_SIGNAL:               return new PushedSignal(mutableJsonObject, modelRegistry);

            
          default:                          return new LiveCurrentMessage(mutableJsonObject, modelRegistry);
        }
      }
      else
        throw new IllegalArgumentException("No version present.");
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */