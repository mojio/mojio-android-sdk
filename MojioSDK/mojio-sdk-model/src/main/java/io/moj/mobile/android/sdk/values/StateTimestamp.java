package io.moj.mobile.android.sdk.values;

/**
 * Model object for an StateTimestamp value.
 * Created by mhorie on 2016-01-14.
 */
public abstract class StateTimestamp {

    private String Timestamp;

    // TODO add methods returning DateTime instead of String
    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }
}
