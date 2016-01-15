package io.moj.mobile.android.sdk.values;

import io.moj.mobile.android.sdk.models.values.FloatUnitValue;

/**
 * Model object for a FuelEfficiency value.
 * Created by mhorie on 2016-01-12.
 */
public class FuelEfficiency extends DeviceMeasurement {

    private MeasurementStatistics Statistics;
    private String BenchmarkTime;
    private MeasurementStatistics BenchmarkStatistics;

    public MeasurementStatistics getBenchmarkStatistics() {
        return BenchmarkStatistics;
    }

    public void setBenchmarkStatistics(MeasurementStatistics benchmarkStatistics) {
        BenchmarkStatistics = benchmarkStatistics;
    }

    public String getBenchmarkTime() {
        return BenchmarkTime;
    }

    public void setBenchmarkTime(String benchmarkTime) {
        BenchmarkTime = benchmarkTime;
    }

    public MeasurementStatistics getStatistics() {
        return Statistics;
    }

    public void setStatistics(MeasurementStatistics statistics) {
        Statistics = statistics;
    }

    @Override
    public String toString() {
        return "FuelEfficiency{" +
                "BenchmarkStatistics=" + BenchmarkStatistics +
                ", Statistics=" + Statistics +
                ", BenchmarkTime='" + BenchmarkTime + '\'' +
                '}';
    }
}
