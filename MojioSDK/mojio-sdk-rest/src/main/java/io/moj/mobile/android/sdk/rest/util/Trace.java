package io.moj.mobile.android.sdk.rest.util;

import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility log class that will strip out configured log calls.
 */
@SuppressWarnings("unused")
public class Trace {

    private static final Set<String> DISABLED_TAGS = new HashSet<>();
    // using a boolean array instead of a set to avoid autoboxing on every call to shouldLog(int)
    private static final boolean[] ENABLED_LEVELS = new boolean[Log.ERROR + 1];
    static {
        Arrays.fill(ENABLED_LEVELS, true);
    }

    /**
     * Logs at the indicated level.
     * @param level one of {@link Log#ASSERT}, {@link Log#VERBOSE}, {@link Log#DEBUG}, {@link Log#INFO}, {@link Log#WARN}, or {@link Log#ERROR}.
     * @param tag the tag
     * @param message the message
     */
    public static void log(int level, String tag, String message) {
        log(level, tag, message, null);
    }

    /**
     * Logs at the indicated level.
     * @param level one of {@link Log#ASSERT}, {@link Log#VERBOSE}, {@link Log#DEBUG}, {@link Log#INFO}, {@link Log#WARN}, or {@link Log#ERROR}.
     * @param tag the tag
     * @param message the message
     * @param e the throwable
     */
    public static void log(int level, String tag, String message, Throwable e) {
        if (shouldLog(tag) && shouldLog(level)) {
            Log.println(level, tag, message + (e == null ? "" : "\n" + Log.getStackTraceString(e)));
        }
    }

    public static void v(String tag, String message) {
        log(Log.VERBOSE, tag, message);
    }

    public static void v(String tag, String message, Throwable e) {
        log(Log.VERBOSE, tag, message, e);
    }

    public static void d(String tag, String message) {
        log(Log.DEBUG, tag, message);
    }

    public static void d(String tag, String message, Throwable e) {
        log(Log.DEBUG, tag, message, e);
    }

    public static void i(String tag, String message) {
        log(Log.INFO, tag, message);
    }

    public static void i(String tag, String message, Throwable e) {
        log(Log.INFO, tag, message, e);
    }

    public static void w(String tag, String message) {
        log(Log.WARN, tag, message);
    }

    public static void w(String tag, String message, Throwable e) {
        log(Log.WARN, tag, message, e);
    }

    public static void e(String tag, String message) {
        log(Log.ERROR, tag, message);
    }

    public static void e(String tag, String message, Throwable e) {
        log(Log.ERROR, tag, message, e);
    }

    /**
     * Disables the tag for logging in the MojioClient SDK. Clients should always do this in their
     * application's onCreate() method to ensure sensitive information like API keys don't get
     * logged in release builds.
     * @param tag the tag
     */
    public static void disable(String tag) {
        DISABLED_TAGS.add(tag);
    }

    /**
     * Disables a log level for logging in the MojioClient SDK. Clients should always do this in their
     * application's onCreate() method to ensure sensitive information like API keys don't get
     * logged in release builds.
     * @param level the level
     */
    public static void disable(int level) {
        ENABLED_LEVELS[level] = false;
    }

    public static boolean shouldLog(int level) {
        return ENABLED_LEVELS[level];
    }

    public static boolean shouldLog(String tag) {
        return !DISABLED_TAGS.contains(tag);
    }
}
