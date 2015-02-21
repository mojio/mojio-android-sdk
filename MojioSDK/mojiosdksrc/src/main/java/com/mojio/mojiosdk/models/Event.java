package com.mojio.mojiosdk.models;

/**
 * Created by ssawchenko on 15-02-20.
 */
public class Event {
    public String MojioId;
    public String VehicleId;
    public String OwnerId;
    public String EventType;
    public String Time;
    public Location Location;
    //public String Accelerometer;
    public boolean TimeIsApprox;
    public boolean BatteryVoltage;
    public boolean ConnectionLost;
    public String _id;

    public Diagnostics[] DTCs;

}
