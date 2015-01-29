package com.TTT.MojioSDKSource.Models;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

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


	@JsonProperty("Type")
	public String getType() {
		return Type;
	}



	public void setType(String type) {
		Type = type;
	}


	@JsonProperty("OwnerId")
	public String getOwnerId() {
		return OwnerId;
	}



	public void setOwnerId(String ownerId) {
		OwnerId = ownerId;
	}


	@JsonProperty("MojioId")
	public String getMojioId() {
		return MojioId;
	}



	public void setMojioId(String mojioId) {
		MojioId = mojioId;
	}


	@JsonProperty("Name")
	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}


	@JsonProperty("VIN")
	public String getVIN() {
		return VIN;
	}



	public void setVIN(String vIN) {
		VIN = vIN;
	}


	@JsonProperty("LicensePlate")
	public String getLicensePlate() {
		return LicensePlate;
	}



	public void setLicensePlate(String licencePlate) {
		LicensePlate = licencePlate;
	}


	@JsonProperty("IgnitionOn")
	public boolean isIgnitionOn() {
		return IgnitionOn;
	}



	public void setIgnitionOn(boolean ignitionOn) {
		IgnitionOn = ignitionOn;
	}


	@JsonProperty("VehicleTime")
	public String getVehicleTime() {
		return VehicleTime;
	}



	public void setVehicleTime(String vehicleTime) {
		VehicleTime = vehicleTime;
	}


	@JsonProperty("LastTripEvent")
	public String getLastTripEvent() {
		return LastTripEvent;
	}



	public void setLastTripEvent(String lastTripEvent) {
		LastTripEvent = lastTripEvent;
	}


	@JsonProperty("LastLocationTime")
	public String getLastLocationTime() {
		return LastLocationTime;
	}



	public void setLastLocationTime(String lastLocationTime) {
		LastLocationTime = lastLocationTime;
	}


	@JsonProperty("LastLocation")
	public LastLocation getLastLocation() {
		return LastLocation;
	}



	public void setLastLocation(LastLocation lastLocation) {
		LastLocation = lastLocation;
	}


	@JsonProperty("LastSpeed")
	public float getLastSpeed() {
		return LastSpeed;
	}



	public void setLastSpeed(float lastSpeed) {
		LastSpeed = lastSpeed;
	}


	@JsonProperty("FuelLevel")
	public float getFuelLevel() {
		return FuelLevel;
	}



	public void setFuelLevel(float fuelLevel) {
		FuelLevel = fuelLevel;
	}


	@JsonProperty("LastAcceleration")
	public float getLastAcceleration() {
		return LastAcceleration;
	}



	public void setLastAcceleration(float lastAcceleration) {
		LastAcceleration = lastAcceleration;
	}


	@JsonProperty("LastAccelerometer")
	public LastAccelerometer getLastAccelerometer() {
		return LastAccelerometer;
	}



	public void setLastAccelerometer(LastAccelerometer lastAccelerometer) {
		LastAccelerometer = lastAccelerometer;
	}


	@JsonProperty("LastAltitude")
	public float getLastAltitude() {
		return LastAltitude;
	}


	@JsonProperty("LastAltitude")
	public void setLastAltitude(float lastAltitude) {
		LastAltitude = lastAltitude;
	}


	@JsonProperty("LastBatteryVoltage")
	public float getLastBatteryVoltage() {
		return LastBatteryVoltage;
	}



	public void setLastBatteryVoltage(float lastBatteryVoltage) {
		LastBatteryVoltage = lastBatteryVoltage;
	}


	@JsonProperty("LastDistance")
	public float getLastDistance() {
		return LastDistance;
	}



	public void setLastDistance(float lastDistance) {
		LastDistance = lastDistance;
	}


	@JsonProperty("LastHeading")
	public float getLastHeading() {
		return LastHeading;
	}



	public void setLastHeading(float lastHeading) {
		LastHeading = lastHeading;
	}


	@JsonProperty("LastVirtualOdometer")
	public float getLastVirtualOdometer() {
		return LastVirtualOdometer;
	}



	public void setLastVirtualOdometer(float lastVirtualOdometer) {
		LastVirtualOdometer = lastVirtualOdometer;
	}


	@JsonProperty("LastOdometer")
	public float getLastOdometer() {
		return LastOdometer;
	}



	public void setLastOdometer(float lastOdometer) {
		LastOdometer = lastOdometer;
	}


	@JsonProperty("LastRpm")
	public float getLastRpm() {
		return LastRpm;
	}



	public void setLastRpm(float lastRpm) {
		LastRpm = lastRpm;
	}


	@JsonProperty("LastFuelEfficiency")
	public float getLastFuelEfficiency() {
		return LastFuelEfficiency;
	}



	public void setLastFuelEfficiency(float lastFuelEfficiency) {
		LastFuelEfficiency = lastFuelEfficiency;
	}


	@JsonProperty("CurrentTrip")
	public String getCurrentTrip() {
		return CurrentTrip;
	}



	public void setCurrentTrip(String currentTrip) {
		CurrentTrip = currentTrip;
	}


	@JsonProperty("LastTrip")
	public String getLastTrip() {
		return LastTrip;
	}



	public void setLastTrip(String lastTrip) {
		LastTrip = lastTrip;
	}


	@JsonProperty("LastContactTime")
	public String getLastContactTime() {
		return LastContactTime;
	}



	public void setLastContactTime(String lastContactTime) {
		LastContactTime = lastContactTime;
	}


	@JsonProperty("MilStatus")
	public boolean isMilStatus() {
		return MilStatus;
	}



	public void setMilStatus(boolean milStatus) {
		MilStatus = milStatus;
	}


	@JsonProperty("DiagnosticCodes")
	public Object getDiagnosticCodes() {
		return DiagnosticCodes;
	}



	public void setDiagnosticCodes(Object diagnosticCodes) {
		DiagnosticCodes = diagnosticCodes;
	}


	@JsonProperty("FaultsDetected")
	public boolean isFaultsDetected() {
		return FaultsDetected;
	}



	public void setFaultsDetected(boolean faultsDetected) {
		FaultsDetected = faultsDetected;
	}


	@JsonProperty("LastLocationTimes")
	public ArrayList<String> getLastLocationTimes() {
		return LastLocationTimes;
	}



	public void setLastLocationTimes(ArrayList<String> lastLocationTimes) {
		LastLocationTimes = lastLocationTimes;
	}


	@JsonProperty("LastLocations")
	public ArrayList<Float> getLastLocations() {
		return LastLocations;
	}



	public void setLastLocations(ArrayList<Float> lastLocations) {
		LastLocations = lastLocations;
	}


	@JsonProperty("LastSpeeds")
	public ArrayList<Float> getLastSpeeds() {
		return LastSpeeds;
	}



	public void setLastSpeeds(ArrayList<Float> lastSpeeds) {
		LastSpeeds = lastSpeeds;
	}


	@JsonProperty("LastHeadings")
	public ArrayList<Float> getLastHeadings() {
		return LastHeadings;
	}



	public void setLastHeadings(ArrayList<Float> lastHeadings) {
		LastHeadings = lastHeadings;
	}


	@JsonProperty("LastAltitudes")
	public ArrayList<Float> getLastAltitudes() {
		return LastAltitudes;
	}



	public void setLastAltitudes(ArrayList<Float> lastAltitudes) {
		LastAltitudes = lastAltitudes;
	}


	@JsonProperty("Viewers")
	public ArrayList<Object> getViewers() {
		return Viewers;
	}



	public void setViewers(ArrayList<Object> viewers) {
		Viewers = viewers;
	}

	@JsonProperty("_deleted")
	public boolean is_deleted() {
		return _deleted;
	}


	public void set_deleted(boolean _deleted) {
		this._deleted = _deleted;
	}

	@JsonProperty("_id")
	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}







}
