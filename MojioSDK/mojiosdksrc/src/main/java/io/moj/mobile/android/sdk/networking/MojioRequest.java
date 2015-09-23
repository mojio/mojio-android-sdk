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

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.moj.mobile.android.sdk.DataStorageHelper;

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
public class MojioRequest<T> extends Request<T> {

    private static final String TAG = MojioRequest.class.getSimpleName();
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private Context mAppContext;
    private Gson mGson = new Gson();
    private Class<T> clazz;
    private Map<String, String> params;
    private String contentBody;
    private byte[] imageByteArray;
    private Response.Listener<T> listener;
    private String mUrl;
    private int mMethod;

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
                        Response.Listener<T> listener,
                        Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        commonInit(appContext, method, url, clazz, listener, errorListener);
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
                        Response.Listener<T> listener,
                        Response.ErrorListener errorListener) {

        super(method, url, errorListener);
        commonInit(appContext, method, url, clazz, listener, errorListener);
        this.params = params;
    }

    public MojioRequest(Context appContext,
                        int method,
                        String url,
                        Class<T> clazz,
                        String contentBody,
                        Response.Listener<T> listener,
                        Response.ErrorListener errorListener) {

        super(method, url, errorListener);
        commonInit(appContext, method, url, clazz, listener, errorListener);
        this.contentBody = contentBody;
    }

    public MojioRequest(Context appContext,
                        int method,
                        String url,
                        Class<T> clazz,
                        Bitmap contentBody,
                        Response.Listener<T> listener,
                        Response.ErrorListener errorListener) {

        super(method, url, errorListener);
        commonInit(appContext, method, url, clazz, listener, errorListener);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        contentBody.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);


        this.imageByteArray = b;
        this.contentBody = imageEncoded;
    }

    private void commonInit(Context appContext,
                            int method,
                            String url,
                            Class<T> clazz,
                            Response.Listener<T> listener,
                            Response.ErrorListener errorListener) {
        this.mUrl = url;
        this.mAppContext = appContext;
        this.clazz = clazz;
        this.listener = listener;
        this.mMethod = method;
        mGson = new Gson();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        DataStorageHelper oauth = new DataStorageHelper(this.mAppContext);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.putAll(super.getHeaders());

        // Check for auth token
        // Start with user auth, if that does not exist, check for app auth
        String mojioAuth = oauth.getAccessToken();
        if (mojioAuth != null) {
            headers.put("MojioAPIToken", mojioAuth);
        }

        if (imageByteArray != null) {
            String body = String.format("\"%s\"", this.contentBody);
            headers.put("Content-Type", "application/json; charset=utf-8");
            headers.put("Content-Length", String.valueOf(body.length()));
        }

        // Client locale header
        headers.put("Accept-Language", getLocaleString(this.mAppContext));

        // TODO may want to init MojioClient WITH access token. This would make the app responsible for storing token data.
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
        if (this.contentBody == null) {
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
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            // Check result
            if (response.statusCode != HttpStatus.SC_OK) { // TODO is this the correct enum?
                // TODO Error.
            }

            T result = null;

            String responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.i(TAG, "Response [" + VolleyHelper.parseMethodString(getMethod()) + " " + mUrl + "]: " + responseString);
            if (responseString.isEmpty()) {
                // Body was empty, no need to parse.
                return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
            }

            // If we want just a String, simply return the response.data casted as such.
            if (this.clazz == String.class) {
                result = (T) responseString;
                return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
            }

            JSONObject testObject = new JSONObject(responseString);

            if (testObject.has("Data")) {
                // Result contains Data object (array).
                result = mGson.fromJson(testObject.getString("Data"), clazz);
            } else {
                // Result does not contain the Data object - assumed to be a single result.
                result = mGson.fromJson(responseString, clazz);
            }

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

        if (TextUtils.isEmpty(country))
            return language;
        else
            return String.format("%s-%s", language, country);
    }

}

