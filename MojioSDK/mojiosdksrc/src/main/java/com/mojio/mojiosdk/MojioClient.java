package com.mojio.mojiosdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.mojio.mojiosdk.networking.MojioRequest;
import com.mojio.mojiosdk.networking.OAuthLoginActivity;
import com.mojio.mojiosdk.networking.VolleyHelper;

import java.util.Map;

/**
 * Created by ssawchenko on 15-02-01.
 */
public class MojioClient {

    // Static
    private static String REQUEST_TAG = "MojioRequest";
    private static String URL_AUTH_PATH = "https://api.moj.io/OAuth2/authorize?response_type=token&client_id=%s";

    // Config
    //https://api.moj.io/v1/Vehicles/53cdeca5-b268-4a25-bfde-3938b5cf7d47/Store/
    private String _apiBaseUrl = "https://api.moj.io/v1/"; // Default
    private String _mojioAppID;
    private String _redirectUrl;

    // Interfaces
    public interface ResponseListener<T> {
        public void onSuccess(T result);
        public void onFailure();
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
    public MojioClient(Context ctx, String mojioAppID, String redirectUrl) {
        _ctx = ctx;
        _oauthHelper = new DataStorageHelper(_ctx);
        _requestHelper = new VolleyHelper(_ctx);
        _mojioAppID = mojioAppID;
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
                        reportVolleyError(error);
                        listener.onFailure();
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    // Update - PUT
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
                        reportVolleyError(error);
                        listener.onFailure();
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
                        reportVolleyError(error);
                        listener.onFailure();
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
                        reportVolleyError(error);
                        listener.onFailure();
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest, REQUEST_TAG);
    }

    // Helpers
    private void reportVolleyError(VolleyError error) {
        // Attempt to parse response errors
        try {
            String errorResponse = new String(error.networkResponse.data,
                    HttpHeaderParser.parseCharset(error.networkResponse.headers));
            Log.e("MOJIO", errorResponse);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MOJIO", e.getMessage());

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
