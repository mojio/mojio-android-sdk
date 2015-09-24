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
    private String transport;

    public String getDeviceRegistrationId() {
        return deviceRegistrationId;
    }

    public void setDeviceRegistrationId(String deviceRegistrationId) {
        this.deviceRegistrationId = deviceRegistrationId;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
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
