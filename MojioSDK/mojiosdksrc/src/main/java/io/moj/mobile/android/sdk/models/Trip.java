package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a trip.
 */
public class Trip {

    @SerializedName("MojioId")
    private String mojioId;

    @SerializedName("VehicleId")
    private String vehicleId;

    @SerializedName("StartTime")
    private String startTime;

    @SerializedName("LastUpdatedTime")
    private String lastUpdatedTime;

    @SerializedName("EndTime")
    private String endTime;

    @SerializedName("MaxSpeed")
    private float maxSpeed;

    @SerializedName("MaxAcceleration")
    private float maxAcceleration;

    @SerializedName("MaxDeceleration")
    private float maxDeceleration;

    @SerializedName("MaxRPM")
    private int maxRPM;

    @SerializedName("FuelLevel")
    private float fuelLevel;

    @SerializedName("FuelEfficiency")
    private float fuelEfficiency;

    @SerializedName("Distance")
    private float distance;

    @SerializedName("StartLocation")
    private Location startLocation;

    @SerializedName("LastKnownLocation")
    private Location lastKnownLocation;

    @SerializedName("EndLocation")
    private Location endLocation;

    @SerializedName("StartAddress")
    private Address startAddress;

    @SerializedName("EndAddress")
    private Address endAddress;

    @SerializedName("ForcefullyEnded")
    private boolean forcefullyEnded;

    @SerializedName("StartMilage")
    private float startMilage;

    @SerializedName("EndMilage")
    private float endMilage;

    @SerializedName("StartOdometer")
    private float startOdometer;

    @SerializedName("_id")
    private String id;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Address getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(Address endAddress) {
        this.endAddress = endAddress;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public float getEndMilage() {
        return endMilage;
    }

    public void setEndMilage(float endMilage) {
        this.endMilage = endMilage;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isForcefullyEnded() {
        return forcefullyEnded;
    }

    public void setForcefullyEnded(boolean forcefullyEnded) {
        this.forcefullyEnded = forcefullyEnded;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public float getMaxAcceleration() {
        return maxAcceleration;
    }

    public void setMaxAcceleration(float maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }

    public float getMaxDeceleration() {
        return maxDeceleration;
    }

    public void setMaxDeceleration(float maxDeceleration) {
        this.maxDeceleration = maxDeceleration;
    }

    public int getMaxRPM() {
        return maxRPM;
    }

    public void setMaxRPM(int maxRPM) {
        this.maxRPM = maxRPM;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getMojioId() {
        return mojioId;
    }

    public void setMojioId(String mojioId) {
        this.mojioId = mojioId;
    }

    public Address getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(Address startAddress) {
        this.startAddress = startAddress;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public float getStartMilage() {
        return startMilage;
    }

    public void setStartMilage(float startMilage) {
        this.startMilage = startMilage;
    }

    public float getStartOdometer() {
        return startOdometer;
    }

    public void setStartOdometer(float startOdometer) {
        this.startOdometer = startOdometer;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "distance=" + distance +
                ", mojioId='" + mojioId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", lastUpdatedTime='" + lastUpdatedTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", maxAcceleration=" + maxAcceleration +
                ", maxDeceleration=" + maxDeceleration +
                ", maxRPM=" + maxRPM +
                ", fuelLevel=" + fuelLevel +
                ", fuelEfficiency=" + fuelEfficiency +
                ", startLocation=" + startLocation +
                ", lastKnownLocation=" + lastKnownLocation +
                ", endLocation=" + endLocation +
                ", startAddress=" + startAddress +
                ", endAddress=" + endAddress +
                ", forcefullyEnded=" + forcefullyEnded +
                ", startMilage=" + startMilage +
                ", endMilage=" + endMilage +
                ", startOdometer=" + startOdometer +
                ", id='" + id + '\'' +
                '}';
    }
}
