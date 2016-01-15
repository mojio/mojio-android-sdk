package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.DistanceUnit;

/**
 * Model object for a Distance value.
 * Created by mhorie on 2016-01-14.
 */
public class Distance extends DeviceMeasurement {

    public DistanceUnit getBaseDistanceUnit() {
        // TODO
        return DistanceUnit.METERS;
    }

    public void setBaseDistanceUnit(DistanceUnit baseUnit) {
        // TODO
    }

    public DistanceUnit getDistanceUnit() {
        // TODO
        return DistanceUnit.METERS;
    }

    public void setDistanceUnit(DistanceUnit unit) {
        // TODO
    }
}