package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.FuelLevelUnit;

/**
 * Model object for a FuelLevel value.
 * Created by mhorie on 2016-01-14.
 */
public class FuelLevel extends DeviceMeasurement {

    public FuelLevelUnit getBaseFuelLevelUnit() {
        // TODO
        return FuelLevelUnit.PERCENTAGE;
    }

    public void setBaseFuelLevelUnit(FuelLevelUnit baseUnit) {
        // TODO
    }

    public FuelLevelUnit getFuelLevelUnit() {
        // TODO
        return FuelLevelUnit.PERCENTAGE;
    }

    public void setFuelLevelUnit(FuelLevelUnit unit) {
        // TODO
    }
}
