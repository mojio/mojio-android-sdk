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
    private String VehicleName;
    public void setVehicleName(String name) { VehicleName = name; }
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

    // Not from Json - populated on demand
    public Trip LastTripDetails;
    public int VehicleImage;
    public VehicleDetails VehicleDetails;

    public String getLastContactTimeDescription() {
        // TODO time format
        return this.LastContactTime;
    }

    public String getNameDescription() {
        return (VehicleName == null) ? "Unknown Vehicle" : VehicleName;
    }

    public String getDrivingDescription() {
        return (IgnitionOn) ? "Driving" : "Parked";
    }

    public boolean isConnected() {
        return (this.MojioId != null) && (!this.MojioId.isEmpty());
    }

    public int getBatteryPercentage() {
        float result = (this.LastBatteryVoltage / 12.5f) * 100;
        return (int)result;
    }

    public int getFuelPercentage() {
        return (int)(this.FuelLevel);
    }

    public int getDisplacementPercentage() {
        if (this.VehicleDetails != null) {
            return (this.VehicleDetails.InstalledEngine.Displacement);
        }
        return 0;
    }
}
