package io.moj.mobile.android.sdk.rest;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.moj.mobile.android.sdk.rest.networking.MojioCallback;
import io.moj.mobile.android.sdk.rest.networking.MojioGlobalCallback;
import io.moj.mobile.android.sdk.rest.networking.MojioRequest;
import io.moj.mobile.android.sdk.rest.networking.OkHttp3Stack;
import io.moj.mobile.android.sdk.rest.util.DeviceUtils;

/**
 * Fluent API for the Mojio SDK.
 * Created by mhorie on 2016-01-18.
 */
@SuppressWarnings("unused")
public class MojioClient {

    public static final String TAG = MojioClient.class.getSimpleName();

    public static final String PARAM_TOP = "top";
    public static final String PARAM_SKIP = "skip";
    public static final String PARAM_FILTER = "filter";
    public static final String PARAM_SELECT = "select";
    public static final String PARAM_ORDERBY = "orderby";

    private static final String PATH_APPS = "apps";
    private static final String PATH_GROUPS = "groups";
    private static final String PATH_USERS = "users";
    private static final String PATH_TRIPS = "trips";
    private static final String PATH_VEHICLES = "vehicles";
    private static final String PATH_MOJIOS = "mojios";
    private static final String PATH_STATES = "states";
    private static final String PATH_LOCATIONS = "locations";
    private static final String PATH_PERMISSION = "permission";
    private static final String PATH_PERMISSIONS = "permissions";
    private static final String PATH_TAGS = "tags";
    private static final String PATH_ADDRESS = "address";
    private static final String PATH_VIN = "vin";
    private static final String PATH_SERVICE_SCHEDULE = "serviceschedule";
    private static final String PATH_ME = "me";
    private static final String PATH_IMAGE = "image";
    private static final String PATH_HISTORY = "history";
    private static final String PATH_SECRET = "secret";
    private static final String PATH_NEXT = "next";

    private static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    private static final String HEADER_API_TOKEN = "MojioAPIToken";
    private static final int SOCKET_TIMEOUT_MS = 5000;
    private static final DefaultRetryPolicy RETRY_POLICY = new DefaultRetryPolicy(
            SOCKET_TIMEOUT_MS,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    private Context context;
    private Environment environment;
    private RequestQueue requestQueue;
    private Gson gson;
    private Map<String, String> headers;
    private Map<Class, MojioGlobalCallback> globalCallbacks = new HashMap<>();

    private MojioClient(Context context, Environment environment) {
        this.context = context.getApplicationContext();
        this.environment = environment;
        this.headers = new HashMap<>();
        this.headers.put(HEADER_ACCEPT_LANGUAGE, DeviceUtils.getLocaleString(context));
    }

    public static MojioClient with(Context context) {
        return with(context, Environment.getDefault(context));
    }

    public static MojioClient with(Context context, Environment environment) {
        return new MojioClient(context, environment);
    }

    public MojioClient withEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }

