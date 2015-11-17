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

    PLATFORM_1("", true),
    PROD("prod", false),
    CZECH("cz", false),
    TRIAL("trial", false),
    STAGING("staging", false),
    DEVELOP("develop", false);

    private static final int DEFAULT_VERSION = 1;
    private static final String URL_FORMAT_AUTH = "https://%1$s/OAuth2/authorize?response_type=token&client_id=%2$s";
    private static final String URL_FORMAT_API = "https://%s/v%d/";
    private static final String URL_FORMAT_SIGNALR = "https://%s/v%d/signalr";
    private static final String URL_FORMAT_URI = "https://%s";
    private static final String URL_SUFFIX_API = "api.moj.io";
    private static final String URL_SUFFIX_MY_MOJIO = "my.moj.io";
    private static final String URL_SUFFIX_ACCOUNTS = "accounts.moj.io";
    private static final String URL_SUFFIX_PASSWORD_RECOVERY = URL_SUFFIX_ACCOUNTS + "/account/forgot-password";

    private final String prefix;
    private final boolean sandboxAvailable;

    Environment(String prefix, boolean sandboxAvailable) {
        this.prefix = prefix;
        this.sandboxAvailable = sandboxAvailable;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isSandboxAvailable() {
        return sandboxAvailable;
    }

    public String getApiUrl() {
        return getApiUrl(DEFAULT_VERSION);
    }

    public String getApiUrl(int version) {
        return String.format(URL_FORMAT_API, buildUrl(prefix, URL_SUFFIX_API), version);
    }

    public String getAuthUrl(String mojioAppId) {
        return String.format(URL_FORMAT_AUTH, buildUrl(prefix, URL_SUFFIX_API), mojioAppId);
    }

    public String getSignalRUrl() {
        return getSignalRUrl(DEFAULT_VERSION);
    }

    public String getSignalRUrl(int version) {
        return String.format(URL_FORMAT_SIGNALR, buildUrl(prefix, URL_SUFFIX_API), version);
    }

    public static Environment getDefault() {
        return TRIAL;
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
        return String.format(URL_FORMAT_API, buildUrl(environment.getPrefix(), URL_SUFFIX_API), DEFAULT_VERSION);
    }

    private static String buildUrl(String prefix, String suffix) {
        if (TextUtils.isEmpty(prefix))
            return suffix;
        return prefix + "-" + suffix;
    }
}
