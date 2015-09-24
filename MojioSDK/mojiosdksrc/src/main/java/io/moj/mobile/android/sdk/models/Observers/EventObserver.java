package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Model class for an event observer.
 * Created by jian on 01/04/2015.
 */
public class EventObserver extends ConditionalObserverBase{

    @SerializedName("EventTypes")
    private ArrayList<Object> eventTypes;

    public ArrayList<Object> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(ArrayList<Object> eventTypes) {
        this.eventTypes = eventTypes;
    }

    @Override
    public String toString() {
        return "EventObserver{" +
                "eventTypes=" + eventTypes +
                '}';
    }
}
