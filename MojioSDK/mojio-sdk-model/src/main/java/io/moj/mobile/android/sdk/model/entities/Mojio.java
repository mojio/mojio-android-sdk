package io.moj.mobile.android.sdk.model.entities;

import org.joda.time.DateTime;

import io.moj.mobile.android.sdk.model.utils.TimeFormatHelpers;

/**
 * Model object for an Mojio.
 * Created by mhorie on 2016-01-13.
 */
public class Mojio extends MojioObject {

    public static final String NAME = "Name";
    public static final String DEVICE_IMEI = "IMEI";
    public static final String LAST_CONTACT_TIME = "LastContactTime";
    public static final String GATEWAY_TIME = "GatewayTime";
    public static final String TAGS = "Tags";

    private String Name;
    private String IMEI;
    private String LastContactTime;
    private String GatewayTime;
    private String[] Tags;

    /**
     * @return the time the server received the message from the device
     */
    public DateTime getGatewayTime() {
        return TimeFormatHelpers.fromServerFormatted(GatewayTime);
    }

    public void setGatewayTime(DateTime gatewayTime) {
        GatewayTime = TimeFormatHelpers.toServerFormatted(gatewayTime);
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public DateTime getLastContactTime() {
        return TimeFormatHelpers.fromServerFormatted(LastContactTime);
    }

    public void setLastContactTime(DateTime lastContactTime) {
        LastContactTime = TimeFormatHelpers.toServerFormatted(lastContactTime);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String[] getTags() {
        return Tags;
    }

    public void setTags(String[] tags) {
        Tags = tags;
    }
}
