package com.mojio.mojiosdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

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

    private Context mContext;

    public DataStorageHelper(Context context) {
        mContext = context;
    }

    //=======================================================
    // User
    //=======================================================
    public void SetAccessToken(String accessToken) {
        Log.i("MOJIO", "THE ACCESS TOKEN IS: " + accessToken);
        setSharedPreference(PREF_ACCESS_TOKEN, accessToken);
    }

    public void SetAccessExpireTime(String expireTime) {
        SetExpireTime(expireTime, PREF_ACCESS_TOKEN_EXPIRES);
    }

    public String GetAccessToken() {
        if (ShouldRefreshToken(PREF_ACCESS_TOKEN_EXPIRES)) {
            return null;
        } else {
            return getSharedPreferenceString(PREF_ACCESS_TOKEN);
        }
    }

    public boolean ShouldRefreshAccessToken() {
        return ShouldRefreshToken(PREF_ACCESS_TOKEN);
    }

    //=======================================================
    // App
    //=======================================================
    public void SetAppToken(String accessToken) {
        Log.i("MOJIO", "THE ACCESS TOKEN IS: " + accessToken);
        setSharedPreference(PREF_APP_TOKEN, accessToken);
    }

    public void SetAppExpireTime(String expireTime) {
        SetExpireTime(expireTime, PREF_APP_EXPIRES);
    }

    public String GetAppToken() {
        if (ShouldRefreshToken(PREF_APP_EXPIRES)) {
            return null;
        } else {
            return getSharedPreferenceString(PREF_APP_TOKEN);
        }
    }

    //=======================================================
    // Helpers
    //=======================================================
    private void SetExpireTime(String expireTime, String pref_const) {
        Log.i("MOJIO", "THE EXPIRES TIME IS: " + expireTime);

        long expire;
        DateTime dt = TimeFormatHelpers.fromServerFormatted(expireTime);
        if (dt == null) {
            // Web oauth login result
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, Integer.parseInt(expireTime));
            expire = calendar.getTimeInMillis();
        }
        else {
            // Direct API login result
            expire = dt.getMillis();
        }

        setSharedPreference(pref_const, expire);
    }

    private boolean ShouldRefreshToken(String pref_const) {
        Calendar calendar = Calendar.getInstance();
        long currentTimeMilli = calendar.getTimeInMillis();
        long expiresInMilli = getSharedPreferenceLong(pref_const);
        long timeRemaining = expiresInMilli - currentTimeMilli;
        if (timeRemaining > 0) {
            return false;
        } else {
            return true;
        }
    }

    //========================================================================================
    // Storage methods
    //========================================================================================
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

    /*
    public void removeAllStoredValues() {
        Editor sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE).edit();
        sharedPreferences.clear().commit();
    }
    */

    public void removeAllUserStoredValues() {
        Editor sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE).edit();
        sharedPreferences.remove(PREF_ACCESS_TOKEN).remove(PREF_ACCESS_TOKEN_EXPIRES).commit();
    }

}
