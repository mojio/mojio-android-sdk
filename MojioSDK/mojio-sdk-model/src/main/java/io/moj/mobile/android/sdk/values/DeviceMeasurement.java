package io.moj.mobile.android.sdk.values;

/**
 * Model object for an DeviceMeasurement value.
 * Created by mhorie on 2016-01-14.
 */
public abstract class DeviceMeasurement {

    private String BaseUnit;
    private Float BaseValue;
    private String Unit;
    private Float Value;

    public String getBaseUnit() {
        return BaseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        BaseUnit = baseUnit;
    }

    public Float getBaseValue() {
        return BaseValue;
    }

    public void setBaseValue(Float baseValue) {
        BaseValue = baseValue;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public Float getValue() {
        return Value;
    }

    public void setValue(Float value) {
        Value = value;
    }

    @Override
    public String toString() {
        return "DeviceMeasurement{" +
                "BaseUnit='" + BaseUnit + '\'' +
                ", BaseValue=" + BaseValue +
                ", Unit='" + Unit + '\'' +
                ", Value=" + Value +
                '}';
    }
}
