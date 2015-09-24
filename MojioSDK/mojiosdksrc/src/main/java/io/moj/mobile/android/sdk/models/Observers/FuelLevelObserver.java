package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a fuel level observer.
 * Created by jian on 01/04/2015.
 */
public class FuelLevelObserver extends ConditionalObserverBase {

    @SerializedName("FuelLow")
    private float fuelLow;

    @SerializedName("FuelHigh")
    private float fuelHigh;

    public float getFuelHigh() {
        return fuelHigh;
    }

    public void setFuelHigh(float fuelHigh) {
        this.fuelHigh = fuelHigh;
    }

    public float getFuelLow() {
        return fuelLow;
    }

    public void setFuelLow(float fuelLow) {
        this.fuelLow = fuelLow;
    }

    @Override
    public String toString() {
        return "FuelLevelObserver{" +
                "fuelHigh=" + fuelHigh +
                ", fuelLow=" + fuelLow +
                '}';
    }
}
