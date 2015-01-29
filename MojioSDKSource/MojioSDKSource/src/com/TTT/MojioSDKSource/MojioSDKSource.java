package com.TTT.MojioSDKSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.TTT.MojioSDKSource.Models.Vehicle;
import com.TTT.MojioSDKSource.OauthHelper.OauthHelper;
import com.TTT.MojioSDKSource.Requests.LocationRequest;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;



public class MojioSDKSource{
	
	
	private SpiceManager _contentManager = new SpiceManager(JacksonSpringAndroidSpiceService.class);
	private String _lastRequestCacheKey = null;
	
	public static void RequestAccessToken( Activity context){
		
		Intent i = new Intent(context, MojioSDKActivity.class);
		context.startActivityForResult(i, 22);
			

	}
	
	
	public static void GetElement(String url, Context context){
		new GetElementTask(url, context).execute();
	}
	
	private static class GetElementTask extends AsyncTask<Void, Void, String>{
		
		String _url;
		Context _context;
		
		public GetElementTask(String url, Context context){
			_url = url;
			_context = context;
			
		}

		@Override
		protected String doInBackground(Void... params) {

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
				return jsonData;
				
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
		
		@Override
		protected void onPostExecute(String result) {

			try {
				JSONObject resultJson = new JSONObject(result);
				String type = resultJson.getString("Type");
				
				String className = "com.TTT.MojioSDKSource.Models." + type;
				Class myclass = Class.forName(className);

				myclass = new ObjectMapper().readValue(result, myclass);

				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void getVehicle(Context context){
		
		new GetVehicle(context).execute();
		
		
	}


	
	private static class GetVehicle extends AsyncTask<Void, Void, String>{
		
		Context _context;
		
		public GetVehicle(Context context){
			_context = context;
		}

		@Override
		protected String doInBackground(Void... params) {
			OauthHelper oauthHelper = new OauthHelper(_context);
			LocationRequest lq = new LocationRequest("https://api.moj.io/v1/Vehicles/53cdeca5-b268-4a25-bfde-3938b5cf7d47", oauthHelper);
			try {
				Vehicle test = lq.loadDataFromNetwork();
				Log.e("TESTING LOCATION", "THE LONG IS: " + test.getFuelLevel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {

		}
		
		
	}



}
