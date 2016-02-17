package io.moj.mobile.android.sdk.push;

import com.google.gson.annotations.SerializedName;

/**
 * Model object for an observer transport.
 * Created by skidson on 16-02-16.
 */
public class Transport {

    private Type TransportType;

    // Android
    private String DeviceRegistrationId;

    // Apple
    private String DeviceToken;
    private String AppId;
    private String AlertBody;
    private String AlertSound;
    private String AlertCategory;
    private Integer Badge;

    // HttpPost
    private String Address;

    // Mqtt
    private String HostName;
    private String Topic;
    private Integer Port;

    // MongoDB
    private String ConnectionString;
    private String CollectionName;
    private MongoIdentifierType Identifier;

    // SignalR
    private String HubName;
    private String Callback;

    // Shared
    private String ClientId;
    private String UserName;
    private String Password;

    public Transport() {}

    public Transport(Type type) {
        this.TransportType = type;
    }

    public String getAddress() {
        return Address;
    }

    public String getAlertBody() {
        return AlertBody;
    }

    public String getAlertCategory() {
        return AlertCategory;
    }

    public String getAlertSound() {
        return AlertSound;
    }

    public String getAppId() {
        return AppId;
    }

    public int getBadge() {
        return Badge;
    }

    public String getCallback() {
        return Callback;
    }

    public String getClientId() {
        return ClientId;
    }

    public String getCollectionName() {
        return CollectionName;
    }

    public String getConnectionString() {
        return ConnectionString;
    }

    public String getDeviceRegistrationId() {
        return DeviceRegistrationId;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public String getHostName() {
        return HostName;
    }

    public String getHubName() {
        return HubName;
    }

    public MongoIdentifierType getIdentifier() {
        return Identifier;
    }

    public String getPassword() {
        return Password;
    }

    public int getPort() {
        return Port;
    }

    public String getTopic() {
        return Topic;
    }

    public Type getType() {
        return TransportType;
    }

    public String getUserName() {
        return UserName;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setAlertBody(String alertBody) {
        AlertBody = alertBody;
    }

    public void setAlertCategory(String alertCategory) {
        AlertCategory = alertCategory;
    }

    public void setAlertSound(String alertSound) {
        AlertSound = alertSound;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public void setBadge(int badge) {
        Badge = badge;
    }

    public void setCallback(String callback) {
        Callback = callback;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public void setCollectionName(String collectionName) {
        CollectionName = collectionName;
    }

    public void setConnectionString(String connectionString) {
        ConnectionString = connectionString;
    }

    public void setDeviceRegistrationId(String deviceRegistrationId) {
        DeviceRegistrationId = deviceRegistrationId;
    }

    public void setDeviceToken(String deviceToken) {
        DeviceToken = deviceToken;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }

    public void setHubName(String hubName) {
        HubName = hubName;
    }

    public void setIdentifier(MongoIdentifierType identifier) {
        Identifier = identifier;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPort(int port) {
        Port = port;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public void setType(Type transportType) {
        TransportType = transportType;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "Address='" + Address + '\'' +
                ", TransportType=" + TransportType +
                ", DeviceRegistrationId='" + DeviceRegistrationId + '\'' +
                ", DeviceToken='" + DeviceToken + '\'' +
                ", AppId='" + AppId + '\'' +
                ", AlertBody='" + AlertBody + '\'' +
                ", AlertSound='" + AlertSound + '\'' +
                ", AlertCategory='" + AlertCategory + '\'' +
                ", Badge=" + Badge +
                ", HostName='" + HostName + '\'' +
                ", Topic='" + Topic + '\'' +
                ", Port=" + Port +
                ", ConnectionString='" + ConnectionString + '\'' +
                ", CollectionName='" + CollectionName + '\'' +
                ", Identifier=" + Identifier +
                ", HubName='" + HubName + '\'' +
                ", Callback='" + Callback + '\'' +
                ", ClientId='" + ClientId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    public static Transport forAndroid(String deviceRegistrationId) {
        Transport t = new Transport(Type.ANDROID);
        t.setDeviceRegistrationId(deviceRegistrationId);
        return t;
    }

    public static Transport forSignalR(String clientId, String hubName) {
        return forSignalR(clientId, hubName, null);
    }

    public static Transport forSignalR(String clientId, String hubName, String callback) {
        Transport t = new Transport(Type.SIGNAL_R);
        t.setClientId(clientId);
        t.setHubName(hubName);
        t.setCallback(callback);
        return t;
    }

    public enum Type {
        @SerializedName("Android")
        ANDROID("Android"),

        @SerializedName("Apple")
        APPLE("Apple"),

        @SerializedName("HttpPost")
        HTTP_POST("HttpPost"),

        @SerializedName("MongoDB")
        MONGO_DB("MongoDB"),

        @SerializedName("Mqtt")
        MQTT("Mqtt"),

        @SerializedName("SignalR")
        SIGNAL_R("SignalR");

        private final String key;

        Type(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public static Type fromKey(String key) {
            for (Type type : Type.values()) {
                if (type.getKey().equals(key))
                    return type;
            }
            return null;
        }
    }

    public enum MongoIdentifierType {
        @SerializedName("Default")
        DEFAULT("Default"),

        @SerializedName("Id")
        ID("Id"),

        @SerializedName("Guid")
        GUID("Guid");

        private final String key;

        MongoIdentifierType(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public static Type fromKey(String key) {
            for (Type type : Type.values()) {
                if (type.getKey().equals(key))
                    return type;
            }
            return null;
        }
    }
}
