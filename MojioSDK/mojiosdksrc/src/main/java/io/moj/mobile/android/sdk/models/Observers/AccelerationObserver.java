package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for an acceleration observer.
 * Created by jian on 01/04/2015.
 */
public class AccelerationObserver extends ConditionalObserverBase {

    @SerializedName("AccelerationLow")
    private float accelerationLow;

    @SerializedName("AccelerationHigh")
    private float accelerationHigh;

    public float getAccelerationHigh() {
        return accelerationHigh;
    }

    public void setAccelerationHigh(float accelerationHigh) {
        this.accelerationHigh = accelerationHigh;
    }

    public float getAccelerationLow() {
        return accelerationLow;
    }

    public void setAccelerationLow(float accelerationLow) {
        this.accelerationLow = accelerationLow;
    }

    @Override
    public String toString() {
        return "AccelerationObserver{" +
                "accelerationHigh=" + accelerationHigh +
                ", accelerationLow=" + accelerationLow +
                '}';
    }
}
