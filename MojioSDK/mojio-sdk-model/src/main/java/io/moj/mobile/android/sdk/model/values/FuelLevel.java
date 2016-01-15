package io.moj.mobile.android.sdk.model.values;

import io.moj.mobile.android.sdk.model.enums.FuelLevelUnit;

/**
 * Model object for a FuelLevel value.
 * Created by mhorie on 2016-01-14.
 */
public class FuelLevel extends DeviceMeasurement {

    public FuelLevelUnit getBaseFuelLevelUnit() {
        return FuelLevelUnit.fromKey(getBaseUnit());
    }

    public void setBaseFuelLevelUnit(FuelLevelUnit baseUnit) {
        setBaseUnit(baseUnit.getKey());
    }

    public FuelLevelUnit getFuelLevelUnit() {
        return FuelLevelUnit.fromKey(getUnit());
    }

    public void setFuelLevelUnit(FuelLevelUnit unit) {
        setUnit(unit.getKey());
    }
}
