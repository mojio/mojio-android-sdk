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

    @SerializedName("_id")
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "App{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", downloads=" + downloads +
                ", redirectUris=" + redirectUris +
                ", type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}
