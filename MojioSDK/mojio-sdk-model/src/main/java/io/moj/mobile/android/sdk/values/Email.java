package io.moj.mobile.android.sdk.values;

/**
 * Model object for a Email value.
 * Created by mhorie on 2016-01-14.
 */
public class Email {

    private boolean Verified;
    private String Address;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean isVerified() {
        return Verified;
    }

    public void setVerified(boolean verified) {
        Verified = verified;
    }

    @Override
    public String toString() {
        return "Email{" +
                "Address='" + Address + '\'' +
                ", Verified=" + Verified +
                '}';
    }
}
