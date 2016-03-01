package io.moj.mobile.android.sdk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model object for a response whose data is a List.
 * Created by skidson on 16-02-09.
 */
public class ListResponse<T> {

    @SerializedName("Data")
    private List<T> data;

    @SerializedName("Results")
    private Integer count;

    @SerializedName("Links")
    private Links links;

    public static class Links {
        @SerializedName("Self")
        private String self;

        @SerializedName("Next")
        private String next;

        @SerializedName("First")
        private String first;
    }

    public Integer getCount() {
        return count;
    }

    public List<T> getData() {
        return data;
    }

    public Links getLinks() {
        return links;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "ListResponse{" +
                "count=" + count +
                ", data=" + data +
                ", links=" + links +
                '}';
    }
}
