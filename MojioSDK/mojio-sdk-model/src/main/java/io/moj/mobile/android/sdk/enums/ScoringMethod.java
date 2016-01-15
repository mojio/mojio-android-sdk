package io.moj.mobile.android.sdk.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for DistanceUnit types.
 * Created by mhorie on 2016-01-14.
 */
public enum ScoringMethod {

    @SerializedName("ZScore")
    ZSCORE("ZScore"),

    @SerializedName("MinMaxScore")
    MIN_MAX_SCORE("MinMaxScore");

    private String key;

    ScoringMethod(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
