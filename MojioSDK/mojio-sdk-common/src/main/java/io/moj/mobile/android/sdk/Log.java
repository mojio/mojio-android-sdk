package io.moj.mobile.android.sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Util log class that enables runtime configuration of behaviour so clients can disable
 * SDK logging conditionally. Only supports the following log levels:
 * <ul>
 *     <li>{@link android.util.Log#VERBOSE}</li>
 *     <li>{@link android.util.Log#DEBUG}</li>
 *     <li>{@link android.util.Log#INFO}</li>
 *     <li>{@link android.util.Log#WARN}</li>
 *     <li>{@link android.util.Log#ERROR}</li>
 * </ul>
 *
 * Created by skidson on 16-02-10.
 */
public class Log {

    private static final List<Logger> LOGGERS = new ArrayList<>();
    static {
        LOGGERS.add(new Logger() {
            @Override
            public void log(int level, String tag, String msg, Throwable tr) {
                android.util.Log.println(level, tag, msg + (tr == null ? "" : ('\n' + android.util.Log.getStackTraceString(tr))));
            }
        });
    }

    /**
     * Logs at the indicated level.
     *
     * @param level one of {@link android.util.Log#ASSERT}, {@link android.util.Log#VERBOSE}, {@link android.util.Log#DEBUG}, {@link android.util.Log#INFO}, {@link android.util.Log#WARN}, or {@link android.util.Log#ERROR}.
     * @param tag
     * @param msg
     * @param tr
     */
    public static void log(int level, String tag, String msg, Throwable tr) {
        for (Logger logger : LOGGERS) {
            logger.log(level, tag, msg, tr);
        }
    }

    /**
     * Logs at the indicated level.
     *
     * @param level   one of {@link android.util.Log#ASSERT}, {@link android.util.Log#VERBOSE}, {@link android.util.Log#DEBUG}, {@link android.util.Log#INFO}, {@link android.util.Log#WARN}, or {@link android.util.Log#ERROR}.
     * @param tag
     * @param message
     */
    public static void log(int level, String tag, String message) {
        log(level, tag, message, null);
    }

    public static void v(String tag, String message) {
        log(android.util.Log.VERBOSE, tag, message);
    }

    public static void v(String tag, String message, Throwable e) {
        log(android.util.Log.VERBOSE, tag, message, e);
    }

    public static void d(String tag, String message) {
        log(android.util.Log.DEBUG, tag, message);
    }

    public static void d(String tag, String message, Throwable e) {
        log(android.util.Log.DEBUG, tag, message, e);
    }

    public static void i(String tag, String message) {
        log(android.util.Log.INFO, tag, message);
    }

    public static void i(String tag, String message, Throwable e) {
        log(android.util.Log.INFO, tag, message, e);
    }

    public static void w(String tag, String message) {
        log(android.util.Log.WARN, tag, message);
    }

    public static void w(String tag, String message, Throwable e) {
        log(android.util.Log.WARN, tag, message, e);
    }

    public static void e(String tag, String message) {
        log(android.util.Log.ERROR, tag, message);
    }

    public static void e(String tag, String message, Throwable e) {
        log(android.util.Log.ERROR, tag, message, e);
    }

    public static synchronized void append(Logger logger) {
        LOGGERS.add(logger);
    }

    public static synchronized void remove(Logger logger) {
        LOGGERS.remove(logger);
    }

    public static void clearLoggers() {
        LOGGERS.clear();
    }

    public interface Logger {
        void log(int level, String tag, String msg, Throwable tr);
    }
}