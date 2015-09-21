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
        Endpoint endpoint = null;
        // first check the ISO3166 Alpha-3 country code
        try {
            String iso3Code = locale.getISO3Country();
            if (!TextUtils.isEmpty(iso3Code)) {
                for (Endpoint e : Endpoint.values()) {
                    Set<String> iso3Set = new HashSet<>(Arrays.asList(context.getResources().getStringArray(e.getIso3CountriesResId())));
                    if (iso3Set.contains(iso3Code)) {
                        endpoint = e;
                        break;
                    }
                }
            }
        } catch (final MissingResourceException e) {
            Log.e(TAG, "Device is missing ISO 3166-1 alpha-3 country code resource", e);
        }

        // if no Alpha-3 code, check the Alpha-2 code
        if (endpoint == null) {
            Log.w(TAG, "Device did not report an ISO 3166-1 alpha-3 country code");
            // fallback to checking alpha2 code
            String iso2Code = locale.getCountry();
            if (!TextUtils.isEmpty(iso2Code)) {
                // we're getting desperate, try the device's default country
                iso2Code = locale.getDisplayCountry();
            }

            if (!TextUtils.isEmpty(iso2Code)) {
                for (Endpoint e : Endpoint.values()) {
                    Set<String> iso2Set = new HashSet<>(Arrays.asList(context.getResources().getStringArray(e.getIso2CountriesResId())));
                    if (iso2Set.contains(iso2Code)) {
                        endpoint = e;
                        break;
                    }
                }
            }
        }

        // If still no country code, use the device language
        if (endpoint == null) {
            Log.w(TAG, "Device did not report an ISO 3166-1 alpha-2 country code");
            // fallback to checking language
            String languageCode = locale.getLanguage();
            if (!TextUtils.isEmpty(languageCode)) {
                for (Endpoint e : Endpoint.values()) {
                    Set<String> languageSet = new HashSet<>(Arrays.asList(context.getResources().getStringArray(e.getIso2LanguagesResId())));
                    if (languageSet.contains(languageCode)) {
                        endpoint = e;
                        break;
                    }
                }
            }
        }

        if (endpoint == null) {
            Log.w(TAG, "Could not determine device locale, defaulting to NA endpoints");
            endpoint = Endpoint.NA;
        }

        return endpoint;
    }
}
