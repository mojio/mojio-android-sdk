package io.moj.mobile.android.sdk.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for AccelerationUnit types.
 * Created by mhorie on 2016-01-14.
 */
public enum AccelerationUnit {

    @SerializedName("MetersPerSecondPerSecond")
    METERS_PER_SECOND_PER_SECOND("MetersPerSecondPerSecond"),

    @SerializedName("KilometersPerHourPerSecond")
    KILOMETERS_PER_HOUR_PER_SECOND("KilometersPerHourPerSecond"),

    @SerializedName("MilesPerHourPerSecond")
    MILES_PER_HOUR_PER_SECOND("MilesPerHourPerSecond");

    private String key;

    AccelerationUnit(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
