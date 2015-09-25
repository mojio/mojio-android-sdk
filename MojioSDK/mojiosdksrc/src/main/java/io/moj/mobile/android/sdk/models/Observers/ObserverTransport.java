package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Enumeration of observer transport protocols available on Android.
 * Created by skidson on 15-09-24.
 */
public enum ObserverTransport {

    @SerializedName("SignalR")
    SIGNAL_R("SignalR"),

    @SerializedName("AndroidPush")
    GCM("AndroidPush");

    private final String key;

    ObserverTransport(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    // Not currently supported on Android: PUBNUB, APPLE_PUSH, HTTP_POST
}
