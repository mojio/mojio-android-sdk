package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for an odometer observer.
 * Created by jian on 01/04/2015.
 */
public class OdometerObserver extends ConditionalObserverBase {

    @SerializedName("OdometerLow")
    private float odometerLow;

    @SerializedName("OdometerHigh")
    private float odometerHigh;

    public float getOdometerHigh() {
        return odometerHigh;
    }

    public void setOdometerHigh(float odometerHigh) {
        this.odometerHigh = odometerHigh;
    }

    public float getOdometerLow() {
        return odometerLow;
    }

    public void setOdometerLow(float odometerLow) {
        this.odometerLow = odometerLow;
    }

    @Override
    public String toString() {
        return "OdometerObserver{" +
                "odometerHigh=" + odometerHigh +
                ", odometerLow=" + odometerLow +
                '}';
    }
}
