package io.moj.mobile.android.sdk.rest.networking;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Model object for a Mojio API response.
 * Created by mhorie on 2016-01-18.
 */
@SuppressWarnings("unused")
public class MojioResponse<T> {

    public static final String ATTR_RESULTS = "Results";
    public static final String ATTR_LINKS = "Links";
    public static final String ATTR_DATA = "Data";

    @SerializedName(ATTR_RESULTS)
    private int results = 0;

    @SerializedName(ATTR_LINKS)
    private Map<String, String> links;

    @SerializedName(ATTR_DATA)
    private T data;

    public MojioResponse() {}

    public MojioResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }
}