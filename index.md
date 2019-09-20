---
nav_order: 1
title: Home
---
# OSS Models
This repository contains the Canon (see https://github.com/symphonyoss/canon) schemas for Symphony open source models.

The following models exist:

## allegro-model
https://models.oss.symphony.com/allegro.json

This model defines types used by the Allegro API (see https://allegro.oss.symphony.com/) i.e. used between callers of the API
and the API itself.

## auth-model
https://models.oss.symphony.com/auth.json

This model defines types used by the authentication section of the Symphony REST API (see https://developers.symphony.com/restapi/reference) This is used by Allegro to call Symphony REST API endpoints.

## chat-model
https://models.oss.symphony.com/chat.json

This model defines types used by the the Symphony Chat Application. This includes schemas for the types exchanged between the Symphony client application and its server.

This model is not supported for customer use and is subject to change at any time.

## fundamental-model
https://models.oss.symphony.com/auth.json

This model defines fundamental types used by the Symphony 2.0 Object Store.

## km-internal-model
https://models.oss.symphony.com/kmInternal.json

This model defines types and methods used by the the Key Manager service in the Symphony Chat Application. This includes schemas for the types exchanged between the Symphony client application and the Key Manager.

This model is not supported for customer use and is subject to change at any time.

## pod-internal-model
https://models.oss.symphony.com/podInternal.json

This model defines types and methods used by the the Pod service in the Symphony Chat Application. This includes schemas for the types exchanged between the Symphony client application and the Pod.

This model is not supported for customer use and is subject to change at any time.

## pod-model
https://models.oss.symphony.com/pod.json

This model defines types used by the pod section of the Symphony REST API (see https://developers.symphony.com/restapi/reference) This is used by Allegro to call Symphony REST API endpoints.

## pod-private-model
https://models.oss.symphony.com/podPrivate.json

This model defines types and methods provided on a single-tenant basis by the Symphony 2.0 Object Store. These methods are 
effectively private to members of the pod. In practice this means that callers from different pods will see different results
from the same call.


## system-model
https://models.oss.symphony.com/system.json

This model defines system types used by the Symphony 2.0 Object Store.
