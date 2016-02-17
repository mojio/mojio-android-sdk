package io.moj.mobile.android.sdk.push;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

/**
 * Used for specifying conditions for which an observer's behaviour should be limited.
 * Created by skidson on 16-02-16.
 */
public class Condition {

    /**
     * The format to be used for {@link #Delay} and {@link #Window} properties. For example
     * {@code String.format(Condition.TIME_FORMAT, days, hours, minnutes, seconds, milliseconds)}
     */
    public static final String TIME_FORMAT = "%d.%02d:%02d:%02d.%04d";

    private transient Type type;

    // Shared
    private String Property;

    // Threshold
    private Position Position;
    private Double Max;
    private Double Min;

    // Debounce & Throttle
    private String TimeProperty;
    private String Delay;
    private Integer MinDataPoints;
    private String Window;

    public Condition() {}

    public Condition(Type type) {
        this.type = type;
    }

    public String getDelay() {
        return Delay;
    }

    public void setDelay(String delay) {
        Delay = delay;
    }

    public Double getMax() {
        return Max;
    }

    public void setMax(Double max) {
        Max = max;
    }

    public Double getMin() {
        return Min;
    }

    public void setMin(Double min) {
        Min = min;
    }

    public Integer getMinDataPoints() {
        return MinDataPoints;
    }

    public void setMinDataPoints(Integer minDataPoints) {
        MinDataPoints = minDataPoints;
    }

    public Condition.Position getPosition() {
        return Position;
    }

    public void setPosition(Condition.Position position) {
        Position = position;
    }

    public String getProperty() {
        return Property;
    }

    public void setProperty(String property) {
        Property = property;
    }

    public String getTimeProperty() {
        return TimeProperty;
    }

    public void setTimeProperty(String timeProperty) {
        TimeProperty = timeProperty;
    }

    public String getWindow() {
        return Window;
    }

    public void setWindow(String window) {
        Window = window;
    }

    protected Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "Delay='" + Delay + '\'' +
                ", type=" + type +
                ", Property='" + Property + '\'' +
                ", Position=" + Position +
                ", Max=" + Max +
                ", Min=" + Min +
                ", TimeProperty='" + TimeProperty + '\'' +
                ", MinDataPoints=" + MinDataPoints +
                ", Window='" + Window + '\'' +
                '}';
    }

    public static Condition onPropertyChanged(String property) {
        Condition c = new Condition(Type.PROPERTY_CHANGED);
        c.setProperty(property);
        return c;
    }

    public static Condition onThreshold(String property, Position position, Double min, Double max) {
        Condition c = new Condition(Type.THRESHOLD);
        c.setProperty(property);
        c.setPosition(position);
        c.setMin(min);
        c.setMax(max);
        return c;
    }

    public static Condition debounce(Integer minDataPoints, String delay) {
        Condition c = new Condition(Type.DEBOUNCE);
        c.setMinDataPoints(minDataPoints);
        c.setDelay(delay);
        return c;
    }

    public static Condition debounce(Integer minDataPoints, int days, int hours, int minutes,
                                     int seconds) {
        return debounce(minDataPoints, formatDuration(days, hours, minutes, seconds));
    }

    public static Condition minDataPoints(Integer minDataPoints) {
        return debounce(minDataPoints, null);
    }

    public static Condition delay(String delay) {
        return debounce(null, delay);
    }

    public static Condition delay(int days, int hours, int minutes, int seconds) {
        return debounce(null, days, hours, minutes, seconds);
    }

    public static Condition throttle(String timeProperty, String window) {
        Condition c = new Condition(Type.THROTTLE);
        c.setTimeProperty(timeProperty);
        c.setWindow(window);
        return c;
    }

    public static Condition throttle(String timeProperty, int days, int hours, int minutes, int seconds) {
        return throttle(timeProperty, formatDuration(days, hours, minutes, seconds));
    }

    public static Condition throttle(int days, int hours, int minutes, int seconds) {
        return throttle(null, days, hours, minutes, seconds);
    }

    private static String formatDuration(int days, int hours, int minutes, int seconds) {
        return String.format(Locale.US, TIME_FORMAT, days, hours, minutes, seconds, 0);
    }

    public enum Type {
        /**
         * Limits the Observer to only broadcast when the specified property changes.
         */
        @SerializedName("PropertyChanged")
        PROPERTY_CHANGED("PropertyChanged"),

        /**
         * Limits the Observer to only broadcast when the specified property is above, below, or
         * between the given Min and Max.
         */
        @SerializedName("Threshold")
        THRESHOLD("Threshold"),

        /**
         * Limits an observer to only broadcast when a certain number of data points have already
         * been received and/or the condition has been maintained for a certain amount of time.
         */
        @SerializedName("Debounce")
        DEBOUNCE("Debounce"),

        /**
         * Limits the observer to only broadcast if there has been a certain amount of time since
         * the last successful broadcast of the condition. All changes during the window will be
         * ignored.
         */
        @SerializedName("Throttle")
        THROTTLE("Throttle");

        private final String key;

        Type(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public static Type fromKey(String key) {
            for (Type type : values()) {
                if (type.getKey().equals(key))
                    return type;
            }
            return null;
        }
    }

    public enum Position {
        /**
         * Checks if the current value is greater than the Max value, if Max is not provided will
         * check the Min.
         */
        @SerializedName("Above")
        ABOVE("Above"),

        /**
         * Checks if the current value is less than the Min value, if Min is not provided will check
         * the Max.
         */
        @SerializedName("Below")
        BELOW("Below"),

        /**
         * Checks if the current value is less between the Max and the Min value.
         */
        @SerializedName("Between")
        BETWEEN("Between");

        private final String key;

        Position(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public static Position fromKey(String key) {
            for (Position position : values()) {
                if (position.getKey().equals(key))
                    return position;
            }
            return null;
        }
    }
}
