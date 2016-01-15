package io.moj.mobile.android.sdk.model.entities;

import java.util.Arrays;

import io.moj.mobile.android.sdk.model.values.Image;

/**
 * Model object for an App.
 * Created by mhorie on 2016-01-12.
 */
public class App extends MojioObject {

    public static final String NAME = "Name";
    public static final String DESCRIPTION = "Description";
    public static final String DOWNLOADS = "Downloads";
    public static final String REDIRECT_URIS = "RedirectUris";
    public static final String IMAGE = "Image";
    public static final String TAGS = "Tags";

    private String Name;
    private String Description;
    private int Downloads;
    private String[] RedirectUris;
    private Image Image;
    private String[] Tags;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getDownloads() {
        return Downloads;
    }

    public void setDownloads(int downloads) {
        Downloads = downloads;
    }

    public io.moj.mobile.android.sdk.model.values.Image getImage() {
        return Image;
    }

    public void setImage(io.moj.mobile.android.sdk.model.values.Image image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String[] getRedirectUris() {
        return RedirectUris;
    }

    public void setRedirectUris(String[] redirectUris) {
        RedirectUris = redirectUris;
    }

    public String[] getTags() {
        return Tags;
    }

    public void setTags(String[] tags) {
        Tags = tags;
    }

    @Override
    public String toString() {
        return "App{" +
                "Description='" + Description + '\'' +
                ", Name='" + Name + '\'' +
                ", Downloads=" + Downloads +
                ", RedirectUris=" + Arrays.toString(RedirectUris) +
                ", Image=" + Image +
                '}';
    }
}
