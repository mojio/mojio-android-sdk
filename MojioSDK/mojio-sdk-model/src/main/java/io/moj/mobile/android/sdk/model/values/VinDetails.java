package io.moj.mobile.android.sdk.model.values;

import org.joda.time.DateTime;

import io.moj.mobile.android.sdk.model.utils.TimeFormatHelpers;

/**
 * Model object for an VehicleMeasure.
 * Created by mhorie on 2016-01-14.
 */
public class VinDetails {

    private String Timestamp;
    private String Vin;
    private int Year;
    private String Make;
    private String Model;
    private String Engine;
    private int Cylinders;
    private FuelCapacity TotalFuelCapacity;
    private String FuelType;
    private float CityFuelEfficiency;
    private float HighwayFuelEfficiency;
    private float CombinedFuelEfficiency ;
    private String Transmission;
    private String Message;
    private boolean Success;

    public float getCityFuelEfficiency() {
        return CityFuelEfficiency;
    }

    public void setCityFuelEfficiency(float cityFuelEfficiency) {
        CityFuelEfficiency = cityFuelEfficiency;
    }

    public float getCombinedFuelEfficiency() {
        return CombinedFuelEfficiency;
    }

    public void setCombinedFuelEfficiency(float combinedFuelEfficiency) {
        CombinedFuelEfficiency = combinedFuelEfficiency;
    }

    public int getCylinders() {
        return Cylinders;
    }

    public void setCylinders(int cylinders) {
        Cylinders = cylinders;
    }

    public String getEngine() {
        return Engine;
    }

    public void setEngine(String engine) {
        Engine = engine;
    }

    public String getFuelType() {
        return FuelType;
    }

    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }

    public float getHighwayFuelEfficiency() {
        return HighwayFuelEfficiency;
    }

    public void setHighwayFuelEfficiency(float highwayFuelEfficiency) {
        HighwayFuelEfficiency = highwayFuelEfficiency;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public DateTime getTimestamp() {
        return TimeFormatHelpers.fromServerFormatted(Timestamp);
    }

    public void setTimestamp(DateTime timestamp) {
        Timestamp = TimeFormatHelpers.toServerFormatted(timestamp);
    }

    public FuelCapacity getTotalFuelCapacity() {
        return TotalFuelCapacity;
    }

    public void setTotalFuelCapacity(FuelCapacity totalFuelCapacity) {
        TotalFuelCapacity = totalFuelCapacity;
    }

    public String getTransmission() {
        return Transmission;
    }

    public void setTransmission(String transmission) {
        Transmission = transmission;
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    @Override
    public String toString() {
        return "VinDetails{" +
                "CityFuelEfficiency=" + CityFuelEfficiency +
                ", Timestamp='" + Timestamp + '\'' +
                ", Vin='" + Vin + '\'' +
                ", Year=" + Year +
                ", Make='" + Make + '\'' +
                ", Model='" + Model + '\'' +
                ", Engine='" + Engine + '\'' +
                ", Cylinders=" + Cylinders +
                ", TotalFuelCapacity=" + TotalFuelCapacity +
                ", FuelType='" + FuelType + '\'' +
                ", HighwayFuelEfficiency=" + HighwayFuelEfficiency +
                ", CombinedFuelEfficiency=" + CombinedFuelEfficiency +
                ", Transmission='" + Transmission + '\'' +
                ", Message='" + Message + '\'' +
                ", Success=" + Success +
                '}';
    }
}
