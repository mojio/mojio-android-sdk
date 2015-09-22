package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ssawchenko on 15-02-26.
 */
public class VehicleDetails {

    @SerializedName("VIN")
    private String vin;

    @SerializedName("Timestamp")
    private String timestamp;

    @SerializedName("Market")
    private String market;

    @SerializedName("Year")
    private String year;

    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("Trim")
    private String trim;

    @SerializedName("VehicleType")
    private String vehicleType;

    @SerializedName("BodyType")
    private String bodyType;

    @SerializedName("BodySubtype")
    private String bodySubType;

    @SerializedName("Doors")
    private String doors;

    @SerializedName("OEMDoors")
    private String OEMDoors;

    @SerializedName("ModelNumber")
    private String oemBodyStyle;

    @SerializedName("DriveType")
    private String driveType;

    @SerializedName("BrakeSystem")
    private String brakeSystem;

    @SerializedName("RestraintType")
    private String restraintType;

    @SerializedName("CountryOfManufacture")
    private String countryOfManufacture;

    @SerializedName("Plant")
    private String plant;

    @SerializedName("FuelTankSize")
    private float fuelTankSize;

    @SerializedName("EPAFuelEfficiency")
    private float epaFuelEfficiency;

    @SerializedName("InstalledEngine")
    private Engine engine;

    @SerializedName("Engines")
    private ArrayList<Engine> engines;

    @SerializedName("Transmissions")
    private ArrayList<Transmission> transmissions;

    @SerializedName("Warranties")
    private ArrayList<Warranty> warranties;

    @SerializedName("ServiceBulletins")
    private ServiceBulletin [] ServiceBulletins;

    @SerializedName("Recalls")
    private Recall [] Recalls;

    @SerializedName("_id")
    private String id;
}
