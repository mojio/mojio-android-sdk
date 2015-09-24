package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Model class for a diagnostic code observer.
 * Created by jian on 01/04/2015.
 */
public class DiagnosticCodeObserver extends ConditionalObserverBase{

    @SerializedName("DiagnosticCodes")
    private ArrayList<Object> diagnosticCodes;

    public ArrayList<Object> getDiagnosticCodes() {
        return diagnosticCodes;
    }

    public void setDiagnosticCodes(ArrayList<Object> diagnosticCodes) {
        diagnosticCodes = diagnosticCodes;
    }

    @Override
    public String toString() {
        return "DiagnosticCodeObserver{" +
                "diagnosticCodes=" + diagnosticCodes +
                '}';
    }
}
