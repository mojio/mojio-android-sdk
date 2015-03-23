package com.mojio.mojiosdk.models;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;
import com.mojio.mojiosdk.units.Distance;

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
    public int distanceUnits = Distance.KMS; // Default to KMs
}
