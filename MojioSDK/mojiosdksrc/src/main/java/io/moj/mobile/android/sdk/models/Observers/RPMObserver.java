package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for an RPM observer.
 * Created by jian on 01/04/2015.
 */
public class RPMObserver extends ConditionalObserverBase {

    @SerializedName("RpmLow")
    private float rpmLow;

    @SerializedName("RpmHigh")
    private float rpmHigh;

    public float getRpmHigh() {
        return rpmHigh;
    }

    public void setRpmHigh(float rpmHigh) {
        this.rpmHigh = rpmHigh;
    }

    public float getRpmLow() {
        return rpmLow;
    }

    public void setRpmLow(float rpmLow) {
        this.rpmLow = rpmLow;
    }

    @Override
    public String toString() {
        return "RPMObserver{" +
                "rpmHigh=" + rpmHigh +
                ", rpmLow=" + rpmLow +
                '}';
    }
}
