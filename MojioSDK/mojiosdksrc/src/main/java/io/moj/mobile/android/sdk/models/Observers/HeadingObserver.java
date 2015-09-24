package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a heading observer.
 * Created by jian on 01/04/2015.
 */
public class HeadingObserver extends ConditionalObserverBase {

    @SerializedName("HeadingLow")
    private float headingLow;

    @SerializedName("HeadingLow")
    private float headingHigh;

    public float getHeadingHigh() {
        return headingHigh;
    }

    public void setHeadingHigh(float headingHigh) {
        this.headingHigh = headingHigh;
    }

    public float getHeadingLow() {
        return headingLow;
    }

    public void setHeadingLow(float headingLow) {
        this.headingLow = headingLow;
    }

    @Override
    public String toString() {
        return "HeadingObserver{" +
                "headingHigh=" + headingHigh +
                ", headingLow=" + headingLow +
                '}';
    }
}
