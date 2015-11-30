package io.moj.mobile.android.sdk.enums;

import android.text.TextUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of different backend endpoints for the Mojio API.
 * Created by skidson on 15-09-21.
 */
public enum Environment {

    PLATFORM_1("", "api", true),
    API2("", "api2", true),
    PROD("prod", "api", false),
    CZECH("cz", "api", false),
    EU("eu", "api", false),
    TRIAL("trial", "api", false),
    STAGING("staging", "api", false),
    DEVELOP("develop", "api", false);

    private static final int DEFAULT_VERSION = 1;
    private static final String URL_FORMAT_AUTH = "https://%1$s/OAuth2/authorize?response_type=token&client_id=%2$s";
    private static final String URL_FORMAT_API = "https://%s/v%d/";
    private static final String URL_FORMAT_SIGNALR = "https://%s/v%d/signalr";
    private static final String URL_FORMAT_URI = "https://%s";
    private static final String URL_SUFFIX_API = ".moj.io";
    private static final String URL_SUFFIX_MY_MOJIO = "my.moj.io";
    private static final String URL_SUFFIX_ACCOUNTS = "accounts.moj.io";
    private static final String URL_SUFFIX_PASSWORD_RECOVERY = URL_SUFFIX_ACCOUNTS + "/account/forgot-password";

    private final String prefix;
    private final boolean sandboxAvailable;
    private final String apiPrefix;

    Environment(String prefix, String apiPrefix, boolean sandboxAvailable) {
        this.prefix = prefix;
        this.apiPrefix = apiPrefix;
        this.sandboxAvailable = sandboxAvailable;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getApiPrefix() {
        return apiPrefix;
    }

    public boolean isSandboxAvailable() {
        return sandboxAvailable;
    }

    public String getApiUrl() {
        return getApiUrl(DEFAULT_VERSION);
    }

    public String getApiUrl(int version) {
        return String.format(URL_FORMAT_API, buildUrl(prefix, apiPrefix + URL_SUFFIX_API), version);
    }

    public String getAuthUrl(String mojioAppId) {
        return String.format(URL_FORMAT_AUTH, buildUrl(prefix, apiPrefix + URL_SUFFIX_API), mojioAppId);
    }

    public String getSignalRUrl() {
        return getSignalRUrl(DEFAULT_VERSION);
    }

    public String getSignalRUrl(int version) {
        return String.format(URL_FORMAT_SIGNALR, buildUrl(prefix, apiPrefix + URL_SUFFIX_API), version);
    }

    public static Environment getDefault() {
        return API2;
    }

    public static Environment fromHostname(String hostname) {
        for (Environment environment : values()) {
            if (hostname.equals(getApiUrl(environment))) {
                return environment;
            }
        }
        return null;
    }

    public String getMyMojioUrl() {
        return String.format(URL_FORMAT_URI, buildUrl(prefix, URL_SUFFIX_MY_MOJIO));
    }

    public String getAccountsUrl() {
        return String.format(URL_FORMAT_URI, buildUrl(prefix, URL_SUFFIX_ACCOUNTS));
    }

    public String getPasswordRecoveryUrl() {
        return String.format(URL_FORMAT_URI, buildUrl(prefix, URL_SUFFIX_PASSWORD_RECOVERY));
    }

    private static String getApiUrl(Environment environment) {
        return String.format(URL_FORMAT_API, buildUrl(environment.getPrefix(), environment.getApiPrefix() + URL_SUFFIX_API), DEFAULT_VERSION);
    }

    private static String buildUrl(String prefix, String suffix) {
        if (TextUtils.isEmpty(prefix))
            return suffix;
        return prefix + "-" + suffix;
    }
}
