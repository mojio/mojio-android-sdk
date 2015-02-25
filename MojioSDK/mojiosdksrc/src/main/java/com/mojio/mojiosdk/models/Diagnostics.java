package com.mojio.mojiosdk.models;

import java.util.ArrayList;

/**
 * Created by ssawchenko on 15-02-06.
 */
public class Diagnostics {

    public static  class Instance {
        public String Time;
        public Location Location;

        public Instance(String t, Location l) {
            this.Time = t;
            this.Location = l;
        }
    }

    public String Type;
    public String Code;
    public String Description;
    public String Source;
    public String Category;
    public String Severity;
    public String _id;

    public int Count; // # of identical DTCs; not given in JSON, is set in AlertsFragment
    public ArrayList<Instance> Instances; // Diagnostic events will have instances

    public Diagnostics() {
        this.Count = 1;
        this.Instances = new ArrayList<>();
    }

    public Diagnostics(String... args) {
        this();
        this.Code = args[0];
        this.Description = args[1];
        this.Source = args[2];
        this._id = args[3];
    }

    public static Diagnostics[] createTestData() {
        Diagnostics[] result = new Diagnostics[4];
        result[0] = new Diagnostics("P0200", "Your train has little power", "Powertrain", "00001");
        result[1] = new Diagnostics("P0300", "Something about a car", "Powertrain", "00002");
        result[2] = new Diagnostics("U0120", "The system is down", "Network", "00003");
        result[3] = new Diagnostics("B1000", "Its almost bathing suit season", "Body", "00004");

        return result;
    }

}
