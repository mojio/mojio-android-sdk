package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a distance observer.
 * Created by jian on 01/04/2015.
 */
public class DistanceObserver extends ConditionalObserverBase {

    @SerializedName("DistanceLow")
    private float distanceLow;

    @SerializedName("DistanceLow")
    private float distanceHigh;

    public float getDistanceHigh() {
        return distanceHigh;
    }

    public void setDistanceHigh(float distanceHigh) {
        this.distanceHigh = distanceHigh;
    }

    public float getDistanceLow() {
        return distanceLow;
    }

    public void setDistanceLow(float distanceLow) {
        this.distanceLow = distanceLow;
    }

    @Override
    public String toString() {
        return "DistanceObserver{" +
                "distanceHigh=" + distanceHigh +
                ", distanceLow=" + distanceLow +
                '}';
    }
}
