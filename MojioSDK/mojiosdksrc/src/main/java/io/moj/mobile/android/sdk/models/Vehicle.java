package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ssawchenko on 15-02-01.
 */
public class Vehicle {

    //===================================================================
    // Mojio Vehicle data model
    //===================================================================
    public String Type;
    public String OwnerId;
    public String MojioId;
    @SerializedName("Name")
    public String VehicleName;
    public String VIN;
    public String LicensePlate;
    public boolean IgnitionOn;
    public String VehicleTime;
    public String LastTripEvent;
    public String LastLocationTime;
    public Location LastLocation;
    public float LastSpeed;
    public float FuelLevel;
    public float LastAcceleration;
    public float LastAltitude;
    public float LastBatteryVoltage;
    public float LastDistance;
    public float LastHeading;
    public float LastVirtualOdometer;
    public float LastOdometer;
    public float LastRpm;
    public float LastFuelEfficiency;
    public float LastFuelEfficiencyScore;
    public float LastFuelEfficiencyScoreChange;
    public String CurrentTrip;
    public String LastTrip;
    public String LastContactTime;
    public boolean MilStatus;
    public Object DiagnosticCodes;
    public boolean FaultsDetected;
    public ArrayList<String> LastLocationTimes;
    public ArrayList<Float> LastLocations;
    public ArrayList<Float> LastSpeeds;
    public ArrayList<Float> LastHeadings;
    public ArrayList<Float> LastAltitudes;
    public ArrayList<Object> Viewers;
    public String _id;
    public boolean _deleted;
}