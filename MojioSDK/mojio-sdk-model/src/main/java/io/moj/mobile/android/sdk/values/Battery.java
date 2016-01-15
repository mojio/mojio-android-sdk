package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.RiskSeverity;
import io.moj.mobile.android.sdk.models.values.FloatUnitValue;

/**
 * Model object for a Battery.
 * Created by mhorie on 2016-01-12.
 */
public class Battery extends FloatUnitValue {

    private Boolean Connected;
    private RiskSeverity RiskSeverity;
    private Duration LowVoltageDuration;
    private Duration HighVoltageDuration;

    public Boolean isConnected() {
        return Connected;
    }

    public void setConnected(Boolean connected) {
        Connected = connected;
    }

    public Duration getHighVoltageDuration() {
        return HighVoltageDuration;
    }

    public void setHighVoltageDuration(Duration highVoltageDuration) {
        HighVoltageDuration = highVoltageDuration;
    }

    public Duration getLowVoltageDuration() {
        return LowVoltageDuration;
    }

    public void setLowVoltageDuration(Duration lowVoltageDuration) {
        LowVoltageDuration = lowVoltageDuration;
    }

    public RiskSeverity getRiskSeverity() {
        return RiskSeverity;
    }

    public void setRiskSeverity(RiskSeverity riskSeverity) {
        RiskSeverity = riskSeverity;
    }

    @Override
    public String toString() {
        return "Battery{" +
                "Connected=" + Connected +
                ", RiskSeverity='" + RiskSeverity + '\'' +
                ", LowVoltageDuration=" + LowVoltageDuration +
                ", HighVoltageDuration=" + HighVoltageDuration +
                '}';
    }
}
