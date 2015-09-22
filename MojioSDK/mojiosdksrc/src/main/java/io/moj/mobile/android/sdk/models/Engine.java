package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for an engine.
 * Created by ssawchenko on 15-02-26.
 */
public class Engine {

    @SerializedName("Name")
    private String name;

    @SerializedName("Cylinders")
    private int cylinders;

    @SerializedName("Displacement")
    private float displacement;

    @SerializedName("MaxHp")
    private String maxHp;

    @SerializedName("MaxHpAt")
    private String maxHpAt;

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public float getDisplacement() {
        return displacement;
    }

    public void setDisplacement(float displacement) {
        this.displacement = displacement;
    }

    public String getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(String maxHp) {
        this.maxHp = maxHp;
    }

    public String getMaxHpAt() {
        return maxHpAt;
    }

    public void setMaxHpAt(String maxHpAt) {
        this.maxHpAt = maxHpAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "cylinders=" + cylinders +
                ", name='" + name + '\'' +
                ", displacement=" + displacement +
                ", maxHp='" + maxHp + '\'' +
                ", maxHpAt='" + maxHpAt + '\'' +
                '}';
    }
}