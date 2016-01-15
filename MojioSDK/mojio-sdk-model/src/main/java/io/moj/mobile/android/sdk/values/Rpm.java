package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.RpmUnit;

/**
 * Model object for a Rpm object.
 * Created by mhorie on 2016-01-14.
 */
public class Rpm extends DeviceMeasurement {

    public RpmUnit getBaseRpmUnit() {
        return RpmUnit.fromKey(getBaseUnit());
    }

    public void setBaseRpmUnit(RpmUnit baseUnit) {
        setBaseUnit(baseUnit.getKey());
    }

    public RpmUnit getRpmUnit() {
        return RpmUnit.fromKey(getUnit());
    }

    public void setRpmUnit(RpmUnit unit) {
        setUnit(unit.getKey());
    }
}
