package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class App extends MojioObject {

    @SerializedName("Name")
    private String name;

    @SerializedName("Description")
    private String description;

    @SerializedName("Downloads")
    private long downloads;

    @SerializedName("RedirectUris")
    private ArrayList<String> redirectUris;

    @SerializedName("ApplicationType")
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDownloads() {
        return downloads;
    }

    public void setDownloads(long downloads) {
        this.downloads = downloads;
    }

    public ArrayList<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(ArrayList<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "App{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", downloads=" + downloads +
                ", redirectUris=" + redirectUris +
                ", type=" + type +
                "} " + super.toString();
    }
}
