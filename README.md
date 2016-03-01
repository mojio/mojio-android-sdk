![](static/banner_mojio.png)
# Mojio Android SDK #

Android SDK for integrating with the Mojio platform.

For more information please see the [developer website](http://developer.moj.io/)

### Disclaimer ###
**UNDER ACTIVE DEVELOPMENT**

* Methods may change without notice
* Currently provided "AS IS"

## Download ##
```gradle
compile 'io.moj.mobile.android:mojio-sdk-auth:0.0.8'
compile 'io.moj.mobile.android:mojio-sdk-common:0.0.8'
compile 'io.moj.mobile.android:mojio-sdk-model:0.0.8'
compile 'io.moj.mobile.android:mojio-sdk-push:0.0.8'
```

The Mojio Android SDK requires at minimum Android 3.0 (API 11)

## Structure ##
The SDK is broken into modules - you may choose to use some or all of them for your application.
See each module's subdirectory for more detailed instructions.

### 1. [mojio-sdk-auth](https://github.com/mojio/mojio-android-sdk/tree/develop/mojio-sdk-auth) ###
  Implementation of OAuthActivity and OAuthFragment for retrieving an access token using our
  web-based login flow.

### 2. [mojio-sdk-model](https://github.com/mojio/mojio-android-sdk/tree/develop/mojio-sdk-model) ###
  Model classes for use with the Mojio REST API. These objects are annotated for serialization with
  GSON.

### 3. [mojio-sdk-push](https://github.com/mojio/mojio-android-sdk/tree/develop/mojio-sdk-push) ###
  Model classes for use with the Mojio PUSH API. These objects are annotated for serialization with
  GSON.
  
### 4. [mojio-sdk-common](https://github.com/mojio/mojio-android-sdk/tree/develop/mojio-sdk-common) ###
  Common functionality used by other modules such as environment info and a lightweight,
  configurable, logger.
  
### 5. [mojio-sdk-test](https://github.com/mojio/mojio-android-sdk/tree/develop/mojio-sdk-test) ###
  A module purely for common functionality used for each module's tests. You probably only care
  about this module if you are forking or building from scratch.
  
As much as possible we have tried to reduce the number of dependencies our SDK brings into your app.
Some models, unfortunately, require distinct serialization behaviour (in particular enums) and we
have opted to annotate these classes using GSON's @SerializedName annotation.

## License ##
    Copyright 2016 Mojio, Inc
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.