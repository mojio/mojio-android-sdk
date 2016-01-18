package io.moj.mobile.android.sdk.rest.networking;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Listener for callbacks for a Mojio API response.
 * Created by mhorie on 2016-01-15.
 */
public abstract class MojioCallback<T> implements Response.ErrorListener {

    public abstract void onSuccess(MojioResponse<T> response);

    public abstract void onFailure(MojioResponseError error);

    @Override
    public void onErrorResponse(VolleyError error) {
        onFailure(new MojioResponseError(error));
    }
}
