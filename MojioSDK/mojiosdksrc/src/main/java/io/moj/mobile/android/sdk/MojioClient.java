package io.moj.mobile.android.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

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

    private static final String TAG = MojioClient.class.getSimpleName();

    //========================================================================
    // Server Config - DO NOT CHANGE
    //========================================================================

    private static final String REQUEST_TAG = "MojioRequest";
    private static final String URL_AUTH_PATH = "https://%s/OAuth2/authorize?response_type=token&client_id=%%s";
    private static final String URL_BASE_PATH = "https://%s/v1/";
    private static final String URL_SIGNAL_R_HOST = "https://%s/v1/signalr";

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
    private int _configuredMcc = -1;        // The most recently configured MCC
    private int _configuredMnc = -1;        // The most recently configured MNC
    private AtomicBoolean refreshTokenLock = new AtomicBoolean(false);

    //========================================================================
    // Generic response listener interface
    //========================================================================
    public static final int RESPONSE_ERR_UNKNOWN = 0;
    public static final int RESPONSE_ERR_NOT_LOGGED_IN = 1;
    public static final int RESPONSE_ERR_SIGNALR_ERROR = 2;
    public static final int RESPONSE_ERR_VOLLEY_ERROR = 3;
    public static final int RESPONSE_ERR_SERVER_TIMEOUT = 4;

    private static final int SESSION_LENGTH_MIN = 43829; // 1 month

    private static final Locale EN_GB = new Locale("en", "GB");
    private static final Locale CS_CZ = new Locale("cs");
    private static final int CZ_MCC = 230;
    private static final int CZ_TMOBILE_MNC = 1;

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
    private boolean sandboxAvailable = true;
    private Gson subscriberGson;

    public MojioClient(Context ctx, String mojioAppID, String mojioSecretkey, String redirectUrl) {
        _ctx = ctx;
        _oauthHelper = new DataStorageHelper(_ctx);
        _requestHelper = new VolleyHelper(_ctx);
        _mojioAppID = mojioAppID;
        _mojioAppSecretKey = mojioSecretkey;
        _redirectUrl = redirectUrl;

        this.sandboxAvailable = false;
        this.updateUrl("eu-api.moj.io");
        this._configuredMcc = 0;
        this._configuredMnc = 0;
    }

    //========================================================================
    // Endpoint setup
    //========================================================================

    /**
     * Sets the endpoint depending on the specific locale.
     *
     * @param mcc The specified MCC.
     * @param mnc The specified MNC.
     */
    public synchronized void updateRegion(int mcc, int mnc) {
        if (_configuredMcc == mcc && _configuredMnc == mnc)
            return;

        Log.i(TAG, "Configuring SDK for MCC '" + mcc + "' and MNC '" + mnc + "'...");
        Endpoint endpoint = new Endpoint(_ctx, mcc, mnc);
        this.sandboxAvailable = endpoint.isSandboxAvailable();
        this.updateUrl(endpoint.getApiUrl());
        this._configuredMcc = mcc;
        this._configuredMnc = mnc;
    }

    private void updateUrl(String apiUrl) {
        _authPath = String.format(URL_AUTH_PATH, apiUrl);
        _apiBaseUrl = String.format(URL_BASE_PATH, apiUrl);
        _signalRHost = String.format(URL_SIGNAL_R_HOST, apiUrl);

        if (endPointDidChange()) {
            Log.i(TAG, "Endpoints have changed from " + _oauthHelper.getEndpoint() + " to " + _authPath);
            _oauthHelper.removeAllUserStoredValues();
        }
    }

    /**
     * Determines whether this instance of the MojioClient is connecting to the same
     * endpoint as the endpoint that has been cached. This call allows the application to make
     * configuration changes.
     *
     * @return Whether the end point did change.
     */
    public boolean endPointDidChange() {
        return !_authPath.equals(_oauthHelper.getEndpoint());
    }

    /**
     * Returns true if the API supports a sandboxed environment. Note that the V2 APIs no longer
     * support the sandbox.
     *
     * @return
     */
    public boolean isSandboxAvailable() {
        return sandboxAvailable;
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
        return !_oauthHelper.shouldRefreshAccessToken() && _oauthHelper.isUserToken();
    }

    /**
     * Launches oauth web view to login / get user auth key.
     * Calling Activity must override onActivityResult with the given requestCode
     *
     * @param activity
     * @param requestCode The requestCode used to startActivityForResult
     */
    public void launchLoginActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, OAuthLoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("URL_AUTH_PATH", String.format(_authPath, _mojioAppID));
        bundle.putString("USER_AUTH_TOKEN", _oauthHelper.getAccessToken());
        bundle.putString("REDIRECT_URL", _redirectUrl);
        intent.putExtras(bundle);
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
    public void authenticateApp(final ResponseListener<UserToken> responseListener) {
        String entityPath = String.format("Login/%s?secretKey=%s&minutes=%d",
                _mojioAppID, _mojioAppSecretKey, SESSION_LENGTH_MIN);

        this.create(UserToken.class, entityPath, new MojioClient.ResponseListener<UserToken>() {
            @Override
            public void onSuccess(UserToken result) {
                // Save auth tokens
                // NOTE THIS IS THE APP AUTH TOKEN
                // We only want to use the app auth token when we have no stored USER auth token
                // This may happen when we are creating a new user
                _oauthHelper.setAccessToken(result._id, result.ValidUntil, _authPath, false);
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
    public void login(final String userNameOrEmail, final String password, final ResponseListener<User> responseListener) {
        // If we need an app access token, then fetch it first and call login again.
        // We will need an app access token under the following conditions:
        // 1. There is no access token at all (caught by the shouldRefreshAccessToken() call)
        // 2. The access token needs to be refreshed (caught by the shouldRefreshAccessToken() call)
        // 3. The access token is not an app access token
        if (_oauthHelper.shouldRefreshAccessToken() || _oauthHelper.isUserToken()) {
            authenticateApp(new ResponseListener<UserToken>() {
                @Override
                public void onSuccess(UserToken result) {
                    Log.d(TAG, "App token " + result._id + " set. Proceeding to login.");
                    login(userNameOrEmail, password, responseListener);
                }

                @Override
                public void onFailure(ResponseError error) {
                    responseListener.onFailure(error);
                }
            });
            return;
        }

        // Process to login.
        String urlEncodedUsername = userNameOrEmail;
        String urlEncodedPassword = password;
        try {
            urlEncodedUsername = URLEncoder.encode(userNameOrEmail, "UTF-8");
            urlEncodedPassword = URLEncoder.encode(password, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Unsupported encoding of username or password string to UTF-8");
        }

        String entityPath = String.format("Login/%s?secretKey=%s&userOrEmail=%s&password=%s&minutes=%d",
                _mojioAppID, _mojioAppSecretKey, urlEncodedUsername, urlEncodedPassword, SESSION_LENGTH_MIN);

        this.create(UserToken.class, entityPath, new MojioClient.ResponseListener<UserToken>() {
            @Override
            public void onSuccess(UserToken result) {
                // Save auth tokens
                Log.d(TAG, "Login successful. User token: " + result._id);
                _oauthHelper.setAccessToken(result._id, result.ValidUntil, _authPath);

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

    public void refreshAccessToken(final ResponseListener<UserToken> responseListener) {
        String accessToken = _oauthHelper.getAccessToken();
        String entityPath = String.format("Login/%s/Session?minutes=%d", accessToken, SESSION_LENGTH_MIN);
        this.create(UserToken.class, entityPath, new ResponseListener<UserToken>() {
            @Override
            public void onSuccess(UserToken result) {
                _oauthHelper.setAccessToken(result._id, result.ValidUntil, _authPath);
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
        String currentAccessToken = _oauthHelper.getAccessToken();

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
                Log.d(TAG, "Successfully updated Sandboxed to: " + result.Sandboxed);
                // Update access tokens
                _oauthHelper.setAccessToken(result._id, result.ValidUntil, _authPath);
                responseListener.onSuccess(true);
            }

            @Override
            public void onFailure(ResponseError error) {
                responseListener.onFailure(error);
            }
        });
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
        if (_oauthHelper.shouldRefreshAccessToken() || _oauthHelper.isUserToken()) {
            authenticateApp(new ResponseListener<UserToken>() {
                @Override
                public void onSuccess(UserToken result) {
                    Log.d(TAG, "App token " + result._id + " set. Proceeding to external user login.");
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

        this.create(UserToken.class, entityPath, new MojioClient.ResponseListener<UserToken>() {
            @Override
            public void onSuccess(UserToken result) {
                // Save user auth token
                Log.d(TAG, "Login successful. User token: " + result._id);
                _oauthHelper.setAccessToken(result._id, result.ValidUntil, _authPath);

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

    public void logout() {
        _oauthHelper.removeAllUserStoredValues();
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
        addRequestToQueue(apiRequest);
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
        addRequestToQueue(apiRequest);
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
        addRequestToQueue(apiRequest);
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
        addRequestToQueue(apiRequest);
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
        addRequestToQueue(apiRequest);
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
            addRequestToQueue(apiRequest);
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
        addRequestToQueue(apiRequest);
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
        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.DELETE, _apiBaseUrl + "Observers/" + observerId, modelClass,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        Log.d(TAG, "Observer " + observerId + " successfully deleted");
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
        addRequestToQueue(apiRequest);
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
        String obj = new Gson().toJson(conditionalObserverObject);
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
        addRequestToQueue(apiRequest);
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
        Log.d(TAG, "Subscribing to observer " + observer._id + "...");
        subscriberGson = new Gson();
        Platform.loadPlatformComponent(new AndroidPlatformComponent());

        connection = new HubConnection(_signalRHost);
        hub = connection.createHubProxy("ObserverHub");
        awaitConnection = connection.start();

        //create a handler that waits to check if we can subscribe using web sockets.  if we can't
        //switch to long polling to subscribe
        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!connectionEstablished) {
                    Log.e(TAG, "Websockets failed. switching to long polling");
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

    private void connectToObserver(SignalRFuture<Void> awaitConnection, HubProxy hub, Observer observer) {
        try {
            awaitConnection.get();
            connectionEstablished = true;
            hub.invoke("Subscribe", observer._id);
        } catch (InterruptedException | ExecutionException e) {
            Log.e(TAG, "Error connecting to observer " + observer._id, e);
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
                T result = subscriberGson.fromJson(json, modelClass);
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
    public void getImage(String entityPath, Map<String, String> queryOptions, final ResponseListener<Bitmap> listener) {
        String getParams = "";
        if (queryOptions != null) {
            for (String key : queryOptions.keySet()) {
                getParams += String.format("&%s=%s", key, queryOptions.get(key));
            }
            entityPath += getParams.replaceFirst("&", "?");
        }

        MojioImageRequest imageRequest = new MojioImageRequest(_ctx, _apiBaseUrl + entityPath, 0, 0, null,
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
        addRequestToQueue(apiRequest);
    }

    public <T> void addRequestToQueue(final Request<T> request) {
        // Login requests shouldn't get blocked checking the access token
        addRequestToQueue(request, !request.getUrl().contains("Login"));
    }

    public <T> void addRequestToQueue(final Request<T> request, boolean checkAccessToken) {
        // Note we use an AtomicBoolean for refreshTokenLock as locks give ownership to threads
        // and it's possible for the same thread to get in here more than once before listener returns
        if (checkAccessToken && _oauthHelper.shouldRefreshAccessToken() && refreshTokenLock.compareAndSet(false, true)) {
            Log.i(TAG, "Access token is about to expire, refreshing...");
            refreshAccessToken(new ResponseListener<UserToken>() {
                @Override
                public void onSuccess(UserToken result) {
                    Log.i(TAG, "Successfully refreshed access token");
                    refreshTokenLock.set(false);
                }

                @Override
                public void onFailure(ResponseError error) {
                    Log.e(TAG, "Failed to refresh access token: " + error.message);
                    refreshTokenLock.set(false);
                }
            });
        }
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
            Log.e(TAG, respErr.message);
            listener.onFailure(respErr);
        } catch (Exception e) {
            ResponseError respErr = new ResponseError();

            if (error instanceof TimeoutError) {
                respErr.type = RESPONSE_ERR_SERVER_TIMEOUT;
                respErr.message = "Server timeout";
            } else {
                try {
                    // Report raw volley error
                    respErr.type = RESPONSE_ERR_VOLLEY_ERROR;
                    respErr.message = error.getMessage();
                } catch (Exception e2) {
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

            Log.e(TAG, respErr.message);
            listener.onFailure(respErr);
        }
    }
}
