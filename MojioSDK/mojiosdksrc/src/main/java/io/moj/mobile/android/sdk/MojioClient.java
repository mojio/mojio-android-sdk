package io.moj.mobile.android.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import io.moj.mobile.android.sdk.models.Observers.Observer;
import io.moj.mobile.android.sdk.models.User;
import io.moj.mobile.android.sdk.models.UserToken;
import io.moj.mobile.android.sdk.networking.MojioImageRequest;
import io.moj.mobile.android.sdk.networking.MojioRequest;
import io.moj.mobile.android.sdk.networking.OAuthLoginActivity;
import io.moj.mobile.android.sdk.networking.VolleyHelper;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;

public class MojioClient {

    //========================================================================
    // Server Config - DO NOT CHANGE
    //========================================================================
    private static String REQUEST_TAG = "MojioRequest";
    private static String URL_AUTH_PATH = "https://api.moj.io/OAuth2/authorize?response_type=token&client_id=%s";
    private String _apiBaseUrl = "https://api.moj.io/v1/";
    private String _signalRHost = "http://api.moj.io:80/v1/signalr";

    //========================================================================
    // MojioClient private properties
    //========================================================================
    private String _mojioAppID;             // Mojio app id
    private String _mojioAppSecretKey;      // Mojio app secret key
    private String _redirectUrl;            // Mojio redirect url
    private Context _ctx;                   // Client context
    private DataStorageHelper _oauthHelper; // OAuth cache / helper
    private VolleyHelper _requestHelper;    // Network requests

    //========================================================================
    // Generic response listener interface
    //========================================================================
    public static int RESPONSE_ERR_UNKNOWN = 0;
    public static int RESPONSE_ERR_NOT_LOGGED_IN = 1;
    public static int RESPONSE_ERR_SIGNALR_ERROR = 2;
    public static int RESPONSE_ERR_VOLLEY_ERROR = 3;
    public static int RESPONSE_ERR_SERVER_TIMEOUT = 4;

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
    // Constructors
    //========================================================================
    public MojioClient(Context ctx, String mojioAppID, String mojioSecretkey, String redirectUrl) {
        _ctx = ctx;
        _oauthHelper = new DataStorageHelper(_ctx);
        _requestHelper = new VolleyHelper(_ctx);
        _mojioAppID = mojioAppID;
        _mojioAppSecretKey = mojioSecretkey;
        _redirectUrl = redirectUrl;
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
        bundle.putString("URL_AUTH_PATH", String.format(URL_AUTH_PATH, _mojioAppID));
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
     * @param userNameOrPassword
     * @param password
     * @param responseListener
     */
    public void login(String userNameOrPassword, String password, final ResponseListener<User> responseListener) {
        //String entityPath = String.format("Login/User?userOrEmail=%s&password=%s&minutes=%s",
        //        userNameOrPassword, password, "43829");

        String entityPath = String.format("Login/%s?secretKey=%s&userOrEmail=%s&password=%s&minutes=%s",
                _mojioAppID, _mojioAppSecretKey, userNameOrPassword, password, "43829");

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
    public <T> void subscribeToObserver(final Class<T> modelClass, Observer observer, final ResponseListener<T> listener){

        final Gson mGson = new Gson();
        Platform.loadPlatformComponent(new AndroidPlatformComponent());

        HubConnection connection = new HubConnection( _signalRHost );
        HubProxy hub = connection.createHubProxy( "ObserverHub" );

        SignalRFuture<Void> awaitConnection = connection.start();
        try {
            awaitConnection.get();
            hub.invoke( "Subscribe",observer._id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        hub.subscribe( this );
        hub.on("UpdateEntity", new SubscriptionHandler1<JsonObject>() {
            @Override
            public void run(JsonObject o) {
                T result = mGson.fromJson(o.toString(), modelClass);
                listener.onSuccess(result); // NOTE still on handler thread.
            }
        },JsonObject.class);

        hub.on("Error", new SubscriptionHandler1<Object>() {
            @Override
            public void run(Object o) {
                ResponseError error = new ResponseError(
                        "Failed to subscribe to signalr hub",
                        RESPONSE_ERR_SIGNALR_ERROR);
                listener.onFailure(error); // NOTE still on handler thread.
            }
        },Object.class);

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
}
