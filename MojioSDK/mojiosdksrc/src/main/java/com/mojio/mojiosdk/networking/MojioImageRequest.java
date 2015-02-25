package com.mojio.mojiosdk.networking;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.mojio.mojiosdk.DataStorageHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jian on 24/02/2015.
 */
public class MojioImageRequest extends ImageRequest{

    private Context mAppContext;
    private String mUrl;
    private Response.Listener<Bitmap> listener;
    private int mMaxWidth, mMaxHeight;
    private Bitmap.Config mDecodeConfig;
    private Response.ErrorListener errorListener;

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
    public MojioImageRequest(Context appContext,
                             String url,
                             int maxWidth, int maxHeight,
                             Bitmap.Config decodeConfig,
                             Response.Listener<Bitmap> listener,
                             Response.ErrorListener errorListener) {
        super(url, listener, maxWidth, maxHeight, decodeConfig, errorListener);
        commonInit(appContext, url, listener, maxWidth, maxHeight, decodeConfig, errorListener);

    }

    private void commonInit(Context appContext,
                            String url,
                       Response.Listener<Bitmap> listener,
                       int maxWidth, int maxHeight,
                       Bitmap.Config decodeConfig,
                       Response.ErrorListener errorListener){

        this.mAppContext = appContext;
        this.mUrl = url;
        this.listener = listener;
        this.mMaxWidth = maxWidth;
        this.mMaxHeight = maxHeight;
        this.mDecodeConfig = decodeConfig;
        this.errorListener = errorListener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        DataStorageHelper oauth = new DataStorageHelper(this.mAppContext);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.putAll(super.getHeaders());

        // Check for auth token
        // Start with user auth, if that does not exist, check for app auth
        String mojioAuth = oauth.GetAccessToken();
        if (mojioAuth == null) {
            mojioAuth = oauth.GetAppToken();
        }
        if (mojioAuth != null) {
            headers.put("MojioAPIToken", mojioAuth);
        }

        // TODO may want to init MojioClient WITH access token. This would make the app responsible for storing token data.
        Log.i("MOJIO", "Adding headers: " + headers.toString());
        return headers;
    }
}
