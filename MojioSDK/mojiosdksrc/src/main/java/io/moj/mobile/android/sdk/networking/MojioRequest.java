package io.moj.mobile.android.sdk.networking;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.moj.mobile.android.sdk.DataStorageHelper;
import io.moj.mobile.android.sdk.MojioClient;

/**
 * Writen by Shayla Sawchenko
 * Based on GsonVolleyRequest by Ognyan Bankov
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Volley adapter for JSON requests with POST method that will be parsed into Java objects by Gson.
 */
public class MojioRequest<T> extends Request<MojioResponse<T>> {

    private static final String TAG = MojioRequest.class.getSimpleName();
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private Context mAppContext;
    private Class<T> clazz;
    private Map<String, String> params;
    private String contentBody;
    private MojioClient.ResponseListener<T> listener;
    private boolean blobContent = false;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url   URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     */
    public MojioRequest(Context appContext,
                        int method,
                        String url,
                        Class<T> clazz,
                        MojioClient.ResponseListener<T> listener) {
        super(method, url, listener);
        commonInit(appContext, clazz, null, null, listener);
    }

    /**
     * Make a POST request and return a parsed object from JSON.
     *
     * @param url   URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     */
    public MojioRequest(Context appContext,
                        int method,
                        String url,
                        Class<T> clazz,
                        Map<String, String> params,
                        MojioClient.ResponseListener<T> listener) {
        super(method, url, listener);
        commonInit(appContext, clazz, params, null, listener);
    }

    public MojioRequest(Context appContext,
                        int method,
                        String url,
                        Class<T> clazz,
                        String contentBody,
                        MojioClient.ResponseListener<T> listener) {
        super(method, url, listener);
        commonInit(appContext, clazz, params, contentBody, listener);
    }

    public MojioRequest(Context appContext,
                        int method,
                        String url,
                        Class<T> clazz,
                        Bitmap contentBody,
                        MojioClient.ResponseListener<T> listener) {
        super(method, url, listener);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        contentBody.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        this.blobContent = true;
        commonInit(appContext, clazz, null, imageEncoded, listener);
    }

    private void commonInit(Context appContext,
                            Class<T> clazz,
                            Map<String, String> params,
                            String contentBody,
                            MojioClient.ResponseListener<T> listener) {
        // TODO we could avoid holding context by passing headers and locale here instead
        this.mAppContext = appContext;
        this.clazz = clazz;
        this.params = params;
        this.contentBody = contentBody;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        DataStorageHelper oauth = new DataStorageHelper(this.mAppContext);
        HashMap<String, String> headers = new HashMap<>();
        headers.putAll(super.getHeaders());
        String mojioAuth = oauth.getAccessToken();
        if (mojioAuth != null) {
            headers.put("MojioAPIToken", mojioAuth);
        }

        // TODO would it hurt to always specify these headers if contentBody isn't empty?
        if (blobContent) {
            String body = String.format("\"%s\"", this.contentBody);
            headers.put("Content-Type", PROTOCOL_CONTENT_TYPE);
            headers.put("Content-Length", String.valueOf(body.length()));
        }

        // Client locale header
        headers.put("Accept-Language", getLocaleString(this.mAppContext));
        Log.i(TAG, "Adding headers: " + headers.toString());
        return headers;
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        // If content body given, use it.
        if (contentBody == null) {
            return super.getBody();
        } else {
            String body = this.contentBody;
            if (!body.startsWith("{")) {
                body = String.format("\"%s\"", this.contentBody); // Add quotes around body
            }
            Log.i(TAG, "Adding content body: " + body);
            return body.getBytes();
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    protected void deliverResponse(MojioResponse<T> response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<MojioResponse<T>> parseNetworkResponse(NetworkResponse response) {
        try {
            MojioResponse<T> result = null;
            String responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.v(TAG, "Response [" + VolleyHelper.parseMethodString(getMethod()) + " " + getUrl() + "]: " + responseString.replace("\n", ""));

            if (responseString.isEmpty()) {
                result = new MojioResponse<>();
                // Body was empty, no need to parse.
            } else if (this.clazz == String.class) {
                // If we want just a String, simply return the response.data casted as such.
                result = (MojioResponse<T>) new MojioResponse<>(responseString);
            } else {
                // this handles responses that wrap the data and have a Data element
                // TODO constructing a JSONObject just to throw it away - is there a more efficient way?
                JSONObject testObject = new JSONObject(responseString);
                if (testObject.has("Data")) {
                    // result contains Data object (array), parse as a MojioRequest directly
                    Type typeToken = new TypeToken<MojioRequest<T>>() {}.getType();
                    result = MojioClient.getGson().fromJson(responseString, typeToken);
                } else {
                    // result does not contain the Data object - assumed to be a single result.
                    result = new MojioResponse<>(MojioClient.getGson().fromJson(responseString, clazz));
                }
            }

            result.setStatusCode(response.statusCode);
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            Log.e(TAG, "Error parsing network response", e);
            return Response.error(new ParseError(e));
        }
    }

    /**
     * @return a locale string in the format xx-XX or xx only if XX is not available
     */
    private String getLocaleString(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        String country = locale.getCountry();
        return TextUtils.isEmpty(country) ? language : String.format("%s-%s", language, country);
    }

}

