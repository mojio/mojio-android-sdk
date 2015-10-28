package io.moj.mobile.android.sdk.networking;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import io.moj.mobile.android.sdk.OAuthHelper;
import io.moj.mobile.android.sdk.MojioClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jian on 24/02/2015.
 */
public class MojioImageRequest extends ImageRequest {

    private static final String TAG = MojioImageRequest.class.getSimpleName();
    private Context context;

    /**
     * Creates a new image request, decoding to a maximum specified width and
     * height. If both width and height are zero, the image will be decoded to
     * its natural size. If one of the two is nonzero, that dimension will be
     * clamped and the other one will be set to preserve the image's aspect
     * ratio. If both width and height are nonzero, the image will be decoded to
     * be fit in the rectangle of dimensions width x height while keeping its
     * aspect ratio.
     *
     * @param url           URL of the image
     * @param listener      Listener to receive the decoded bitmap
     * @param maxWidth      Maximum width to decode this bitmap to, or zero for none
     * @param maxHeight     Maximum height to decode this bitmap to, or zero for
     *                      none
     * @param decodeConfig  Format to decode the bitmap to
     * @param errorListener Error listener, or null to ignore errors
     */
    public MojioImageRequest(Context context,
                             String url,
                             int maxWidth, int maxHeight,
                             Bitmap.Config decodeConfig,
                             final MojioClient.ResponseListener<Bitmap> listener) {
        super(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                listener.onResponse(new MojioResponse<>(response));
            }
        }, maxWidth, maxHeight, decodeConfig, listener);
        // TODO we could avoid holding context by just passing headers in here instead
        this.context = context;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        OAuthHelper oauth = new OAuthHelper(context);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.putAll(super.getHeaders());

        // Check for auth token
        // Start with user auth, if that does not exist, check for app auth
        String mojioAuth = oauth.getAccessToken();
        if (mojioAuth != null) {
            headers.put("MojioAPIToken", mojioAuth);
        }

        // TODO may want to init MojioClient WITH access token. This would make the app responsible for storing token data.
        Log.i(TAG, "Adding headers: " + headers.toString());
        return headers;
    }
}
