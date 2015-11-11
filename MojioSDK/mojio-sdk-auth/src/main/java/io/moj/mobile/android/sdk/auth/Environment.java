package io.moj.mobile.android.sdk.auth;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * An enumeration of different backend endpoints for the Mojio API.
 * Created by skidson on 15-11-09.
 */
public enum Environment {

    PROD("prod"),
    CZECH("cz"),
    TRIAL("trial"),
    STAGING("staging"),
    DEVELOP("develop");

    private static final int DEFAULT_VERSION = 1;
    private static final String SCHEME = "https://";
    private static final String FORMAT_ACCOUNTS_HOSTNAME = SCHEME + "%s-accounts.moj.io";
    private static final String FORMAT_MY_MOJIO_HOSTNAME = SCHEME + "%s-my.moj.io";
    private static final String FORMAT_API_HOSTNAME = SCHEME + "%s-api.moj.io/v%d";
    private static final String FORMAT_PUSH_HOSTNAME = SCHEME + "%s-push.moj.io/v%d";

    private static final Map<String, Environment> PREFIX_MAP;
    static {
        Map<String, Environment> prefixes = new HashMap<>();
        for (Environment environment : values())
            prefixes.put(environment.prefix, environment);
        PREFIX_MAP = Collections.unmodifiableMap(prefixes);
    }

    private final String prefix;

    Environment(String hostname) {
        this.prefix = hostname;
    }

    public String getAccountsUrl() {
        return String.format(Locale.US, FORMAT_ACCOUNTS_HOSTNAME, prefix);
    }

    public String getApiUrl() {
        return getApiUrl(DEFAULT_VERSION);
    }

    public String getApiUrl(int version) {
        return String.format(Locale.US, FORMAT_API_HOSTNAME, prefix, version);
    }

    public String getPushUrl() {
        return getPushUrl(DEFAULT_VERSION);
    }

    public String getPushUrl(int version) {
        return String.format(Locale.US, FORMAT_PUSH_HOSTNAME, prefix, version);
    }

    public String getMyMojioUrl() {
        return String.format(Locale.US, FORMAT_MY_MOJIO_HOSTNAME, prefix);
    }

    public String getPrefix() {
        return prefix;
    }

    public static Environment getDefault() {
        return TRIAL;
    }

    public static Environment fromPrefix(String prefix) {
        return PREFIX_MAP.get(prefix);
    }

}
