package com.mojio.mojiosdk.models;

import com.google.gson.annotations.SerializedName;
import com.mojio.mojiosdk.units.Measure;

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
    private String VehicleName;
    public void setVehicleName(String name) { VehicleName = name; }
    private String VIN;
    public String getVIN() { return VIN; }
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
    private float LastVirtualOdometer;
    private float LastOdometer;
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

    //===================================================================
    // Not from Json - apps must set these values if they want to use them
    //===================================================================
    public Trip LastTripDetails;
    public int VehicleImage;
    public VehicleDetails VehicleDetails;
    public ArrayList<ServiceNote> ServiceNotes;
    public String LastServiceODO;

    //===================================================================
    // Get methods
    //===================================================================
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

    /**
     * Currently 14V is max
     */
    public int getBatteryPercentage() {
        float result = (this.LastBatteryVoltage / 14f) * 100;
        return (int)result;
    }

    public int getFuelPercentage() {
        return (int)(this.FuelLevel);
    }

    /**
     * For now always return 100.
     * TODO Determine the percentage range for displacement.
     */
    public int getDisplacementPercentage() {
        return 100;
        /*
        if (this.VehicleDetails != null) {
            return (this.VehicleDetails.InstalledEngine.Displacement);
        }
        return 0;
        */
    }

    //===================================================================
    // AltVIN
    // Indicates if the VIN came from the altVin data store or has been entered manually
    //===================================================================
    private boolean usingAltVIN = false;
    public void setAltVIN(String vin) {
        VIN = vin;
        usingAltVIN = true;
    }

    public void clearAltVIN() {
        VIN = null;
        usingAltVIN = false;
        VehicleDetails = null;
    }

    public boolean isUsingAltVIN() {
        return usingAltVIN;
    }

    //===================================================================
    // Unit conversion helpers
    // NOTE Currently Distance units are stored per user; we may want to change
    // this to be stored per vehicle. If stored per vehicle we would not
    // have to pass in the unit enum to each of these methods.
    //===================================================================
    /**
     * Units from SDK units.Measure class
     */
    public float getOdometerForUnits(int units) {
        // If last odometer is null, then use last virtual odometer
        Float result = LastOdometer;
        if (result == null) {
            result = LastVirtualOdometer;
        }

        switch (units) {
            case Measure.IMPERIAL:
                result = Measure.kmsToMiles(result);
                break;
        }

        return result;
    }

    public void setOdometerForUnits(float odometer, int units) {
        float result = odometer;

        switch (units) {
            case Measure.IMPERIAL:
                result = Measure.kmsToMiles(result);
                break;
        }

        LastOdometer = odometer;
    }

    // TODO instead search through list of last speeds?
    public float getLastMaxSpeedForUnits(int units) {
        float result = LastTripDetails.MaxSpeed;

        switch (units) {
            case Measure.IMPERIAL:
                result = Measure.kmsToMiles(result);
                break;
        }

        return result;
    }

    public float getLastDistanceForUnits(int units) {
        float result = LastTripDetails.Distance;

        switch (units) {
            case Measure.IMPERIAL:
                result = Measure.kmsToMiles(result);
                break;
        }

        return result;
    }

    /**
     *
     */
    public float getFuelEfficiencyForUnits(int units) {
        float result = LastFuelEfficiency;

        switch (units) {
            case Measure.IMPERIAL:
                result = Measure.lp100kmTompg(result);
                break;
        }

        return result;
    }
}
