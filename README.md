# Mojio Android SDK #

Android SDK for integrating with the Mojio platform.

For more information please see the [developer website](http://developer.moj.io/)

### UNDER ACTIVE DEVELOPMENT ###
* Methods may change without notice
* Current provided "AS IS"

### Examples ###
If you are new to the Mojio platform, it may be beneficial to fork the example projects which will provide some basic structure out of the box:
* https://github.com/mojio/mojio-android-example-oAuth2
* https://github.com/mojio/mojio-android-myFirstMojioApp

## Download ##

### Using a dependency manager ###
_Maven and Gradle instructions coming soon_

### Using the prebuilt AAR project library ###

1. Clone this repo

2. Create a new project in Android Studio

3. Copy the mojiosrc-release.aar file to your application's libs folder; this is found under the releases/aar folder in this repo. Note, you may have to switch to the *Project* view instead of the *Android* view to see the libs folder)

4. Copy the signalr libs to your app's libs folder; these are found under the releases/libs folder in this repo.

5. Add the following to your app's build.gradle file:

```
repositories{
    flatDir{
        dirs 'libs'
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:21.0.3'
   
    // Mojio SDK dependencies
    compile 'com.google.code.gson:gson:2.2.4'
    compile 'com.mcxiaoke.volley:library:1.0.+'
    compile 'joda-time:joda-time:2.5'
    compile files('libs/signalr-client-sdk.jar')
    compile files('libs/signalr-client-sdk-android.jar')
    compile 'io.moj.mobile.android.sdk:mojiosdksrc-release:1.0.1@aar'
}
```

6. Build your project to make sure the dependencies were applied correctly.

## Structure ##
The SDK is broken into three modules - you may choose to use some or all of them for your application:

### 1. [mojio-sdk-auth](https://github.com/mojio/mojio-android-sdk/tree/develop/MojioSDK/mojio-sdk-auth) ###
  Implementation of OAuthActivity and OAuthFragment for retrieving an access token 

### 2. [mojio-sdk-model](https://github.com/mojio/mojio-android-sdk/tree/develop/MojioSDK/mojio-sdk-model) ###
  Model objects entities retrieved from the REST API. These objects are annotated for use with GSON.

### 3. [mojio-sdk-rest](https://github.com/mojio/mojio-android-sdk/tree/develop/MojioSDK/mojio-sdk-rest) ###
  A fluent API for making structured REST calls to the Mojio API. Uses Volley and OKHttp.
