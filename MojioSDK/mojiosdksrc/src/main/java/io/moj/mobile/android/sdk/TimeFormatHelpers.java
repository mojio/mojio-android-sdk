package io.moj.mobile.android.sdk;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Created by ssawchenko on 15-01-27.
 */
public class TimeFormatHelpers {
    private static final String TAG = TimeFormatHelpers.class.getSimpleName();

    private static final String FORMAT_FROM_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static DateTimeFormatter FORMATTER_FROM_SERVER = DateTimeFormat.forPattern(FORMAT_FROM_SERVER).withZoneUTC();
    private static DateTimeFormatter FORMATTER_VERBOSE_DATE = DateTimeFormat.forPattern("MMMM dd, YYYY hh:mma").withZoneUTC();
    private static DateTimeFormatter FORMATTER_TIME_CRITERIA = DateTimeFormat.forPattern("YYYY.MM.dd").withZoneUTC();
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

    private static String ERROR_RESPONSE = "??";

    /**
     * Returns a UTC {@link DateTime} given a UTC date from the server. Note that since the server sends a
     * variable amount of milliseconds we always chop them off for parsing.
     * @param date
     * @return a {@link DateTime} instance for the given date or null if parsing failed.
     */
    public static DateTime fromServerFormatted(String date) {
        StringBuilder builder = new StringBuilder();
        builder.append(date);

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
            Log.e(TAG, "Server sent an invalid date format: " + date, e);
        }
        return dateTime;
    }

    private static String getPaddedMilliseconds(String milliseconds) {
        milliseconds = milliseconds == null ? "" : milliseconds;
        if (milliseconds.length() == MILLISECOND_PRECISION)
            return milliseconds;
        else if (milliseconds.length() > MILLISECOND_PRECISION)
            return milliseconds.substring(0, MILLISECOND_PRECISION);
        else {
            int digitsToAdd = MILLISECOND_PRECISION - milliseconds.length();
            char[] zeroes = new char[digitsToAdd];
            Arrays.fill(zeroes, '0');
            return milliseconds + new String(zeroes);
        }
    }

    public static String getVerboseDateTime(String datetime) {
        // Feb 1st, 2015 5:00pm
        try {
            DateTime dt = DateTime.parse(datetime, FORMATTER_FROM_SERVER);
            return getVerboseDateTime(dt);
        } catch (Exception e) {
            return ERROR_RESPONSE;
        }
    }

    public static String getVerboseDateTime(DateTime dt) {
        // Feb 1st, 2015 5:00pm
        try {
            return FORMATTER_VERBOSE_DATE.print(dt);
        } catch (Exception e) {
            return ERROR_RESPONSE;
        }
    }

    public static String getElapsedTime(String startTime, String endTime) {
        DateTime start = TimeFormatHelpers.fromServerFormatted(startTime);
        DateTime end = TimeFormatHelpers.fromServerFormatted(endTime);
        Period elapsed = new Period(start, end);
        return FORMATTER_FOR_ELAPSED_TIME.print(elapsed);
    }

    public static String getElapsedTime(long startTimeMS, long endTimeMS) {
        Period elapsed = new Period(startTimeMS, endTimeMS);
        return FORMATTER_FOR_ELAPSED_TIME.print(elapsed);
    }

    /**
     * Ex 2014.12.31
     * Used in Events criteria
     */
    public static String toCriteriaTime(DateTime time) {
        return FORMATTER_TIME_CRITERIA.print(time);
    }

    public static boolean isToday(String date) {
        DateTime dt = fromServerFormatted(date);
        return isToday(dt);
    }

    public static boolean isToday(DateTime dt) {
        DateTime today = new DateTime();
        // TODO is there a better way to do this?
        return (today.getYear() == dt.getYear()
                && today.getMonthOfYear() == dt.getMonthOfYear()
                && today.getDayOfMonth() == dt.getDayOfMonth());
    }
}
