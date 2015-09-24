package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import io.moj.mobile.android.sdk.models.DiagnosticCode;

/**
 * Model class for a diagnostic status. This is a collection of diagnostic codes at a specific time.
 * Created by skidson on 15-09-21.
 */
public class DiagnosticStatus {

    @SerializedName("TimeStamp")
    private String timestamp;

    @SerializedName("DiagnosticCodes")
    private ArrayList<DiagnosticCode> diagnosticCodes;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<DiagnosticCode> getDiagnosticCodes() {
        return diagnosticCodes;
    }

    public void setDiagnosticCodes(ArrayList<DiagnosticCode> diagnosticCodes) {
        this.diagnosticCodes = diagnosticCodes;
    }

    @Override
    public String toString() {
        return "DiagnosticStatus{" +
                "timestamp='" + timestamp + '\'' +
                ", diagnosticCodes=" + diagnosticCodes +
                '}';
    }
}
