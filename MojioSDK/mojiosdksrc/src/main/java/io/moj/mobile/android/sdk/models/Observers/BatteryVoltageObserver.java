package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a battery voltage observer.
 * Created by jian on 01/04/2015.
 */
public class BatteryVoltageObserver extends ConditionalObserverBase {

    @SerializedName("BatteryVoltageLow")
    private float batteryVoltageLow;

    @SerializedName("BatteryVoltageHigh")
    private float batteryVoltageHigh;

    public float getBatteryVoltageHigh() {
        return batteryVoltageHigh;
    }

    public void setBatteryVoltageHigh(float batteryVoltageHigh) {
        this.batteryVoltageHigh = batteryVoltageHigh;
    }

    public float getBatteryVoltageLow() {
        return batteryVoltageLow;
    }

    public void setBatteryVoltageLow(float batteryVoltageLow) {
        this.batteryVoltageLow = batteryVoltageLow;
    }

    @Override
    public String toString() {
        return "BatteryVoltageObserver{" +
                "batteryVoltageHigh=" + batteryVoltageHigh +
                ", batteryVoltageLow=" + batteryVoltageLow +
                '}';
    }
}
