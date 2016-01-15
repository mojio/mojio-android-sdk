package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.RiskSeverity;

/**
 * Model object for an DiagnosticCode value.
 * Created by mhorie on 2016-01-12.
 */
public class DiagnosticCode {

    public String Code;
    public String Description ;
    public String Timestamp;
    public RiskSeverity Severity;
    public String Instructions;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getInstructions() {
        return Instructions;
    }

    public void setInstructions(String instructions) {
        Instructions = instructions;
    }

    public RiskSeverity getSeverity() {
        return Severity;
    }

    public void setSeverity(RiskSeverity severity) {
        Severity = severity;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DiagnosticCode{" +
                "Code='" + Code + '\'' +
                ", Description='" + Description + '\'' +
                ", Timestamp='" + Timestamp + '\'' +
                ", Severity=" + Severity +
                ", Instructions='" + Instructions + '\'' +
                '}';
    }
}
