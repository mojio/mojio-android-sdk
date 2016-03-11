package io.moj.mobile.android.sdk.model.values;

import java.util.List;

/**
 * Model object for vehicle details based on VIN.
 * Created by mhorie on 2016-01-14.
 */
public class VinDetails {

    private String Vin;
    private String Timestamp;
    private String Market;
    private Integer Year;
    private String Make;
    private String Model;
    private String VehicleType;
    private String BodyType;
    private String DriveType;
    private Double FuelTankSize;
    private Double EPAFuelEfficiency;
    private Engine Engine;
    private Transmission Transmission;
    private List<Warranty> Warranties;
    private List<Recall> Recalls;
    private List<ServiceBulletin> ServiceBulletins;

    public String getBodyType() {
        return BodyType;
    }

    public void setBodyType(String bodyType) {
        BodyType = bodyType;
    }

    public String getDriveType() {
        return DriveType;
    }

    public void setDriveType(String driveType) {
        DriveType = driveType;
    }

    public io.moj.mobile.android.sdk.model.values.Engine getEngine() {
        return Engine;
    }

    public void setEngine(io.moj.mobile.android.sdk.model.values.Engine engine) {
        Engine = engine;
    }

    public Double getEPAFuelEfficiency() {
        return EPAFuelEfficiency;
    }

    public void setEPAFuelEfficiency(Double EPAFuelEfficiency) {
        this.EPAFuelEfficiency = EPAFuelEfficiency;
    }

    public Double getFuelTankSize() {
        return FuelTankSize;
    }

    public void setFuelTankSize(Double fuelTankSize) {
        FuelTankSize = fuelTankSize;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getMarket() {
        return Market;
    }

    public void setMarket(String market) {
        Market = market;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public List<Recall> getRecalls() {
        return Recalls;
    }

    public void setRecalls(List<Recall> recalls) {
        Recalls = recalls;
    }

    public List<ServiceBulletin> getServiceBulletins() {
        return ServiceBulletins;
    }

    public void setServiceBulletins(List<ServiceBulletin> serviceBulletins) {
        ServiceBulletins = serviceBulletins;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public io.moj.mobile.android.sdk.model.values.Transmission getTransmission() {
        return Transmission;
    }

    public void setTransmission(io.moj.mobile.android.sdk.model.values.Transmission transmission) {
        Transmission = transmission;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public List<Warranty> getWarranties() {
        return Warranties;
    }

    public void setWarranties(List<Warranty> warranties) {
        Warranties = warranties;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    @Override
    public String toString() {
        return "VinDetails{" +
                "BodyType='" + BodyType + '\'' +
                ", Vin='" + Vin + '\'' +
                ", Timestamp='" + Timestamp + '\'' +
                ", Market='" + Market + '\'' +
                ", Year=" + Year +
                ", Make='" + Make + '\'' +
                ", Model='" + Model + '\'' +
                ", VehicleType='" + VehicleType + '\'' +
                ", DriveType='" + DriveType + '\'' +
                ", FuelTankSize=" + FuelTankSize +
                ", EPAFuelEfficiency=" + EPAFuelEfficiency +
                ", Engine=" + Engine +
                ", Transmission=" + Transmission +
                ", Warranties=" + Warranties +
                ", Recalls=" + Recalls +
                ", ServiceBulletins=" + ServiceBulletins +
                '}';
    }
}
