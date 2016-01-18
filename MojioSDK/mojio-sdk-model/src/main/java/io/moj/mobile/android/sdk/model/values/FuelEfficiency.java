package io.moj.mobile.android.sdk.model.values;

import org.joda.time.DateTime;

import io.moj.mobile.android.sdk.model.enums.FuelEfficiencyUnit;
import io.moj.mobile.android.sdk.model.utils.TimeFormatHelpers;

/**
 * Model object for a FuelEfficiency value.
 * Created by mhorie on 2016-01-12.
 */
public class FuelEfficiency extends DeviceMeasurement {

    private MeasurementStatistics Statistics;
    private String BenchmarkTime;
    private MeasurementStatistics BenchmarkStatistics;

    public FuelEfficiencyUnit getBaseFuelEfficiencyUnit() {
        return FuelEfficiencyUnit.fromKey(getBaseUnit());
    }

    public void setBaseFuelEfficiencyUnit(FuelEfficiencyUnit baseUnit) {
        setBaseUnit(baseUnit.getKey());
    }

    public FuelEfficiencyUnit getFuelEfficiencyUnit() {
        return FuelEfficiencyUnit.fromKey(getUnit());
    }

    public void setFuelEfficiencyUnit(FuelEfficiencyUnit unit) {
        setUnit(unit.getKey());
    }

    public MeasurementStatistics getBenchmarkStatistics() {
        return BenchmarkStatistics;
    }

    public void setBenchmarkStatistics(MeasurementStatistics benchmarkStatistics) {
        BenchmarkStatistics = benchmarkStatistics;
    }

    public DateTime getBenchmarkTime() {
        return TimeFormatHelpers.fromServerFormatted(BenchmarkTime);
    }

    public void setBenchmarkTime(DateTime benchmarkTime) {
        BenchmarkTime = TimeFormatHelpers.toServerFormatted(benchmarkTime);
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
                "} " + super.toString();
    }
}
