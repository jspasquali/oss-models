/*
 * Copyright 2018 Symphony Communication Services, LLC.
 *
 * All Rights Reserved
 */

package com.symphony.oss.model.chat;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.IJsonDomNode;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.commons.type.provider.IStringProvider;
import com.symphony.oss.models.chat.canon.Bookmark;
import com.symphony.oss.models.chat.canon.DeleteEvent;
import com.symphony.oss.models.chat.canon.DeliveryReceipt;
import com.symphony.oss.models.chat.canon.DownloadAttachmentEvent;
import com.symphony.oss.models.chat.canon.HideStream;
import com.symphony.oss.models.chat.canon.ILiveCurrentMessage;
import com.symphony.oss.models.chat.canon.KeepAlive;
import com.symphony.oss.models.chat.canon.LikedEvent;
import com.symphony.oss.models.chat.canon.LiveCurrentMessage;
import com.symphony.oss.models.chat.canon.LiveCurrentMessageType;
import com.symphony.oss.models.chat.canon.MaestroMessage;
import com.symphony.oss.models.chat.canon.MaestroSynth;
import com.symphony.oss.models.chat.canon.MentionNotification;
import com.symphony.oss.models.chat.canon.MessageStatus;
import com.symphony.oss.models.chat.canon.Notification;
import com.symphony.oss.models.chat.canon.ObjectStatusMessage;
import com.symphony.oss.models.chat.canon.OfflineNotice;
import com.symphony.oss.models.chat.canon.PushedSignal;
import com.symphony.oss.models.chat.canon.ReadReceipt;
import com.symphony.oss.models.chat.canon.RemoveBadgeCount;
import com.symphony.oss.models.chat.canon.RemoveMentionBadgeCount;
import com.symphony.oss.models.chat.canon.Signal;
import com.symphony.oss.models.chat.canon.SignalNotification;
import com.symphony.oss.models.chat.canon.Statistic;
import com.symphony.oss.models.chat.canon.StreamSettings;
import com.symphony.oss.models.chat.canon.TypingNotification;
import com.symphony.oss.models.chat.canon.UserLastRead;
import com.symphony.oss.models.chat.canon.WallPostNotification;
import com.symphony.oss.models.chat.canon.facade.PresenceChangeMessage;
import com.symphony.oss.models.chat.canon.facade.SocialMessage;

/**
 * Factory for LiveCurrentMessages which understands the old SBE type mechanism rather than the canon 
 * generated type identifiers.
 * 
 * @author Bruce Skingle
 *
 */
public class LiveCurrentMessageFactory
{
  /**
   * Parse the given JSON into a LiveCurrentMessage.
   * 
   * @param mutableJsonObject A JSON Object.
   * @param modelRegistry     A model registry.
   * 
   * @return  The given JSON as a LiveCurrentMessage.
   */
  public ILiveCurrentMessage  newLiveCurrentMessage(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
      IJsonDomNode versionNode = mutableJsonObject.get("version");
      
      if(versionNode instanceof IStringProvider)
      {
        String versionStr = ((IStringProvider) versionNode).asString();
        LiveCurrentMessageType type = LiveCurrentMessageType.valueOf(versionStr);
        
        if(type == null)
          return new LiveCurrentMessage(mutableJsonObject, modelRegistry);
        
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
          case PRESENCE_CHANGE:             return new PresenceChangeMessage(mutableJsonObject, modelRegistry);
          case BOOKMARK:                    return new Bookmark(mutableJsonObject, modelRegistry);
          case SIGNAL_NOTIFICATION:         return new SignalNotification(mutableJsonObject, modelRegistry);
          case MENTION_NOTIFICATION:        return new MentionNotification(mutableJsonObject, modelRegistry);
          case WALLPOST_NOTIFICATION:       return new WallPostNotification(mutableJsonObject, modelRegistry);
          case TYPING:                      return new TypingNotification(mutableJsonObject, modelRegistry);
          case PUSHED_SIGNAL:               return new PushedSignal(mutableJsonObject, modelRegistry);

          case OBJECTSTATUS:                return new ObjectStatusMessage(unwrapObjectStatus(mutableJsonObject), modelRegistry);
            
          default:                          return new LiveCurrentMessage(mutableJsonObject, modelRegistry);
        }
      }
      else
        throw new IllegalArgumentException("No version present.");
  }

  public MutableJsonObject unwrapObjectStatus(MutableJsonObject mutableJsonObject)
  {
    return mutableJsonObject;
  }
}
