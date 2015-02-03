package com.mojio.mojiosdk.models;

public class Location {
	
	private float Lat;
	private float Lng;
	private boolean FromLockedGPS;
	private float Dilution;
	private boolean IsValid;

	public Location(){
		
	}

	public float getLat() {
		return Lat;
	}

	public void setLat(float lat) {
		Lat = lat;
	}

	public float getLng() {
		return Lng;
	}

	public void setLng(float lng) {
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
