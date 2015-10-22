package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a service bulletin.
 * Created by ssawchenko on 15-03-19.
 */
public class ServiceBulletin extends MojioObject {

    @SerializedName("ItemNumber")
    private String itemNumber;

    @SerializedName("BulletinNumber")
    private String bulletinNumber;

    @SerializedName("ReplacementBulletinNumber")
    private String replacementBulletinNumber;

    @SerializedName("DateAdded")
    private String dateAdded;

    @SerializedName("Component")
    private String component;

    @SerializedName("BulletinDate")
    private String bulletinDate;

    @SerializedName("Summary")
    private String summary;

    public String getBulletinDate() {
        return bulletinDate;
    }

    public void setBulletinDate(String bulletinDate) {
        this.bulletinDate = bulletinDate;
    }

    public String getBulletinNumber() {
        return bulletinNumber;
    }

    public void setBulletinNumber(String bulletinNumber) {
        this.bulletinNumber = bulletinNumber;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getReplacementBulletinNumber() {
        return replacementBulletinNumber;
    }

    public void setReplacementBulletinNumber(String replacementBulletinNumber) {
        this.replacementBulletinNumber = replacementBulletinNumber;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "ServiceBulletin{" +
                "bulletinDate='" + bulletinDate + '\'' +
                ", itemNumber='" + itemNumber + '\'' +
                ", bulletinNumber='" + bulletinNumber + '\'' +
                ", replacementBulletinNumber='" + replacementBulletinNumber + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", component='" + component + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
