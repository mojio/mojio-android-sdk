package io.moj.mobile.android.sdk.values;

/**
 * Model object for a BooleanState value.
 * Created by mhorie on 2016-01-14.
 */
public class BooleanState extends StateTimestamp {

    private Boolean Value;

    public Boolean getValue() {
        return Value;
    }

    public void setValue(Boolean value) {
        Value = value;
    }
}