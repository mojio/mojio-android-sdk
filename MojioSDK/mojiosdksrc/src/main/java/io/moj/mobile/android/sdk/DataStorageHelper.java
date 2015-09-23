package io.moj.mobile.android.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class DataStorageHelper {

    private static final String TAG = DataStorageHelper.class.getSimpleName();
    private static String SHARED_PREF_ID = "mojioauthsdk";

    private static String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";
    private static String PREF_ACCESS_TOKEN_EXPIRES = "PREF_ACCESS_TOKEN_EXPIRES";
    private static String PREF_ACCESS_TOKEN_IS_USER = "PREF_ACCESS_TOKEN_IS_USER";
    private static String PREF_APP_ENDPOINT = "PREF_APP_ENDPOINT";

    // refresh the access token when we have less than 1 minute left
    private static final long TOKEN_REFRESH_MS = 60000;

    private Context mContext;

    public DataStorageHelper(Context context) {
        mContext = context;
    }

    public String getAccessToken() {
        return getSharedPreferences().getString(PREF_ACCESS_TOKEN, null);
    }

    @SuppressLint("CommitPrefEdits")
    public void setAccessToken(String accessToken, String expirationTime, boolean isUserToken) {
        long expirationTimestamp = TimeFormatHelpers.fromServerFormatted(expirationTime).getMillis();

        Editor sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE).edit();
        sharedPreferences.putString(PREF_ACCESS_TOKEN, accessToken);
        sharedPreferences.putLong(PREF_ACCESS_TOKEN_EXPIRES, expirationTimestamp);
        sharedPreferences.putBoolean(PREF_ACCESS_TOKEN_IS_USER, isUserToken);
        sharedPreferences.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public void setAccessToken(String accessToken, String expirationTime) {
        setAccessToken(accessToken, expirationTime, true);
    }

    public boolean shouldRefreshAccessToken() {
        long expirationTimestamp;
        try {
            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE);
            expirationTimestamp = sharedPreferences.getLong(PREF_ACCESS_TOKEN_EXPIRES, 0);
        } catch (ClassCastException e) {
            // this can happen if someone updated from an old version of the app whose shared prefs
            // stored this differently
            Log.w(TAG, PREF_ACCESS_TOKEN_EXPIRES + " was of an unexpected type", e);
            expirationTimestamp = 0;
        }
        long msToExpiration = expirationTimestamp - System.currentTimeMillis();
        Log.d(TAG, "Access token expires in: " + msToExpiration + "ms");
        return msToExpiration < TOKEN_REFRESH_MS;
    }

    public boolean isUserToken() {
        return getSharedPreferences().getBoolean(PREF_ACCESS_TOKEN_IS_USER, false);
    }

    /**
     * Saves the end point with which the app token is valid.
     *
     * @param url The end point URL.
     */
    public void setEndpoint(String url) {
        getSharedPreferences().edit().putString(PREF_APP_ENDPOINT, url).commit();
    }

    /**
     * @return The end point with which the app token is valid.
     */
    public String getEndpoint() {
        return getSharedPreferences().getString(PREF_APP_ENDPOINT, null);
    }

    //========================================================================================
    // Storage methods
    //========================================================================================
    private SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE);
    }

    @SuppressLint("CommitPrefEdits")
    public void removeAllUserStoredValues() {
        getSharedPreferences().edit()
                .remove(PREF_ACCESS_TOKEN)
                .remove(PREF_ACCESS_TOKEN_EXPIRES)
                .commit();
    }

}
