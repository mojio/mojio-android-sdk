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

    @SerializedName("_deleted")
    private boolean deleted;
	
}
