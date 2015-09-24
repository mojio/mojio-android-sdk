package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a conditional observer.
 * Created by jian on 01/04/2015.
 */
public class ConditionalObserver extends ConditionalObserverBase {

    @SerializedName("Field")
    private String field;

    @SerializedName("Threshold1")
    private float threshold1;

    @SerializedName("Threshold2")
    private float threshold2;

    @SerializedName("Operation1")
    private String operation1;

    @SerializedName("Operation2")
    private String operation2;

    @SerializedName("Conjunction")
    private String conjunction;

    @SerializedName("ConditionalValue")
    private boolean conditionalValue;

    public boolean isConditionalValue() {
        return conditionalValue;
    }

    public void setConditionalValue(boolean conditionalValue) {
        this.conditionalValue = conditionalValue;
    }

    public String getConjunction() {
        return conjunction;
    }

    public void setConjunction(String conjunction) {
        this.conjunction = conjunction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperation1() {
        return operation1;
    }

    public void setOperation1(String operation1) {
        this.operation1 = operation1;
    }

    public String getOperation2() {
        return operation2;
    }

    public void setOperation2(String operation2) {
        this.operation2 = operation2;
    }

    public float getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(float threshold1) {
        this.threshold1 = threshold1;
    }

    public float getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(float threshold2) {
        this.threshold2 = threshold2;
    }

    @Override
    public String toString() {
        return "ConditionalObserver{" +
                "conditionalValue=" + conditionalValue +
                ", field='" + field + '\'' +
                ", threshold1=" + threshold1 +
                ", threshold2=" + threshold2 +
                ", operation1='" + operation1 + '\'' +
                ", operation2='" + operation2 + '\'' +
                ", conjunction='" + conjunction + '\'' +
                '}';
    }
}
