package io.moj.mobile.android.sdk.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enumeration of event types.
 * Created by jian on 16/04/2015.
 */
public enum EventType {

    @SerializedName("Log")
    LOG(1),

    @SerializedName("Message")
    MESSAGE(2),

    @SerializedName("Information")
    INFORMATION(100),

    @SerializedName("MojioOn")
    MOJIO_ON(101),

    @SerializedName("MojioIdle")
    MOJIO_IDLE(102),

    @SerializedName("MojioWake")
    MOJIO_WAKE(103),

    @SerializedName("IgnitionOn")
    IGNITION_ON(104),

    @SerializedName("IgnitionOff")
    IGNITION_OFF(105),

    @SerializedName("MojioOff")
    MOJIO_OFF(106),

    @SerializedName("LowBattery")
    LOW_BATTERY(107),

    @SerializedName("TripEvent")
    TRIP_EVENT(1005),

    @SerializedName("FenceEntered")
    FENCE_ENTERED(1006),

    @SerializedName("FenceExited")
    FENCE_EXITED(1007),

    @SerializedName("TripStatus")
    TRIP_STATUS(1010),

    @SerializedName("Warning")
    WARNING(30000),

    @SerializedName("MILWarning")
    MALFUNCTION_INDICATOR_LIGHT_WARNING(30001),

    @SerializedName("ConnectionLost")
    CONNECTION_LOST(40000),

    @SerializedName("Alert")
    ALERT(100000),

    @SerializedName("Accident")
    ACCIDENT(100001),

    @SerializedName("TowStart")
    TOW_START(100002),

    @SerializedName("TowStop")
    TOW_STOP(100003),

    @SerializedName("HardAcceleration")
    HARD_ACCELERATION(100004),

    @SerializedName("HardBrake")
    HARD_BRAKE(100005),

    @SerializedName("HardRight")
    HARD_RIGHT(100006),

    @SerializedName("HardLeft")
    HARD_LEFT(100007),

    @SerializedName("Speed")
    SPEED(100008),

    @SerializedName("Diagnostic")
    DIAGNOSTIC(100009),

    @SerializedName("OffStatus")
    OFF_STATUS(100010),

    @SerializedName("Park")
    PARK(100011),

    @SerializedName("Accelerometer")
    ACCELEROMETER(100012),

    @SerializedName("Acceleration")
    ACCELERATION(100013),

    @SerializedName("Deceleration")
    DECELERATION(100014),

    @SerializedName("HeadingChange")
    HEADING_CHANGE(100015),

    @SerializedName("Mileage")
    MILEAGE(100016),

    @SerializedName("LowFuel")
    LOW_FUEL(100017),

    @SerializedName("RPM")
    RPM(100018),

    @SerializedName("MovementStart")
    MOVEMENT_START(100019),

    @SerializedName("MovementStop")
    MOVEMENT_STOP(100020),

    @SerializedName("HeartBeat")
    HEARTBEAT(100021),

    @SerializedName("DeviceDiagnostic")
    DEVICE_DIAGNOSTIC(100022),

    @SerializedName("IdleEvent")
    IDLE(100023),

    @SerializedName("PreSleepWarning")
    PRE_SLEEP_WARNING(100024),

    @SerializedName("Unknown")
    UNKNOWN(-1);

    private final int id;

    EventType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
