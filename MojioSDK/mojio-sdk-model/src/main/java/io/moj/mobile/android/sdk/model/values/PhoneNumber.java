package io.moj.mobile.android.sdk.model.values;

import io.moj.mobile.android.sdk.model.enums.PhoneType;

/**
 * Model object for a PhoneNumber value.
 * Created by mhorie on 2016-01-14.
 */
public class PhoneNumber {

    private PhoneType Type;
    private int CountryCode;
    private int AreaCode;
    private int Number;
    private Integer Ext;

    public int getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(int areaCode) {
        AreaCode = areaCode;
    }

    public int getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(int countryCode) {
        CountryCode = countryCode;
    }

    public Integer getExt() {
        return Ext;
    }

    public void setExt(Integer ext) {
        Ext = ext;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public PhoneType getType() {
        return Type;
    }

    public void setType(PhoneType type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "AreaCode=" + AreaCode +
                ", Type=" + Type +
                ", CountryCode=" + CountryCode +
                ", Number=" + Number +
                ", Ext=" + Ext +
                '}';
    }
}
