package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a transmission.
 * Created by skidson on 15-09-21.
 */
public class Transmission {

    @SerializedName("Name")
    private String name;

    @SerializedName("Brand")
    private String brand;

    @SerializedName("MarketingName")
    private String marketingName;

    @SerializedName("TransmissionId")
    private String transmissionId;

    @SerializedName("Availability")
    private String availability;

    @SerializedName("DetailType")
    private String detailType;

    @SerializedName("Gears")
    private String gears;

    @SerializedName("Msrp")
    private String msrp;

    @SerializedName("InvoicePrice")
    private String invoicePrice;

    @SerializedName("OrderCode")
    private String orderCode;

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getGears() {
        return gears;
    }

    public void setGears(String gears) {
        this.gears = gears;
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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(String transmissionId) {
        this.transmissionId = transmissionId;
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "availability='" + availability + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", marketingName='" + marketingName + '\'' +
                ", transmissionId='" + transmissionId + '\'' +
                ", detailType='" + detailType + '\'' +
                ", gears='" + gears + '\'' +
                ", msrp='" + msrp + '\'' +
                ", invoicePrice='" + invoicePrice + '\'' +
                ", orderCode='" + orderCode + '\'' +
                '}';
    }
}
