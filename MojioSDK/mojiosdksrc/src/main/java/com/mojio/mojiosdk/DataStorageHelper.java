package com.mojio.mojiosdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import org.joda.time.DateTime;

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

    public String GetAccessToken() {
        if (ShouldRefreshAccessToken()) {
            return null;
        } else {
            return getSharedPreferenceString(PREF_ACCESS_TOKEN);
        }
    }

    public void SetExpireTime(String expireTime) {
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

        setSharedPreference(PREF_TOKEN_EXPIRES, expire);
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

    public void removeAllStoredValues() {
        Editor sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE).edit();
        sharedPreferences.clear().commit();
    }

}
