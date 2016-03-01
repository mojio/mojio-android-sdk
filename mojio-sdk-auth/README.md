# Mojio Android Auth SDK #

Implementation of OAuthActivity and OAuthFragment for retrieving an access token using our 
web-based login flow.

## Disclaimer ##
**UNDER ACTIVE DEVELOPMENT**

* Methods may change without notice
* Currently provided "AS IS"

## Download ##
```gradle
compile 'io.moj.mobile.android:mojio-sdk-auth:0.0.8'
```

## Instructions ##
The following steps are taken in the sample to perform basic OAuth2; if you wish to add authentication into your personal Mojio applications please follow the following steps:

1. Give your application Internet access in the AndroidManifest.xml file; if you do not do this, then the web view will fail to load with a rather undesciptive "Webpage not available" message
  
  ```xml
  <uses-permission android:name="android.permission.INTERNET" />
  ````

2. Your application can implement the OAuth2 WebView in one of two ways:

#### OAuthActivity ####
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
    ...
    <FrameLayout
        android:id="@+id/container_oauth"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    ...
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
  If you used android:launchmode="singleTask" as above, this will call onNewIntent() in your current activity:
  
  ```java
  @Override
  protected void onNewIntent(Intent intent) {
      super.onNewIntent(intent);

      String accessToken = intent.getStringExtra(OAuthActivity.EXTRA_ACCESS_TOKEN);
      long expiresIn = intent.getLongExtra(OAuthActivity.EXTRA_EXPIRES_IN, 0);
      
      // TODO store the access token securely and use for your Mojio API requests
  }
  ```
  
  Otherwise the activity you set the IntentFilter on can get the data via getIntent().
  
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