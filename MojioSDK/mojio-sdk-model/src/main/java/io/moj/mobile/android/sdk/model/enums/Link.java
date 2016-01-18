package io.moj.mobile.android.sdk.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for Link types.
 * Created by mhorie on 2016-01-13.
 */
public enum Link {

    @SerializedName("Self")
    SELF("Self"),

    @SerializedName("First")
    FIRST("First"),

    @SerializedName("Next")
    NEXT("Next"),

    @SerializedName("Vehicle")
    VEHICLE("Vehicle"),

    @SerializedName("Mojio")
    MOJIO("Mojio"),

    @SerializedName("Permissions")
    PERMISSIONS("Permissions"),

    @SerializedName("Permission")
    PERMISSION("Permission"),

    @SerializedName("States")
    STATES("States"),

    @SerializedName("Locations")
    LOCATIONS("Locations");

    private String key;

    Link(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static Link fromKey(String key) {
        for (Link unit : Link.values()) {
            if (unit.getKey().equals(key)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Illegal Link value supplied: " + key);
    }
}
