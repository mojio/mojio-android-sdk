package io.moj.mobile.android.sdk.model.values;

import org.joda.time.DateTime;

import io.moj.mobile.android.sdk.model.utils.TimeFormatHelpers;

/**
 * Model object for an StateTimestamp value.
 * Created by mhorie on 2016-01-14.
 */
public abstract class StateTimestamp {

    private String Timestamp;

    public DateTime getTimestamp() {
        return TimeFormatHelpers.fromServerFormatted(Timestamp);
    }

    public void setTimestamp(DateTime timestamp) {
        Timestamp = TimeFormatHelpers.toServerFormatted(timestamp);
    }
}
