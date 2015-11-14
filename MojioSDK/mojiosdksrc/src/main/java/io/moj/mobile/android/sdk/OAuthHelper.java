package io.moj.mobile.android.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import io.moj.mobile.android.sdk.enums.Environment;

/**
 * Helper class for storing user authentication data.
 */
public class OAuthHelper {

    private static final String TAG = OAuthHelper.class.getSimpleName();
    private static final String SHARED_PREF_ID = "mojioauthsdk";

    private static final String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";
    private static final String PREF_ACCESS_TOKEN_EXPIRES = "PREF_ACCESS_TOKEN_EXPIRES";
    private static final String PREF_ACCESS_TOKEN_IS_USER = "PREF_ACCESS_TOKEN_IS_USER";
    private static final String PREF_ACCESS_TOKEN_ENVIRONMENT = "PREF_ACCESS_TOKEN_ENVIRONMENT";

    // refresh the access token when we have less than 1 minute left
    private static final long TOKEN_REFRESH_MS = 60000;

    private Context mContext;

    public OAuthHelper(Context context) {
        mContext = context;
    }

    public String getAccessToken() {
        return getSharedPreferences().getString(PREF_ACCESS_TOKEN, null);
    }

    public void setAccessToken(String accessToken, String expirationTime, Environment environment) {
        setAccessToken(accessToken, expirationTime, environment, true);
    }

    @SuppressLint("CommitPrefEdits")
    public void setAccessToken(String accessToken, String expirationTime, Environment environment, boolean isUserToken) {
        long expirationTimestamp = TimeFormatHelpers.fromServerFormatted(expirationTime).getMillis();
        getSharedPreferences().edit()
                .putString(PREF_ACCESS_TOKEN, accessToken)
                .putLong(PREF_ACCESS_TOKEN_EXPIRES, expirationTimestamp)
                .putString(PREF_ACCESS_TOKEN_ENVIRONMENT, environment.name())
                .putBoolean(PREF_ACCESS_TOKEN_IS_USER, isUserToken)
                .commit();
    }

    public boolean shouldRefreshAccessToken() {
        long expirationTimestamp;
        try {
            expirationTimestamp = getSharedPreferences().getLong(PREF_ACCESS_TOKEN_EXPIRES, 0);
        } catch (ClassCastException e) {
            // this can happen if someone updated from an old version of the app whose shared prefs
            // stored this differently
            Log.w(TAG, PREF_ACCESS_TOKEN_EXPIRES + " was of an unexpected type", e);
            expirationTimestamp = 0;
        }
        long msToExpiration = expirationTimestamp - System.currentTimeMillis();
        Log.v(TAG, "Access token expires in: " + msToExpiration + "ms");
        return msToExpiration < TOKEN_REFRESH_MS;
    }

    /**
     * Returns whether the stored access token is for an authenticated user. This can be false if
     * the access token is for the application but not a specific user.
     * @return
     */
    public boolean isUserToken() {
        return getSharedPreferences().getBoolean(PREF_ACCESS_TOKEN_IS_USER, false);
    }

    /**
     * @return The environment with which the app token is valid.
     */
    public Environment getEnvironment() {
        String env = getSharedPreferences().getString(PREF_ACCESS_TOKEN_ENVIRONMENT, null);
        if (!TextUtils.isEmpty(env)) {
            try {
                return Environment.valueOf(env);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "Could not parse stored environment '" + env + "'");
            }
        }
        return null;
    }

    private SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE);
    }

    @SuppressLint("CommitPrefEdits")
    public void clear() {
        getSharedPreferences().edit()
                .remove(PREF_ACCESS_TOKEN)
                .remove(PREF_ACCESS_TOKEN_EXPIRES)
                .remove(PREF_ACCESS_TOKEN_IS_USER)
                .remove(PREF_ACCESS_TOKEN_ENVIRONMENT)
                .commit();
    }

}
