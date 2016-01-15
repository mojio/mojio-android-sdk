package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.AccelerationUnit;
import io.moj.mobile.android.sdk.enums.DistanceUnit;

/**
 * Model object for a ProperAcceleration value.
 * Created by mhorie on 2016-01-14.
 */
public class ProperAcceleration extends DeviceMeasurement {

    public AccelerationUnit getBaseAccelerationUnit() {
        // TODO
        return AccelerationUnit.METERS_PER_SECOND_PER_SECOND;
    }

    public void setBaseAccelerationUnit(AccelerationUnit baseUnit) {
        // TODO
    }

    public AccelerationUnit getAccelerationUnit() {
        // TODO
        return AccelerationUnit.METERS_PER_SECOND_PER_SECOND;
    }

    public void setAccelerationUnit(AccelerationUnit unit) {
        // TODO
    }
}
