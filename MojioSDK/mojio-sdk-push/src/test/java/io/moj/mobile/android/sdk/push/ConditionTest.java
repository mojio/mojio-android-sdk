package io.moj.mobile.android.sdk.push;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ConditionTest {

    @Test
    public void testOnPropertyChanged() {
        String property = "Speed.Value";
        Condition c = Condition.onPropertyChanged(property);
        assertEquals(Condition.Type.PROPERTY_CHANGED, c.getType());
        assertEquals(property, c.getProperty());
    }

    @Test
    public void testOnThreshold() {
        String property = "Speed.Value";
        Condition.Position position = Condition.Position.ABOVE;
        double min = 0;
        double max = 123.456;

        Condition c = Condition.onThreshold(property, position, min, max);
        assertEquals(Condition.Type.THRESHOLD, c.getType());
        assertEquals(min, c.getMin());
        assertEquals(max, c.getMax());
    }

    @Test
    public void testDebounce() {
        int minDataPoints = 4;
        String delay = "0.19:20:34.0000";

        Condition c = Condition.debounce(minDataPoints, delay);
        assertEquals(Condition.Type.DEBOUNCE, c.getType());
        assertEquals(minDataPoints, (int) c.getMinDataPoints());
        assertEquals(delay, c.getDelay());
    }

    @Test
    public void testDebounce_ints() {
        int minDataPoints = 5;
        int days = 0;
        int hours = 4;
        int minutes = 20;
        int seconds = 0;
        String expectedDelay = "0.04:20:00.0000";

        Condition c = Condition.debounce(minDataPoints, days, hours, minutes, seconds);
        assertEquals(Condition.Type.DEBOUNCE, c.getType());
        assertEquals(minDataPoints, (int) c.getMinDataPoints());
        assertEquals(expectedDelay, c.getDelay());
    }

    @Test
    public void testMinDataPoints() {
        int minDataPoints = 1337;

        Condition c = Condition.minDataPoints(minDataPoints);
        assertEquals(Condition.Type.DEBOUNCE, c.getType());
        assertEquals(minDataPoints, (int) c.getMinDataPoints());
        assertNull(c.getDelay());
    }

    @Test
    public void testDelay_string() {
        String delay = "0.05:00:00.0000";

        Condition c = Condition.delay(delay);
        assertEquals(Condition.Type.DEBOUNCE, c.getType());
        assertEquals(delay, c.getDelay());
        assertNull(c.getMinDataPoints());
    }

    @Test
    public void testDelay_ints() {
        int days = 2;
        int hours = 0;
        int minutes = 4;
        int seconds = 42;
        String expectedDelay = "2.00:04:42.0000";

        Condition c = Condition.delay(days, hours, minutes, seconds);
        assertEquals(Condition.Type.DEBOUNCE, c.getType());
        assertEquals(expectedDelay, c.getDelay());
        assertNull(c.getMinDataPoints());
    }

    @Test
    public void testThrottle_timeProperty_window_strings() {
        String timeProperty = "LastContactTime";
        String window = "0.06:00:00.1234";

        Condition c = Condition.throttle(timeProperty, window);
        assertEquals(Condition.Type.THROTTLE, c.getType());
        assertEquals(timeProperty, c.getTimeProperty());
        assertEquals(window, c.getWindow());
    }

    @Test
    public void testThrottle_timeProperty_window_ints() {
        String timeProperty = "LastContactTime";
        int days = 1;
        int hours = 2;
        int minutes = 3;
        int seconds = 4;
        String expectedWindow = "1.02:03:04.0000";

        Condition c = Condition.throttle(timeProperty, days, hours, minutes, seconds);
        assertEquals(Condition.Type.THROTTLE, c.getType());
        assertEquals(timeProperty, c.getTimeProperty());
        assertEquals(expectedWindow, c.getWindow());
    }

    @Test
    public void testThrottle_window_ints() {
        int days = 1;
        int hours = 2;
        int minutes = 3;
        int seconds = 4;
        String expectedWindow = "1.02:03:04.0000";

        Condition c = Condition.throttle(days, hours, minutes, seconds);
        assertEquals(Condition.Type.THROTTLE, c.getType());
        assertEquals(expectedWindow, c.getWindow());
        assertNull(c.getTimeProperty());
    }

}