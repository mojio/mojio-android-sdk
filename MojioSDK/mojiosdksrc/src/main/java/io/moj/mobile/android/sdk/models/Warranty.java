package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Model class for a warranty.
 * Created by skidson on 15-09-21.
 */
public class Warranty extends MojioObject {

    @SerializedName("Name")
    private String name;

    @SerializedName("Months")
    private String months;

    @SerializedName("Km")
    private String distance;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Warranty{" +
                "distance='" + distance + '\'' +
                ", name='" + name + '\'' +
                ", months='" + months + '\'' +
                '}';
    }
}
