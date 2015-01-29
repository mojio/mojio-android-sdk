package com.TTT.MojioSDKSource.OauthHelper;

import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class OauthHelper {
	
	Context mContext;
	
	public OauthHelper(Context context){
		
		mContext = context;
	}
	
	private static String SHARED_PREF_ID = "mojioauthsdk";
	private static String PREF_ACCESS_TOKEN = "accesstoken";
	private static String PREF_TOKEN_EXPIRES = "tokenexpires";
	
	
	public void SetAccessToken(String url){
		
		String[] stringArray = url.split("&");
		String accessTokenString = stringArray[0];
		String[] tempArray = accessTokenString.split("=");
		String accessToken = tempArray[1];
		Log.e("TESTING", "THE ACCESS TOKEN IS: " + accessToken);
		setSharedPreference(PREF_ACCESS_TOKEN, accessToken);
		
	}
	
	public void SetExpireTime(String url){
		String[] stringArray = url.split("&");
		String expireTimeString = stringArray[2];
		String[] tempArray = expireTimeString.split("=");
		String expireTime = tempArray[1];
		Log.e("TESTING", "THE EXPIRES TIME IS: " + expireTime);
		Calendar calendar = Calendar.getInstance(); 
		calendar.add(Calendar.SECOND, Integer.parseInt(expireTime));		
		setSharedPreference(PREF_TOKEN_EXPIRES, calendar.getTimeInMillis());
	}
	
	public String GetAccessToken(){
		if(ShouldRefreshAccessToken()){
			return null;
		}else{
			return getSharedPreferenceString(PREF_ACCESS_TOKEN);
		}
	}
	
	private boolean ShouldRefreshAccessToken(){
		Calendar calendar = Calendar.getInstance();
		long currentTimeMilli = calendar.getTimeInMillis();
		long expiresInMilli = getSharedPreferenceLong(PREF_TOKEN_EXPIRES);
		long timeRemaining = expiresInMilli - currentTimeMilli;
		if(timeRemaining > 0){
			return false;
		}else{
			return true;
		}
	}
	
	
	
	//========================================================================================
	// Storage methods
	//========================================================================================
	private String getSharedPreferenceString(String key) {
	SharedPreferences sharedPreferences =  mContext.getSharedPreferences(SHARED_PREF_ID, Context.MODE_PRIVATE);
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
