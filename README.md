#Mojio Android SDK#

#UNDER ACTIVE DEVELOPMENT
* Methods may change without notice.
* Currently provided "AS IS"

These instructions assume the usage of Android Studio / gradle.

##Examples##
If you are new to the Mojio platform, it may be benificial to fork the example projects which will provide some basic structure out of the box:
* https://github.com/mojio/mojio-android-example-oAuth2
* https://github.com/mojio/mojio-android-myFirstMojioApp

##Adding the SDK to your own project##
To add the SDK to your project you may either (1) use the prebuilt AAR included in this repo, or (2) import the entire SDK project as a module into your application.

###Using the prebuilt AAR project library###
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

6. Build your project to make sure the dependancies were applied correctly.

7. Test to make sure the SDK has been including by attempting to add the MojioClient class to any of your Activities.

###Import the SDK source directly###
Instructions coming soon.

##Using the MojioClient Class##
To use the Mojio SDK you must have setup a Mojio App in the developer console. The Mojio app id, secret key and redirect url, are required for making requests for data from the Mojio platform. Please see the Mojio Developer console for creating a new Mojio application.

####Create MojioClient instance####

```
MojioClient mojioClient = new MojioClient(mContext, MOJIO_APP_ID, MOJIO_APP_SECRET_KEY, REDIRECT_URL);
```

####API Login####
This must be done before attempting to use CRUD API calls. 
The SDK supports both OAuth and user/password login.
If you wish to do OAuth login, please see the sample applications listed above.

```
privateUser mCurrentUser;
mojioClient.login([name], [password], new MojioClient.ResponseListener<User>() {
    @Override
    public void onSuccess(User result) { 
        // Store user for later requests
        mCurrentUser = result;
    }
    @Override
    public void onFailure(String error) { 
        // Boo 
    }
});
```

####CRUD API Usage####
All CRUD operations are generic; they require the developer to indicate the class that they are expecting from the resulting request:

```
public <T> void get(final Class<T> modelClass, String entityPath, Map<String, String> queryOptions, final ResponseListener<T> listener) 
```

**Example: Requesting all vehicles for a user (Get / Read)**

```
// We expect to get an array of Vehicles from this request.
String entityPath = String.format("Users/%s/Vehicles", mCurrentUser._id);
HashMap<String, String> queryParams = new HashMap<>();
queryParams.put("sortBy", "Name");
queryParams.put("desc", "true");

mMojioClient.get(Vehicle[].class, entityPath, queryParams, new MojioClient.ResponseListener<Vehicle[]>() {
    @Override
    public void onSuccess(Vehicle[] result) {
        // Use vehicle data as desired
    }
    @Override
    public void onFailure(String error) {
        // Respond to error  
    }
});
```