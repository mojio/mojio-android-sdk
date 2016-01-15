package io.moj.mobile.android.sdk.model.entities;

import org.joda.time.DateTime;

import java.util.Map;

import io.moj.mobile.android.sdk.model.enums.Link;
import io.moj.mobile.android.sdk.model.utils.TimeFormatHelpers;

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

    public DateTime getCreatedOn() {
        return TimeFormatHelpers.fromServerFormatted(CreatedOn);
    }

    public void setCreatedOn(DateTime createdOn) {
        CreatedOn = TimeFormatHelpers.toServerFormatted(createdOn);
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public DateTime getLastModified() {
        return TimeFormatHelpers.fromServerFormatted(LastModified);
    }

    public void setLastModified(DateTime lastModified) {
        LastModified = TimeFormatHelpers.toServerFormatted(lastModified);
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
                "CreatedOn='" + CreatedOn + '\'' +
                ", Id='" + Id + '\'' +
                ", LastModified='" + LastModified + '\'' +
                ", Links=" + Links +
                '}';
    }
}
