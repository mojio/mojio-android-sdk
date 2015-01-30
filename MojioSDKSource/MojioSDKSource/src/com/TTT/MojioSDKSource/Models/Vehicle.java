package com.TTT.MojioSDKSource.Models;

import java.util.ArrayList;


public class Vehicle {
	
	private String Type;
	private String OwnerId;
	private String MojioId;
	private String Name;
	private String VIN;
	private String LicensePlate;
	private boolean IgnitionOn;
	private String VehicleTime;
	private String LastTripEvent;
	private String LastLocationTime;
	private LastLocation LastLocation;
	private float LastSpeed;
	private float FuelLevel;
	private float LastAcceleration;
	private LastAccelerometer LastAccelerometer;
	private float LastAltitude;
	private float LastBatteryVoltage;
	private float LastDistance;
	private float LastHeading;
	private float LastVirtualOdometer;
	private float LastOdometer;
	private float LastRpm;
	private float LastFuelEfficiency;
	private String CurrentTrip;
	private String LastTrip;
	private String LastContactTime;
	private boolean MilStatus;
	private Object DiagnosticCodes;
	private boolean FaultsDetected;
	private ArrayList<String> LastLocationTimes;
	private ArrayList<Float> LastLocations;
	private ArrayList<Float> LastSpeeds;
	private ArrayList<Float> LastHeadings;
	private ArrayList<Float> LastAltitudes;
	private ArrayList<Object> Viewers;
	private String _id;
	private boolean _deleted;
	
	
	
	public Vehicle(){
		
	}



	public String getType() {
		return Type;
	}



	public void setType(String type) {
		Type = type;
	}



	public String getOwnerId() {
		return OwnerId;
	}



	public void setOwnerId(String ownerId) {
		OwnerId = ownerId;
	}



	public String getMojioId() {
		return MojioId;
	}



