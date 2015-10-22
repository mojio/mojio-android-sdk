package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

import io.moj.mobile.android.sdk.enums.EventType;

/**
 * Model class for an event.
 * Created by ssawchenko on 15-02-20.
 */
public class Event extends MojioObject {

    @SerializedName("MojioId")
    private String mojioId;

    @SerializedName("VehicleId")
    private String vehicleId;

    @SerializedName("OwnerId")
    private String ownerId;

    @SerializedName("EventType")
    private EventType type;

    @SerializedName("Time")
    private String time;

    @SerializedName("Location")
    private Location location;

    @SerializedName("TimeIsApprox")
    private boolean timeIsApprox;

    @SerializedName("BatteryVoltage")
    private float batteryVoltage;

    @SerializedName("ConnectionLost")
    private boolean connectionLost;

    @SerializedName("_id")
    private String id;

    public float getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(float batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public boolean isConnectionLost() {
        return connectionLost;
    }

    public void setConnectionLost(boolean connectionLost) {
        this.connectionLost = connectionLost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getMojioId() {
        return mojioId;
    }

    public void setMojioId(String mojioId) {
        this.mojioId = mojioId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isTimeIsApprox() {
        return timeIsApprox;
    }

    public void setTimeIsApprox(boolean timeIsApprox) {
        this.timeIsApprox = timeIsApprox;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Event{" +
                "batteryVoltage=" + batteryVoltage +
                ", mojioId='" + mojioId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", type=" + type +
                ", time='" + time + '\'' +
                ", location=" + location +
                ", timeIsApprox=" + timeIsApprox +
                ", connectionLost=" + connectionLost +
                ", id='" + id + '\'' +
                '}';
    }
}
