package com.TTT.MojioSDKSource;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.TTT.MojioSDKSource.Models.Vehicle;
//import com.TTT.MojioSDKSource.MojioSDKSource.GetElementTask;
import com.TTT.MojioSDKSource.OauthHelper.OauthHelper;


	

public class MojioSDKSource_temp {

	
	public static void PutElement(String url, Context context){
		new PutElementTask(url, context).execute();
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

			if (params.length<=0){
				return null;
			} 
			else{
				//append the users' requests to the base url
				_url = _url + params;
			}
			
			try {

				OauthHelper oauthHelper = new OauthHelper(_context);
				URL productUrl = new URL(_url);
				
				HttpsURLConnection conn = (HttpsURLConnection) productUrl.openConnection();
				conn.setRequestMethod("PUT");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				
				//TBD, Zhe Yang, what are the correct properties 
				conn.setRequestProperty("MojioAPIToken", oauthHelper.GetAccessToken());
				
				//set content-Type
	            conn.setRequestProperty("Content-Type", "application/json");
	            //set request length
	            conn.setRequestProperty("Content-Length", String.valueOf(params.length));
	         /*   //o
	            OutputStream outputStream = conn.getOutputStream();
	            outputStream.write(data);
	            
	            int response = httpURLConnection.getResponseCode();            //获得服务器的响应码
	            if(response == HttpURLConnection.HTTP_OK) {
	                InputStream inptStream = httpURLConnection.getInputStream();
	                return dealResponseResult(inptStream);                     //处理服务器的响应结果
	            }Content-Length:20
				Content-Type:
				
				conn.connect();		        
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				
				*/
				OutputStreamWriter output = new OutputStreamWriter(conn.getOutputStream());
				output.write("Resource content");
				output.close();
				conn.getInputStream();
				
				
				
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
