package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

import io.moj.mobile.android.sdk.models.Location;

/**
 * Model class for a geo-fence observer.
 * Created by jian on 01/04/2015.
 */
public class GeoFenceObserver extends ConditionalObserverBase {

    @SerializedName("Location")
    private Location location;

    @SerializedName("Radius")
    private float radius;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "GeoFenceObserver{" +
                "location=" + location +
                ", radius=" + radius +
                '}';
    }
}
