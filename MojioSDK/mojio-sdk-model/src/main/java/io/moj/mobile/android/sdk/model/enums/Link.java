package io.moj.mobile.android.sdk.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum for Link types.
 * Created by mhorie on 2016-01-13.
 */
public enum Link {

    // TODO: Add all Link types
    @SerializedName("Self")
    SELF("Self");

    private String key;

    Link(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    /**
     * "Self": "/v2/trips/e63e85fa-915c-4e6d-ab22-e677e52ef729",
     "Vehicle": "/v2/vehicles/9e05efa5-e238-499a-b7bc-2d35f756e0b5",
     "Mojio": "/v2/mojios/61d04264-44fe-4f23-a43b-f2b3cb097412",
     "Permissions": "/v2/trips/e63e85fa-915c-4e6d-ab22-e677e52ef729/permissions",
     "Permission": "/v2/trips/e63e85fa-915c-4e6d-ab22-e677e52ef729/permission",
     "States": "/v2/trips/e63e85fa-915c-4e6d-ab22-e677e52ef729/history/states",
     "Locations": "/v2/trips/e63e85fa-915c-4e6d-ab22-e677e52ef729/history/locations"
     "Next": "https://staging-api.moj.io/v2/trips?$skip=2",
     "First": "https://staging-api.moj.io/v2/trips?$skip=0"
     */

    public static Link fromKey(String key) {
        for (Link unit : Link.values()) {
            if (unit.getKey().equals(key)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Illegal Link value supplied: " + key);
    }
}
