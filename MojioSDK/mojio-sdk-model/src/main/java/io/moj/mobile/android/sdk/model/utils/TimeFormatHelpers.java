package io.moj.mobile.android.sdk.model.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Arrays;

/**
 * Util class used to format DateTime to and from the server time format.
 * Created by mhorie on 16-01-15.
 */
public class TimeFormatHelpers {

    private static final String FORMAT_FROM_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final String FORMAT_TO_SERVER = "yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'";
    private static final DateTimeFormatter FORMATTER_FROM_SERVER = DateTimeFormat.forPattern(FORMAT_FROM_SERVER);
    private static final DateTimeFormatter FORMATTER_TO_SERVER = DateTimeFormat.forPattern(FORMAT_TO_SERVER);
    private static final int MILLISECOND_PRECISION = 3;

    private static PeriodFormatter FORMATTER_FOR_ELAPSED_TIME = new PeriodFormatterBuilder()
            .printZeroAlways()
            .minimumPrintedDigits(2)
            .appendHours()
            .appendSeparator(":")
            .appendMinutes()
            .printZeroAlways()
            .minimumPrintedDigits(2)
            .appendSeparator(":")
            .appendSeconds()
            .printZeroAlways()
            .minimumPrintedDigits(2)
            .toFormatter();

    /**
     * Returns a UTC {@link DateTime} given a UTC date from the server. Note that since the server sends a
     * variable amount of milliseconds we always chop them off for parsing.
     * @param date the date string
     * @return a {@link DateTime} instance for the given date or null if parsing failed.
     */
    public static DateTime fromServerFormatted(String date) {
        StringBuilder builder = new StringBuilder(date);

        int periodIndex = date.lastIndexOf(".");
        int zIndex = date.lastIndexOf("Z");
        // Add Z at the end if not present
        if (zIndex < 0) {
            builder.append("Z");
            zIndex = date.length();
        }
        // Add a period before Z if not present
        if (periodIndex < 0) {
            builder.insert(zIndex, ".");
            periodIndex = zIndex;
            zIndex++;
        }
        date = builder.toString();
        // Ensure the millisecond portion contains MILLISECOND_PRECISION characters
        String millis = date.substring(periodIndex + 1, zIndex);
        millis = getPaddedMilliseconds(millis);
        builder.replace(periodIndex + 1, zIndex, millis);

        date = builder.toString();

        DateTime dateTime = null;
        try {
            dateTime = DateTime.parse(date, FORMATTER_FROM_SERVER);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Server sent an invalid date format: " + date);
        }
        return dateTime;
    }

    /**
     * @param dateTime the {@link DateTime} object in UTC
     * @return the timestamp formatted in the same way as the server formats timestamps
     */
    public static String toServerFormatted(DateTime dateTime) {
        return FORMATTER_TO_SERVER.print(dateTime);
    }

    private static String getPaddedMilliseconds(String milliseconds) {
        milliseconds = milliseconds == null ? "" : milliseconds;
        if (milliseconds.length() == MILLISECOND_PRECISION) {
            return milliseconds;
        } else if (milliseconds.length() > MILLISECOND_PRECISION) {
            return milliseconds.substring(0, MILLISECOND_PRECISION);
        } else {
            int digitsToAdd = MILLISECOND_PRECISION - milliseconds.length();
            char[] zeroes = new char[digitsToAdd];
            Arrays.fill(zeroes, '0');
            return milliseconds + new String(zeroes);
        }
    }
}
