package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Base class for a conditional observer.
 * Created by jian on 01/04/2015.
 */
public class ConditionalObserverBase extends Observer {

    @SerializedName("Timing")
    private String timing;

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "ConditionalObserverBase{" +
                "timing='" + timing + '\'' +
                '}';
    }
}
