package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model object for a diagnostic's severity. The {@link #ordinal()} of this enumeration represents
 * increasing severity.
 * Created by skidson on 15-09-21.
 */
public enum Severity {

    @SerializedName("Unknown")
    UNKNOWN,

    @SerializedName("Notice")
    NOTICE,

    @SerializedName("Warning")
    WARNING,

    @SerializedName("Alert")
    ALERT,

    @SerializedName("Emergency")
    EMERGENCY

}
