package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for vehicle service.
 * Created by ssawchenko on 15-04-01.
 */
public class VehicleService {

    @SerializedName("DOVehicleId")
    private int DOVehicleId;

    @SerializedName("DOEngineId")
    private int DOEngineId;

    @SerializedName("TransNotes")
    private String TransNotes;

    @SerializedName("MaintenanceCategory")
    private String MaintenanceCategory;

    @SerializedName("MaintenanceName")
    private String MaintenanceName;

    @SerializedName("MaintenanceNotes")
    private String MaintenanceNotes;

    @SerializedName("ScheduleName")
    private String ScheduleName;

    @SerializedName("ScheduleDescription")
    private String ScheduleDescription;

    @SerializedName("OperatingParameter")
    private String OperatingParameter;

    @SerializedName("OperatingParameterNotes")
    private String OperatingParameterNotes;

    @SerializedName("ComputerCode")
    private String ComputerCode;

    @SerializedName("ServiceEvent")
    private String ServiceEvent;

    @SerializedName("IntervalType")
    private String IntervalType;

    @SerializedName("Value")
    private float Value;

    @SerializedName("Units")
    private String Units;

    @SerializedName("InitialValue")
    private float InitialValue;

    public String getComputerCode() {
        return ComputerCode;
    }

    public void setComputerCode(String computerCode) {
        ComputerCode = computerCode;
    }

    public int getDOEngineId() {
        return DOEngineId;
    }

    public void setDOEngineId(int DOEngineId) {
        this.DOEngineId = DOEngineId;
    }

    public int getDOVehicleId() {
        return DOVehicleId;
    }

    public void setDOVehicleId(int DOVehicleId) {
        this.DOVehicleId = DOVehicleId;
    }

    public float getInitialValue() {
        return InitialValue;
    }

    public void setInitialValue(float initialValue) {
        InitialValue = initialValue;
    }

    public String getIntervalType() {
        return IntervalType;
    }

    public void setIntervalType(String intervalType) {
        IntervalType = intervalType;
    }

    public String getMaintenanceCategory() {
        return MaintenanceCategory;
    }

    public void setMaintenanceCategory(String maintenanceCategory) {
        MaintenanceCategory = maintenanceCategory;
    }

    public String getMaintenanceName() {
        return MaintenanceName;
    }

    public void setMaintenanceName(String maintenanceName) {
        MaintenanceName = maintenanceName;
    }

    public String getMaintenanceNotes() {
        return MaintenanceNotes;
    }

    public void setMaintenanceNotes(String maintenanceNotes) {
        MaintenanceNotes = maintenanceNotes;
    }

    public String getOperatingParameter() {
        return OperatingParameter;
    }

    public void setOperatingParameter(String operatingParameter) {
        OperatingParameter = operatingParameter;
    }

    public String getOperatingParameterNotes() {
        return OperatingParameterNotes;
    }

    public void setOperatingParameterNotes(String operatingParameterNotes) {
        OperatingParameterNotes = operatingParameterNotes;
    }

    public String getScheduleDescription() {
        return ScheduleDescription;
    }

    public void setScheduleDescription(String scheduleDescription) {
        ScheduleDescription = scheduleDescription;
    }

    public String getScheduleName() {
        return ScheduleName;
    }

    public void setScheduleName(String scheduleName) {
        ScheduleName = scheduleName;
    }

    public String getServiceEvent() {
        return ServiceEvent;
    }

    public void setServiceEvent(String serviceEvent) {
        ServiceEvent = serviceEvent;
    }

    public String getTransNotes() {
        return TransNotes;
    }

    public void setTransNotes(String transNotes) {
        TransNotes = transNotes;
    }

    public String getUnits() {
        return Units;
    }

    public void setUnits(String units) {
        Units = units;
    }

    public float getValue() {
        return Value;
    }

    public void setValue(float value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "VehicleService{" +
                "ComputerCode='" + ComputerCode + '\'' +
                ", DOVehicleId=" + DOVehicleId +
                ", DOEngineId=" + DOEngineId +
                ", TransNotes='" + TransNotes + '\'' +
                ", MaintenanceCategory='" + MaintenanceCategory + '\'' +
                ", MaintenanceName='" + MaintenanceName + '\'' +
                ", MaintenanceNotes='" + MaintenanceNotes + '\'' +
                ", ScheduleName='" + ScheduleName + '\'' +
                ", ScheduleDescription='" + ScheduleDescription + '\'' +
                ", OperatingParameter='" + OperatingParameter + '\'' +
                ", OperatingParameterNotes='" + OperatingParameterNotes + '\'' +
                ", ServiceEvent='" + ServiceEvent + '\'' +
                ", IntervalType='" + IntervalType + '\'' +
                ", Value=" + Value +
                ", Units='" + Units + '\'' +
                ", InitialValue=" + InitialValue +
                '}';
    }
}
