package com.mojio.mojiosdk.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ssawchenko on 15-02-01.
 */
public class Vehicle {

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
    //public LastLocation LastLocation;
    public float LastSpeed;
    public float FuelLevel;
    public float LastAcceleration;
    //public LastAccelerometer LastAccelerometer;
    public float LastAltitude;
    public float LastBatteryVoltage;
    public float LastDistance;
    public float LastHeading;
    public float LastVirtualOdometer;
    public float LastOdometer;
    public float LastRpm;
    public float LastFuelEfficiency;
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

    // Note, currently the Vehicle model does not contain this info
    // TODO Talk to Ashish
    public String getModelAndYearDescription() {
        return String.format("Model: %s Year: %s", "N/A", "N/A");
    }

    public String getLicensePlateDescription() {
        return String.format("License Plate: %s", this.LicensePlate);
    }

    public String getLastContactTimeDescription() {
        // TODO time format
        return this.LastContactTime;
    }

    public String getLastContactLocationDescription() {
        // TODO Talk to Ashish, not returned in data
        return "N/A";
    }
}