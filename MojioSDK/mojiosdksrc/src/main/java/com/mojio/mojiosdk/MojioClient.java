package com.mojio.mojiosdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.mojio.mojiosdk.models.User;
import com.mojio.mojiosdk.models.UserToken;
import com.mojio.mojiosdk.networking.MojioImageRequest;
import com.mojio.mojiosdk.networking.MojioRequest;
import com.mojio.mojiosdk.networking.OAuthLoginActivity;
import com.mojio.mojiosdk.networking.VolleyHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssawchenko on 15-02-01.
 */
public class MojioClient {

    // Static
    private static String REQUEST_TAG = "MojioRequest";
    private static String URL_AUTH_PATH = "https://api.moj.io/OAuth2/authorize?response_type=token&client_id=%s";

    // Config
    private String _apiBaseUrl = "https://api.moj.io/v1/"; // Default

    private String _mojioAppID;
    private String _mojioAppSecretKey;
    private String _redirectUrl;

    // Interfaces
    public interface ResponseListener<T> {
        public void onSuccess(T result);
        public void onFailure(String error);
    }

    // Properties
    private Context _ctx;
    private DataStorageHelper _oauthHelper;
    private VolleyHelper _requestHelper;

    // Assign the values of the App id, secret key and redirect uri scheme to the client
    //- (void) initWithAppId : (NSString *)
    // appId andSecretrKey : (NSString *)secretKey
    // andRedirectUrlScheme : (NSString *) urlScheme;

    // Initialization
    public MojioClient(Context ctx, String mojioAppID, String mojioSecretkey, String redirectUrl) {
        _ctx = ctx;
        _oauthHelper = new DataStorageHelper(_ctx);
        _requestHelper = new VolleyHelper(_ctx);
        _mojioAppID = mojioAppID;
        _mojioAppSecretKey = mojioSecretkey;
        _redirectUrl = redirectUrl;
    }

    /**
     * Returns true if a user is concidered to be logged in (ie. the proper auth token is stored
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
     *  Get app authentication
     *  Calling this cNOTE THIS IS THE APP AUTH TOKEN
     *  We only want to use the app auth token when we have no stored USER auth token
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
            public void onFailure(String error) {
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
            public void onFailure(String error) {
                responseListener.onFailure(error);
                // TODO Need a way to pass back failures better (reasons)
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
                // Save auth tokens
                // NOTE THIS IS THE APP AUTH TOKEN
                // We only want to use the app auth token when we have no stored USER auth token
                // This may happen when we are creating a new user
                _oauthHelper.SetAppToken(result._id);
                _oauthHelper.SetAppExpireTime(result.ValidUntil);
                String userID = result.UserId;
                getUser(userID, responseListener); // Pass along response listener

            }

            @Override
            public void onFailure(String error) {
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

    // Get - GET
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
//                        mProfileImage.setImageBitmap(response);
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

    // Update - PUT
    public <T> void update(final Class<T> modelClass, String entityPath, JSONObject data, final ResponseListener<T> listener) {

        String contentBody = data.toString();

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

    // Delete - DELETE
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

    // Create - POST
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

    // Create - POST
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

    // Create - POST
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

    // Helpers
    private void reportVolleyError(final VolleyError error, ResponseListener listener) {
        // Attempt to parse response errors
        try {
            String errorResponse = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
            Log.e("MOJIO", errorResponse);
            listener.onFailure(errorResponse);

        } catch (Exception e) {
            String result;
            try {
                // Report raw volley error
                result = error.getMessage();
            }
            catch (Exception e2) {
                // Return unknown
                result = "Unknown error";
            }

            e.printStackTrace();
            Log.e("MOJIO", result);
            listener.onFailure(result);
        }
    }

    public void addRequestToQueue(Request request) {
        Log.i("MOJIO", "Adding to request queue: " + request.getUrl());
        _requestHelper.addToRequestQueue(request, REQUEST_TAG);
    }

    public void cancelAllRequests() {
        _requestHelper.cancelPendingRequests(REQUEST_TAG);
    }


    //  (void) updateEntityWithPath:(NSString*)path
    // withContentBody : (NSString *)contentBody
    // success : (void(^)(void))success
    // failure : (void(^)(void)) failure;


}
