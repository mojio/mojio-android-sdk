package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.DurationUnit;

/**
 * Model object for a Duration object.
 * Created by mhorie on 2016-01-14.
 */
public class Duration extends DeviceMeasurement {

    public DurationUnit getBaseDurationUnit() {
        return DurationUnit.fromKey(getBaseUnit());
    }

    public void setBaseDurationUnit(DurationUnit baseUnit) {
        setBaseUnit(baseUnit.getKey());
    }

    public DurationUnit getDurationUnit() {
        return DurationUnit.fromKey(getUnit());
    }

    public void setDurationUnit(DurationUnit unit) {
        setUnit(unit.getKey());
    }
}