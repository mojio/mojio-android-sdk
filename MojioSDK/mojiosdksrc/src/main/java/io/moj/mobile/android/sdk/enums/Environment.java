package io.moj.mobile.android.sdk.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of different backend endpoints for the Mojio API.
 * Created by skidson on 15-09-21.
 */
public enum Environment {

    PLATFORM_1("api.moj.io", true),
    PROD("prod-api.moj.io", false),
    CZECH("cz-api.moj.io", false),
    TRIAL("trial-api.moj.io", false),
    STAGING("staging-api.moj.io", false),
    DEVELOP("develop-api.moj.io", false);

    private static final int DEFAULT_VERSION = 1;
    private static final String URL_FORMAT_AUTH = "https://%1$s/OAuth2/authorize?response_type=token&client_id=%2$s";
    private static final String URL_FORMAT_API = "https://%s/v%d/";
    private static final String URL_FORMAT_SIGNALR = "https://%s/v%d/signalr";
    private static final Map<String, Environment> HOSTNAME_MAP;
    static {
        Map<String, Environment> hostnameToEnvironment = new HashMap<>();
        for (Environment environment : values()) {
            hostnameToEnvironment.put(environment.hostname, environment);
        }
        HOSTNAME_MAP = Collections.unmodifiableMap(hostnameToEnvironment);
    }

    private final String hostname;
    private final boolean sandboxAvailable;

    Environment(String hostname, boolean sandboxAvailable) {
        this.hostname = hostname;
        this.sandboxAvailable = sandboxAvailable;
    }

    public boolean isSandboxAvailable() {
        return sandboxAvailable;
    }

    public String getApiUrl() {
        return getApiUrl(DEFAULT_VERSION);
    }

    public String getApiUrl(int version) {
        return String.format(URL_FORMAT_API, hostname, version);
    }

    public String getAuthUrl(String mojioAppId) {
        return String.format(URL_FORMAT_AUTH, hostname, mojioAppId);
    }

    public String getSignalRUrl() {
        return getSignalRUrl(DEFAULT_VERSION);
    }

    public String getSignalRUrl(int version) {
        return String.format(URL_FORMAT_SIGNALR, hostname, version);
    }

    public static Environment getDefault() {
        return TRIAL;
    }

    public static Environment fromHostname(String hostname) {
        return HOSTNAME_MAP.get(hostname);
    }

}
