package io.moj.mobile.android.sdk.enums;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;

import io.moj.mobile.android.sdk.R;

/**
 * An enumeration of different backend endpoints for the Mojio API.
 * Created by skidson on 15-09-21.
 */
public enum Endpoint {

    NA("api.moj.io", true, R.array.iso3166_alpha3_na, R.array.iso3166_alpha2_na, R.array.iso639_1_na),
    EU("cz-api.moj.io", false, R.array.iso3166_alpha3_eu, R.array.iso3166_alpha2_eu, R.array.iso639_1_eu);

    private static final String TAG = Endpoint.class.getSimpleName();

    private final String apiUrl;
    private final boolean sandboxAvailable;
    private final int iso3CountriesResId;
    private final int iso2CountriesResId;
    private final int iso2LanguagesResId;

    Endpoint(String apiUrl, boolean sandboxAvailable, int iso3CountriesResId, int iso2CountriesResId, int iso2LanguagesResId) {
        this.apiUrl = apiUrl;
        this.sandboxAvailable = sandboxAvailable;
        this.iso3CountriesResId = iso3CountriesResId;
        this.iso2CountriesResId = iso2CountriesResId;
        this.iso2LanguagesResId = iso2LanguagesResId;
    }

    public boolean isSandboxAvailable() {
        return sandboxAvailable;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public int getIso3CountriesResId() {
        return iso3CountriesResId;
    }

    public int getIso2CountriesResId() {
        return iso2CountriesResId;
    }

    public int getIso2LanguagesResId() {
            return iso2LanguagesResId;
        }

    public static Endpoint fromLocale(Context context, Locale locale) {
        // for Beta we are always using EU
        return EU;
    }
}
