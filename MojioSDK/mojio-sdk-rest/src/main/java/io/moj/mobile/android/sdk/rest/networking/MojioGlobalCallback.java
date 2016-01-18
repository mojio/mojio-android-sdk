package io.moj.mobile.android.sdk.rest.networking;

/**
 * Callback object for intercepting Mojio API responses globally.
 * Created by skidson on 15-11-20.
 */
public class MojioGlobalCallback<T> {

    /**
     * Subclasses should override this to do work with the data before passing the response on down
     * to the original caller. It is the responsibility of the subclass to invoke
     * super.onSuccess(Uri, MojioResponse, MojioCallback) to continue the callback.
     * @param request the original request. Useful for determining the type of operation with {@link MojioRequest#getMethod()}
     * @param response the response
     * @param nextCallback the callback to be invoked afterwards
     */
    public void onSuccess(MojioRequest<T> request, MojioResponse<T> response, MojioCallback<T> nextCallback) {
        if (nextCallback != null)
            nextCallback.onSuccess(response);
    }
}
