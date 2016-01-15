package io.moj.mobile.android.sdk.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for RiskSeverity types.
 * Created by mhorie on 2016-01-14.
 */
public enum RiskSeverity {

    @SerializedName("Unknown")
    UNKNOWN("Unknown"),

    @SerializedName("Low")
    LOW("Low"),

    @SerializedName("Medium")
    MEDIUM("Medium"),

    @SerializedName("High")
    HIGH("High"),

    @SerializedName("None")
    NONE("None");

    private String key;

    RiskSeverity(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
