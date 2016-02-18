package io.moj.mobile.android.sdk;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by skidson on 16-02-18.
 */
public class EnvironmentTest {

    @Test
    public void testTypeFromKey() {
        for (Environment environment : Environment.values()) {
            Environment environmentFromPrefix = Environment.fromPrefix(environment.getPrefix());
            assertEquals(environment, environmentFromPrefix);
        }
        assertNull(Environment.fromPrefix("NotARealPrefix"));
    }

}
