package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model object for a diagnostic code.
 * Created by ssawchenko on 15-02-06.
 */
public class DiagnosticCode {

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
    private Severity Severity;

    @SerializedName("_id")
    private String id;

    @SerializedName("_deleted")
    private boolean deleted;

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public io.moj.mobile.android.sdk.models.Severity getSeverity() {
        return Severity;
    }

    public void setSeverity(io.moj.mobile.android.sdk.models.Severity severity) {
        Severity = severity;
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
                ", Severity=" + Severity +
                ", id='" + id + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
