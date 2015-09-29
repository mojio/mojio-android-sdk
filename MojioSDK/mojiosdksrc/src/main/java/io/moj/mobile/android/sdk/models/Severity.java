package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Model object for a diagnostic's severity. The {@link #ordinal()} of this enumeration represents
 * increasing severity.
 * Created by skidson on 15-09-21.
 */
public enum Severity {

    @SerializedName("Unknown")
    UNKNOWN("Unknown"),

    @SerializedName("Notice")
    NOTICE("Notice"),

    @SerializedName("Warning")
    WARNING("Warning"),

    @SerializedName("Alert")
    ALERT("Alert"),

    @SerializedName("Emergency")
    EMERGENCY("Emergency");

    private static final Map<String, Severity> NAME_TO_SEVERITY = new HashMap<>();
    static {
        for (Severity severity : values()) {
            NAME_TO_SEVERITY.put(severity.getName(), severity);
        }
    }

    private final String name;

    Severity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Severity fromName(String name) {
        return NAME_TO_SEVERITY.get(name);
    }
}
