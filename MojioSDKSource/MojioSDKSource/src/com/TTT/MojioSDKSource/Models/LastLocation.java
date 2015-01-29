package com.TTT.MojioSDKSource.Models;

import org.codehaus.jackson.annotate.JsonProperty;

public class LastLocation {
	
	private long Lat;
	private long Lng;
	private boolean FromLockedGPS;
	private float Dilution;
	private boolean IsValid;

	public LastLocation(){
		
	}

	@JsonProperty("Lat")
	public long getLat() {
		return Lat;
	}

	public void setLat(long lat) {
		Lat = lat;
	}

	@JsonProperty("Lng")
	public long getLng() {
		return Lng;
	}

	public void setLng(long lng) {
		Lng = lng;
	}

	@JsonProperty("FromLockedGPS")
	public boolean isFromLockedGPS() {
		return FromLockedGPS;
	}

	public void setFromLockedGPS(boolean fromLockedGPS) {
		FromLockedGPS = fromLockedGPS;
	}

	@JsonProperty("Dilution")
	public float getDilution() {
		return Dilution;
	}

	public void setDilution(float dilution) {
		Dilution = dilution;
	}

	@JsonProperty("IsValid")
	public boolean isIsValid() {
		return IsValid;
	}

	public void setIsValid(boolean isValid) {
		IsValid = isValid;
	}
}
