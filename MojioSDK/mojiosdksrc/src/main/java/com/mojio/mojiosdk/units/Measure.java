package com.mojio.mojiosdk.units;

/**
 * Created by ssawchenko on 15-03-25.
 */
public class Measure {

    public static final int METRIC = 0;     // Kms, Litres
    public static final int IMPERIAL = 1;   // Miles, Gallons

    private static final float GALLONS_PER_LITRE = 0.264172f;
    private static final float MILES_PER_KM = 0.621371f;

    public static float litresToGallons(float litres) {
        return litres * GALLONS_PER_LITRE;
    }

    public static float GallonsToLitres(float gallons) {
        return gallons * (1/GALLONS_PER_LITRE);
    }

    public static float kmsToMiles(float kms) {
        return kms * MILES_PER_KM;
    }

    public static float milesToKms(float miles) {
        return miles * (1/MILES_PER_KM);
    }
}
