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

    @SerializedName("_deleted")
    private String deleted;

    @SerializedName("_id")
    private String id;

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

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

    @Override
    public String toString() {
        return "Mojio{" +
                "deleted='" + deleted + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                ", imei='" + imei + '\'' +
                ", lastContactTime='" + lastContactTime + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
