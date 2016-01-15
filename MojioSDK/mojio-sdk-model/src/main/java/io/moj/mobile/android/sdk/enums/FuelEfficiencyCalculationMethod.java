package io.moj.mobile.android.sdk.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for FuelEfficiencyCalculationMethod types.
 * Created by mhorie on 2016-01-14.
 */
public enum FuelEfficiencyCalculationMethod {

    @SerializedName("Query")
    QUERY("Query"),

    @SerializedName("EngineFuelRate")
    ENGINE_FUEL_RATE("EngineFuelRate"),

    @SerializedName("MassAirFlow")
    MASS_AIR_FLOW("MassAirFlow"),

    @SerializedName("Calculated")
    CALCULATED("Calculated"),

    @SerializedName("None")
    NONE("None");

    private String key;

    FuelEfficiencyCalculationMethod(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
