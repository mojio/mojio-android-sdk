package io.moj.mobile.android.sdk;

import android.content.Context;
import android.content.res.Configuration;

/**
 * The beta version of the Endpoint class will always retrieve the Czech endpoint.
 */
public class Endpoint {

    private static final int MCC = 230;
    private static final int MNC = 1;

    private static final String TAG = Endpoint.class.getSimpleName();

    private final String apiUrl;
    private final boolean sandboxAvailable;

    public Endpoint(Context context, int mcc, int mnc) {
        Configuration originalConfiguration = context.getResources().getConfiguration();

        Configuration config = new Configuration();
        config.mcc = MCC;
        config.mnc = MNC;
        context.getResources().updateConfiguration(config, null);

        this.apiUrl = context.getString(R.string.endpoint_url);
        this.sandboxAvailable = context.getResources().getBoolean(R.bool.sandbox_available);
        context.getResources().updateConfiguration(originalConfiguration, null);
    }

    public boolean isSandboxAvailable() {
        return sandboxAvailable;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
