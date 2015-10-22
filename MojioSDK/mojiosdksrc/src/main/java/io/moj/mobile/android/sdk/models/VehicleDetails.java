package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Model class for vehicle details.
 * Created by ssawchenko on 15-02-26.
 */
public class VehicleDetails extends MojioObject {

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

    @SerializedName("OEMBodyStyle")
    private String oemBodyStyle;

    @SerializedName("Doors")
    private String doors;

    @SerializedName("OEMDoors")
    private String OEMDoors;

    @SerializedName("ModelNumber")
    private String modelNumber;

    @SerializedName("PackageCode")
    private String packageCode;

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
    private Engine installedEngine;

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

    public String getBodySubType() {
        return bodySubType;
    }

    public void setBodySubType(String bodySubType) {
        this.bodySubType = bodySubType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getBrakeSystem() {
        return brakeSystem;
    }

    public void setBrakeSystem(String brakeSystem) {
        this.brakeSystem = brakeSystem;
    }

    public String getCountryOfManufacture() {
        return countryOfManufacture;
    }

    public void setCountryOfManufacture(String countryOfManufacture) {
        this.countryOfManufacture = countryOfManufacture;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public ArrayList<Engine> getEngines() {
        return engines;
    }

    public void setEngines(ArrayList<Engine> engines) {
        this.engines = engines;
    }

    public float getEpaFuelEfficiency() {
        return epaFuelEfficiency;
    }

    public void setEpaFuelEfficiency(float epaFuelEfficiency) {
        this.epaFuelEfficiency = epaFuelEfficiency;
    }

    public float getFuelTankSize() {
        return fuelTankSize;
    }

    public void setFuelTankSize(float fuelTankSize) {
        this.fuelTankSize = fuelTankSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getOemBodyStyle() {
        return oemBodyStyle;
    }

    public void setOemBodyStyle(String oemBodyStyle) {
        this.oemBodyStyle = oemBodyStyle;
    }

    public String getOEMDoors() {
        return OEMDoors;
    }

    public void setOEMDoors(String OEMDoors) {
        this.OEMDoors = OEMDoors;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public Recall[] getRecalls() {
        return Recalls;
    }

    public void setRecalls(Recall[] recalls) {
        Recalls = recalls;
    }

    public String getRestraintType() {
        return restraintType;
    }

    public void setRestraintType(String restraintType) {
        this.restraintType = restraintType;
    }

    public ServiceBulletin[] getServiceBulletins() {
        return ServiceBulletins;
    }

    public void setServiceBulletins(ServiceBulletin[] serviceBulletins) {
        ServiceBulletins = serviceBulletins;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Transmission> getTransmissions() {
        return transmissions;
    }

    public void setTransmissions(ArrayList<Transmission> transmissions) {
        this.transmissions = transmissions;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public ArrayList<Warranty> getWarranties() {
        return warranties;
    }

    public void setWarranties(ArrayList<Warranty> warranties) {
        this.warranties = warranties;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Engine getInstalledEngine() {
        return installedEngine;
    }

    public void setInstalledEngine(Engine installedEngine) {
        this.installedEngine = installedEngine;
    }

    @Override
    public String toString() {
        return "VehicleDetails{" +
                "bodySubType='" + bodySubType + '\'' +
                ", vin='" + vin + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", market='" + market + '\'' +
                ", year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", trim='" + trim + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", oemBodyStyle='" + oemBodyStyle + '\'' +
                ", doors='" + doors + '\'' +
                ", OEMDoors='" + OEMDoors + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", packageCode='" + packageCode + '\'' +
                ", driveType='" + driveType + '\'' +
                ", brakeSystem='" + brakeSystem + '\'' +
                ", restraintType='" + restraintType + '\'' +
                ", countryOfManufacture='" + countryOfManufacture + '\'' +
                ", plant='" + plant + '\'' +
                ", fuelTankSize=" + fuelTankSize +
                ", epaFuelEfficiency=" + epaFuelEfficiency +
                ", installedEngine=" + installedEngine +
                ", engines=" + engines +
                ", transmissions=" + transmissions +
                ", warranties=" + warranties +
                ", ServiceBulletins=" + Arrays.toString(ServiceBulletins) +
                ", Recalls=" + Arrays.toString(Recalls) +
                ", id='" + id + '\'' +
                '}';
    }
}
