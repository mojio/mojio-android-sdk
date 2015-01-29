package com.TTT.MojioSDKSource.Models;

import org.codehaus.jackson.annotate.JsonProperty;

public class LastAccelerometer {
	
	private float X;
	private float Y;
	private float Z;
	private float Magnitude;
	
	
	public LastAccelerometer(){
		
	}

	@JsonProperty("X")
	public float getX() {
		return X;
	}


	public void setX(float x) {
		X = x;
	}

	@JsonProperty("Y")
	public float getY() {
		return Y;
	}


	public void setY(float y) {
		Y = y;
	}

	@JsonProperty("Z")
	public float getZ() {
		return Z;
	}


	public void setZ(float z) {
		Z = z;
	}

	@JsonProperty("Magnitude")
	public float getMagnitude() {
		return Magnitude;
	}


	public void setMagnitude(float magnitude) {
		Magnitude = magnitude;
	}

}
