package com.TTT.MojioSDKSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.TTT.MojioSDKSource.Models.Vehicle;
//import com.TTT.MojioSDKSource.MojioSDKSource.GetElementTask;
import com.TTT.MojioSDKSource.OauthHelper.OauthHelper;
import com.TTT.MojioSDKSource.Requests.LocationRequest;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;


	

public class MojioSDKSource_temp {
	private SpiceManager _contentManager = new SpiceManager(JacksonSpringAndroidSpiceService.class);
	private String _lastRequestCacheKey = null;
	
	public static void PutElement(String url, Context context){
		new PutElementTask(url, context).execute();
	} 
	
	{
	
	try {
		Class	c = Class.forName("test");
		Object yourObj = c.newInstance();
	} catch (Exception e)
	{
		
	}
	}
	private static class PutElementTask extends AsyncTask<Void, Void, Void>{
		
		String _url;
		Context _context;
		
		public PutElementTask(String url, Context context){
			_url = url;
			_context = context;
			
		}

		@Override
		protected Void doInBackground(Void... params) {

			try {

				OauthHelper oauthHelper = new OauthHelper(_context);
				URL productUrl = new URL(_url);
				HttpsURLConnection conn = (HttpsURLConnection) productUrl.openConnection();
				conn.setRequestProperty("MojioAPIToken", oauthHelper.GetAccessToken());
				
				// Build JSON object from HTTP response.
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = rd.readLine()) != null)
					sb.append(line);

				rd.close();
				String jsonData = sb.toString();
//				new ObjectMapper().readValue(jsonData, Vehicle.class);
				
				Log.e("TESTING", "THIS IS WHAT I GOT: " + jsonData);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(Exception e){}
			
			return null;
		}
		
	}
	

}
