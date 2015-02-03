package com.mojio.mojiosdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import java.util.Calendar;

public class DataStorageHelper {

    private static String SHARED_PREF_ID = "mojioauthsdk";
    private static String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";
    private static String PREF_TOKEN_EXPIRES = "PREF_TOKEN_EXPIRES";

    private Context mContext;

    public DataStorageHelper(Context context) {
        mContext = context;
    }

    public void SetAccessToken(String accessToken) {
        Log.i("MOJIO", "THE ACCESS TOKEN IS: " + accessToken);
        setSharedPreference(PREF_ACCESS_TOKEN, accessToken);

    }

    public void SetExpireTime(String expireTime) {
        Log.i("MOJIO", "THE EXPIRES TIME IS: " + expireTime);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, Integer.parseInt(expireTime));
        setSharedPreference(PREF_TOKEN_EXPIRES, calendar.getTimeInMillis());
    }

    // TODO rename
    public String GetAccessToken() {
        if (ShouldRefreshAccessToken()) {
            return null;
        } else {
            return getSharedPreferenceString(PREF_ACCESS_TOKEN);
        }
    }

    public boolean ShouldRefreshAccessToken() {
        Calendar calendar = Calendar.getInstance();
        long currentTimeMilli = calendar.getTimeInMillis();
        long expiresInMilli = getSharedPreferenceLong(PREF_TOKEN_EXPIRES);
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

}
