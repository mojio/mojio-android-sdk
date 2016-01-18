package io.moj.mobile.android.sdk.rest.networking;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

import io.moj.mobile.android.sdk.rest.util.Trace;

/**
 * Encapsulates error information about a failed API request.
 * Created by skidson on 15-11-20.
 */
@SuppressWarnings("unused")
public class MojioResponseError {

    private static final String TAG = MojioResponseError.class.getSimpleName();

    private NetworkResponse networkResponse;
    private String message;

    public MojioResponseError() {
        this((String) null);
    }

    public MojioResponseError(String message) {
        this.message = message;
    }

    public MojioResponseError(VolleyError error) {
        this.networkResponse = error.networkResponse;
        if (networkResponse != null && networkResponse.data != null) {
            try {
                this.message = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            } catch (UnsupportedEncodingException e) {
                Trace.e(TAG, "Error encoding response data", e);
            }
        }
    }

    public String getMessage() {
        return message;
    }

    public NetworkResponse getNetworkResponse() {
        return networkResponse;
    }

    public boolean hasResponse() {
        return networkResponse != null;
    }
}
