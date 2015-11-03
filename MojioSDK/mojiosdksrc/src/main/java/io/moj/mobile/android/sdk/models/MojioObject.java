package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Base class for all model classes.
 * Created by mhorie on 15-10-21.
 */
public class MojioObject {

    @SerializedName("__id")
    private Long _id;

    @SerializedName("_id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MojioObject{" +
                "id='" + id + '\'' +
                '}';
    }
}
