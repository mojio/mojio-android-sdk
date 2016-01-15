package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.DurationUnit;

/**
 * Model object for a Duration object.
 * Created by mhorie on 2016-01-14.
 */
public class Duration extends DeviceMeasurement {

    public DurationUnit getBaseDurationUnit() {
        // TODO
        return DurationUnit.HOURS;
    }

    public void setBaseDurationUnit(DurationUnit baseUnit) {
        // TODO
    }

    public DurationUnit getDurationUnit() {
        // TODO
        return DurationUnit.HOURS;
    }

    public void setDurationUnit(DurationUnit unit) {
        // TODO
    }
}