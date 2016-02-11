package io.moj.mobile.android.sdk;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LogTest {

    @Before
    public void before() {
        Log.clearLoggers();
    }

    @Test
    public void testAppend() {
        String tag = "tag";
        String message = "message";

        Log.Logger logger = mock(Log.Logger.class);

        Log.append(logger);
        Log.d(tag, message);
        verify(logger).log(android.util.Log.DEBUG, tag, message, null);

        // add the same logger again and verify it gets executed 2 more times
        Log.append(logger);
        Log.w(tag, message);
        verify(logger, times(2)).log(android.util.Log.WARN, tag, message, null);

        // append a different logger and verify it is invoked once
        Log.Logger otherLogger = mock(Log.Logger.class);
        Log.append(otherLogger);
        Log.e(tag, message);
        verify(otherLogger).log(android.util.Log.ERROR, tag, message, null);
        verify(logger, times(2)).log(android.util.Log.ERROR, tag, message, null);
    }

    @Test
    public void testClearLoggers() {
        // add and remove the logger
        Log.Logger logger = mock(Log.Logger.class);
        Log.append(logger);
        Log.clearLoggers();

        // verify our logger is not invoked
        Log.d("tag", "message");
        verify(logger, never()).log(anyInt(), anyString(), anyString(), any(Throwable.class));
    }

    @Test
    public void testRemoveLogger() {
        // add and remove the logger
        Log.Logger logger = mock(Log.Logger.class);
        Log.append(logger);
        Log.remove(logger);

        // verify our logger is not invoked
        Log.d("tag", "message");
        verify(logger, never()).log(anyInt(), anyString(), anyString(), any(Throwable.class));
    }

    @Test
    public void testV() {
        verifyLogLevel(android.util.Log.VERBOSE);
    }

    @Test
    public void testD() {
        verifyLogLevel(android.util.Log.DEBUG);
    }

    @Test
    public void testI() {
        verifyLogLevel(android.util.Log.INFO);
    }

    @Test
    public void testW() {
        verifyLogLevel(android.util.Log.WARN);
    }

    @Test
    public void testE() {
        verifyLogLevel(android.util.Log.ERROR);
    }

    @SuppressWarnings("ThrowableInstanceNeverThrown")
    private void verifyLogLevel(int level) {
        Random r = new Random();
        String tag = String.valueOf(r.nextInt());
        String msg = String.valueOf(r.nextInt());
        Throwable t = new Throwable();

        Log.Logger logger = mock(Log.Logger.class);
        Log.append(logger);

        switch (level) {
            case android.util.Log.VERBOSE:
                Log.v(tag, msg);
                Log.v(tag, msg, t);
                break;
            case android.util.Log.DEBUG:
                Log.d(tag, msg);
                Log.d(tag, msg, t);
                break;
            case android.util.Log.INFO:
                Log.i(tag, msg);
                Log.i(tag, msg, t);
                break;
            case android.util.Log.WARN:
                Log.w(tag, msg);
                Log.w(tag, msg, t);
                break;
            case android.util.Log.ERROR:
                Log.e(tag, msg);
                Log.e(tag, msg, t);
                break;
        }

        verify(logger).log(level, tag, msg, null);
        verify(logger).log(level, tag, msg, t);
    }

}
