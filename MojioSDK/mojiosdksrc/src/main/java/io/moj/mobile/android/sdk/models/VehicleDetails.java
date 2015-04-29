package io.moj.mobile.android.sdk.models;

/**
 * Created by ssawchenko on 15-02-26.
 */
public class VehicleDetails {
    public String VIN;
    public String Timestamp;
    private String Year;
    public String Make;
    public String Model;
    public String VehicleType;
    public String BodyType;
    public Engine InstalledEngine;
    public ServiceBulletin [] ServiceBulletins;
    public Recall [] Recalls;
    public float FuelTankSize;
    public String _id;

    private String mUnknownData = "--";

    public String getYear() {
        if ((Year == null) || (Year.isEmpty())) {
            return mUnknownData;
        }
        else {
            return Year;
        }
    }
}
