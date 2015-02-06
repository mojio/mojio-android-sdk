package com.mojio.mojiosdk.networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mojio.mojiosdk.DataStorageHelper;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Writen by Shayla Sawchenko
 * Based on GsonVolleyRequest by Ognyan Bankov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
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

    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private Context mAppContext;
    private Gson mGson = new Gson();
    private Class<T> clazz;
    private Map<String, String> params;
    private String contentBody;
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
        // Error listener?

        Log.i("MOJIO", "Creating request for " + this.mUrl);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        DataStorageHelper oauth = new DataStorageHelper(this.mAppContext);  // TODO move out of here
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.putAll(super.getHeaders());
        headers.put("MojioAPIToken", oauth.GetAccessToken());
        Log.i("MOJIO", "Adding headers: " + headers.toString());
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
        }
        else {
            //String body = this.contentBody;
            String body = String.format("\"%s\"", this.contentBody); // Add quotes around body
            Log.i("MOJIO", "Adding content body: " + body);
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
            // If putting an entity, result will be empty.
            if ((this.mMethod == Method.PUT)
                    || (this.mMethod == Method.DELETE)
                    || (this.mMethod == Method.POST)) {
                // Response body will be empty, no need to parse.
                return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
            }

            // If we want just a String, simply return the response.data casted as such.
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.i("MOJIO", "Response for " + mUrl);
            Log.i("MOJIO", json);

            if (this.clazz == String.class) {
                result = (T)json;
                return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
            }

            // If here, attempt to parse response into the desired class.
            // Parse into small test object first to determine if we have an array of objects, or
            // just a single object.
            JSONObject testObject = new JSONObject(json);

            if (testObject.has("Data")) {
                // Result contains Data object (array).
                result = mGson.fromJson(testObject.getString("Data"), clazz);
            } else {
                // Result does not contain the Data object - assumed to be a single result.
                result = mGson.fromJson(json, clazz);
            }

            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));

        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));

        } catch (JSONException e) {
            return Response.error(new ParseError(e));

        }
    }
}

