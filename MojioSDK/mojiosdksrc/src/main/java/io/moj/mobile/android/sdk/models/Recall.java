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

    // TODO here
    private String componentDescription;
    private String reportManufacturer;
    private String manufacturingStartDate;
    private String manufacturingEndDate;

    @SerializedName("RecallTypeCode")
    private String typeCode;

    private String potentialUnitsAffected;
    private String ownerNotificationDate;
    private String recallInitiator;
    private String productManufacturer;
    private String reportReceivedDate;
    private String regulationPartNumber;
    private String fmvvsNumber;
    private String defectSummary;
    private String consequenceSummary;
    private String correctiveAction;
    private String notes;
    private String recalledComponentId;

}
