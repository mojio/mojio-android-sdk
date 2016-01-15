package io.moj.mobile.android.sdk.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for HeadingUnit types.
 * Created by mhorie on 2016-01-14.
 */
public enum HeadingUnit {

    @SerializedName("Degree")
    DEGREE("Degree");

    private String key;

    HeadingUnit(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
