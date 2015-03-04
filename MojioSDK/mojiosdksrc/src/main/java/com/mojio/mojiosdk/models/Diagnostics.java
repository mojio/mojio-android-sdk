package com.mojio.mojiosdk.models;

/**
 * Created by ssawchenko on 15-02-06.
 */
public class Diagnostics {

    public String Type;
    public String Code;
    public String Description;
    public String Source;
    public String Category;
    public String Severity;
    public String _id;

    public Diagnostics() {}

    public Diagnostics(String... args) {
        this();
        this.Code = args[0];
        this.Description = args[1];
        this.Source = args[2];
        this._id = args[3];
    }
}
