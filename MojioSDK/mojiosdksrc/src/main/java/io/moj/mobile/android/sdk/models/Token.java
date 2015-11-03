package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for an access token.
 * Created by ssawchenko on 15-02-17.
 */
public class Token extends MojioObject {

    @SerializedName("AppId")
    private String AppId;

    @SerializedName("UserId")
    private String UserId;

    @SerializedName("ValidUntil")
    private String ValidUntil;

    @SerializedName("Scopes")
    private String Scopes;

    @SerializedName("Sandboxed")
    private boolean Sandboxed;

    @SerializedName("Deprecated")
    private boolean Deprecated;

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public boolean isDeprecated() {
        return Deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        Deprecated = deprecated;
    }

    public boolean isSandboxed() {
        return Sandboxed;
    }

    public void setSandboxed(boolean sandboxed) {
        Sandboxed = sandboxed;
    }

    public String getScopes() {
        return Scopes;
    }

    public void setScopes(String scopes) {
        Scopes = scopes;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getValidUntil() {
        return ValidUntil;
    }

    public void setValidUntil(String validUntil) {
        ValidUntil = validUntil;
    }

    @Override
    public String toString() {
        return "Token{" +
                "AppId='" + AppId + '\'' +
                ", UserId='" + UserId + '\'' +
                ", ValidUntil='" + ValidUntil + '\'' +
                ", Scopes='" + Scopes + '\'' +
                ", Sandboxed=" + Sandboxed +
                ", Deprecated=" + Deprecated +
                "} " + super.toString();
    }
}