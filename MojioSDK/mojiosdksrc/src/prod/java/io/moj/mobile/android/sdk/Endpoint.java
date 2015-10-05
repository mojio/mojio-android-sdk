package io.moj.mobile.android.sdk;

import android.content.Context;
import android.content.res.Configuration;

/**
 * The prod version of the Endpoint class will retrieve the Czech endpoint if the SIM card is
 * in the Czech region. Otherwise, it will retrieve the base API endpoint.
 */
public class Endpoint {

    private static final String TAG = Endpoint.class.getSimpleName();

    private final String apiUrl;
    private final boolean sandboxAvailable;

    public Endpoint(Context context, int mcc, int mnc) {
        Configuration originalConfiguration = context.getResources().getConfiguration();

        Configuration config = new Configuration();
        config.mcc = mcc;
        config.mnc = mnc;
        context.getResources().updateConfiguration(config, null);

        this.apiUrl = context.getString(R.string.endpoint_url);
        this.sandboxAvailable = Boolean.parseBoolean(context.getString(R.string.sandbox_available));
        context.getResources().updateConfiguration(originalConfiguration, null);
    }

    public boolean isSandboxAvailable() {
        return sandboxAvailable;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
