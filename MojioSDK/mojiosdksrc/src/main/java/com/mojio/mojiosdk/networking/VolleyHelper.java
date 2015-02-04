package com.mojio.mojiosdk.networking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by ssawchenko on 15-01-14.
 */
public class VolleyHelper {

    private static String TAG = "VolleyHelper";
    private RequestQueue _requestQueue;
    private CookieManager _cookieManager;
    private Context _ctx;

    private DefaultRetryPolicy _defaultRetryPolicy = new DefaultRetryPolicy(
            30000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    public VolleyHelper(Context ctx) {
        _ctx = ctx;
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // Lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (_requestQueue == null) {

            // Set default cookie manager
            _cookieManager = new CookieManager();
            _cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(_cookieManager);

            // newRequestQueue uses volley's BasicNetwork, which uses an underlying HttpURLConnection object
            // SHOULD automatically query the CookieManager
            _requestQueue = Volley.newRequestQueue(_ctx);
        }

        return _requestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {

        if (_requestQueue == null) {
            getRequestQueue();
        }

        // set the default tag if tag is empty
        req.setTag(tag == null || TextUtils.isEmpty(tag) ? TAG : tag);
        req.setRetryPolicy(_defaultRetryPolicy);
        Log.i(TAG, String.format("VolleyHelper adding request to queue: %s", req.getUrl()));

        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     */
    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        addToRequestQueue(req, null);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important
     * to specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (_requestQueue != null) {
            _requestQueue.cancelAll(tag);
        }
    }
}
