package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

public class Location extends MojioObject {

    @SerializedName("Lat")
	private float latitude;

    @SerializedName("Lng")
    private float longitude;

    @SerializedName("FromLockedGPS")
    private boolean fromLockedGPS;

    @SerializedName("Dilution")
    private float dilution;

    @SerializedName("IsValid")
    private boolean valid;

    public float getDilution() {
        return dilution;
    }

    public void setDilution(float dilution) {
        this.dilution = dilution;
    }

    public boolean isFromLockedGPS() {
        return fromLockedGPS;
    }

    public void setFromLockedGPS(boolean fromLockedGPS) {
        this.fromLockedGPS = fromLockedGPS;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Location{" +
                "dilution=" + dilution +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", fromLockedGPS=" + fromLockedGPS +
                ", valid=" + valid +
                '}';
    }
}
