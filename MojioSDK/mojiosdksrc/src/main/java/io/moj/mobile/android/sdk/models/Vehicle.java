package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model class for a vehicle.
 * Created by ssawchenko on 15-02-01.
 */
public class Vehicle extends MojioObject {

    @SerializedName("OwnerId")
    private String ownerId;

    @SerializedName("mojioId")
    private String mojioId;

    @SerializedName("Name")
    private String name;

    @SerializedName("VIN")
    private String VIN;

    @SerializedName("LicensePlate")
    private String licensePlate;

    @SerializedName("IgnitionOn")
    private boolean ignitionOn;

    @SerializedName("vehicleTime")
    private String vehicleTime;

    @SerializedName("LastTripEvent")
    private String lastTripEvent;

    @SerializedName("LastLocationTime")
    private String lastLocationTime;

    @SerializedName("LastLocation")
    private Location lastLocation;

    @SerializedName("LastSpeed")
    private float lastSpeed;

    @SerializedName("FuelLevel")
    private float fuelLevel;

    @SerializedName("LastAcceleration")
    private float lastAcceleration;

    @SerializedName("LastAltitude")
    private float lastAltitude;

    @SerializedName("LastBatteryVoltage")
    private float lastBatteryVoltage;

    @SerializedName("LastDistance")
    private float lastDistance;

    @SerializedName("LastHeading")
    private float lastHeading;

    @SerializedName("LastVirtualOdometer")
    private float lastVirtualOdometer;

    @SerializedName("LastOdometer")
    private float lastOdometer;

    @SerializedName("LastRpm")
    private float lastRpm;

    @SerializedName("LastFuelEfficiency")
    private float lastFuelEfficiency;

    @SerializedName("CurrentTrip")
    private String currentTrip;

    @SerializedName("LastTrip")
    private String lastTrip;

    @SerializedName("LastContactTime")
    private String lastContactTime;

    @SerializedName("MilStatus")
    private boolean milStatus;

    @SerializedName("DiagnosticCodes")
    private DiagnosticStatus diagnosticStatus;

    @SerializedName("FaultsDetected")
    private boolean faultsDetected;

    @SerializedName("LastLocationTimes")
    private List<String> lastLocationTimes;

    @SerializedName("LastLocations")
    private List<Location> lastLocations;

    @SerializedName("LastSpeeds")
    private List<Float> lastSpeeds;

    @SerializedName("LastHeadings")
    private List<Float> lastHeadings;

    @SerializedName("LastAltitudes")
    private List<Float> lastAltitudes;

    @SerializedName("Viewers")
    private List<String> viewers;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getMojioId() {
        return mojioId;
    }

