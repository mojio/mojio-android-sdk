package io.moj.mobile.android.sdk.units;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by skidson on 16-01-07.
 */
public class MeasureTest {

    private static final float DELTA = 0.001f;

    @Test
    public void testRound() {
        assertEquals(0.0f, Measure.round(0.0001f), DELTA);
        assertEquals(3.14f, Measure.round(3.14159f), DELTA);
        assertEquals(100f, Measure.round(100.001f), DELTA);
        assertEquals(1.02f, Measure.round(1.015f), DELTA);
        assertEquals(-1.01f, Measure.round(-1.015f), DELTA);
    }

    @Test
    public void testLp100KmToMpg() {
        assertEquals(235.21f, Measure.lp100kmTompg(1), DELTA);
        assertEquals(0.05f, Measure.lp100kmTompg(5000), DELTA);
    }

}
