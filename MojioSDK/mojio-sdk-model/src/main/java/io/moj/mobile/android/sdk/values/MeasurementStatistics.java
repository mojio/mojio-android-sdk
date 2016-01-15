package io.moj.mobile.android.sdk.values;

/**
 * Model object for an MeasurementStatistics.
 * Created by mhorie on 2016-01-14.
 */
public class MeasurementStatistics {

    private float NumOfSamples;
    private float Average;
    private float Variance;
    private float StdDev;
    private float IndexOfDispersion;
    private float CoeffOfVariation;
    private float M2;
    private float Min;
    private float Max;
    private Score StandardScore;
    private Score MinMaxScore;

    public float getAverage() {
        return Average;
    }

    public void setAverage(float average) {
        Average = average;
    }

    public float getCoeffOfVariation() {
        return CoeffOfVariation;
    }

    public void setCoeffOfVariation(float coeffOfVariation) {
        CoeffOfVariation = coeffOfVariation;
    }

    public float getIndexOfDispersion() {
        return IndexOfDispersion;
    }

    public void setIndexOfDispersion(float indexOfDispersion) {
        IndexOfDispersion = indexOfDispersion;
    }

    public float getM2() {
        return M2;
    }

    public void setM2(float m2) {
        M2 = m2;
    }

    public float getMax() {
        return Max;
    }

    public void setMax(float max) {
        Max = max;
    }

    public float getMin() {
        return Min;
    }

    public void setMin(float min) {
        Min = min;
    }

    public Score getMinMaxScore() {
        return MinMaxScore;
    }

    public void setMinMaxScore(Score minMaxScore) {
        MinMaxScore = minMaxScore;
    }

    public float getNumOfSamples() {
        return NumOfSamples;
    }

    public void setNumOfSamples(float numOfSamples) {
        NumOfSamples = numOfSamples;
    }

    public Score getStandardScore() {
        return StandardScore;
    }

    public void setStandardScore(Score standardScore) {
        StandardScore = standardScore;
    }

    public float getStdDev() {
        return StdDev;
    }

    public void setStdDev(float stdDev) {
        StdDev = stdDev;
    }

    public float getVariance() {
        return Variance;
    }

    public void setVariance(float variance) {
        Variance = variance;
    }

    @Override
    public String toString() {
        return "MeasurementStatistics{" +
                "Average=" + Average +
                ", NumOfSamples=" + NumOfSamples +
                ", Variance=" + Variance +
                ", StdDev=" + StdDev +
                ", IndexOfDispersion=" + IndexOfDispersion +
                ", CoeffOfVariation=" + CoeffOfVariation +
                ", M2=" + M2 +
                ", Min=" + Min +
                ", Max=" + Max +
                ", StandardScore=" + StandardScore +
                ", MinMaxScore=" + MinMaxScore +
                '}';
    }
}
