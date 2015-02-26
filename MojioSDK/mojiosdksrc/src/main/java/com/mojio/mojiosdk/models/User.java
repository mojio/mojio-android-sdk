package com.mojio.mojiosdk.models;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class User {

    // Note, commented out data we are not yet using.

    //public String Type;
    public String UserName;
    public String FirstName;
    public String LastName;
    public String Email;
    //public int UserCount;
    //public int Credits;
    //public String CreationDate;
    public String LastActivityDate;
    public String LastLoginDate;
    public String Locale;
    //public Developer DeveloperDetails;
    //public boolean IsAuthenticated;
    @SerializedName("Name")
    public String AnotherName;
    public String _id;
    //public boolean _deleted;

    public Bitmap downloadedImage;

}
