package com.TTT.MojioSDKSource.Models;

public class Trip {
	
	private int Type;
	private String MojioId;
	private String VehicleId;
	private String StartTime;
	private String LastUpdatedTime;
	private String EndTime;
	private float MaxSpeed;
	private float MaxAcceleration;
	private float MaxDeceleration;
	private int MaxRPM;
	private float FuelLevel;
	private float FuelEfficiency;
	private float Distance;
	private Object StartLocation;
	private Object LastKnownLocation;
	private Object EndLocation;
	private Object StartAddress;
	private Object EndAddress;
	private boolean ForcefullyEnded;
	private float StartMilage;
	private float EndMilage;
	private float StartOdometer;
	private String _id;
	private boolean _deleted;
	
	public Trip(){
		
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public String getMojioId() {
		return MojioId;
	}

	public void setMojioId(String mojioId) {
		MojioId = mojioId;
	}

	public String getVehicleId() {
		return VehicleId;
	}

	public void setVehicleId(String vehicleId) {
		VehicleId = vehicleId;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getLastUpdatedTime() {
		return LastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		LastUpdatedTime = lastUpdatedTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public float getMaxSpeed() {
		return MaxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		MaxSpeed = maxSpeed;
	}

	public float getMaxAcceleration() {
		return MaxAcceleration;
	}

	public void setMaxAcceleration(float maxAcceleration) {
		MaxAcceleration = maxAcceleration;
	}

	public float getMaxDeceleration() {
		return MaxDeceleration;
	}

	public void setMaxDeceleration(float maxDeceleration) {
		MaxDeceleration = maxDeceleration;
	}

	public int getMaxRPM() {
		return MaxRPM;
	}

	public void setMaxRPM(int maxRPM) {
		MaxRPM = maxRPM;
	}

	public float getFuelLevel() {
		return FuelLevel;
	}

	public void setFuelLevel(float fuelLevel) {
		FuelLevel = fuelLevel;
	}

	public float getFuelEfficiency() {
		return FuelEfficiency;
	}

	public void setFuelEfficiency(float fuelEfficiency) {
		FuelEfficiency = fuelEfficiency;
	}

	public float getDistance() {
		return Distance;
	}

	public void setDistance(float distance) {
		Distance = distance;
	}

	public Object getStartLocation() {
		return StartLocation;
	}

	public void setStartLocation(Object startLocation) {
		StartLocation = startLocation;
	}

	public Object getLastKnownLocation() {
		return LastKnownLocation;
	}

	public void setLastKnownLocation(Object lastKnownLocation) {
		LastKnownLocation = lastKnownLocation;
	}

	public Object getEndLocation() {
		return EndLocation;
	}

	public void setEndLocation(Object endLocation) {
		EndLocation = endLocation;
	}

	public Object getStartAddress() {
		return StartAddress;
	}

	public void setStartAddress(Object startAddress) {
		StartAddress = startAddress;
	}

	public Object getEndAddress() {
		return EndAddress;
	}

	public void setEndAddress(Object endAddress) {
		EndAddress = endAddress;
	}

	public boolean isForcefullyEnded() {
		return ForcefullyEnded;
	}

	public void setForcefullyEnded(boolean forcefullyEnded) {
		ForcefullyEnded = forcefullyEnded;
	}

	public float getStartMilage() {
		return StartMilage;
	}

	public void setStartMilage(float startMilage) {
		StartMilage = startMilage;
	}

	public float getEndMilage() {
		return EndMilage;
	}

	public void setEndMilage(float endMilage) {
		EndMilage = endMilage;
	}

	public float getStartOdometer() {
		return StartOdometer;
	}

	public void setStartOdometer(float startOdometer) {
		StartOdometer = startOdometer;
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
