package io.moj.mobile.android.sdk.entities;

import io.moj.mobile.android.sdk.values.Address;
import io.moj.mobile.android.sdk.enums.GPSStatus;

/**
 * Model object for a Location object. A Location is what is returned by calls to v2/.../history/locations.
 * It is not an entity per se as it neither has an ID nor a reference to its parent object when returned
 * by the server, but it is being subclassed from MojioObject here because it is worth caching or
 * persisting at the application layer due to its size.
 * Created by mhorie on 2016-01-12.
 */
public class Location extends MojioObject {

    private Address Address;
    private String Timestamp;
    private Float Lat;
    private Float Lng;
    private GPSStatus Status;
    private Float Dilution;
    private Float Altitude;
    private String GeoHash;
    private String Time;

    public Address getAddress() {
        return Address;
    }

    public void setAddress(Address address) {
        Address = address;
    }

    public Float getAltitude() {
        return Altitude;
    }

    public void setAltitude(Float altitude) {
        Altitude = altitude;
    }

    public Float getDilution() {
        return Dilution;
    }

    public void setDilution(Float dilution) {
        Dilution = dilution;
    }

    public String getGeoHash() {
        return GeoHash;
    }

    public void setGeoHash(String geoHash) {
        GeoHash = geoHash;
    }

    public Float getLat() {
        return Lat;
    }

    public void setLat(Float lat) {
        Lat = lat;
    }

    public Float getLng() {
        return Lng;
    }

    public void setLng(Float lng) {
        Lng = lng;
    }

    public GPSStatus getStatus() {
        return Status;
    }

    public void setStatus(GPSStatus status) {
        Status = status;
    }

    // TODO difference between Time and Timestamp
    // TODO add methods returning DateTime instead of String
    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    // TODO add methods returning DateTime instead of String
    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Location{" +
                "Address=" + Address +
                ", Timestamp='" + Timestamp + '\'' +
                ", Lat=" + Lat +
                ", Lng=" + Lng +
                ", Status=" + Status +
                ", Dilution=" + Dilution +
                ", Altitude=" + Altitude +
                ", GeoHash='" + GeoHash + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