    public MojioClient withRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
        return this;
    }

    public MojioClient withGson(Gson gson) {
        this.gson = gson;
        return this;
    }

    public MojioClient withAuthentication(String authToken) {
        Map<String, String> headers = new HashMap<>(2);
        headers.putAll(this.headers);
        headers.put(HEADER_API_TOKEN, authToken);
        this.headers = Collections.unmodifiableMap(headers);
        return this;
    }

    /**
     * Attaches a global callback that will always be executed before an individual request's callback.
     * This is useful for persisting or caching the record locally.
     * @param responseClass the class the response should be parsed into
     * @param callback callback to receive the response with
     * @return this {@link MojioClient} instance for chaining calls
     */
    public <T> MojioClient withGlobalCallback(Class<T> responseClass, MojioGlobalCallback<T> callback) {
        this.globalCallbacks.put(responseClass, callback);
        return this;
    }

    public GetRequestBuilder get() {
        return new GetRequestBuilder();
    }

    public PostRequestBuilder post() {
        return new PostRequestBuilder();
    }

    public PutRequestBuilder put() {
        return new PutRequestBuilder();
    }

    public DeleteRequestBuilder delete() {
        return new DeleteRequestBuilder();
    }

    public Environment getEnvironment() {
        return environment;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null)
            initRequestQueue();
        return requestQueue;
    }

    private Gson getGson() {
        if (gson == null)
            initGson();
        return gson;
    }

    private synchronized void initRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context, new OkHttp3Stack());
    }

    private synchronized void initGson() {
        if (gson == null)
            gson = new Gson();
    }

    /**
     * Adds the specified request to the queue, with the MojioClient tag.
     *
     * @param request the request to be enqueued
     */
    private <T> void enqueue(MojioRequest<T> request) {
        request.setTag(TAG);
        request.setRetryPolicy(RETRY_POLICY);
        request.logRequest();
        getRequestQueue().add(request);
    }

    public abstract class RequestBuilder {
        protected final int method;
        protected final Uri.Builder url;

        public RequestBuilder(int method) {
            this.method = method;
            this.url = Uri.parse(environment.getApiUrl()).buildUpon();
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder apps(String id) {
            return appendEntity(PATH_APPS, id);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder apps() {
            return appendEntity(PATH_APPS);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder groups(String id) {
            return appendEntity(PATH_GROUPS, id);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder groups() {
            return appendEntity(PATH_GROUPS);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder users(String id) {
            return appendEntity(PATH_USERS, id);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder users() {
            return appendEntity(PATH_USERS);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder trips(String id) {
            return appendEntity(PATH_TRIPS, id);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder trips() {
            return appendEntity(PATH_TRIPS);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder vehicles(String id) {
            return appendEntity(PATH_VEHICLES, id);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder vehicles() {
            return appendEntity(PATH_VEHICLES);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder mojios(String id) {
            return appendEntity(PATH_MOJIOS, id);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder mojios() {
            return appendEntity(PATH_MOJIOS);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder states() {
            return appendEntity(PATH_STATES);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder locations() {
            return appendEntity(PATH_LOCATIONS);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder permission() {
            return appendEntity(PATH_PERMISSION);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder permissions() {
            return appendEntity(PATH_PERMISSIONS);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder tags(String tag) {
            return appendEntity(PATH_TAGS, tag);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder address() {
            return appendEntity(PATH_ADDRESS);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder vin() {
            return appendEntity(PATH_VIN);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder serviceSchedule() {
            return appendEntity(PATH_SERVICE_SCHEDULE);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder me() {
            return appendEntity(PATH_ME);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder image() {
            return appendEntity(PATH_IMAGE);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder history() {
            return appendEntity(PATH_HISTORY);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder secret() {
            return appendEntity(PATH_SECRET);
        }

        @SuppressWarnings("unchecked")
        public RequestBuilder next() {
            return appendEntity(PATH_NEXT);
        }

        private RequestBuilder appendEntity(@NonNull String path) {
            return appendEntity(path, null);
        }

        private RequestBuilder appendEntity(@NonNull String path, String id) {
            this.url.appendPath(path);
            if (!TextUtils.isEmpty(id)) {
                this.url.appendPath(id);
            }
            return this;
        }

        protected abstract <T> MojioRequest build(Class<T> responseClass, MojioCallback<T> callback);

        @SuppressWarnings("unchecked")
        public <T> void callback(Class<T> responseClass, MojioCallback<T> callback) {
            callback(responseClass, callback, null);
        }

        @SuppressWarnings("unchecked")
        public <T> void callback(Class<T> responseClass, MojioCallback<T> callback, RetryPolicy retryPolicy) {
            MojioRequest<T> request = build(responseClass, callback);
            request.setGlobalCallback(globalCallbacks.get(responseClass));
            if (retryPolicy != null)
                request.setRetryPolicy(retryPolicy);
            enqueue(request);
        }

        public void execute() {
            callback(null, null);
        }
    }

    public class GetRequestBuilder extends RequestBuilder {
        public GetRequestBuilder() {
            super(Request.Method.GET);
        }

        /**
         * Specify how many records to limit the output.
         * @param count the maximum number of records to return.
         * @return this {@link GetRequestBuilder} builder for chaining calls
         */
        public GetRequestBuilder top(int count) {
            if (count < 0)
                throw new IllegalArgumentException("Cannot limit to a negative amount of records");
            url.appendQueryParameter(PARAM_TOP, String.valueOf(count));
            return this;
        }

        /**
         * Specify the number of records to skip when listing. Used for pagination.
         * @param skip the number of records to skip
         * @return this {@link GetRequestBuilder} builder for chaining calls
         */
        public GetRequestBuilder skip(int skip) {
            if (skip < 0)
                throw new IllegalArgumentException("Cannot skip a negative amount of pages");
            url.appendQueryParameter(PARAM_SKIP, String.valueOf(skip));
            return this;
        }

        /**
         * Specifies an expression or function that must evaluate to true for a record to be returned
         * in the collection.
         * @param filter expression or function that must evaluate to true for a record to be
         *               returned in the collection.
         * @return this {@link GetRequestBuilder} builder for chaining calls
         */
        public GetRequestBuilder filter(String filter) {
            url.appendQueryParameter(PARAM_FILTER, filter);
            return this;
        }

        /**
         * Specify which fields will be returned by the query.
         * @param select the fields that should be included in the response
         * @return this {@link GetRequestBuilder} builder for chaining calls
         */
        public GetRequestBuilder select(String select) {
            url.appendQueryParameter(PARAM_SELECT, select);
            return this;
        }

        /**
         * Specify which field to sort the records on.
         * @param orderBy the field by which the results should be sorted
         * @return this {@link GetRequestBuilder} builder for chaining calls
         */
        public GetRequestBuilder orderBy(String orderBy) {
            url.appendQueryParameter(PARAM_ORDERBY, orderBy);
            return this;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected <T> MojioRequest build(Class<T> responseClass, MojioCallback<T> callback) {
            return new MojioRequest<>(getGson(), method, url.build(), headers, responseClass,
                    callback);
        }
    }

    public class UpdateRequestBuilder extends RequestBuilder {
        private String body;

        private UpdateRequestBuilder(int method) {
            super(method);
        }

        /**
         * Sets the request's body.
         * @param jsonBody the body in JSON
         * @return this {@link UpdateRequestBuilder} for chaining calls.
         */
        public UpdateRequestBuilder body(JSONObject jsonBody) {
            this.body = jsonBody.toString();
            return this;
        }

        /**
         * Sets the request's body
         * @param entity the updated entity
         * @return this {@link UpdateRequestBuilder} for chaining calls.
         */
        public <T> UpdateRequestBuilder body(T entity) {
            this.body = getGson().toJson(entity);
            return this;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected <T> MojioRequest build(Class<T> responseClass, MojioCallback<T> callback) {
            return new MojioRequest<>(getGson(), method, url.build(), body, headers,
                    responseClass, callback);
        }
    }

    public class PostRequestBuilder extends UpdateRequestBuilder {
        private PostRequestBuilder() {
            super(Request.Method.POST);
        }
    }

    public class PutRequestBuilder extends UpdateRequestBuilder {
        private PutRequestBuilder() {
            super(Request.Method.PUT);
        }
    }

    public class DeleteRequestBuilder extends UpdateRequestBuilder {
        private DeleteRequestBuilder() {
            super(Request.Method.DELETE);
        }
    }
}