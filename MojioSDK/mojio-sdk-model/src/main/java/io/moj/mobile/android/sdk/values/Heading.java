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
        // TODO
        return HeadingUnit.DEGREE;
    }

    public void setBaseHeadingUnit(HeadingUnit baseUnit) {
        // TODO
    }

    public HeadingUnit getHeadingUnit() {
        // TODO
        return HeadingUnit.DEGREE;
    }

    public void setHeadingUnit(HeadingUnit unit) {
        // TODO
    }

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
