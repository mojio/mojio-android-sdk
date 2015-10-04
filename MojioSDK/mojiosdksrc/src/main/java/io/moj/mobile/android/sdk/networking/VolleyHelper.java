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

/**
 * Wraps Volley's {@link RequestQueue} and provides initial configuration.
 * Created by ssawchenko on 15-01-14.
 */
public class VolleyHelper {

    private static String TAG = VolleyHelper.class.getSimpleName();
    private static final int SOCKET_TIMEOUT_MS = 5000;

    private static final DefaultRetryPolicy RETRY_POLICY = new DefaultRetryPolicy(
            SOCKET_TIMEOUT_MS,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    private RequestQueue requestQueue;

    public VolleyHelper(Context context) {
        // Set default cookie manager
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
        requestQueue = Volley.newRequestQueue(context, new MojioHttpStack());
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(tag == null || TextUtils.isEmpty(tag) ? TAG : tag);
        req.setRetryPolicy(RETRY_POLICY);
        Log.v(TAG, "Request [" + parseMethodString(req.getMethod()) + ": " + req.getUrl() + "]");
        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     */
    public <T> void addToRequestQueue(MojioRequest<T> req) {
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
     * Returns the string method name given a Volley integer {@link com.android.volley.Request.Method}.
     * @param method
     * @return
     */
    public static String parseMethodString(int method) {
        switch (method) {
            case Request.Method.GET:
                return "GET";
            case Request.Method.POST:
                return "POST";
            case Request.Method.PUT:
                return "PUT";
            case Request.Method.DELETE:
                return "DELETE";
            case Request.Method.HEAD:
                return "HEAD";
            case Request.Method.OPTIONS:
                return "OPTIONS";
            case Request.Method.TRACE:
                return "TRACE";
            case Request.Method.PATCH:
                return "PATCH";
            default:
                return null;
        }
    }

}
