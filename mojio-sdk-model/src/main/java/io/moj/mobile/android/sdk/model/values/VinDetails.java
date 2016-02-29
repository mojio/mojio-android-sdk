package io.moj.mobile.android.sdk.model.values;

/**
 * Model object for an VehicleMeasure.
 * Created by mhorie on 2016-01-14.
 */
public class VinDetails {

    private String Timestamp;
    private String Vin;
    private Integer Year;
    private String Make;
    private String Model;
    private String Engine;
    private Integer Cylinders;
    private FuelCapacity TotalFuelCapacity;
    private String FuelType;
    private Float CityFuelEfficiency;
    private Float HighwayFuelEfficiency;
    private Float CombinedFuelEfficiency ;
    private String Transmission;
    private String Message;
    private Boolean Success;

    public Float getCityFuelEfficiency() {
        return CityFuelEfficiency;
    }

    public void setCityFuelEfficiency(Float cityFuelEfficiency) {
        CityFuelEfficiency = cityFuelEfficiency;
    }

    public Float getCombinedFuelEfficiency() {
        return CombinedFuelEfficiency;
    }

    public void setCombinedFuelEfficiency(Float combinedFuelEfficiency) {
        CombinedFuelEfficiency = combinedFuelEfficiency;
    }

    public Integer getCylinders() {
        return Cylinders;
    }

    public void setCylinders(Integer cylinders) {
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

    public Float getHighwayFuelEfficiency() {
        return HighwayFuelEfficiency;
    }

    public void setHighwayFuelEfficiency(Float highwayFuelEfficiency) {
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

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
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

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
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
