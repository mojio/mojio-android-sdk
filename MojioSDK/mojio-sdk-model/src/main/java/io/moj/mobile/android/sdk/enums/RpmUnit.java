package io.moj.mobile.android.sdk.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for RpmUnit types.
 * Created by mhorie on 2016-01-14.
 */
public enum RpmUnit {

    @SerializedName("RevolutionsPerMinute")
    RPM("RevolutionsPerMinute");

    private String key;

    RpmUnit(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
