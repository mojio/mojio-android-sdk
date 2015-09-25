package io.moj.mobile.android.sdk.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("UserName")
    private String userName;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("Email")
    private String Email;

    @SerializedName("CreationDate")
    private String creationDate;

    @SerializedName("LastActivityDate")
    private String lastActivityDate;

    @SerializedName("LastLoginDate")
    private String lastLoginDate;

    @SerializedName("Locale")
    private String locale;

    @SerializedName("PhoneNumber")
    private String PhoneNumber;

    @SerializedName("Name")
    private String Name;

    @SerializedName("_id")
    private String id;

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(String lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "creationDate='" + creationDate + '\'' +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Email='" + Email + '\'' +
                ", lastActivityDate='" + lastActivityDate + '\'' +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", locale='" + locale + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Name='" + Name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
