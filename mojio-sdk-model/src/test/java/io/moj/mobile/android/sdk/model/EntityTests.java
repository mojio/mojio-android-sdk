package io.moj.mobile.android.sdk.model;

import org.junit.Test;

import io.moj.mobile.android.sdk.model.values.Location;

import static io.moj.mobile.android.sdk.test.TestUtils.assertGettersAndSetters;
import static io.moj.mobile.android.sdk.test.TestUtils.assertToStringContainsAllFields;

/**
 * Created by skidson on 16-02-23.
 */
public class EntityTests {

    @Test
    public void testApp() throws IllegalAccessException {
        App app = new App();
        assertToStringContainsAllFields(app);
        assertGettersAndSetters(app);
    }

    @Test
    public void testGroup() throws IllegalAccessException {
        Group group = new Group();
        assertToStringContainsAllFields(group);
        assertGettersAndSetters(group);
    }

    @Test
    public void testListResponse() throws IllegalAccessException {
        ListResponse response = new ListResponse();
        assertToStringContainsAllFields(response);
        assertGettersAndSetters(response);
    }

    @Test
    public void testMojio() throws IllegalAccessException {
        Mojio mojio = new Mojio();
        assertToStringContainsAllFields(mojio);
        assertGettersAndSetters(mojio);
    }

    @Test
    public void testTrip() throws IllegalAccessException {
        Trip trip = new Trip();
        assertToStringContainsAllFields(trip);
        assertGettersAndSetters(trip);
    }

    @Test
    public void testUser() throws IllegalAccessException {
        User user = new User();
        assertToStringContainsAllFields(user);
        assertGettersAndSetters(user);
    }

    @Test
    public void testVehicle() throws IllegalAccessException {
        Vehicle vehicle = new Vehicle();
        assertToStringContainsAllFields(vehicle);
        assertGettersAndSetters(vehicle);
    }

    @Test
    public void testVehicleMeasure() throws IllegalAccessException {
        VehicleMeasure vehicleMeasure = new VehicleMeasure();
        assertToStringContainsAllFields(vehicleMeasure);
        assertGettersAndSetters(vehicleMeasure);
    }

}
