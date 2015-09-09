package io.moj.mobile.android.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import io.moj.mobile.android.sdk.models.Observers.Observer;
import io.moj.mobile.android.sdk.models.User;
import io.moj.mobile.android.sdk.models.UserToken;
import io.moj.mobile.android.sdk.networking.MojioImageRequest;
import io.moj.mobile.android.sdk.networking.MojioRequest;
import io.moj.mobile.android.sdk.networking.OAuthLoginActivity;
import io.moj.mobile.android.sdk.networking.VolleyHelper;
import microsoft.aspnet.signalr.client.NullLogger;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;
import microsoft.aspnet.signalr.client.transport.LongPollingTransport;

public class MojioClient {

    //========================================================================
    // Server Config - DO NOT CHANGE
    //========================================================================

    private static final String REQUEST_TAG = "MojioRequest";
    private static final String URL_AUTH_PATH = "https://%s/OAuth2/authorize?response_type=token&client_id=%%s";
    private static final String URL_BASE_PATH = "https://%s/v1/";
    private static final String URL_SIGNAL_R_HOST = "https://%s/v1/signalr";
    private static final String API_URL = "api.moj.io";
    private static final String STAGING_API_URL = "staging-api.moj.io";


    //========================================================================
    // MojioClient private properties
    //========================================================================
    private String _mojioAppID;             // Mojio app id
    private String _mojioAppSecretKey;      // Mojio app secret key
    private String _redirectUrl;            // Mojio redirect url
    private Context _ctx;                   // Client context
    private DataStorageHelper _oauthHelper; // OAuth cache / helper
    private VolleyHelper _requestHelper;    // Network requests
    private String _authPath;               // Authentication path
    private String _apiBaseUrl;             // Base API URL
    private String _signalRHost;            // SignalR Host
    private boolean _didSwitchEndpoints;    // Defines whether the new endpoint differs from the cached data

    //========================================================================
    // Generic response listener interface
    //========================================================================
    public static final int RESPONSE_ERR_UNKNOWN = 0;
    public static final int RESPONSE_ERR_NOT_LOGGED_IN = 1;
    public static final int RESPONSE_ERR_SIGNALR_ERROR = 2;
    public static final int RESPONSE_ERR_VOLLEY_ERROR = 3;
    public static final int RESPONSE_ERR_SERVER_TIMEOUT = 4;

    public static class ResponseError {
        public String message;
        public int type;

        public ResponseError(String m, int t) {
            message = m;
            type = t;
        }

        public ResponseError() {
           this(null, RESPONSE_ERR_UNKNOWN);
        }
    }

    public interface ResponseListener<T> {
        public void onSuccess(T result);
        public void onFailure(ResponseError error);
    }

    //========================================================================
    // Observer subscription data
    //========================================================================
    private HubConnection connection;
    private HubProxy hub;
    private SignalRFuture<Void> awaitConnection;
    private boolean connectionEstablished = false;
    private Gson subscriberGson;

    //========================================================================
    // EU Locales
    //========================================================================
    private static final String[] EU_LOCALES = { "BE", "BG", "CZ", "DK", "DE", "EE", "IE", "EL",
            "ES", "FR", "HR", "IT", "CY", "LV", "LT", "LU", "HU", "MT", "NL", "AT", "PL", "PT",
            "RO", "SI", "SK", "FI", "SE", "UK", "GB" };

    //========================================================================
    // Constructors
    //========================================================================
    public MojioClient(Context ctx, String mojioAppID, String mojioSecretkey, String redirectUrl) {
        _ctx = ctx;
        _oauthHelper = new DataStorageHelper(_ctx);
        _requestHelper = new VolleyHelper(_ctx);
        _mojioAppID = mojioAppID;
        _mojioAppSecretKey = mojioSecretkey;
        _redirectUrl = redirectUrl;
        _didSwitchEndpoints = false;

        this.updateUrl(API_URL);
    }

    public MojioClient(Context ctx, String mojioAppID, String mojioSecretkey, String redirectUrl, Locale clientLocale) {
        _ctx = ctx;
        _oauthHelper = new DataStorageHelper(_ctx);
        _requestHelper = new VolleyHelper(_ctx);
        _mojioAppID = mojioAppID;
        _mojioAppSecretKey = mojioSecretkey;
        _redirectUrl = redirectUrl;
        _didSwitchEndpoints = false;

        this.updateLocale(clientLocale);
    }

