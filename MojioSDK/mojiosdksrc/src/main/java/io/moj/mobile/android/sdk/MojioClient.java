package io.moj.mobile.android.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import io.moj.mobile.android.sdk.enums.Environment;
import io.moj.mobile.android.sdk.models.Token;
import io.moj.mobile.android.sdk.models.User;
import io.moj.mobile.android.sdk.models.observers.Observer;
import io.moj.mobile.android.sdk.networking.MojioImageRequest;
import io.moj.mobile.android.sdk.networking.MojioRequest;
import io.moj.mobile.android.sdk.networking.MojioResponse;
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

    private static final String TAG = MojioClient.class.getSimpleName();
    private static final String ENCODING = "UTF-8";
    private static final Gson GSON = new Gson();
    private static final int SESSION_LENGTH_MIN = 43829; // 1 month

    private final AtomicBoolean refreshTokenLock = new AtomicBoolean(false);

    private HubConnection connection;
    private HubProxy hub;
    private SignalRFuture<Void> awaitConnection;

    private String mojioAppID;
    private String mojioAppSecretKey;
    private Context context;
    private Environment environment;
    private OAuthHelper oauthHelper;
    private VolleyHelper requestHelper;

    private boolean connectionEstablished = false;

    public MojioClient(Context context) {
        this(context, null, null, null);
    }

    public MojioClient(Context context, Environment environment) {
        this(context, null, null, environment);
    }

    public MojioClient(Context context, String mojioAppID, String mojioSecretkey) {
        this(context, mojioAppID, mojioSecretkey, null);
    }

    public MojioClient(Context context, String mojioAppID, String mojioSecretkey, Environment environment) {
        this.context = context;
        this.oauthHelper = new OAuthHelper(context);
        this.requestHelper = new VolleyHelper(context);
        this.mojioAppID = mojioAppID;
        this.mojioAppSecretKey = mojioSecretkey;

        // if the user is logged in, default to their current environment
        if (environment == null)
            environment = oauthHelper.getEnvironment();
        if (environment == null)
            environment = Environment.getDefault(context);
        setEnvironment(environment);
    }

    //========================================================================
    // Environment setup
    //========================================================================

    /**
     * Changes the environment the SDK will use. Note: changing this will clear all current auth
     * tokens and state - this should only be done when the user has been logged out of your application.
     * @param environment the environment to switch to
     * @return true if the endpoint has been changed and all user data cleared, otherwise false.
     */
    public boolean setEnvironment(@NonNull Environment environment) {
        Log.i(TAG, "Configuring SDK for " + environment + " environment");
        if (environment == this.environment)
            return false;

        this.environment = environment;
        if (endPointDidChange())
            oauthHelper.clear();
        return true;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public boolean endPointDidChange() {
        return !environment.equals(oauthHelper.getEnvironment());
    }

    //========================================================================
    // Login / Logout methods
    //========================================================================

    /**
     * Returns true if a user is considered to be logged in (ie. the proper auth token is stored
     * and still valid).
     *
     * @return
     */
    public boolean isUserLoggedIn() {
        // Currently we determine if a user is logged in by checking the remaining time on
        // an access token (ie. if it has to be refreshed or not).
        return !oauthHelper.shouldRefreshAccessToken() && oauthHelper.isUserToken();
    }

    /**
     * Launches oauth web view to login / get user auth key.
     * Calling Activity must override onActivityResult with the given requestCode
     *
     * @param activity
     * @param redirectUrl the URL the SDK should trigger an Intent for when authentication is complete
     * @param requestCode the requestCode used to startActivityForResult
     */
    public void launchLoginActivity(Activity activity, String redirectUrl, int requestCode) {
        Intent intent = OAuthLoginActivity.newIntent(activity, environment.getAuthUrl(mojioAppID), redirectUrl);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Get app authentication
     * NOTE THIS IS THE APP AUTH TOKEN
     * We only want to use the app auth token when we have no stored USER auth token
     * This may happen when we are creating a new user
     *
     * @param responseListener
     */
    public void authenticateApp(final ResponseListener<Token> responseListener) {
        String entityPath = String.format("Login/%s?secretKey=%s&minutes=%d",
                mojioAppID, mojioAppSecretKey, SESSION_LENGTH_MIN);

        this.create(Token.class, entityPath, new MojioClient.ResponseListener<Token>() {
            @Override
            public void onSuccess(MojioResponse<Token> result) {
                // Save auth tokens
                // NOTE THIS IS THE APP AUTH TOKEN
                // We only want to use the app auth token when we have no stored USER auth token
                // This may happen when we are creating a new user
                oauthHelper.setAccessToken(result.getData().getId(), result.getData().getValidUntil(), environment, false);
                responseListener.onSuccess(result);
            }

            @Override
            public void onFailure(ResponseError error) {
                responseListener.onFailure(error);
            }
        });
    }

    /**
     * @param userNameOrEmail
     * @param password
     * @param responseListener
     */
    public void login(final String userNameOrEmail, final String password, final ResponseListener<User> responseListener) {
        if (oauthHelper.shouldRefreshAccessToken() || oauthHelper.isUserToken()) {
            authenticateApp(new ResponseListener<Token>() {
                @Override
                public void onSuccess(MojioResponse<Token> result) {
                    Log.d(TAG, "Successfully retrieved app access token, proceeding with login...");
                    login(userNameOrEmail, password, responseListener);
                }

                @Override
                public void onFailure(ResponseError error) {
                    responseListener.onFailure(error);
                }
            });
            return;
        }

        String urlEncodedUsername = userNameOrEmail;
        String urlEncodedPassword = password;
        try {
            urlEncodedUsername = URLEncoder.encode(userNameOrEmail, ENCODING);
            urlEncodedPassword = URLEncoder.encode(password, ENCODING);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Unsupported encoding of username or password string to " + ENCODING);
        }

        String entityPath = String.format("Login/%s?secretKey=%s&userOrEmail=%s&password=%s&minutes=%d",
                mojioAppID, mojioAppSecretKey, urlEncodedUsername, urlEncodedPassword, SESSION_LENGTH_MIN);

        this.create(Token.class, entityPath, new MojioClient.ResponseListener<Token>() {
            @Override
            public void onSuccess(MojioResponse<Token> result) {
                oauthHelper.setAccessToken(result.getData().getId(), result.getData().getValidUntil(), environment);
                getUser(result.getData().getUserId(), responseListener);
            }

            @Override
            public void onFailure(ResponseError error) {
                responseListener.onFailure(error);
                // TODO Need a way to pass back failures better (reasons)
            }
        });
    }

    public void loginOAuth(String accessToken, String validUntil, final ResponseListener<User> responseListener) {
        oauthHelper.setAccessToken(accessToken, validUntil, environment);
        getUser(responseListener);
    }

    public void refreshAccessToken(final ResponseListener<Token> responseListener) {
        String accessToken = oauthHelper.getAccessToken();
        String entityPath = String.format("Login/%s/Session?minutes=%d", accessToken, SESSION_LENGTH_MIN);
        this.create(Token.class, entityPath, new ResponseListener<Token>() {
            @Override
            public void onSuccess(MojioResponse<Token> result) {
                oauthHelper.setAccessToken(result.getData().getId(), result.getData().getValidUntil(), environment);
                responseListener.onSuccess(result);
            }

            @Override
            public void onFailure(ResponseError error) {
                responseListener.onFailure(error);
            }
        });
    }

    /**
     * Must already be logged in and have an access token
     */
    public void setSandboxedAccess(boolean sandboxed, final ResponseListener<Boolean> responseListener) {
        String currentAccessToken = oauthHelper.getAccessToken();

        if (currentAccessToken == null) {
            ResponseError error = new ResponseError("Not logged in");
            responseListener.onFailure(error);
            return;
        }

        String entityPath = String.format("Login/%s/Sandboxed", currentAccessToken);
        entityPath += "?sandboxed=" + String.valueOf(sandboxed);

        this.update(Token.class, entityPath, null, new MojioClient.ResponseListener<Token>() {
            @Override
            public void onSuccess(MojioResponse<Token> result) {
                Log.d(TAG, "Successfully updated Sandboxed to: " + result.getData().isSandboxed());
                // Update access tokens
                oauthHelper.setAccessToken(result.getData().getId(), result.getData().getValidUntil(), environment);
                responseListener.onSuccess(new MojioResponse<>(true));
            }

            @Override
            public void onFailure(ResponseError error) {
                responseListener.onFailure(error);
            }
        });
    }

    public String getAccessToken() {
        return oauthHelper.getAccessToken();
    }

    /**
     * @param fbAccessToken
     * @param responseListener
     */
    public void loginFacebook(final String fbAccessToken, final MojioClient.ResponseListener<User> responseListener) {
        // If we need an app access token, then fetch it first and call login again.
        // We will need an app access token under the following conditions:
        // 1. There is no access token at all (caught by the shouldRefreshAccessToken() call)
        // 2. The access token needs to be refreshed (caught by the shouldRefreshAccessToken() call)
        // 3. The access token is not an app access token
        if (oauthHelper.shouldRefreshAccessToken() || oauthHelper.isUserToken()) {
            authenticateApp(new ResponseListener<Token>() {
                @Override
                public void onSuccess(MojioResponse<Token> result) {
                    Log.d(TAG, "Got Facebook access token, proceeding to external user login...");
                    loginFacebook(fbAccessToken, responseListener);
                }

                @Override
                public void onFailure(ResponseError error) {
                    responseListener.onFailure(error);
                }
            });
            return;
        }

        // Process to login.
        String entityPath = String.format("Login/ExternalUser?accessToken=%s", fbAccessToken);

        this.create(Token.class, entityPath, new MojioClient.ResponseListener<Token>() {
            @Override
            public void onSuccess(MojioResponse<Token> result) {
                Log.d(TAG, "Login successful. User token: " + result.getData().getId());
                oauthHelper.setAccessToken(result.getData().getId(), result.getData().getValidUntil(), environment);
                getUser(result.getData().getUserId(), responseListener);
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
     *
     * @param userID
     * @param responseListener
     */
    private void getUser(String userID, final MojioClient.ResponseListener<User> responseListener) {
        // TODO Check cache?
        String entityPath = "Users/" + userID;
        HashMap<String, String> queryParams = new HashMap<>();
        this.get(User.class, entityPath, queryParams, responseListener);
    }

    private void getUser(final MojioClient.ResponseListener<User> responseListener) {
        String entityPath = "Users/Me";
        this.get(User.class, entityPath, null, responseListener);
    }

    public void logout() {
        oauthHelper.clear();
    }

    //========================================================================
    // Generic CRUD methods
    //========================================================================
    //

    /**
     * GET (GET)
     *
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
        addRequestToQueue(new MojioRequest<>(context,
                Request.Method.GET, environment.getApiUrl() + entityPath, modelClass, queryOptions, listener));
    }

    /**
     * UPDATE (PUT) with string content body
     *
     * @param modelClass
     * @param entityPath
     * @param contentBody
     * @param listener
     * @param <T>
     */
    public <T> void update(final Class<T> modelClass, String entityPath, String contentBody, final ResponseListener<T> listener) {
        addRequestToQueue(new MojioRequest<>(context, Request.Method.PUT, environment.getApiUrl() + entityPath,
                modelClass, contentBody, listener));
    }

    /**
     * UPDATE V2 (PUT) with string content body
     *
     * @param modelClass
     * @param entityPath
     * @param contentBody
     * @param listener
     * @param <T>
     */
    public <T> void updateV2(final Class<T> modelClass, String entityPath, String contentBody, final ResponseListener<T> listener) {
        addRequestToQueue(new MojioRequest<>(context, Request.Method.PUT, environment.getApiUrl(2) + entityPath,
                modelClass, contentBody, listener));
    }

    /**
     * DELETE (DELETE)
     *
     * @param modelClass
     * @param entityPath
     * @param listener
     * @param <T>
     */
    public <T> void delete(final Class<T> modelClass, String entityPath, final ResponseListener<T> listener) {
        addRequestToQueue(new MojioRequest<>(context, Request.Method.DELETE,
                environment.getApiUrl() + entityPath, modelClass, listener));
    }

    /**
     * DELETE V2 (DELETE)
     *
     * @param modelClass
     * @param entityPath
     * @param listener
     * @param <T>
     */
    public <T> void deleteV2(final Class<T> modelClass, String entityPath, final ResponseListener<T> listener) {
        addRequestToQueue(new MojioRequest<>(context, Request.Method.DELETE,
                environment.getApiUrl(2) + entityPath, modelClass, listener));
    }

    /**
     * CREATE (POST) with no content body
     *
     * @param modelClass
     * @param entityPath
     * @param listener
     * @param <T>
     */
    public <T> void create(final Class<T> modelClass, String entityPath, final ResponseListener<T> listener) {
        create(modelClass, entityPath, (String) null, listener);
    }

    /**
     * CREATE (POST) with a string content body
     *
     * @param modelClass
     * @param entityPath
     * @param contentBody
     * @param listener
     * @param <T>
     */
    public <T> void create(final Class<T> modelClass, String entityPath, String contentBody, final ResponseListener<T> listener) {
        addRequestToQueue(new MojioRequest<>(context, Request.Method.POST, environment.getApiUrl() + entityPath,
                modelClass, contentBody, listener));
    }

    /**
     * CREATE (POST) using a Map of key / value pairs
     *
     * @param modelClass
     * @param entityPath
     * @param params
     * @param listener
     * @param <T>
     */
    public <T> void create(final Class<T> modelClass, String entityPath, Map<String, String> params, final ResponseListener<T> listener) {
        addRequestToQueue(new MojioRequest<>(context, Request.Method.POST, environment.getApiUrl() + entityPath,
                modelClass, params, listener));
    }

    //========================================================================
    // Observer CRUD methods
    //========================================================================

    /**
     * GET (GET) Observer
     *
     * @param modelClass
     * @param entityPath
     * @param queryOptions
     * @param listener
     * @param <T>
     */
    public <T> void getObservers(final Class<T> modelClass, String entityPath, Map<String, String> queryOptions, final ResponseListener<T> listener) {
        String getParams = "";
        if (queryOptions != null) {
            for (String key : queryOptions.keySet()) {
                getParams += String.format("&%s=%s", key, queryOptions.get(key));
            }
            entityPath += getParams.replaceFirst("&", "?");
            addRequestToQueue(new MojioRequest<>(context, Request.Method.GET,
                    environment.getApiUrl() + entityPath, modelClass, queryOptions, listener));
        }
    }

    /**
     * CREATE (POST) Observer
     *
     * @param modelClass
     * @param contentBody
     * @param listener
     * @param <T>
     */
    public <T> void createObserver(final Class<T> modelClass, String contentBody, final ResponseListener<T> listener) {
        addRequestToQueue(new MojioRequest<>(context, Request.Method.POST, environment.getApiUrl() + "Observers",
                modelClass, contentBody, listener));
    }

    /**
     * DELETE (DELETE) Observer
     *
     * @param modelClass
     * @param observerId
     * @param listener
     * @param <T>
     */
    public <T> void deleteObserver(final Class<T> modelClass, final String observerId, final ResponseListener<T> listener) {
        addRequestToQueue(new MojioRequest<>(context, Request.Method.DELETE,
                environment.getApiUrl() + "Observers/" + observerId, modelClass, listener));
    }

    /**
     * CREATE (POST) Conditional Observer
     * Must provide Name, ObserverType, Timing, Subject, SubjectId, Transport and Condition values
     *
     * @param modelClass
     * @param conditionalObserverObject
     * @param listener
     * @param <T>
     */
    public <T> void createConditionalObserver(final Class<T> modelClass, Object conditionalObserverObject, final ResponseListener<T> listener) {
        String obj = GSON.toJson(conditionalObserverObject);
        addRequestToQueue(new MojioRequest<>(context, Request.Method.POST, environment.getApiUrl() + "Observers",
                modelClass, obj, listener));
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
    public <T> void subscribeToObserver(final Class<T> modelClass, final Observer observer, final ResponseListener<T> listener) {
        Log.d(TAG, "Subscribing to observer " + observer.getId() + "...");
        Platform.loadPlatformComponent(new AndroidPlatformComponent());

        final String signalRHost = environment.getSignalRUrl();
        connection = new HubConnection(signalRHost);
        hub = connection.createHubProxy("ObserverHub");
        awaitConnection = connection.start();

        //create a handler that waits to check if we can subscribe using web sockets.  if we can't
        //switch to long polling to subscribe

        // TODO this is bad - use SignalRFuture.get(timeout) instead
        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!connectionEstablished) {
                    Log.e(TAG, "Websockets failed. switching to long polling");
                    LongPollingTransport transport = new LongPollingTransport(new NullLogger());
                    connection.disconnect();
                    connection = new HubConnection(signalRHost);
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

    private void connectToObserver(SignalRFuture<Void> awaitConnection, HubProxy hub, Observer observer) {
        try {
            awaitConnection.get();
            connectionEstablished = true;
            hub.invoke("Subscribe", observer.getId());
        } catch (InterruptedException | ExecutionException e) {
            Log.e(TAG, "Error connecting to observer " + observer.getId(), e);
        }
    }

    private <T> void subscribe(final Class<T> modelClass, final ResponseListener<T> listener) {
        Log.d(TAG, "Subscribing " + modelClass.getSimpleName() + " listeners to hub...");
        hub.subscribe(this);
        hub.on("UpdateEntity", new SubscriptionHandler1<JsonObject>() {
            @Override
            public void run(JsonObject o) {
                String json = o.toString();
                Log.d(TAG, "Received " + modelClass + " update: " + json);
                MojioResponse<T> result = new MojioResponse<>(GSON.fromJson(json, modelClass));
                listener.onSuccess(result); // NOTE still on handler thread.
            }
        }, JsonObject.class);

        hub.on("Error", new SubscriptionHandler1<Object>() {
            @Override
            public void run(Object o) {
                ResponseError error = new ResponseError("Failed to subscribe to signalr hub");
                listener.onFailure(error); // NOTE still on handler thread.
            }
        }, Object.class);
    }

    public void getImage(String entityPath, Map<String, String> queryOptions, final ResponseListener<Bitmap> listener) {
        String getParams = "";
        if (queryOptions != null) {
            for (String key : queryOptions.keySet()) {
                getParams += String.format("&%s=%s", key, queryOptions.get(key));
            }
            entityPath += getParams.replaceFirst("&", "?");
        }
        addRequestToQueue(new MojioImageRequest(context, environment.getApiUrl() + entityPath, 0, 0, null,
                listener));
    }

    public <T> void updateImage(final Class<T> modelClass, String entityPath, Bitmap data, final ResponseListener<T> listener) {
        addRequestToQueue(new MojioRequest<>(context, Request.Method.PUT, environment.getApiUrl() + entityPath,
                modelClass, data, listener));
    }

    public RequestQueue getRequestQueue() {
        return requestHelper.getRequestQueue();
    }

    public <T> void addRequestToQueue(final Request<T> request) {
        // Login requests shouldn't get blocked checking the access token
        addRequestToQueue(request, !request.getUrl().contains("Login"));
    }

    public <T> void addRequestToQueue(final Request<T> request, boolean checkAccessToken) {
        // Note we use an AtomicBoolean for refreshTokenLock as locks give ownership to threads
        // and it's possible for the same thread to get in here more than once before listener returns
        if (checkAccessToken && oauthHelper.shouldRefreshAccessToken() && refreshTokenLock.compareAndSet(false, true)) {
            Log.i(TAG, "Access token is about to expire, refreshing...");
            refreshAccessToken(new ResponseListener<Token>() {
                @Override
                public void onSuccess(MojioResponse<Token> result) {
                    Log.i(TAG, "Successfully refreshed access token");
                    refreshTokenLock.set(false);
                }

                @Override
                public void onFailure(ResponseError error) {
                    Log.e(TAG, "Failed to refresh access token: " + error.getMessage());
                    refreshTokenLock.set(false);
                }
            });
        }
        requestHelper.addToRequestQueue(request, TAG);
    }

    public void cancelAllRequests() {
        requestHelper.cancelPendingRequests(TAG);
    }

    public static Gson getGson() {
        return GSON;
    }

    public static class ResponseError {
        private String message;
        private NetworkResponse networkResponse;

        public ResponseError() {
            this((String) null);
        }

        public ResponseError(String message) {
            this.message = message;
        }

        public ResponseError(VolleyError error) {
            this.networkResponse = error.networkResponse;
            if (networkResponse != null && networkResponse.data != null) {
                try {
                    this.message = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
                } catch (UnsupportedEncodingException e) {
                    Log.e(TAG, "Error encoding response data", e);
                }
            }
        }

        public String getMessage() {
            return message;
        }

        public NetworkResponse getNetworkResponse() {
            return this.networkResponse;
        }

    }

    public static abstract class ResponseListener<T> implements Response.Listener<MojioResponse<T>>, Response.ErrorListener {
        public abstract void onSuccess(MojioResponse<T> result);
        public abstract void onFailure(ResponseError error);

        @Override
        public void onResponse(MojioResponse<T> response) {
            // TODO backwards compatibility
            onSuccess(response);
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            onFailure(new ResponseError(error));
        }
    }

}
