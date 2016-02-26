package io.moj.mobile.android.sdk.model;

import java.util.Map;

import io.moj.mobile.android.sdk.model.enums.Link;

/**
 * Base model for objects returned by the server.
 * Note: field names are the same as used by the backend so that if reflection is used for
 * serialization or persistence to database, then the column name will be the same.
 * Created by mhorie on 2016-01-13.
 */
public abstract class MojioObject {

    public static final String ID = "Id";
    public static final String LOCAL_ID = "_id";
    public static final String CREATED_ON = "CreatedOn";
    public static final String LAST_MODIFIED = "LastModified";
    public static final String LINKS = "Links";

    private String Id;
    private Long _id;
    private String CreatedOn;
    private String LastModified;
    private Map<Link, String> Links;

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLastModified() {
        return LastModified;
    }

    public void setLastModified(String lastModified) {
        LastModified = lastModified;
    }

    public Map<Link, String> getLinks() {
        return Links;
    }

    public void setLinks(Map<Link, String> links) {
        Links = links;
    }

    public Long getLocalId() {
        return _id;
    }

    public void setLocalId(Long id) {
        this._id = id;
    }

    @Override
    public String toString() {
        return "MojioObject{" +
                "_id=" + _id +
                ", Id='" + Id + '\'' +
                ", CreatedOn='" + CreatedOn + '\'' +
                ", LastModified='" + LastModified + '\'' +
                ", Links=" + Links +
                '}';
    }
}