    private void updateUrl(String apiUrl) {
        _authPath = String.format(URL_AUTH_PATH, apiUrl);
        _apiBaseUrl = String.format(URL_BASE_PATH, apiUrl);
        _signalRHost = String.format(URL_SIGNAL_R_HOST, apiUrl);

        if (!_authPath.equals(_oauthHelper.getEndpoint())) {
            Log.i("MOJIO", "Endpoints have changed from " + _oauthHelper.getEndpoint() + " to " + _authPath);
            clearEndPointSpecificCache();
            _didSwitchEndpoints = true;
        }
    }

    //========================================================================
    // Endpoint setup
    //========================================================================
    /**
     * Sets the endpoint depending on the specific locale. European regions will
     * map to the staging API.
     *
     * @param clientLocale      The specified locale.
     */
    public void updateLocale(Locale clientLocale) {
        String apiUrl = this.isInEu(clientLocale) ? STAGING_API_URL : API_URL;
        this.updateUrl(apiUrl);
    }

    /**
     * Determines whether this instance of the MojioClient is connecting to the same
     * endpoint as the endpoint that has been cached. This call allows the application to make
     * configuration changes.
     *
     * @return          Whether the end point did change.
     */
    public boolean endPointDidChange() {
        return _didSwitchEndpoints;
    }

    /**
     * Removes all cache specific to the cached endpoint. This is called when the endpoint changes.
     */
    private void clearEndPointSpecificCache() {
        String api = _oauthHelper.getEndpoint();
        if (api == null) {
            _oauthHelper.setEndpoint(_authPath);
        } else if (!api.equals(_authPath)) {
            _oauthHelper.clearAppToken();
            _oauthHelper.removeAllUserStoredValues();
            _oauthHelper.setEndpoint(_authPath);
        }
    }

    //========================================================================
    // Login / Logout methods
    //========================================================================
    /**
     * Returns true if a user is considered to be logged in (ie. the proper auth token is stored
     * and still valid).
     * @return
     */
    public boolean isUserLoggedIn() {
        // Currently we determine if a user is logged in by checking the remaining time on
        // an access token (ie. if it has to be refreshed or not).
        return !_oauthHelper.ShouldRefreshAccessToken();
    }

    /**
     * Launches oauth web view to login / get user auth key.
     * Calling Activity must override onActivityResult with the given requestCode
     * @param activity
     * @param requestCode The requestCode used to startActivityForResult
     */
    public void launchLoginActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, OAuthLoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("URL_AUTH_PATH", String.format(_authPath, _mojioAppID));
        bundle.putString("USER_AUTH_TOKEN", _oauthHelper.GetAccessToken());
        bundle.putString("REDIRECT_URL",  _redirectUrl);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Get app authentication
     * NOTE THIS IS THE APP AUTH TOKEN
     * We only want to use the app auth token when we have no stored USER auth token
     * This may happen when we are creating a new user
     * @param responseListener
     */
    public void authenticateApp(final ResponseListener<UserToken> responseListener) {
        String entityPath = String.format("Login/%s?secretKey=%s&minutes=%s",
                _mojioAppID, _mojioAppSecretKey, "43829");

        this.create(UserToken.class, entityPath, new MojioClient.ResponseListener<UserToken>() {
            @Override
            public void onSuccess(UserToken result) {
                // Save auth tokens
                // NOTE THIS IS THE APP AUTH TOKEN
                // We only want to use the app auth token when we have no stored USER auth token
                // This may happen when we are creating a new user
                _oauthHelper.SetAppToken(result._id);
                _oauthHelper.SetAppExpireTime(result.ValidUntil);
                responseListener.onSuccess(result);
            }

            @Override
            public void onFailure(ResponseError error) {
                responseListener.onFailure(error);
                // TODO Need a way to pass back failures better (reasons)
            }
        });
    }

