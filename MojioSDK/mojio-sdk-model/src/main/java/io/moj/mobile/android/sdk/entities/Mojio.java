package io.moj.mobile.android.sdk.entities;

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

    // TODO add methods returning DateTime instead of String
    // TODO: What is GatewayTime
    public String getGatewayTime() {
        return GatewayTime;
    }

    public void setGatewayTime(String gatewayTime) {
        GatewayTime = gatewayTime;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    // TODO add methods returning DateTime instead of String
    public String getLastContactTime() {
        return LastContactTime;
    }

    public void setLastContactTime(String lastContactTime) {
        LastContactTime = lastContactTime;
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
