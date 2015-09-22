package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a store.
 * Created by ssawchenko on 15-02-03.
 */
public class Store {

    @SerializedName("Key")
    private String key;

    @SerializedName("Value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Store{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
