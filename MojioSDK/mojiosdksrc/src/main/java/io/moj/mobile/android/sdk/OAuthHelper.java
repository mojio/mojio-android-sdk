package io.moj.mobile.android.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import io.moj.mobile.android.sdk.enums.Environment;
import io.moj.mobile.android.sdk.models.Token;

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
    private static final String PREF_ACCESS_TOKEN_RECEIVED = "PREF_ACCESS_TOKEN_RECEIVED";

    private static final int REQUESTED_SESSION_LENGTH_MIN = 43829; // 1 month

    /**
     * The fraction of the current access token's session length at which the access token should
     * be refreshed.
     */
    private static final float SESSION_REFRESH_FACTOR = 0.5f;

    private Context context;

    public OAuthHelper(Context context) {
        this.context = context;
    }

    public String getAccessToken() {
        return getSharedPreferences().getString(PREF_ACCESS_TOKEN, null);
    }

    @SuppressLint("CommitPrefEdits")
    public void setAccessToken(Token token, Environment environment) {
        long expirationTimestamp = TimeFormatHelpers.fromServerFormatted(token.getValidUntil()).getMillis();
        getSharedPreferences().edit()
                .putString(PREF_ACCESS_TOKEN, token.getId())
                .putLong(PREF_ACCESS_TOKEN_EXPIRES, expirationTimestamp)
                .putLong(PREF_ACCESS_TOKEN_RECEIVED, System.currentTimeMillis())
                .putString(PREF_ACCESS_TOKEN_ENVIRONMENT, environment.name())
                .putBoolean(PREF_ACCESS_TOKEN_IS_USER, !TextUtils.isEmpty(token.getUserId()))
                .commit();
    }

    public long getMsToTokenExpiration() {
        long expirationTimestamp;
        try {
            expirationTimestamp = getSharedPreferences().getLong(PREF_ACCESS_TOKEN_EXPIRES, 0);
        } catch (ClassCastException e) {
            // this can happen if someone updated from an old version of the app whose shared prefs
            // stored this differently
            Log.w(TAG, PREF_ACCESS_TOKEN_EXPIRES + " was of an unexpected type", e);
            expirationTimestamp = 0;
        }
        return expirationTimestamp - System.currentTimeMillis();
    }

    public long getAccessTokenExpiresTimestamp() {
        return getSharedPreferences().getLong(PREF_ACCESS_TOKEN_EXPIRES, 0);
    }

    public long getAccessTokenReceivedTimestamp() {
        return getSharedPreferences().getLong(PREF_ACCESS_TOKEN_RECEIVED, 0);
    }

    public boolean shouldRefreshAccessToken() {
        long sessionLength = getAccessTokenExpiresTimestamp() - getAccessTokenReceivedTimestamp();
        long refreshThreshold = (long) (sessionLength * SESSION_REFRESH_FACTOR);
        return getMsToTokenExpiration() < refreshThreshold;
    }

    public boolean isTokenExpired() {
        return getMsToTokenExpiration() < 0;
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
        return context.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE);
    }

    /**
     * Returns the session length, in minutes, to be requested.
     * @return
     */
    public int getRequestedSessionLength() {
        return REQUESTED_SESSION_LENGTH_MIN;
    }

    @SuppressLint("CommitPrefEdits")
    public void clear() {
        getSharedPreferences().edit()
                .remove(PREF_ACCESS_TOKEN)
                .remove(PREF_ACCESS_TOKEN_EXPIRES)
                .remove(PREF_ACCESS_TOKEN_IS_USER)
                .remove(PREF_ACCESS_TOKEN_ENVIRONMENT)
                .remove(PREF_ACCESS_TOKEN_RECEIVED)
                .commit();
    }

}
