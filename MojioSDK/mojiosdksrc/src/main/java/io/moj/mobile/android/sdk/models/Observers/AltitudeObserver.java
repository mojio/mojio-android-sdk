package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jian on 01/04/2015.
 */
public class AltitudeObserver extends ConditionalObserverBase{

    @SerializedName("AltitudeLow")
    private float altitudeLow;

    @SerializedName("AltitudeLow")
    private float altitudeHigh;

    public float getAltitudeHigh() {
        return altitudeHigh;
    }

    public void setAltitudeHigh(float altitudeHigh) {
        this.altitudeHigh = altitudeHigh;
    }

    public float getAltitudeLow() {
        return altitudeLow;
    }

    public void setAltitudeLow(float altitudeLow) {
        this.altitudeLow = altitudeLow;
    }

    @Override
    public String toString() {
        return "AltitudeObserver{" +
                "altitudeHigh=" + altitudeHigh +
                ", altitudeLow=" + altitudeLow +
                '}';
    }
}
