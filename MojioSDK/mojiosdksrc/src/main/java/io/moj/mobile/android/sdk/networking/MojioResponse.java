package io.moj.mobile.android.sdk.networking;

import com.google.gson.annotations.SerializedName;

/**
 * Model object for a Mojio API response.
 * Created by skidson on 15-10-05.
 */
public class MojioResponse<T> {

    public static final String ATTR_PAGE_SIZE = "PageSize";
    public static final String ATTR_PAGE_OFFSET = "Offset";
    public static final String ATTR_PAGE_TOTAL_ROWS = "TotalRows";
    public static final String ATTR_DATA = "Data";

    @SerializedName(ATTR_PAGE_SIZE)
    private int pageSize = 0;

    @SerializedName(ATTR_PAGE_OFFSET)
    private int offset = 0;

    @SerializedName(ATTR_PAGE_TOTAL_ROWS)
    private int totalRows = 0;

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

    public boolean hasMorePages() {
        return pageSize + offset < totalRows;
    }

}
