package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.SpeedUnit;

/**
 * Model object for a Speed object.
 * Created by mhorie on 2016-01-14.
 */
public class Speed extends DeviceMeasurement {

    private Integer SpeedBandId;
    private Duration SpeedBandDuration;

    public SpeedUnit getBaseSpeedUnit() {
        // TODO
        return SpeedUnit.KILOMETERS_PER_HOUR;
    }

    public void setBaseSpeedUnit(SpeedUnit baseUnit) {
        // TODO
    }

    public SpeedUnit getSpeedUnit() {
        // TODO
        return SpeedUnit.KILOMETERS_PER_HOUR;
    }

    public void setSpeedUnit(SpeedUnit unit) {
        // TODO
    }

    public Duration getSpeedBandDuration() {
        return SpeedBandDuration;
    }

    public void setSpeedBandDuration(Duration speedBandDuration) {
        SpeedBandDuration = speedBandDuration;
    }

    public Integer getSpeedBandId() {
        return SpeedBandId;
    }

    public void setSpeedBandId(Integer speedBandId) {
        SpeedBandId = speedBandId;
    }

    @Override
    public String toString() {
        return "Speed{" +
                "SpeedBandDuration=" + SpeedBandDuration +
                ", SpeedBandId=" + SpeedBandId +
                '}';
    }
}