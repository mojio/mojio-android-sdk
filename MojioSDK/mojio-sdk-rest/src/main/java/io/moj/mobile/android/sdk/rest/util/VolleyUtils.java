package io.moj.mobile.android.sdk.rest.util;

import com.android.volley.Request;

/**
 * Static utilities for interactinv with Volley classes.
 * Created by skidson on 15-11-20.
 */
public class VolleyUtils {

    private VolleyUtils() {}

    /**
     * Returns the string method name given a Volley integer
     * {@link com.android.volley.Request.Method}.
     * @param method the method type
     * @return The string corresponding to the method type
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
