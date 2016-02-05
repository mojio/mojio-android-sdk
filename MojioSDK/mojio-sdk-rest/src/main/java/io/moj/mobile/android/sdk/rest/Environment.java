package io.moj.mobile.android.sdk.rest;

import android.content.Context;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * An enumeration of different backend endpoints for the Mojio API.
 * Created by mhorie on 2016-01-18.
 */
@SuppressWarnings("unused")
public enum Environment {

    /**
     * North America and European Union are the two environments in which Mojio has a stable
     * deployment. The MojioClient will point to one of the two by default depending on the
     * MCC provided in the Context. Do not change the default behavior if you do not have a
     * reason to do so.
     */
    NORTH_AMERICA("", "api", false),
    EU("eu", "api", false),
    /**
     * The deployments below are for development and testing. Do not use them unless
     * you have a reason to do so.
     */
    PROD("prod", "api", false),
    CZECH("cz", "api", false),
    TRIAL("trial", "api", false),
    STAGING("staging", "api", false),
    DEVELOP("develop", "api", false);

    private static final int DEFAULT_VERSION = 2;
    private static final String URL_FORMAT_AUTH = "https://%1$s/OAuth2/authorize?response_type=token&client_id=%2$s";
    private static final String URL_FORMAT_API = "https://%s/v%d/";
    private static final String URL_FORMAT_SIGNALR = "https://%s/v%d/signalr";
    private static final String URL_FORMAT_URI = "https://%s";
    private static final String URL_SUFFIX_API = ".moj.io";
    private static final String URL_SUFFIX_MY_MOJIO = "my.moj.io";
    private static final String URL_SUFFIX_ACCOUNTS = "accounts.moj.io";
    private static final String URL_SUFFIX_PASSWORD_RECOVERY = URL_SUFFIX_ACCOUNTS + "/account/forgot-password";

    // Note: Gathered from https://en.wikipedia.org/wiki/List_of_sovereign_states_and_dependent_territories_in_Europe
    // and http://www.mcc-mnc.com/
    private static final Integer[] EU_MCC = { 276, 213, 283, 232, 400, 257, 206, 218, 284, 219, 280, 230
            , 238, 248, 244, 208, 282, 262, 202, 216, 274, 272, 222, 401, 247, 295, 246, 270, 294, 278
            , 259, 212, 297, 204, 242, 260, 268, 226, 250, 292, 220, 231, 293, 214, 240, 228, 286, 255
            , 234, 235 };
    private static final Set<Integer> EU_MCC_SET = new HashSet<>(Arrays.asList(EU_MCC));

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

    public static Environment getDefault(Context context) {
        if (isInEurope(context))
            return EU;
        return NORTH_AMERICA;
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

    private static boolean isInEurope(Context context) {
        // TODO handle cases in which there is no SIM card installed. Maybe use http://ip-api.com/json?
        int mcc = context.getResources().getConfiguration().mcc;
        return EU_MCC_SET.contains(mcc);
    }
}