package com.mojio.mojiosdk.models;

/**
 * Created by ssawchenko on 15-02-10.
 */
public class GooglePlace {

    public static class Location {
        public double lat;
        public double lng;
    }

    public static class Viewport {
        public GooglePlace.Location northeast;
        public GooglePlace.Location southwest;
    }

    public static class Geometry {
        public GooglePlace.Location location;
        public GooglePlace.Viewport viewport;
    }

    public static class OpeningHours {
        public boolean open_now;
    }

    public Geometry geometry;
    public String icon;
    public String id;
    public String place_id;
    public String rating;
    public String name;
    public String reference;
    public String[] types;
    public String vicinity;
    public OpeningHours opening_hours;

    // Found via secondary lookup, but cached here
    public String phone_number;

    public boolean isMechanic() {
        return isType("car_repair");
    }

    public boolean isDealer() {
        return isType("car_dealer");
    }

    private boolean isType(String type) {
        for (int i=0; i < types.length; i++) {
            if (types[i].equals(type)) {
                return true;
            }
        }
        return false;
    }
}
