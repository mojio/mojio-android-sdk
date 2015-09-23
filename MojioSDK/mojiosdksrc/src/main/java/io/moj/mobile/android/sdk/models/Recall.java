package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ssawchenko on 15-03-19.
 */
public class Recall {

/*
Recall {
NHTSACampaignNumber (string, optional),
MFRCampaignNumber (string, optional),
ComponentDescription (string, optional),
ReportManufacturer (string, optional),
ManufacturingStartDate (string, optional),
ManufacturingEndDate (string, optional),
RecallTypeCode (string, optional),
PotentialUnitsAffected (string, optional),
OwnerNotificationDate (string, optional),
RecallInitiator (string, optional),
ProductManufacturer (string, optional),
ReportRecievedDate (string, optional),
RecordCreationDate (string, optional),
RegulationPartNumber (string, optional),
FMVVSNumber (string, optional),
DefectSummary (string, optional),
ConsequenceSummary (string, optional),
CorrectiveAction (string, optional),
Notes (string, optional),
RecalledComponentId (string, optional)
}*/

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

    @SerializedName("RegulationPartNumber")
    private String regulationPartNumber;

    @SerializedName("FMVVSNumber")
    private String fmvvsNumber;

    @SerializedName("DefectSummary")
    private String defectSummary;

    @SerializedName("ConsequenceSummary")
    private String consequenceSummary;
    private String correctiveAction;
    private String notes;
    private String recalledComponentId;

}
