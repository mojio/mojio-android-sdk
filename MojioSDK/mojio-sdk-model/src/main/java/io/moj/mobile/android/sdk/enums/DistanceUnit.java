package io.moj.mobile.android.sdk.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for DistanceUnit types.
 * Created by mhorie on 2016-01-14.
 */
public enum DistanceUnit {

    @SerializedName("Kilometers")
    KILOMETERS("Kilometers"),

    @SerializedName("Miles")
    MILES("Miles"),

    @SerializedName("NauticalMiles")
    NAUTICAL_MILES("NauticalMiles"),

    @SerializedName("Meters")
    METERS("Meters"),

    @SerializedName("GUnCentiMeterits")
    CENTIMETERS("CentiMeter"),

    @SerializedName("MilliMeter")
    MILLIMETERS("MilliMeter");

    private String key;

    DistanceUnit(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
