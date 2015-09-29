package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Model class for an observer.
 * Created by jian on 01/04/2015.
 */
public class Observer {

    // TODO we should create a Builder for Observers to make it clearer what is required

    @SerializedName("Name")
    public String Name;

    @SerializedName("ObserverType")
    public String observerType;

    @SerializedName("AppId")
    public String appId;

    @SerializedName("OwnerId")
    public String ownerId;

    @SerializedName("Parent")
    public String parent;

    @SerializedName("ParentId")
    public String parentId;

    @SerializedName("Subject")
    public String subject;

    @SerializedName("SubjectId")
    public String subjectId;

    // TODO we should custom serialize this as a comma-separate list of {@link ObserverTransport}s
    @SerializedName("Transports")
    public String transports;

    @SerializedName("Status")
    public String status;

    @SerializedName("Tokens")
    public ArrayList<ObserverToken> tokens;

    @SerializedName("TimeWindow")
    public String timeWindow;

    @SerializedName("BroadcastOnlyRecent")
    public boolean broadcastOnlyRecent;

    @SerializedName("Throttle")
    public String throttle;

    @SerializedName("NextAllowedBroadcast")
    public String nextAllowedBroadcast;

    @SerializedName("_id")
    public String id;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public boolean isBroadcastOnlyRecent() {
        return broadcastOnlyRecent;
    }

    public void setBroadcastOnlyRecent(boolean broadcastOnlyRecent) {
        this.broadcastOnlyRecent = broadcastOnlyRecent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNextAllowedBroadcast() {
        return nextAllowedBroadcast;
    }

    public void setNextAllowedBroadcast(String nextAllowedBroadcast) {
        this.nextAllowedBroadcast = nextAllowedBroadcast;
    }

    public String getObserverType() {
        return observerType;
    }

    public void setObserverType(String observerType) {
        this.observerType = observerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getThrottle() {
        return throttle;
    }

    public void setThrottle(String throttle) {
        this.throttle = throttle;
    }

    public String getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(String timeWindow) {
        this.timeWindow = timeWindow;
    }

    public ArrayList<ObserverToken> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<ObserverToken> tokens) {
        this.tokens = tokens;
    }

    public String getTransports() {
        return transports;
    }

    public void setTransports(String transports) {
        this.transports = transports;
    }

    @Override
    public String toString() {
        return "Observer{" +
                "appId='" + appId + '\'' +
                ", Name='" + Name + '\'' +
                ", observerType='" + observerType + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", parent='" + parent + '\'' +
                ", parentId='" + parentId + '\'' +
                ", subject='" + subject + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", transports='" + transports + '\'' +
                ", status='" + status + '\'' +
                ", tokens=" + tokens +
                ", timeWindow='" + timeWindow + '\'' +
                ", broadcastOnlyRecent=" + broadcastOnlyRecent +
                ", throttle='" + throttle + '\'' +
                ", nextAllowedBroadcast='" + nextAllowedBroadcast + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
