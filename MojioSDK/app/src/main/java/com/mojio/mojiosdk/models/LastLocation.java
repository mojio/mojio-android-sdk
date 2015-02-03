package com.mojio.mojiosdk.models;


public class LastLocation {
	
	private long Lat;
	private long Lng;
	private boolean FromLockedGPS;
	private float Dilution;
	private boolean IsValid;

	public LastLocation(){
		
	}

	public long getLat() {
		return Lat;
	}

	public void setLat(long lat) {
		Lat = lat;
	}

	public long getLng() {
		return Lng;
	}

	public void setLng(long lng) {
		Lng = lng;
	}

	public boolean isFromLockedGPS() {
		return FromLockedGPS;
	}

	public void setFromLockedGPS(boolean fromLockedGPS) {
		FromLockedGPS = fromLockedGPS;
	}

	public float getDilution() {
		return Dilution;
	}

	public void setDilution(float dilution) {
		Dilution = dilution;
	}

	public boolean isIsValid() {
		return IsValid;
	}

	public void setIsValid(boolean isValid) {
		IsValid = isValid;
	}
}
