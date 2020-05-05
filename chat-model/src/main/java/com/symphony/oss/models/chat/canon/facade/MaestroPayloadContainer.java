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
 *  At                  2019-02-18 17:03:55 GMT
 *----------------------------------------------------------------------------------------------------
 */

package com.symphony.oss.models.chat.canon.facade;

import javax.annotation.concurrent.Immutable;

import com.symphony.oss.canon.runtime.IModelRegistry;
import com.symphony.oss.commons.dom.json.ImmutableJsonObject;
import com.symphony.oss.commons.dom.json.MutableJsonObject;
import com.symphony.oss.models.chat.canon.CreateIMMaestroPayload;
import com.symphony.oss.models.chat.canon.CreateRoomMaestroPayload;
import com.symphony.oss.models.chat.canon.IMaestroPayload;
import com.symphony.oss.models.chat.canon.JoinRoomMaestroPayload;
import com.symphony.oss.models.chat.canon.LeaveRoomMaestroPayload;
import com.symphony.oss.models.chat.canon.MaestroPayloadContainerEntity;
import com.symphony.oss.models.chat.canon.MemberModifiedMaestroPayload;

/**
 * Facade for Object ObjectSchema(MaestroPayloadContainer)
 * Generated from ObjectSchema(MaestroPayloadContainer) at #/components/schemas/MaestroPayloadContainer
 */
@Immutable
public class MaestroPayloadContainer extends MaestroPayloadContainerEntity implements IMaestroPayloadContainer
{
  private final IMaestroPayload typedCargo_;

  /**
   * Constructor from builder.
   * 
   * @param builder A mutable builder containing all values.
   */
  public MaestroPayloadContainer(AbstractMaestroPayloadContainerBuilder<?,?> builder)
  {
    super(builder);
    
    typedCargo_ = getCargo();
  }
  
  /**
   * Constructor from serialised form.
   * 
   * @param jsonObject An immutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public MaestroPayloadContainer(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    super(jsonObject, modelRegistry);
    
    typedCargo_ = createTypedCargo(jsonObject, modelRegistry);
  }
  
  /**
   * Constructor from mutable JSON object.
   * 
   * @param mutableJsonObject A mutable JSON object containing the serialized form of the object.
   * @param modelRegistry A model registry to use to deserialize any nested objects.
   */
  public MaestroPayloadContainer(MutableJsonObject mutableJsonObject, IModelRegistry modelRegistry)
  {
    super(mutableJsonObject, modelRegistry);
    
    typedCargo_ = getCargo();
  }

  @Override
  public IMaestroPayload getCargo()
  {
    return typedCargo_;
  }
  
  /**
   * Copy constructor.
   * 
   * @param other Another instance from which all attributes are to be copied.
   */
  public MaestroPayloadContainer(IMaestroPayloadContainer other)
  {
    super(other);
    
    typedCargo_ = other.getCargo();
  }
  
  IMaestroPayload createTypedCargo(ImmutableJsonObject jsonObject, IModelRegistry modelRegistry)
  {
    String version = jsonObject.getRequiredString("version");
    ImmutableJsonObject cargoJson = (ImmutableJsonObject)jsonObject.get("cargo");
    
    switch(version)
    {
      case "D9ProvisioningPayload":                 // return D9new ProvisioningPayload.Builder().withValues(getJsonObject(), false).build();
      case "activateRoomPayload":                   // return new ActivateRoomMaestroPayload.Builder().withValues(getJsonObject(), false).build();
      case "reactivateRoomPayload":                 // return new ReactivateRoomMaestroPayload.Builder().withValues(getJsonObject(), false).build();
      case "createRoomPayload":
        return new CreateRoomMaestroPayload(cargoJson, modelRegistry);
        
      case "createIMPayload":
        return new CreateIMMaestroPayload(cargoJson, modelRegistry);
        
      case "deactivateRoomPayload":                 // return new DeactivateRoomPayload.Builder().withValues(getJsonObject(), false).build();
        return getCargo();

      case "joinRoomPayload":
        return new JoinRoomMaestroPayload(cargoJson, modelRegistry);
        
      case "leaveRoomPayload":
        return new LeaveRoomMaestroPayload(cargoJson, modelRegistry);
        
      case "memberModifiedPayload":
        return new MemberModifiedMaestroPayload(cargoJson, modelRegistry);
        
      case "updateRoomPayload":                     // return new UpdateRoomMaestroPayload.Builder().withValues(getJsonObject(), false).build();
      case "updateStreamPayload":                   // return new UpdateStreamMaestroPayload.Builder().withValues(getJsonObject(), false).build();
      case "publicCertPayload":                     // return new PublicCertPayload.Builder().withValues(getJsonObject(), false).build();
      case "wrappedStreamKeyPayload":               // return new WrappedStreamKeyPayload.Builder().withValues(getJsonObject(), false).build();
      case "streamKeyRequestPayload":               // return new StreamKeyRequestPayload.Builder().withValues(getJsonObject(), false).build();
      case "pubNubMessagePayload":                  // return new PubNubMessagePayload.Builder().withValues(getJsonObject(), false).build();
      case "aliasPayload":                          // return new AliasPayload.Builder().withValues(getJsonObject(), false).build();
      case "roomRequestPayload":                    // return new RoomRequestPayload.Builder().withValues(getJsonObject(), false).build();
      case "enabledEmailIntegrationPayload":        // return new EnabledEmailIntegrationPayload.Builder().withValues(getJsonObject(), false).build();
      case "externalWrappedStreamKeySavedPayload":  // return new ExternalWrappedStreamKeySavedPayload.Builder().withValues(getJsonObject(), false).build();
      case "distributionListPayload":               // return new DistributionListPayload.Builder().withValues(getJsonObject(), false).build();
      case "roomSyncPayload":                       // return new RoomSyncPayload.Builder().withValues(getJsonObject(), false).build();
      case "connectionRequestAlertPayload":         // return new ConnectionRequestAlertPayload.Builder().withValues(getJsonObject(), false).build();
      case "messageSuppressionPayload":             // return new MessageSuppressionPayLoad.Builder().withValues(getJsonObject(), false).build();
      case "webRtcScreensharingPayload":            // return new WebRtcScreensharingPayload.Builder().withValues(getJsonObject(), false).build();
      case "createRoomXpodMaestroPayload":          // return new CreateRoomXpodMaestroPayload.Builder().withValues(getJsonObject(), false).build();
      case "createRoomXpod2MaestroPayload":         // return CreateRoomXpod2new MaestroPayload.Builder().withValues(getJsonObject(), false).build();
      case "synthPayload":                          // return new SynthPayload.Builder().withValues(getJsonObject(), false).build();
      case "xPodIMStateChangeMaestroPayload":       // return new XPodIMStateChangeMaestroPayload.Builder().withValues(getJsonObject(), false).build();
      case "requestContentExportKeyMaestroPayload": // return new RequestContentExportKeyMaestroPayload.Builder().withValues(getJsonObject(), false).build();
      case "updateStreamMembershipMetadataPayload": // return new UpdateStreamMembershipMetadataPayload.Builder().withValues(getJsonObject(), false).build();
 
      default:
        return getCargo();
    }
    
  }
}
/*----------------------------------------------------------------------------------------------------
 * End of template proforma/java/Object/_.java.ftl
 * End of code generation
 *------------------------------------------------------------------------------------------------- */