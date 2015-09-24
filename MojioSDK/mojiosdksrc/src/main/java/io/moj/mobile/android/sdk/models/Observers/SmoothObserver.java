package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a smooth observer.
 * Created by jian on 01/04/2015.
 */
public class SmoothObserver extends Observer {

    @SerializedName("InterpolationRate")
    private float interpolationRate;

    public float getInterpolationRate() {
        return interpolationRate;
    }

    public void setInterpolationRate(float interpolationRate) {
        this.interpolationRate = interpolationRate;
    }

    @Override
    public String toString() {
        return "SmoothObserver{" +
                "interpolationRate=" + interpolationRate +
                '}';
    }
}
