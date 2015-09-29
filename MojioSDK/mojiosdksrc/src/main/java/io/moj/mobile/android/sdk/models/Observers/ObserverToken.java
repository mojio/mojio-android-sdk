package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for an observer token.
 * Created by jian on 01/04/2015.
 */
public class ObserverToken {

    @SerializedName("DeviceRegistrationId")
    private String deviceRegistrationId;

    @SerializedName("Transport")
    private ObserverTransport transport;

    /**
     * @deprecated Should only be used for JSON serialization.
     */
    @Deprecated
    public ObserverToken() { }

    public ObserverToken(String deviceRegistrationId, ObserverTransport transport) {
        this.deviceRegistrationId = deviceRegistrationId;
        this.transport = transport;
    }

    public String getDeviceRegistrationId() {
        return deviceRegistrationId;
    }

    public void setDeviceRegistrationId(String deviceRegistrationId) {
        this.deviceRegistrationId = deviceRegistrationId;
    }

    public ObserverTransport getTransport() {
        return transport;
    }

    public void setTransport(ObserverTransport transport) {
        this.transport = transport;
    }

    @Override
     public String toString() {
        return "ObserverToken{" +
                "deviceRegistrationId='" + deviceRegistrationId + '\'' +
                ", transport='" + transport + '\'' +
                '}';
    }
}