    /**
     * @param userNameOrEmail
     * @param password
     * @param responseListener
     */
    public void login(String userNameOrEmail, String password, final ResponseListener<User> responseListener) {
        //String entityPath = String.format("Login/User?userOrEmail=%s&password=%s&minutes=%s",
        //        userNameOrPassword, password, "43829");
        String urlEncodedUsername = userNameOrEmail;
        String urlEncodedPassword = password;
        try {
            urlEncodedUsername = URLEncoder.encode(userNameOrEmail, "UTF-8");
            urlEncodedPassword = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String entityPath = String.format("Login/%s?secretKey=%s&userOrEmail=%s&password=%s&minutes=%s",
                _mojioAppID, _mojioAppSecretKey, urlEncodedUsername, urlEncodedPassword, "43829");

        this.create(UserToken.class, entityPath, new MojioClient.ResponseListener<UserToken>() {
            @Override
            public void onSuccess(UserToken result) {
                // Save auth tokens
                _oauthHelper.SetAccessToken(result._id);
                _oauthHelper.SetAccessExpireTime(result.ValidUntil);

                // Now we need to get the USER
                String userID = result.UserId;
                getUser(userID, responseListener); // Pass along response listener
            }

            @Override
            public void onFailure(ResponseError error) {
                responseListener.onFailure(error);
                // TODO Need a way to pass back failures better (reasons)
            }
        });
    }

    /**
     * Must already be logged in and have an access token
     */
    public void setSandboxedAccess(boolean sandboxed, final ResponseListener<Boolean> responseListener) {
        String currentAccessToken = _oauthHelper.GetAccessToken();

        if (currentAccessToken == null) {
            ResponseError error = new ResponseError("Not logged in", RESPONSE_ERR_NOT_LOGGED_IN);
            responseListener.onFailure(error);
            return;
        }

        String entityPath = String.format("Login/%s/Sandboxed", currentAccessToken);
        entityPath += "?sandboxed=" + String.valueOf(sandboxed);

        this.update(UserToken.class, entityPath, null, new MojioClient.ResponseListener<UserToken>() {
            @Override
            public void onSuccess(UserToken result) {
                Log.e("testing", "success.  sandboxmode is now: " + result.Sandboxed);
                // Update access tokens
                _oauthHelper.SetAccessToken(result._id);
                _oauthHelper.SetAccessExpireTime(result.ValidUntil);
                responseListener.onSuccess(true);
            }

            @Override
            public void onFailure(ResponseError error) {
                responseListener.onFailure(error);
            }
        });
    }

    /**
     * @param fbAccesstoken
     * @param responseListener
     */
    public void loginFacebook(String fbAccesstoken, final MojioClient.ResponseListener<User> responseListener){

        String entityPath = String.format("Login/ExternalUser?accessToken=%s", fbAccesstoken);

        this.create(UserToken.class, entityPath, new MojioClient.ResponseListener<UserToken>() {
            @Override
            public void onSuccess(UserToken result) {
                // Save user auth token
                _oauthHelper.SetAccessToken(result._id);
                _oauthHelper.SetAccessExpireTime(result.ValidUntil);
                String userID = result.UserId;
                getUser(userID, responseListener); // Pass along response listener
            }

            @Override
            public void onFailure(ResponseError error) {
                responseListener.onFailure(error);
                // TODO Need a way to pass back failures better (reasons)
            }
        });
    }

    /**
     * Part of the "Login" flow.
     * @param userID
     * @param responseListener
     */
    private void getUser(String userID, final MojioClient.ResponseListener<User> responseListener) {
        // TODO Check cache?
        String entityPath = "Users/" + userID;
        HashMap<String, String> queryParams = new HashMap<>();
        this.get(User.class, entityPath, queryParams, responseListener);
    }

    public void logout() {
        _oauthHelper.removeAllUserStoredValues();
    }

    //========================================================================
    // Generic CRUD methods
    //========================================================================
    //

    /**
     * GET (GET)
     * @param modelClass
     * @param entityPath
     * @param queryOptions
     * @param listener
     * @param <T>
     */
    public <T> void get(final Class<T> modelClass, String entityPath, Map<String, String> queryOptions, final ResponseListener<T> listener) {
        // Add query options to get url
        String getParams = "";
        if (queryOptions != null) {
            for (String key : queryOptions.keySet()) {
                getParams += String.format("&%s=%s", key, queryOptions.get(key));
            }
            entityPath += getParams.replaceFirst("&", "?");
        }

        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.GET, _apiBaseUrl + entityPath, modelClass, queryOptions,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    /**
     * UPDATE (PUT) with string content body
     * @param modelClass
     * @param entityPath
     * @param contentBody
     * @param listener
     * @param <T>
     */
    public <T> void update(final Class<T> modelClass, String entityPath, String contentBody, final ResponseListener<T> listener) {
        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.PUT, _apiBaseUrl + entityPath, modelClass, contentBody,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    /**
     * DELETE (DELETE)
     * @param modelClass
     * @param entityPath
     * @param listener
     * @param <T>
     */
    public <T> void delete(final Class<T> modelClass, String entityPath, final ResponseListener<T> listener) {
        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.DELETE, _apiBaseUrl + entityPath, modelClass,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    /**
     * CREATE (POST) with no content body
     * @param modelClass
     * @param entityPath
     * @param listener
     * @param <T>
     */
    public <T> void create(final Class<T> modelClass, String entityPath, final ResponseListener<T> listener) {

        String contentBody = null;

        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.POST, _apiBaseUrl + entityPath, modelClass, contentBody,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    /**
     * CREATE (POST) with a string content body
     * @param modelClass
     * @param entityPath
     * @param contentBody
     * @param listener
     * @param <T>
     */
    public <T> void create(final Class<T> modelClass, String entityPath, String contentBody, final ResponseListener<T> listener) {
        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.POST, _apiBaseUrl + entityPath, modelClass, contentBody,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    /**
     * CREATE (POST) using a Map of key / value pairs
     * @param modelClass
     * @param entityPath
     * @param params
     * @param listener
     * @param <T>
     */
    public <T> void create(final Class<T> modelClass, String entityPath, Map<String, String> params, final ResponseListener<T> listener) {
        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.POST, _apiBaseUrl + entityPath, modelClass, params,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    //========================================================================
    // Observer CRUD methods
    //========================================================================

    /**
     * GET (GET) Observer
     * @param modelClass
     * @param entityPath
     * @param queryOptions
     * @param listener
     * @param <T>
     */
    public <T> void getObservers(final Class<T> modelClass, String entityPath, Map<String, String> queryOptions, final ResponseListener<T> listener){
        String getParams = "";
        if (queryOptions != null) {
            for (String key : queryOptions.keySet()) {
                getParams += String.format("&%s=%s", key, queryOptions.get(key));
            }
            entityPath += getParams.replaceFirst("&", "?");

            MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.GET, _apiBaseUrl + entityPath, modelClass, queryOptions,
                    new Response.Listener<T>() {
                        @Override
                        public void onResponse(T response) {
                            listener.onSuccess(response);
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            reportVolleyError(error, listener);
                        }
                    });

            // Run request
            _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
        }
    }

    /**
     * CREATE (POST) Observer
     * @param modelClass
     * @param contentBody
     * @param listener
     * @param <T>
     */
    public <T> void createObserver(final Class<T> modelClass, String contentBody, final ResponseListener<T> listener) {
        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.POST, _apiBaseUrl + "Observers", modelClass, contentBody,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    /**
     * DELETE (DELETE) Observer
     * @param modelClass
     * @param observerId
     * @param listener
     * @param <T>
     */
    public <T> void deleteObserver(final Class<T> modelClass, String observerId, final ResponseListener<T> listener) {
        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.DELETE, _apiBaseUrl + "Observers/" + observerId, modelClass,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        Log.e("testing", "successfully deleted observer");
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    /**
     * CREATE (POST) Conditional Observer
     * Must provide Name, ObserverType, Timing, Subject, SubjectId, Transport and Condition values
     * @param modelClass
     * @param conditionalObserverObject
     * @param listener
     * @param <T>
     */
    public <T> void createConditionalObserver(final Class<T> modelClass, Object conditionalObserverObject, final ResponseListener<T> listener) {

        String obj = new Gson().toJson(conditionalObserverObject);
        Log.e("testing", "the object looks like: " + obj);

        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.POST, _apiBaseUrl + "Observers", modelClass, obj,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    /**
     * Currently returns on a background thread as the UpdateEntity and Error messages are handled in
     * their own SubscriptionHandler1(s). Response listener needs to update to UI thread if
     * desired. May want to try and solve this by returning on main thread here instead.
     *
     * @param modelClass
     * @param observer
     * @param listener
     * @param <T>
     */
    public <T> void subscribeToObserver(final Class<T> modelClass, final Observer observer, final ResponseListener<T> listener){

        subscriberGson = new Gson();
        Platform.loadPlatformComponent(new AndroidPlatformComponent());


        connection = new HubConnection( _signalRHost );
        hub = connection.createHubProxy("ObserverHub");
        awaitConnection = connection.start();

        //create a handler that waits to check if we can subscribe using web sockets.  if we can't
        //switch to long polling to subscribe
        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!connectionEstablished) {
                    Log.e("SignalR", "Websockets failed. switching to long polling");
                    LongPollingTransport transport = new LongPollingTransport(new NullLogger());
                    connection.disconnect();
                    connection = new HubConnection(_signalRHost);
                    hub = connection.createHubProxy("ObserverHub");
                    awaitConnection = connection.start(transport);
                    connectToObserver(awaitConnection, hub, observer);
                    subscribe(modelClass, listener);
                }
            }
        }, 2000);

        //connect on seperate thread as to not hang the UI thread
        new Thread(new Runnable() {
            public void run() {
                connectToObserver(awaitConnection, hub, observer);
                subscribe(modelClass, listener);
            }
        }).start();




    }

    private void connectToObserver(SignalRFuture<Void> awaitConnection, HubProxy hub, Observer observer){
        try {
            awaitConnection.get();
            connectionEstablished = true;
            hub.invoke( "Subscribe",observer._id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private <T> void subscribe(final Class<T> modelClass, final ResponseListener<T> listener){
        hub.subscribe(this );
        hub.on("UpdateEntity", new SubscriptionHandler1<JsonObject>() {
            @Override
            public void run(JsonObject o) {
                T result = subscriberGson.fromJson(o.toString(), modelClass);
                listener.onSuccess(result); // NOTE still on handler thread.
            }
        }, JsonObject.class);

        hub.on("Error", new SubscriptionHandler1<Object>() {
            @Override
            public void run(Object o) {
                ResponseError error = new ResponseError(
                        "Failed to subscribe to signalr hub",
                        RESPONSE_ERR_SIGNALR_ERROR);
                listener.onFailure(error); // NOTE still on handler thread.
            }
        }, Object.class);

    }

    //========================================================================
    // Image methods
    //========================================================================
    // Get image
    public void getImage(String entityPath, Map<String, String> queryOptions,  final ResponseListener<Bitmap> listener){

        String getParams = "";
        if (queryOptions != null) {
            for (String key : queryOptions.keySet()) {
                getParams += String.format("&%s=%s", key, queryOptions.get(key));
            }
            entityPath += getParams.replaceFirst("&", "?");
        }

        MojioImageRequest imageRequest = new MojioImageRequest(_ctx, _apiBaseUrl + entityPath, 0,0,null,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        listener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);

                    }
                });

        addRequestToQueue(imageRequest);
    }

    public <T> void updateImage(final Class<T> modelClass, String entityPath, Bitmap data, final ResponseListener<T> listener) {
        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.PUT, _apiBaseUrl + entityPath, modelClass, data,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reportVolleyError(error, listener);
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    //========================================================================
    // Manual Volley requests
    //========================================================================
    public void addRequestToQueue(Request request) {
        Log.i("MOJIO", "Adding to request queue: " + request.getUrl());
        _requestHelper.addToRequestQueue(request, REQUEST_TAG);
    }

    public void cancelAllRequests() {
        _requestHelper.cancelPendingRequests(REQUEST_TAG);
    }

    //========================================================================
    // Error reporting
    //========================================================================
    // Helpers
    private void reportVolleyError(final VolleyError error, ResponseListener listener) {
        // Attempt to parse response errors
        try {
            ResponseError respErr = new ResponseError();
            respErr.message = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
            respErr.type = RESPONSE_ERR_VOLLEY_ERROR;
            Log.e("MOJIO", respErr.message);
            listener.onFailure(respErr);
        }
        catch (Exception e) {
            ResponseError respErr = new ResponseError();

            if (error instanceof TimeoutError) {
                respErr.type = RESPONSE_ERR_SERVER_TIMEOUT;
                respErr.message = "Server timeout";
            }
            else {
                try {
                    // Report raw volley error
                    respErr.type = RESPONSE_ERR_VOLLEY_ERROR;
                    respErr.message = error.getMessage();
                }
                catch (Exception e2) {
                    // Return unknown
                    respErr.type = RESPONSE_ERR_UNKNOWN;
                    respErr.message = "Unknown error";
                }
            }

            // Last ditch chance to set error; cannot be null
            if (respErr.message == null) {
                respErr.type = RESPONSE_ERR_UNKNOWN;
                respErr.message = "Unknown error";
            }

            e.printStackTrace();

            Log.e("MOJIO", respErr.message);
            listener.onFailure(respErr);
        }
    }

    //========================================================================
    // Locale-Specific Endpoints
    //========================================================================
    private boolean isInEu(Locale locale) {
        Set<String> euLocales = new HashSet<>(Arrays.asList(EU_LOCALES));
        return euLocales.contains(locale.getCountry());
    }
}
