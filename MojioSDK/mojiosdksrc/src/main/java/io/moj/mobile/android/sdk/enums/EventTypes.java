package io.moj.mobile.android.sdk.enums;

import android.os.Message;

/**
 * Created by jian on 16/04/2015.
 */
public enum EventTypes {

    /// <summary>
    /// for server-side diagnostics
    /// </summary>
    Log (1),

    /// <summary>
    /// for Mojio communication session.
    /// </summary>
    Message (2),

    /// <summary>
    /// information
    /// </summary>
    Information (100),

    /// <summary>
    /// mojio on (Mojio)
    /// </summary>
    MojioOn (101),

    /// <summary>
    /// mojio idle (Mojio)
    /// </summary>
    MojioIdle (102),

    /// <summary>
    /// mojio awake (Mojio)
    /// </summary>
    MojioWake (103),

    /// <summary>
    /// ignition on (Mojio)
    /// </summary>
    IgnitionOn (104),

    /// <summary>
    /// ignition off (Mojio)
    /// </summary>
    IgnitionOff (105),

    /// <summary>
    /// mojio off (Mojio)
    /// </summary>
    MojioOff (106),

    /// <summary>
    /// low battery (Mojio)
    /// </summary>
    LowBattery (107),

    /// <summary>
    /// GPS update (Mojio)
    /// </summary>
    TripEvent (1005),

    /// <summary>
    /// fence enter (Mojio)
    /// </summary>
    FenceEntered (1006),

    /// <summary>
    /// fence exit (Mojio)
    /// </summary>
    FenceExited (1007),

    /// <summary>
    /// trip status (Mojio)
    /// </summary>
    TripStatus (1010),

    /// <summary>
    /// warning
    /// </summary>
    Warning (30000),

    /// <summary>
    /// malfunction indicator light warning (Mojio)
    /// </summary>
    MILWarning (30001),

    /// <summary>
    /// connection lost (server)
    /// </summary>
    ConnectionLost (40000),

    /// <summary>
    /// alert
    /// </summary>
    Alert (100000),

    /// <summary>
    /// accident (Mojio)
    /// </summary>
    Accident (100001),

    /// <summary>
    /// tow start (Mojio)
    /// </summary>
    TowStart (100002),

    /// <summary>
    /// tow stop (Mojio)
    /// </summary>
    TowStop (100003),

    /// <summary>
    /// hard acceleration (Mojio)
    /// </summary>
    HardAcceleration (100004),

    /// <summary>
    /// hard brake (Mojio)
    /// </summary>
    HardBrake (100005),

    /// <summary>
    /// hard right (Mojio)
    /// </summary>
    HardRight (100006),

    /// <summary>
    /// hard left (Mojio)
    /// </summary>
    HardLeft (100007),

    /// <summary>
    /// Mojio-defined excessive speed (Mojio)
    /// </summary>
    Speed (100008),

    /// <summary>
    /// Mojio-defined diagnostics event
    /// </summary>
    Diagnostic (100009),

    /// <summary>
    /// trip status (Mojio)
    /// </summary>
    OffStatus (100010),

    /// <summary>
    /// park
    /// </summary>
    Park (100011),

    /// <summary>
    /// acceleromter
    /// </summary>
    Accelerometer (100012),

    /// <summary>
    /// acceleration
    /// </summary>
    Acceleration (100013),

    /// <summary>
    /// deceleration
    /// </summary>
    Deceleration (100014),

    /// <summary>
    /// HeadingChange
    /// </summary>
    HeadingChange (100015),

    /// <summary>
    /// Mileage
    /// </summary>
    Mileage (100016),

    /// <summary>
    /// LowFuel
    /// </summary>
    LowFuel (100017),

    /// <summary>
    /// RPM
    /// </summary>
    RPM (100018),

    /// <summary>
    /// Movement Start
    /// </summary>
    MovementStart (100019),

    /// <summary>
    /// Movement Stop
    /// </summary>
    MovementStop (100020),

    /// <summary>
    /// HeartBeat
    /// </summary>
    HeartBeat (100021),

    /// <summary>
    /// Mojio Diagnostic Data
    /// </summary>
    DeviceDiagnostic (100022),

    /// <summary>
    /// Vehicle Idle Event
    /// </summary>
    IdleEvent (100023),

    /// <summary>
    /// Pre Sleep Event
    /// </summary
    PreSleepWarning (100024),
    /// <summary>
    /// Unknown
    /// </summary>
    Unknown (-1);

    private final int id;
    EventTypes(int id) { this.id = id; }
    public int getValue() { return id; }
}
