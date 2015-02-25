package com.mojio.mojiosdk;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * Created by ssawchenko on 15-01-27.
 */
public class TimeFormatHelpers {

    private static DateTimeFormatter FORMATTER_FROM_SERVER = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ");
    private static DateTimeFormatter FORMATTER_VERBOSE_DATE = DateTimeFormat.forPattern("MMMM dd, YYYY hh:mma"); // Feb 1st, 2015 5:00pm
    private static DateTimeFormatter FORMATTER_TIME_CRITERIA = DateTimeFormat.forPattern("YYYY.MM.dd");

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

    public static DateTime fromServerFormatted(String datetime) {
        try {
            return DateTime.parse(datetime, FORMATTER_FROM_SERVER);
        }
        catch (Exception e) {
            return null;
        }
    }
    public static String getVerboseDateTime(String datetime) {
        // Feb 1st, 2015 5:00pm
        try {
            DateTime dt = DateTime.parse(datetime, FORMATTER_FROM_SERVER);
            return getVerboseDateTime(dt);
        }
        catch (Exception e) {
            return ERROR_RESPONSE;
        }
    }

    public static String getVerboseDateTime(DateTime dt) {
        // Feb 1st, 2015 5:00pm
        try {
            return FORMATTER_VERBOSE_DATE.print(dt);
        }
        catch (Exception e) {
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
