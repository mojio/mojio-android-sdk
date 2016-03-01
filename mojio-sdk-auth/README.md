# Mojio Android Auth SDK #

Implementation of OAuthActivity and OAuthFragment for retrieving an access token using our 
web-based login flow.

## Disclaimer ##
**UNDER ACTIVE DEVELOPMENT**

* Methods may change without notice
* Currently provided "AS IS"

## Download ##
```gradle
compile 'io.moj.mobile.android:mojio-sdk-auth:0.0.9'
```

## Instructions ##
All approaches to implementing authentication require the INTERNET permission. If you are seeing a
"Webpage not available" message, missing this is usually the cause:
  
  ```xml
  <uses-permission android:name="android.permission.INTERNET" />
  ````

Your application can implement the OAuth2 WebView in one of two ways:

#### OAuthActivity ####
  This approach involves calling our OAuthActivity via startActivityForResult() and then pulling
  the access token out of the resulting Intent.

  1. Add **io.moj.mobile.android.sdk.oauth.OAuthActivity** to your AndroidManifest.xml
  
  ```xml
  <activity android:name="io.moj.mobile.android.sdk.oauth.OAuthActivity" />
  ```

  2. Create an Intent for OAuthActivity using the newIntent() method. Note your clientId and redirectUri should be the values
  you configured when setting up your app with the Mojio API.
  
  ```java
  Intent oauthIntent = OAuthActivity.newIntent(getActivity(), "your app id", "full", "oauth://com.example.auth");
  ```
  
  3. Start OAuthActivity for a result
  
  ```java
  startActivityForResult(oauthIntent, REQUEST_CODE_OAUTH, null);
  ```
  
  4. Implement **onActivityResult()**
  
  ```java
  protected void onActivityResult (int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE_OAUTH) {
      if (resultCode == RESULT_OK) {
        String accessToken = data.getStringExtra(OAuthActivity.EXTRA_ACCESS_TOKEN);
        long expiresIn = data.getLongExtra(OAuthActivity.EXTRA_EXPIRES_IN, 0);
        // TODO store the access token securely and use for your Mojio API requests
      }
    }
  }
  ```

#### OAuthFragment ####
This approach is useful if you want more flexibility around the UI for the login page (e.g. show content above or below the webview).
  1. Create a layout with a container the OAuthFragment will be added to
  
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <FrameLayout
        android:id="@+id/container_oauth"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    ```
    
  2. Create and add an instance of OAuthFragment in onCreate()
  
  ```java
  OAuthFragment f = OAuthFragment.newInstance(clientId, scope, redirectUri);
  getSupportFragmentManager().beginTransaction()
          .replace(R.id.container_oauth, f)
          .commit();
  ```
  
  3. Add an Intent Filter to the activity that should receive the access token result. Note: you
  will probably want this to be the same activity that is displaying the OAuthFragment by setting
  **android:launchMode="singleTask"**
  
  ```xml
  <activity
      android:name=".LoginActivity"
      android:launchMode="singleTask"
      android:label="@string/app_name"
      android:theme="@style/AppTheme.NoActionBar" >
      <intent-filter>
          <action android:name="android.intent.action.MAIN" />
          <category android:name="android.intent.category.LAUNCHER" />
      </intent-filter>

      <!-- This Intent Filter will intercept the OAuth result in onNewIntent() -->
      <intent-filter>
          <action android:name="android.intent.action.VIEW" />
          <category android:name="android.intent.category.DEFAULT" />
          <category android:name="android.intent.category.BROWSABLE" />
          <!-- This URI must match one of the RedirectUris your app has configured -->
          <data android:scheme="oauth" android:host="com.example.auth"/>
      </intent-filter>
  </activity>
  ```
  
  4. Override the method that will receive the result
  If you used android:launchmode="singleTask" as above, this will call onNewIntent() in your current
  activity:
  
  ```java
  @Override
  protected void onNewIntent(Intent intent) {
      super.onNewIntent(intent);

      String accessToken = intent.getStringExtra(OAuthActivity.EXTRA_ACCESS_TOKEN);
      long expiresIn = intent.getLongExtra(OAuthActivity.EXTRA_EXPIRES_IN, 0);
      
      // TODO store the access token securely and use for your Mojio API requests
  }
  ```
  
  Otherwise the activity you set the IntentFilter on can retrieve the data via getIntent() after it
  has been started.
  
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