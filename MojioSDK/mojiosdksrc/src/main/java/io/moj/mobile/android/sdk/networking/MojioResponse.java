package io.moj.mobile.android.sdk.networking;

import com.google.gson.annotations.SerializedName;

/**
 * Model object for a Mojio API response.
 * Created by skidson on 15-10-05.
 */
public class MojioResponse<T> {

    private int statusCode = 0;

    @SerializedName("PageSize")
    private int pageSize = 0;

    @SerializedName("Offset")
    private int offset = 0;

    @SerializedName("TotalRows")
    private int totalRows = 0;

    @SerializedName("Data")
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

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
