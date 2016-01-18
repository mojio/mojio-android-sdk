package io.moj.mobile.android.sdk.rest.util;

import android.content.Context;
import android.text.TextUtils;

import java.util.Locale;

/**
 * Static utilities for getting device information.
 * Created by mhorie on 2016-01-18.
 */
public class DeviceUtils {

    private DeviceUtils() {}

    /**
     * @return a locale string in the format xx-XX or xx only if XX is not available
     */
    public static String getLocaleString(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        String country = locale.getCountry();
        return TextUtils.isEmpty(country) ? language : String.format("%s-%s", language, country);
    }
}
