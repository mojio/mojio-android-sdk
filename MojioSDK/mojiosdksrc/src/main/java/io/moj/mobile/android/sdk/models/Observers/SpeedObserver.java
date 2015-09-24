package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a speed observer.
 * Created by jian on 01/04/2015.
 */
public class SpeedObserver extends ConditionalObserverBase {

    @SerializedName("SpeedLow")
    private float speedLow;

    @SerializedName("SpeedHigh")
    private float speedHigh;

    public float getSpeedHigh() {
        return speedHigh;
    }

    public void setSpeedHigh(float speedHigh) {
        this.speedHigh = speedHigh;
    }

    public float getSpeedLow() {
        return speedLow;
    }

    public void setSpeedLow(float speedLow) {
        this.speedLow = speedLow;
    }

    @Override
    public String toString() {
        return "SpeedObserver{" +
                "speedHigh=" + speedHigh +
                ", speedLow=" + speedLow +
                '}';
    }
}
