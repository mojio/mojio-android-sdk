package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for an accelerometer observer.
 * Created by jian on 01/04/2015.
 */
public class AccelerometerObserver extends ConditionalObserverBase {

    @SerializedName("AccelerometerLow")
    private Object accelerometerLow;

    @SerializedName("AccelerometerHigh")
    private Object accelerometerHigh;

    public Object getAccelerometerHigh() {
        return accelerometerHigh;
    }

    public void setAccelerometerHigh(Object accelerometerHigh) {
        this.accelerometerHigh = accelerometerHigh;
    }

    public Object getAccelerometerLow() {
        return accelerometerLow;
    }

    public void setAccelerometerLow(Object accelerometerLow) {
        this.accelerometerLow = accelerometerLow;
    }

    @Override
    public String toString() {
        return "AccelerometerObserver{" +
                "accelerometerHigh=" + accelerometerHigh +
                ", accelerometerLow=" + accelerometerLow +
                '}';
    }
}
