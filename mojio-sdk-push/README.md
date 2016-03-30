# Mojio Android Push SDK #

Model classes for use with the Mojio PUSH API. These objects are annotated for serialization with
GSON.

## Disclaimer ##
**UNDER ACTIVE DEVELOPMENT**

* Methods may change without notice
* Currently provided "AS IS"

## Download ##
```gradle
compile 'io.moj.mobile.android:mojio-sdk-push:0.0.14'
```

## Instructions ##

The Mojio Push SDK is most easily implemented using [Retrofit](http://square.github.io/retrofit/).
Some examples for setting up Retrofit can be found in the [mojio-sdk-model](../mojio-sdk-model)
documentation. n simple example of a Retrofit interface for the Mojio Push API would look as follows:

```java
package io.moj.mobile.android.motion.service;

import io.moj.mobile.android.sdk.push.Observer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Retrofit interface for the Mojio PUSH API.
 */
public interface MojioPushApi {

    @GET("vehicles")
    Call<Observer> getVehicleObservers();

    @GET("vehicles/{id}")
    Call<Observer> getVehicleObservers(@Path("id") String id);

    @POST("vehicles")
    Call<Observer> observeVehicles(@Body ObserverCreationRequest request);

    @POST("vehicles/{id}")
    Call<Observer> observeVehicle(@Path("id") String id, @Body ObserverCreationRequest request);

    @POST("mojios")
    Call<Observer> observeMojios(@Body ObserverCreationRequest request);

    @POST("mojios/{id}")
    Call<Observer> observeMojio(@Path("id") String id, @Body ObserverCreationRequest request);

    @POST("users/{id}")
    Call<Observer> observeUser(@Path("id") String id, @Body ObserverCreationRequest request);

}
```

...and setting up the Retrofit client...

```java
mojioPushApi = new Retrofit.Builder()
        .baseUrl("https://push-api.moj.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClientBuilder.build())
        .build().create(MojioPushApi.class);
```

Even if you're not using Retrofit, the mojio-sdk-push module can be plugged into your existing
architecture. Currently, however, we have a dependency on [Gson](https://github.com/google/gson).

### Creating observers ###
The mojio-sdk-push module contains some fluent constructors to make creating observers easy. Some
examples can be found below.

#### Notify when any vehicle location changes ####
This will send the JSON body of the vehicle whose location changes to your specified _transport_.

```java
ObserverCreationRequest o = new ObserverCreationRequest.Builder(key)
        .subject("vehicleId")
        .type(Observer.Type.VEHICLE)
        .transport(transport)
        .condition(Condition.onPropertyChanged("Location"))
        .build();
```

#### Notify when the vehicle's speed goes above 100 km/h ####
```java
ObserverCreationRequest o = new ObserverCreationRequest.Builder(key)
        .subject("vehicleId")
        .type(Observer.Type.VEHICLE)
        .transport(transport)
        .condition(Condition.onThreshold("Speed.Value", Condition.Position.ABOVE, 0.0, 100.0))
        .build();
```

#### Notify when a Mojio is plugged into a different vehicle ####
```java
ObserverCreationRequest o = new ObserverCreationRequest.Builder(key)
        .subject("mojioId")
        .type(Observer.Type.MOJIO)
        .transport(transport)
        .condition(Condition.onPropertyChanged("VehicleId"))
        .build();
```

### Creating conditions ###
Conditions can also be built using static constructors to keep your code readable:

#### Restrict an observer to only fire when a property is inside a range of values ####
```java
double min = 0.0;
double max = 50.0;
Condition c = Condition.onThreshold("Property", Position.IN_BETWEEN, min, max);
```

#### Restrict an observer to only fire when all other conditions maintained for 3 events ####
```java
Condition c = Condition.minDataPoints(3);
```

#### Restrict an observer to only fire when all other conditions maintained for an hour ####
```java
int days = 0;
int hours = 1;
int minutes = 0;
int seconds = 0;
Condition c = Condition.delay(days, hours, minutes, seconds);
```

_OR_

```java
Condition c = Condition.delay("0.01:00:00.0000");
```

#### Restrict an observer to only fire once a day ####
```java
int days = 1;
int hours = 0;
int minutes = 0;
int seconds = 0;
Condition c = Condition.throttle(days, hours, minutes, seconds);
```

_OR_

```java
Condition c = Condition.delay("1.00:00:00.0000");
```

### Creating transports ###
The Mojio Push API supports a large number of transports - for the Android SDK, we've provided
static constructors for easily constructing GCM or SignalR transports:

#### Notify via Google Cloud Messaging ####
```java
Transport t = Transport.forAndroid("deviceRegistrationId");
```

#### Notify via SignalR ####
[SignalR](http://www.asp.net/signalr) is Microsoft's WebSockets library. Microsoft has an open
source Java SDK available [here](https://github.com/SignalR/java-client). We hope to simplify 
Android integration with SignalR soon, stay tuned!

```java
// get this from your SignalR connection
String clientId = connection.getConnectionId();
String hubName = "VehicleHub"; // or "MojioHub"
Transport t = Transport.forSignalR(clientId, hubName);
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