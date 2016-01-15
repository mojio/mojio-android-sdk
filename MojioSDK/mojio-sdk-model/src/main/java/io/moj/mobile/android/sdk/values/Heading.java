package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.HeadingUnit;

/**
 * Model object for a Heading value.
 * Created by mhorie on 2016-01-14.
 */
public class Heading extends DeviceMeasurement {

    private String Direction;
    private Boolean LeftTurn;

    public HeadingUnit getBaseHeadingUnit() {
        return HeadingUnit.fromKey(getBaseUnit());
    }

    public void setBaseHeadingUnit(HeadingUnit baseUnit) {
        setBaseUnit(baseUnit.getKey());
    }

    public HeadingUnit getHeadingUnit() {
        return HeadingUnit.fromKey(getUnit());
    }

    public void setHeadingUnit(HeadingUnit unit) {
        setUnit(unit.getKey());
    }

    // TODO: what can be returned here?
    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public Boolean getLeftTurn() {
        return LeftTurn;
    }

    public void setLeftTurn(Boolean leftTurn) {
        LeftTurn = leftTurn;
    }

    @Override
    public String toString() {
        return "Heading{" +
                "Direction='" + Direction + '\'' +
                ", LeftTurn=" + LeftTurn +
                '}';
    }
}
