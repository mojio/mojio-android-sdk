package com.TTT.MojioSDKSource.Requests;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

import com.TTT.MojioSDKSource.MojioSDKSource;
import com.TTT.MojioSDKSource.Models.Location;
import com.TTT.MojioSDKSource.Models.Vehicle;
import com.TTT.MojioSDKSource.OauthHelper.OauthHelper;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

public class LocationRequest extends SpringAndroidSpiceRequest<Vehicle>{

	String _url;
	OauthHelper _oauthhelper;
	
	public LocationRequest(String url, OauthHelper oauthHelper) {
		super(Vehicle.class);
		_url = url;
		_oauthhelper = oauthHelper;
	}

	@Override
	public Vehicle loadDataFromNetwork() throws Exception {
		
		
		 // Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
		// Add the String message converter
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());


		HttpHeaders headers = new HttpHeaders();
		headers.add("MojioAPIToken", _oauthhelper.GetAccessToken());
		HttpEntity<String> entity = new HttpEntity(headers);
		ResponseEntity<Vehicle> responseEntity = restTemplate.exchange( _url, HttpMethod.GET, entity, Vehicle.class);
		Log.e("tjel", "fjdlksajfd 1");
		return responseEntity.getBody();

	}

}
