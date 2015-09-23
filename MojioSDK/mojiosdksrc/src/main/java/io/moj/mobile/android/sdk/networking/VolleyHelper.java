package io.moj.mobile.android.sdk.networking;

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
import java.util.Map;

/**
 * Created by ssawchenko on 15-01-14.
 */
public class VolleyHelper {

    private static String TAG = "VolleyHelper";
    private final int SOCKET_TIMEOUT_MS = 5000;

    private RequestQueue requestQueue;
    private MojioHttpStack networkStack;

    private DefaultRetryPolicy _defaultRetryPolicy = new DefaultRetryPolicy(
            SOCKET_TIMEOUT_MS,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    public VolleyHelper(Context context) {
        // Set default cookie manager
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        networkStack = new MojioHttpStack();
        requestQueue = Volley.newRequestQueue(context, networkStack);
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // Lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (requestQueue == null) {
            initRequestQueue();
        }
        return requestQueue;
    }

    private synchronized void initRequestQueue() {
        if (requestQueue == null) {

        }
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        if (requestQueue == null) {
            getRequestQueue();
        }

        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
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
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    /**
     * Sets headers that will be added to every request.
     * @param key the key of the header (e.g. Authorization, Accept-Language, etc)
     * @param value the value of the header
     */
    public void setGlobalHeader(String key, String value) {
        this.networkStack.setGlobalHeader(key, value);
    }

}
