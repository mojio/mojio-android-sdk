package io.moj.mobile.android.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.joda.time.DateTime;

import java.util.Calendar;

public class DataStorageHelper {

    private static String SHARED_PREF_ID = "mojioauthsdk";

    // User auth
    private static String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";
    private static String PREF_ACCESS_TOKEN_EXPIRES = "PREF_TOKEN_EXPIRES";

    // App auth
    private static String PREF_APP_TOKEN = "PREF_APP_TOKEN";
    private static String PREF_APP_EXPIRES = "PREF_APP_EXPIRES";

    // Endpoint
    private static String PREF_APP_ENDPOINT = "PREF_APP_ENDPOINT";

    private Context mContext;

    public DataStorageHelper(Context context) {
        mContext = context;
    }

    public void setAccessToken(String accessToken) {
        setSharedPreference(PREF_ACCESS_TOKEN, accessToken);
    }

    public void setAccessTokenExpiration(String expireTime) {
        // TODO why isn't this doing anything!? Really we should force storing the expiration in same method as storing the token
//        setExpirationTime(expireTime, PREF_ACCESS_TOKEN_EXPIRES);
    }

    public String getAccessToken() {
        return getSharedPreferenceString(PREF_ACCESS_TOKEN);
    }

    public boolean shouldRefreshAccessToken() {
        return shouldRefreshToken(PREF_ACCESS_TOKEN_EXPIRES);
    }

    public void setAppToken(String appToken) {
        setSharedPreference(PREF_APP_TOKEN, appToken);
    }

    public void setAppTokenExpiration(String expireTime) {
        // TODO why isn't this doing anything!? Really we should force storing the expiration in same method as storing the token
//        setExpirationTime(expireTime, PREF_APP_EXPIRES);
    }

    public String getAppToken() {
        return getSharedPreferenceString(PREF_APP_TOKEN);
    }

    /**
     * Saves the end point with which the app token is valid.
     *
     * @param url The end point URL.
     */
    public void setEndpoint(String url) {
        setSharedPreference(PREF_APP_ENDPOINT, url);
    }

    /**
     * @return The end point with which the app token is valid.
     */
    public String getEndpoint() {
        return getSharedPreferenceString(PREF_APP_ENDPOINT);
    }

    private void setExpirationTime(String expireTime, String prefKey) {
        long expire;
        // TODO try to use SimpleDateFormat instead to remove heavy JodaTime dependency
        DateTime dt = TimeFormatHelpers.fromServerFormatted(expireTime);
        if (dt == null) {
            // Web oauth login result
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, Integer.parseInt(expireTime));
            expire = calendar.getTimeInMillis();
        } else {
            // Direct API login result
            expire = dt.getMillis();
        }
        setSharedPreference(prefKey, expire);
    }

    private boolean shouldRefreshToken(String pref_const) {
        Calendar calendar = Calendar.getInstance();
        long currentTimeMilli = calendar.getTimeInMillis();
        long expiresInMilli = getSharedPreferenceLong(pref_const);
        return (expiresInMilli - currentTimeMilli) > 0;
    }

    private String getSharedPreferenceString(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    private long getSharedPreferenceLong(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, Integer.MIN_VALUE);
    }

    private void setSharedPreference(String key, String value) {
        Editor sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE).edit();
        sharedPreferences.putString(key, value);
        sharedPreferences.commit();
    }

    private void setSharedPreference(String key, long value) {
        Editor sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE).edit();
        sharedPreferences.putLong(key, value);
        sharedPreferences.commit();
    }

    public void clearAppToken() {
        Editor sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE).edit();
        sharedPreferences.remove(PREF_APP_TOKEN).commit();
    }

    public void removeAllUserStoredValues() {
        Editor sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE).edit();
        sharedPreferences.remove(PREF_ACCESS_TOKEN).remove(PREF_ACCESS_TOKEN_EXPIRES).commit();
    }

}
