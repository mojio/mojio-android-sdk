#UNDER ACTIVE DEVELOPMENT
* Methods may change without notice.
* Currently provided "AS IS"

### SDK Initialization
Note: Requires app id, secret key and redirect url.
Please see the Mojio Developer console for creating a new Mojio application.

####Create MojioClient instance

```
MojioClient mojioClient = new MojioClient(mContext, MOJIO_APP_ID, MOJIO_APP_SECRET_KEY, REDIRECT_URL);
```

####API Login
This must be done before attempting to use CRUD API calls. 

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

#### CRUD API Usage
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