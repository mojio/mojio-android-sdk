# Mojio Android Common SDK #

Common functionality used by other modules such as environment info and a lightweight, 
configurable, logger.

## Disclaimer ##
**UNDER ACTIVE DEVELOPMENT**

* Methods may change without notice
* Currently provided "AS IS"

## Download ##
```gradle
compile 'io.moj.mobile.android:mojio-sdk-common:0.0.13'
```

## Instructions ##
The Mojio SDK uses a simple [Logger](https://github.com/mojio/mojio-android-sdk/blob/develop/MojioSDK/mojio-sdk-common/src/main/java/io/moj/mobile/android/sdk/Log.java)
interface. This allows your app to change how and what the SDK logs at runtime without bringing in
new dependencies (such as [Timber](https://github.com/JakeWharton/timber)).

The default logger prints to Logcat, below is some sample code to disable this logging in release
builds (do this in your Application's onCreate() method):

```java
if (!BuildConfig.DEBUG) {
    io.moj.mobile.android.sdk.Log.clearLoggers();
}
```

You can also change the logging behaviour by adding your own logger. The sample code below removes
the default logcat logger but logs to Crashlytics in your release build:

```java
if (!BuildConfig.DEBUG) {
    // Log to Crashlytics instead of Logcat in release builds
    Fabric.with(this, new Crashlytics());
    Log.clearLoggers();
    Log.append(new Log.Logger() {
        @Override
        public void log(int level, String tag, String msg, Throwable tr) {
            Crashlytics.log(tag + ": " + msg);
            if (tr != null)
                Crashlytics.logException(tr);
        }
    });
}
```
  
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