    public void setMojioId(String mojioId) {
        this.mojioId = mojioId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isIgnitionOn() {
        return ignitionOn;
    }

    public void setIgnitionOn(boolean ignitionOn) {
        this.ignitionOn = ignitionOn;
    }

    public String getVehicleTime() {
        return vehicleTime;
    }

    public void setVehicleTime(String vehicleTime) {
        this.vehicleTime = vehicleTime;
    }

    public String getLastTripEvent() {
        return lastTripEvent;
    }

    public void setLastTripEvent(String lastTripEvent) {
        this.lastTripEvent = lastTripEvent;
    }

    public String getLastLocationTime() {
        return lastLocationTime;
    }

    public void setLastLocationTime(String lastLocationTime) {
        this.lastLocationTime = lastLocationTime;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public float getLastSpeed() {
        return lastSpeed;
    }

    public void setLastSpeed(float lastSpeed) {
        this.lastSpeed = lastSpeed;
    }

    public float getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(float fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public float getLastAcceleration() {
        return lastAcceleration;
    }

    public void setLastAcceleration(float lastAcceleration) {
        this.lastAcceleration = lastAcceleration;
    }

    public float getLastAltitude() {
        return lastAltitude;
    }

    public void setLastAltitude(float lastAltitude) {
        this.lastAltitude = lastAltitude;
    }

    public float getLastBatteryVoltage() {
        return lastBatteryVoltage;
    }

    public void setLastBatteryVoltage(float lastBatteryVoltage) {
        this.lastBatteryVoltage = lastBatteryVoltage;
    }

    public float getLastDistance() {
        return lastDistance;
    }

    public void setLastDistance(float lastDistance) {
        this.lastDistance = lastDistance;
    }

    public float getLastHeading() {
        return lastHeading;
    }

    public void setLastHeading(float lastHeading) {
        this.lastHeading = lastHeading;
    }

    public float getLastVirtualOdometer() {
        return lastVirtualOdometer;
    }

    public void setLastVirtualOdometer(float lastVirtualOdometer) {
        this.lastVirtualOdometer = lastVirtualOdometer;
    }

    public float getLastOdometer() {
        return lastOdometer;
    }

    public void setLastOdometer(float lastOdometer) {
        this.lastOdometer = lastOdometer;
    }

    public float getLastRpm() {
        return lastRpm;
    }

    public void setLastRpm(float lastRpm) {
        this.lastRpm = lastRpm;
    }

    public float getLastFuelEfficiency() {
        return lastFuelEfficiency;
    }

    public void setLastFuelEfficiency(float lastFuelEfficiency) {
        this.lastFuelEfficiency = lastFuelEfficiency;
    }

    public String getCurrentTrip() {
        return currentTrip;
    }

    public void setCurrentTrip(String currentTrip) {
        this.currentTrip = currentTrip;
    }

    public String getLastTrip() {
        return lastTrip;
    }

    public void setLastTrip(String lastTrip) {
        this.lastTrip = lastTrip;
    }

    public String getLastContactTime() {
        return lastContactTime;
    }

    public void setLastContactTime(String lastContactTime) {
        this.lastContactTime = lastContactTime;
    }

    public boolean isMilStatus() {
        return milStatus;
    }

    public void setMilStatus(boolean milStatus) {
        this.milStatus = milStatus;
    }

    public DiagnosticStatus getDiagnosticStatus() {
        return diagnosticStatus;
    }

    public void setDiagnosticStatus(DiagnosticStatus diagnosticStatus) {
        this.diagnosticStatus = diagnosticStatus;
    }

    public boolean isFaultsDetected() {
        return faultsDetected;
    }

    public void setFaultsDetected(boolean faultsDetected) {
        this.faultsDetected = faultsDetected;
    }

    public List<String> getLastLocationTimes() {
        return lastLocationTimes;
    }

    public void setLastLocationTimes(List<String> lastLocationTimes) {
        this.lastLocationTimes = lastLocationTimes;
    }

    public List<Location> getLastLocations() {
        return lastLocations;
    }

    public void setLastLocations(List<Location> lastLocations) {
        this.lastLocations = lastLocations;
    }

    public List<Float> getLastSpeeds() {
        return lastSpeeds;
    }

    public void setLastSpeeds(List<Float> lastSpeeds) {
        this.lastSpeeds = lastSpeeds;
    }

    public List<Float> getLastHeadings() {
        return lastHeadings;
    }

    public void setLastHeadings(List<Float> lastHeadings) {
        this.lastHeadings = lastHeadings;
    }

    public List<Float> getLastAltitudes() {
        return lastAltitudes;
    }

    public void setLastAltitudes(List<Float> lastAltitudes) {
        this.lastAltitudes = lastAltitudes;
    }

    public List<String> getViewers() {
        return viewers;
    }

    public void setViewers(List<String> viewers) {
        this.viewers = viewers;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "currentTrip='" + currentTrip + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", mojioId='" + mojioId + '\'' +
                ", name='" + name + '\'' +
                ", VIN='" + VIN + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", ignitionOn=" + ignitionOn +
                ", vehicleTime='" + vehicleTime + '\'' +
                ", lastTripEvent='" + lastTripEvent + '\'' +
                ", lastLocationTime='" + lastLocationTime + '\'' +
                ", lastLocation=" + lastLocation +
                ", lastSpeed=" + lastSpeed +
                ", fuelLevel=" + fuelLevel +
                ", lastAcceleration=" + lastAcceleration +
                ", lastAltitude=" + lastAltitude +
                ", lastBatteryVoltage=" + lastBatteryVoltage +
                ", lastDistance=" + lastDistance +
                ", lastHeading=" + lastHeading +
                ", lastVirtualOdometer=" + lastVirtualOdometer +
                ", lastOdometer=" + lastOdometer +
                ", lastRpm=" + lastRpm +
                ", lastFuelEfficiency=" + lastFuelEfficiency +
                ", lastTrip='" + lastTrip + '\'' +
                ", lastContactTime='" + lastContactTime + '\'' +
                ", milStatus=" + milStatus +
                ", diagnosticStatus=" + diagnosticStatus +
                ", faultsDetected=" + faultsDetected +
                ", lastLocationTimes=" + lastLocationTimes +
                ", lastLocations=" + lastLocations +
                ", lastSpeeds=" + lastSpeeds +
                ", lastHeadings=" + lastHeadings +
                ", lastAltitudes=" + lastAltitudes +
                ", viewers=" + viewers +
                "} " + super.toString();
    }
}