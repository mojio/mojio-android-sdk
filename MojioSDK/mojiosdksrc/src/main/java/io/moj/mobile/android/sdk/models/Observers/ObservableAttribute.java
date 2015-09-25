package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for an observable attribute.
 * Created by jian on 01/04/2015.
 */
public class ObservableAttribute {

    @SerializedName("LinkType")
    private String linkType;

    // TODO can we just make this a String?
    @SerializedName("TypeId")
    private Object typeId;

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public Object getTypeId() {
        return typeId;
    }

    public void setTypeId(Object typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "ObservableAttribute{" +
                "linkType='" + linkType + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
