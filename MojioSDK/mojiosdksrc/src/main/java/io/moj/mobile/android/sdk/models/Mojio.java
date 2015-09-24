package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model object for a Mojio.
 * Created by ssawchenko on 15-02-06.
 */
public class Mojio {

    @SerializedName("OwnerId")
    private String ownerId;

    @SerializedName("Name")
    private String name;

    @SerializedName("Imei")
    private String imei;

    @SerializedName("LastContactTime")
    private String lastContactTime;

    @SerializedName("VehicleId")
    private String vehicleId;

    @SerializedName("SSID")
    private String ssid;

    @SerializedName("SSIDPassword")
    private String ssidPassword;

    @SerializedName("_id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getLastContactTime() {
        return lastContactTime;
    }

    public void setLastContactTime(String lastContactTime) {
        this.lastContactTime = lastContactTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getSsidPassword() {
        return ssidPassword;
    }

    public void setSsidPassword(String ssidPassword) {
        this.ssidPassword = ssidPassword;
    }

    @Override
    public String toString() {
        return "Mojio{" +
                "ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                ", imei='" + imei + '\'' +
                ", lastContactTime='" + lastContactTime + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", ssid='" + ssid + '\'' +
                ", ssidPassword='" + ssidPassword + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
