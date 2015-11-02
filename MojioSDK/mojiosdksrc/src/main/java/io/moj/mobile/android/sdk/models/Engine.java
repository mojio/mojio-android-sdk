package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for an engine.
 * Created by ssawchenko on 15-02-26.
 */
public class Engine extends MojioObject {

    @SerializedName("Name")
    private String name;

    @SerializedName("Brand")
    private String brand;

    @SerializedName("MarketingName")
    private String marketingName;

    @SerializedName("EngineId")
    private String EngineId;

    @SerializedName("Availability")
    private String availability;

    @SerializedName("Aspiration")
    private String aspiration;

    @SerializedName("BlockType")
    private String Bore;

    @SerializedName("CamType")
    private String camType;

    @SerializedName("Compression")
    private String compression;

    @SerializedName("Cylinders")
    private String cylinders;

    @SerializedName("Displacement")
    private float displacement;

    @SerializedName("FuelInduction")
    private String fuelInduction;

    @SerializedName("FuelQuality")
    private String fuelQuality;

    @SerializedName("FuelType")
    private String fuelType;

    @SerializedName("Msrp")
    private String msrp;

    @SerializedName("InvoicePrice")
    private String invoicePrice;

    @SerializedName("MaxHp")
    private String maxHp;

    @SerializedName("MaxHpAt")
    private String maxHpAt;

    @SerializedName("MaxPayload")
    private String maxPayload;

    @SerializedName("MaxTorque")
    private String maxTorque;

    @SerializedName("MaxTorqueAt")
    private String maxTorqueAt;

    @SerializedName("OilCapacity")
    private String oilCapacity;

    @SerializedName("OrderCode")
    private String orderCode;

    @SerializedName("Redline")
    private String redline;

    @SerializedName("Stroke")
    private String stroke;

    @SerializedName("ValveTiming")
    private String valveTiming;

    @SerializedName("Valves")
    private String valves;

    public String getAspiration() {
        return aspiration;
    }

    public void setAspiration(String aspiration) {
        this.aspiration = aspiration;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getBore() {
        return Bore;
    }

    public void setBore(String bore) {
        Bore = bore;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCamType() {
        return camType;
    }

    public void setCamType(String camType) {
        this.camType = camType;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public String getCylinders() {
        return cylinders;
    }

    public void setCylinders(String cylinders) {
        this.cylinders = cylinders;
    }

    public float getDisplacement() {
        return displacement;
    }

    public void setDisplacement(float displacement) {
        this.displacement = displacement;
    }

    public String getEngineId() {
        return EngineId;
    }

    public void setEngineId(String engineId) {
        EngineId = engineId;
    }

    public String getFuelInduction() {
        return fuelInduction;
    }

    public void setFuelInduction(String fuelInduction) {
        this.fuelInduction = fuelInduction;
    }

    public String getFuelQuality() {
        return fuelQuality;
    }

    public void setFuelQuality(String fuelQuality) {
        this.fuelQuality = fuelQuality;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(String invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(String maxHp) {
        this.maxHp = maxHp;
    }

    public String getMaxHpAt() {
        return maxHpAt;
    }

    public void setMaxHpAt(String maxHpAt) {
        this.maxHpAt = maxHpAt;
    }

    public String getMaxPayload() {
        return maxPayload;
    }

    public void setMaxPayload(String maxPayload) {
        this.maxPayload = maxPayload;
    }

    public String getMaxTorque() {
        return maxTorque;
    }

    public void setMaxTorque(String maxTorque) {
        this.maxTorque = maxTorque;
    }

    public String getMaxTorqueAt() {
        return maxTorqueAt;
    }

    public void setMaxTorqueAt(String maxTorqueAt) {
        this.maxTorqueAt = maxTorqueAt;
    }

    public String getMsrp() {
        return msrp;
    }

    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOilCapacity() {
        return oilCapacity;
    }

    public void setOilCapacity(String oilCapacity) {
        this.oilCapacity = oilCapacity;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getRedline() {
        return redline;
    }

    public void setRedline(String redline) {
        this.redline = redline;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public String getValves() {
        return valves;
    }

    public void setValves(String valves) {
        this.valves = valves;
    }

    public String getValveTiming() {
        return valveTiming;
    }

    public void setValveTiming(String valveTiming) {
        this.valveTiming = valveTiming;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "aspiration='" + aspiration + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", marketingName='" + marketingName + '\'' +
                ", EngineId='" + EngineId + '\'' +
                ", availability='" + availability + '\'' +
                ", Bore='" + Bore + '\'' +
                ", camType='" + camType + '\'' +
                ", compression='" + compression + '\'' +
                ", cylinders='" + cylinders + '\'' +
                ", displacement=" + displacement +
                ", fuelInduction='" + fuelInduction + '\'' +
                ", fuelQuality='" + fuelQuality + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", msrp='" + msrp + '\'' +
                ", invoicePrice='" + invoicePrice + '\'' +
                ", maxHp='" + maxHp + '\'' +
                ", maxHpAt='" + maxHpAt + '\'' +
                ", maxPayload='" + maxPayload + '\'' +
                ", maxTorque='" + maxTorque + '\'' +
                ", maxTorqueAt='" + maxTorqueAt + '\'' +
                ", oilCapacity='" + oilCapacity + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", redline='" + redline + '\'' +
                ", stroke='" + stroke + '\'' +
                ", valveTiming='" + valveTiming + '\'' +
                ", valves='" + valves + '\'' +
                "} " + super.toString();
    }
}