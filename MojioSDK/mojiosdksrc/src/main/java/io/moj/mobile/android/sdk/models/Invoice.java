package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for an invoice.
 * Created by skidson on 15-09-21.
 */
public class Invoice {

    @SerializedName("OwnerId")
    private String ownerId;

    @SerializedName("BuyerId")
    private String buyerId;

    @SerializedName("AppId")
    private String appId;

    @SerializedName("TransactionId")
    private String transactionId;

    @SerializedName("Date")
    private String date;

    @SerializedName("Details")
    private String details;

    @SerializedName("CurrencyCode")
    private String currencyCode;

    @SerializedName("Total")
    private float total;

    @SerializedName("Address")
    private Address address;

    @SerializedName("StatusMessage")
    private String statusMessage;

    @SerializedName("Status")
    private Integer status;

    @SerializedName("_id")
    private String id;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "address=" + address +
                ", ownerId='" + ownerId + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", appId='" + appId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", date='" + date + '\'' +
                ", details='" + details + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", total=" + total +
                ", statusMessage='" + statusMessage + '\'' +
                ", status=" + status +
                ", id='" + id + '\'' +
                '}';
    }
}
