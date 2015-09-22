package io.moj.mobile.android.sdk.models;


import com.google.gson.annotations.SerializedName;

/**
 * Model class for an accelerometer measurement.
 */
public class Accelerometer {

	@SerializedName("X")
	private float x;

	@SerializedName("Y")
	private float y;

	@SerializedName("Z")
	private float z;

	@SerializedName("Magnitude")
	private float magnitude;

	public float getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(float magnitude) {
		this.magnitude = magnitude;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Accelerometer{" +
				"magnitude=" + magnitude +
				", x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}
}
