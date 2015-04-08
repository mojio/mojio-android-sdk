package io.moj.mobile.android.sdk.models;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class User {
    public String UserName;
    public String FirstName;
    public String LastName;
    public String Email;
    public String LastActivityDate;
    public String LastLoginDate;
    public String Locale;
    @SerializedName("Name")
    public String AnotherName;
    public String _id;

    // Not from JSON - set by applications
    // TODO move out of SDK
    public Bitmap downloadedImage;
}
