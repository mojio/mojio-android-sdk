package io.moj.mobile.android.sdk.models;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a recall.
 * Created by ssawchenko on 15-03-19.
 */
public class Recall {

    @SerializedName("NHTSACampaignNumber")
    private String nhtsaCampaignNumber;

    @SerializedName("MFRCampaignNumber")
    private String mfrCampaignNumber;

    @SerializedName("ComponentDescription")
    private String componentDescription;

    @SerializedName("ReportManufacturer")
    private String reportManufacturer;

    @SerializedName("ManufacturingStartDate")
    private String manufacturingStartDate;

    @SerializedName("ManufacturingEndDate")
    private String manufacturingEndDate;

    @SerializedName("RecallTypeCode")
    private String typeCode;

    @SerializedName("PotentialUnitsAffected")
    private String potentialUnitsAffected;

    @SerializedName("OwnerNotificationDate")
    private String ownerNotificationDate;

    @SerializedName("RecallInitiator")
    private String recallInitiator;

    @SerializedName("ProductManufacturer")
    private String productManufacturer;

    @SerializedName("ReportReceivedDate")
    private String reportReceivedDate;

    @SerializedName("ReportRecievedDate")
    private String reportRecievedDate;

    @SerializedName("RegulationPartNumber")
    private String regulationPartNumber;

    @SerializedName("FMVVSNumber")
    private String fmvvsNumber;

    @SerializedName("DefectSummary")
    private String defectSummary;

    @SerializedName("ConsequenceSummary")
    private String consequenceSummary;

    @SerializedName("CorrectiveAction")
    private String correctiveAction;

    @SerializedName("Notes")
    private String notes;

    @SerializedName("RecalledComponentId")
    private String recalledComponentId;

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public String getConsequenceSummary() {
        return consequenceSummary;
    }

    public void setConsequenceSummary(String consequenceSummary) {
        this.consequenceSummary = consequenceSummary;
    }

    public String getCorrectiveAction() {
        return correctiveAction;
    }

    public void setCorrectiveAction(String correctiveAction) {
        this.correctiveAction = correctiveAction;
    }

    public String getDefectSummary() {
        return defectSummary;
    }

    public void setDefectSummary(String defectSummary) {
        this.defectSummary = defectSummary;
    }

    public String getFmvvsNumber() {
        return fmvvsNumber;
    }

    public void setFmvvsNumber(String fmvvsNumber) {
        this.fmvvsNumber = fmvvsNumber;
    }

    public String getManufacturingEndDate() {
        return manufacturingEndDate;
    }

    public void setManufacturingEndDate(String manufacturingEndDate) {
        this.manufacturingEndDate = manufacturingEndDate;
    }

    public String getManufacturingStartDate() {
        return manufacturingStartDate;
    }

    public void setManufacturingStartDate(String manufacturingStartDate) {
        this.manufacturingStartDate = manufacturingStartDate;
    }

    public String getMfrCampaignNumber() {
        return mfrCampaignNumber;
    }

    public void setMfrCampaignNumber(String mfrCampaignNumber) {
        this.mfrCampaignNumber = mfrCampaignNumber;
    }

    public String getNhtsaCampaignNumber() {
        return nhtsaCampaignNumber;
    }

    public void setNhtsaCampaignNumber(String nhtsaCampaignNumber) {
        this.nhtsaCampaignNumber = nhtsaCampaignNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getOwnerNotificationDate() {
        return ownerNotificationDate;
    }

    public void setOwnerNotificationDate(String ownerNotificationDate) {
        this.ownerNotificationDate = ownerNotificationDate;
    }

    public String getPotentialUnitsAffected() {
        return potentialUnitsAffected;
    }

    public void setPotentialUnitsAffected(String potentialUnitsAffected) {
        this.potentialUnitsAffected = potentialUnitsAffected;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public String getRecalledComponentId() {
        return recalledComponentId;
    }

    public void setRecalledComponentId(String recalledComponentId) {
        this.recalledComponentId = recalledComponentId;
    }

    public String getRecallInitiator() {
        return recallInitiator;
    }

    public void setRecallInitiator(String recallInitiator) {
        this.recallInitiator = recallInitiator;
    }

    public String getRegulationPartNumber() {
        return regulationPartNumber;
    }

    public void setRegulationPartNumber(String regulationPartNumber) {
        this.regulationPartNumber = regulationPartNumber;
    }

    public String getReportManufacturer() {
        return reportManufacturer;
    }

    public void setReportManufacturer(String reportManufacturer) {
        this.reportManufacturer = reportManufacturer;
    }

    public String getReportReceivedDate() {
        // API has a typo, coded for backwards compatibility
        return TextUtils.isEmpty(reportReceivedDate) ? reportRecievedDate : reportReceivedDate;
    }

    public void setReportReceivedDate(String reportReceivedDate) {
        this.reportReceivedDate = reportReceivedDate;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Override
    public String toString() {
        return "Recall{" +
                "componentDescription='" + componentDescription + '\'' +
                ", nhtsaCampaignNumber='" + nhtsaCampaignNumber + '\'' +
                ", mfrCampaignNumber='" + mfrCampaignNumber + '\'' +
                ", reportManufacturer='" + reportManufacturer + '\'' +
                ", manufacturingStartDate='" + manufacturingStartDate + '\'' +
                ", manufacturingEndDate='" + manufacturingEndDate + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", potentialUnitsAffected='" + potentialUnitsAffected + '\'' +
                ", ownerNotificationDate='" + ownerNotificationDate + '\'' +
                ", recallInitiator='" + recallInitiator + '\'' +
                ", productManufacturer='" + productManufacturer + '\'' +
                ", reportReceivedDate='" + getReportReceivedDate() + '\'' +
                ", regulationPartNumber='" + regulationPartNumber + '\'' +
                ", fmvvsNumber='" + fmvvsNumber + '\'' +
                ", defectSummary='" + defectSummary + '\'' +
                ", consequenceSummary='" + consequenceSummary + '\'' +
                ", correctiveAction='" + correctiveAction + '\'' +
                ", notes='" + notes + '\'' +
                ", recalledComponentId='" + recalledComponentId + '\'' +
                '}';
    }
}
