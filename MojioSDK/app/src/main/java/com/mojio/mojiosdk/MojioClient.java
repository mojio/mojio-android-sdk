package com.mojio.mojiosdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mojio.mojiosdk.networking.MojioRequest;
import com.mojio.mojiosdk.networking.OAuthLoginActivity;
import com.mojio.mojiosdk.networking.VolleyHelper;

import java.util.Map;

/**
 * Created by ssawchenko on 15-02-01.
 */
public class MojioClient {

    // Config
    private static String BASE_URL = "https://api.moj.io/";
    private static String URL_AUTH_PATH = BASE_URL + "OAuth2/authorize?response_type=token&client_id=%s";

    private static String _userAuthToken;
    private static String _redirectUrl;

    // Interfaces
    public interface ResponseListener<T> {
        public void onSuccess(T result);
        public void onFailure();
    }

    // Properties
    private Context _ctx;
    private DataStorageHelper _oauthHelper;
    private VolleyHelper _requestHelper;

    // Initialization
    public MojioClient(Context ctx, String userSecretKey, String redirectUrl) {
        _ctx = ctx;
        _oauthHelper = new DataStorageHelper(_ctx);
        _requestHelper = new VolleyHelper(_ctx);
        _userAuthToken = userSecretKey;
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
        bundle.putString("URL_AUTH_PATH", String.format(URL_AUTH_PATH, _userAuthToken));
        bundle.putString("USER_AUTH_TOKEN", _oauthHelper.GetAccessToken());
        bundle.putString("REDIRECT_URL",  _redirectUrl);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    // Get
    // TODO with params

    public <T> void get(final Class<T> modelClass, String url, Map<String, String> queryOptions, final ResponseListener<T> listener) {

        // Add query options to get url
        String getParams = "";
        for (String key : queryOptions.keySet()) {
            getParams += String.format("&%s=%s", key, queryOptions.get(key));
        }
        url += getParams.replaceFirst("&", "?");

        MojioRequest apiRequest = new MojioRequest(_ctx, Request.Method.GET, url, modelClass, queryOptions,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        listener.onSuccess(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onFailure();
                        Toast.makeText(_ctx, "FAILED TO GET VALUES", Toast.LENGTH_LONG).show();
                    }
                });

        // Run request
        _requestHelper.addToRequestQueue(apiRequest);
    }
}