	public void setMojioId(String mojioId) {
		MojioId = mojioId;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getVIN() {
		return VIN;
	}



	public void setVIN(String vIN) {
		VIN = vIN;
	}



	public String getLicensePlate() {
		return LicensePlate;
	}



	public void setLicensePlate(String licensePlate) {
		LicensePlate = licensePlate;
	}



	public boolean isIgnitionOn() {
		return IgnitionOn;
	}



	public void setIgnitionOn(boolean ignitionOn) {
		IgnitionOn = ignitionOn;
	}



	public String getVehicleTime() {
		return VehicleTime;
	}



	public void setVehicleTime(String vehicleTime) {
		VehicleTime = vehicleTime;
	}



	public String getLastTripEvent() {
		return LastTripEvent;
	}



	public void setLastTripEvent(String lastTripEvent) {
		LastTripEvent = lastTripEvent;
	}



	public String getLastLocationTime() {
		return LastLocationTime;
	}



	public void setLastLocationTime(String lastLocationTime) {
		LastLocationTime = lastLocationTime;
	}



	public LastLocation getLastLocation() {
		return LastLocation;
	}



	public void setLastLocation(LastLocation lastLocation) {
		LastLocation = lastLocation;
	}



	public float getLastSpeed() {
		return LastSpeed;
	}



	public void setLastSpeed(float lastSpeed) {
		LastSpeed = lastSpeed;
	}



	public float getFuelLevel() {
		return FuelLevel;
	}



	public void setFuelLevel(float fuelLevel) {
		FuelLevel = fuelLevel;
	}



	public float getLastAcceleration() {
		return LastAcceleration;
	}



	public void setLastAcceleration(float lastAcceleration) {
		LastAcceleration = lastAcceleration;
	}



	public LastAccelerometer getLastAccelerometer() {
		return LastAccelerometer;
	}



	public void setLastAccelerometer(LastAccelerometer lastAccelerometer) {
		LastAccelerometer = lastAccelerometer;
	}



	public float getLastAltitude() {
		return LastAltitude;
	}



	public void setLastAltitude(float lastAltitude) {
		LastAltitude = lastAltitude;
	}



	public float getLastBatteryVoltage() {
		return LastBatteryVoltage;
	}



	public void setLastBatteryVoltage(float lastBatteryVoltage) {
		LastBatteryVoltage = lastBatteryVoltage;
	}



	public float getLastDistance() {
		return LastDistance;
	}



	public void setLastDistance(float lastDistance) {
		LastDistance = lastDistance;
	}



	public float getLastHeading() {
		return LastHeading;
	}



	public void setLastHeading(float lastHeading) {
		LastHeading = lastHeading;
	}



	public float getLastVirtualOdometer() {
		return LastVirtualOdometer;
	}



	public void setLastVirtualOdometer(float lastVirtualOdometer) {
		LastVirtualOdometer = lastVirtualOdometer;
	}



	public float getLastOdometer() {
		return LastOdometer;
	}



	public void setLastOdometer(float lastOdometer) {
		LastOdometer = lastOdometer;
	}



	public float getLastRpm() {
		return LastRpm;
	}



	public void setLastRpm(float lastRpm) {
		LastRpm = lastRpm;
	}



	public float getLastFuelEfficiency() {
		return LastFuelEfficiency;
	}



	public void setLastFuelEfficiency(float lastFuelEfficiency) {
		LastFuelEfficiency = lastFuelEfficiency;
	}



	public String getCurrentTrip() {
		return CurrentTrip;
	}



	public void setCurrentTrip(String currentTrip) {
		CurrentTrip = currentTrip;
	}



	public String getLastTrip() {
		return LastTrip;
	}



	public void setLastTrip(String lastTrip) {
		LastTrip = lastTrip;
	}



	public String getLastContactTime() {
		return LastContactTime;
	}



	public void setLastContactTime(String lastContactTime) {
		LastContactTime = lastContactTime;
	}



	public boolean isMilStatus() {
		return MilStatus;
	}



	public void setMilStatus(boolean milStatus) {
		MilStatus = milStatus;
	}



	public Object getDiagnosticCodes() {
		return DiagnosticCodes;
	}



	public void setDiagnosticCodes(Object diagnosticCodes) {
		DiagnosticCodes = diagnosticCodes;
	}



	public boolean isFaultsDetected() {
		return FaultsDetected;
	}



	public void setFaultsDetected(boolean faultsDetected) {
		FaultsDetected = faultsDetected;
	}



	public ArrayList<String> getLastLocationTimes() {
		return LastLocationTimes;
	}



	public void setLastLocationTimes(ArrayList<String> lastLocationTimes) {
		LastLocationTimes = lastLocationTimes;
	}



	public ArrayList<Float> getLastLocations() {
		return LastLocations;
	}



	public void setLastLocations(ArrayList<Float> lastLocations) {
		LastLocations = lastLocations;
	}



	public ArrayList<Float> getLastSpeeds() {
		return LastSpeeds;
	}



	public void setLastSpeeds(ArrayList<Float> lastSpeeds) {
		LastSpeeds = lastSpeeds;
	}



	public ArrayList<Float> getLastHeadings() {
		return LastHeadings;
	}



	public void setLastHeadings(ArrayList<Float> lastHeadings) {
		LastHeadings = lastHeadings;
	}



	public ArrayList<Float> getLastAltitudes() {
		return LastAltitudes;
	}



	public void setLastAltitudes(ArrayList<Float> lastAltitudes) {
		LastAltitudes = lastAltitudes;
	}



	public ArrayList<Object> getViewers() {
		return Viewers;
	}



	public void setViewers(ArrayList<Object> viewers) {
		Viewers = viewers;
	}



	public String get_id() {
		return _id;
	}



	public void set_id(String _id) {
		this._id = _id;
	}



	public boolean is_deleted() {
		return _deleted;
	}



	public void set_deleted(boolean _deleted) {
		this._deleted = _deleted;
	}



}
