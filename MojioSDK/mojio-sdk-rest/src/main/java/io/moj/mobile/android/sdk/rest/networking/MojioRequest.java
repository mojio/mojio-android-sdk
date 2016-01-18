package io.moj.mobile.android.sdk.rest.networking;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Map;

import io.moj.mobile.android.sdk.rest.util.Trace;
import io.moj.mobile.android.sdk.rest.util.VolleyUtils;

/**
 * A request object for interaction with the Mojio API.
 * Created by skidson on 15-11-19.
 */
@SuppressWarnings("unused")
public class MojioRequest<T> extends Request<MojioResponse<T>> {

    public static final String TAG = MojioRequest.class.getSimpleName();

    private static final String CHARSET = "utf-8";
    private static final String CONTENT_TYPE = String.format("application/json; charset=%s", CHARSET);

    private Gson gson;
    private Uri uri;
    private String contentBody;
    private Map<String, String> headers;
    private Class<T> responseClass;
    private MojioCallback<T> callback;
    private MojioGlobalCallback<T> globalCallback;

    public MojioRequest(Gson gson, int method, Uri uri, Map<String, String> headers,
                        Class<T> responseClass, MojioCallback<T> callback) {
        this(gson, method, uri, null, headers, responseClass, callback);
    }

    public MojioRequest(Gson gson, int method, Uri uri, String contentBody,
                        Map<String, String> headers, Class<T> responseClass,
                        MojioCallback<T> callback) {
        super(method, uri.toString(), callback);
        this.gson = gson;
        this.uri = uri;
        this.responseClass = responseClass;
        this.headers = headers;
        this.callback = callback;
        this.contentBody = contentBody;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Response<MojioResponse<T>> parseNetworkResponse(NetworkResponse response) {
        try {
            MojioResponse<T> result;
            String responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            logResponse(responseString);

            if (responseString.isEmpty()) {
                result = new MojioResponse<>();
                // Body was empty, no need to parse.
            } else if (responseClass == String.class) {
                // If we want just a String, simply return the response.data casted as such.
                result = (MojioResponse<T>) new MojioResponse<>(responseString);
            } else {
                T data = null;
                try {
                    data = gson.fromJson(responseString, responseClass);
                } catch (JsonSyntaxException e) {
                    Trace.w(TAG, "Could not parse response as a " + responseClass.getSimpleName());
                }
                result = new MojioResponse<>(data);
            }
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            Log.e(TAG, "Error parsing network response", e);
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(MojioResponse<T> response) {
        if (globalCallback != null) {
            globalCallback.onSuccess(this, response, callback);
        } else if (callback != null) {
            callback.onSuccess(response);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return contentBody == null ? super.getBody() : contentBody.getBytes();
    }

    @Override
    public String getBodyContentType() {
        return CONTENT_TYPE;
    }

    public Uri getUri() {
        return uri;
    }

    public void setGlobalCallback(MojioGlobalCallback<T> globalCallback) {
        this.globalCallback = globalCallback;
    }

    public void logRequest() {
        String message = "Request [" + VolleyUtils.parseMethodString(getMethod()) + " " + getUrl() + "]";
        if (headers != null)
            message += "; Headers: {" + headers.toString() + "}\"";
        if (contentBody != null)
            message += ": " + contentBody;
        Trace.v(TAG, message);
    }

    public void logResponse(String response) {
        String message = "Response [" + VolleyUtils.parseMethodString(getMethod()) + " " + getUrl() + "]: ";
        message += TextUtils.isEmpty(response)
                ? "{ }"
                : response.replace("\n", "");
        Trace.v(TAG, message);
    }
}
