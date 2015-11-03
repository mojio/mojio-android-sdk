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

    @SerializedName("Altitude")
    private float altitude;

    @SerializedName("Heading")
    private float heading;

    @SerializedName("FuelLevel")
    private float fuelLevel;

    @SerializedName("FuelEfficiency")
    private float fuelEfficiency;

    @SerializedName("Speed")
    private float speed;

    @SerializedName("Acceleration")
    private float acceleration;

    @SerializedName("Deceleration")
    private float deceleration;

    @SerializedName("Odometer")
    private float odometer;

    @SerializedName("RPM")
    private float rpm;

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

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }

    public float getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(float fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public float getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(float fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public float getHeading() {
        return heading;
    }

    public void setHeading(float heading) {
        this.heading = heading;
    }

    public float getOdometer() {
        return odometer;
    }

    public void setOdometer(float odometer) {
        this.odometer = odometer;
    }

    public float getRpm() {
        return rpm;
    }

    public void setRpm(float rpm) {
        this.rpm = rpm;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Event{" +
                "acceleration=" + acceleration +
                ", mojioId='" + mojioId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", type=" + type +
                ", time='" + time + '\'' +
                ", location=" + location +
                ", timeIsApprox=" + timeIsApprox +
                ", batteryVoltage=" + batteryVoltage +
                ", connectionLost=" + connectionLost +
                ", altitude=" + altitude +
                ", heading=" + heading +
                ", fuelLevel=" + fuelLevel +
                ", fuelEfficiency=" + fuelEfficiency +
                ", speed=" + speed +
                ", deceleration=" + deceleration +
                ", odometer=" + odometer +
                ", rpm=" + rpm +
                "} " + super.toString();
    }
}
