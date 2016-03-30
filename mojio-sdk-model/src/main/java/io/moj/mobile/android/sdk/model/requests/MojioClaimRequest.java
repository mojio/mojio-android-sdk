package io.moj.mobile.android.sdk.model.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Model object for a request to claim a Mojio.
 * Created by skidson on 16-03-23.
 */
public class MojioClaimRequest {

    @SerializedName("IMEI")
    private String imei;

    public MojioClaimRequest(String imei) {
        this.imei = imei;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return "MojioClaimRequest{" +
                "imei='" + imei + '\'' +
                '}';
    }
}
