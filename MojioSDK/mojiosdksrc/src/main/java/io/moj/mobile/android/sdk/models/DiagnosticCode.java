package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model object for a diagnostic code.
 * Created by ssawchenko on 15-02-06.
 */
public class DiagnosticCode extends MojioObject {

    @SerializedName("Code")
    private String code;

    @SerializedName("Description")
    private String description;

    @SerializedName("Details")
    private String details;

    @SerializedName("Source")
    private String source;

    @SerializedName("Category")
    private String category;

    @SerializedName("Severity")
    private Severity severity;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public io.moj.mobile.android.sdk.models.Severity getSeverity() {
        return severity;
    }

    public void setSeverity(io.moj.mobile.android.sdk.models.Severity severity) {
        this.severity = severity;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "DiagnosticCode{" +
                "category='" + category + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", details='" + details + '\'' +
                ", source='" + source + '\'' +
                ", Severity=" + severity +
                '}';
    }
}
