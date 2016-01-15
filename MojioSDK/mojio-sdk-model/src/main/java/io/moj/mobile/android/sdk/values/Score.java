package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.enums.ScoringMethod;

/**
 * Model object for a Score value.
 * Created by mhorie on 2016-01-14.
 */
public class Score {

    private ScoringMethod ScoringMethod;
    private float Value;
    private float Percentile;
    private float Average;

    public float getAverage() {
        return Average;
    }

    public void setAverage(float average) {
        Average = average;
    }

    public float getPercentile() {
        return Percentile;
    }

    public void setPercentile(float percentile) {
        Percentile = percentile;
    }

    public io.moj.mobile.android.sdk.enums.ScoringMethod getScoringMethod() {
        return ScoringMethod;
    }

    public void setScoringMethod(io.moj.mobile.android.sdk.enums.ScoringMethod scoringMethod) {
        ScoringMethod = scoringMethod;
    }

    public float getValue() {
        return Value;
    }

    public void setValue(float value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "Score{" +
                "Average=" + Average +
                ", ScoringMethod=" + ScoringMethod +
                ", Value=" + Value +
                ", Percentile=" + Percentile +
                '}';
    }
}
