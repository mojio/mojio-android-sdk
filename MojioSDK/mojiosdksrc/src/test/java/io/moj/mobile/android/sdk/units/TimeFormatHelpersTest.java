package io.moj.mobile.android.sdk.units;

import org.joda.time.DateTime;
import org.junit.Test;

import io.moj.mobile.android.sdk.TimeFormatHelpers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by skidson on 15-09-23.
 */
public class TimeFormatHelpersTest {

    @Test
    public void testFromServerFormatted_withMs() {
        assetFromServerFormatted("2015-09-21T22:36:45.2487052Z", 2015, 9, 21, 22, 36, 45);
    }

    @Test
    public void testFromServerFormatted_withoutMs() {
        assetFromServerFormatted("2016-07-02T01:27:11Z", 2016, 7, 2, 1, 27, 11);
    }

    @Test
    public void testFromServerFormatted_invalid() {
        assertNull(TimeFormatHelpers.fromServerFormatted("invalid"));
    }

    private static void assetFromServerFormatted(String serverDate, int expectedYears,
        int expectedMonths, int expectedDays, int expectedHours, int expectedMinutes, int expectedSeconds) {
        DateTime dateTime = TimeFormatHelpers.fromServerFormatted(serverDate);
        assertEquals("Failed to parse years for " + serverDate, expectedYears, dateTime.getYear());
        assertEquals("Failed to parse months for " + serverDate, expectedMonths, dateTime.getMonthOfYear());
        assertEquals("Failed to parse days for " + serverDate, expectedDays, dateTime.getDayOfMonth());
        assertEquals("Failed to parse hours for " + serverDate, expectedHours, dateTime.getHourOfDay());
        assertEquals("Failed to parse minutes for " + serverDate, expectedMinutes, dateTime.getMinuteOfHour());
        assertEquals("Failed to parse seconds for " + serverDate, expectedSeconds, dateTime.getSecondOfMinute());
    }

}
