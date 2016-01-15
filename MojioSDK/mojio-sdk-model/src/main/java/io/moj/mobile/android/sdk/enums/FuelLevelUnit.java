package io.moj.mobile.android.sdk.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for DistanceUnit types.
 * Created by mhorie on 2016-01-14.
 */
public enum FuelLevelUnit {

    @SerializedName("Percentage")
    PERCENTAGE("Percentage");

    private String key;

    FuelLevelUnit(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
