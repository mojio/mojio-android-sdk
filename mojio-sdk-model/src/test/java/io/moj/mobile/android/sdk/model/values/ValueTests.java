package io.moj.mobile.android.sdk.model.values;

import org.junit.Test;

import static io.moj.mobile.android.sdk.test.TestUtils.assertGettersAndSetters;
import static io.moj.mobile.android.sdk.test.TestUtils.assertToStringContainsAllFields;

/**
 * Created by skidson on 16-02-23.
 */
public class ValueTests {

    @Test
    public void testAcceleration() throws IllegalAccessException {
        assertToStringContainsAllFields(new Acceleration());
        assertGettersAndSetters(new Acceleration());
    }

    @Test
    public void testAccelerometer() throws IllegalAccessException {
        assertToStringContainsAllFields(new Accelerometer());
        assertGettersAndSetters(new Accelerometer());
    }

    @Test
    public void testAddress() throws IllegalAccessException {
        assertToStringContainsAllFields(new Address());
        assertGettersAndSetters(new Address());
    }

    @Test
    public void testBattery() throws IllegalAccessException {
        assertToStringContainsAllFields(new Battery());
        assertGettersAndSetters(new Battery());
    }

    @Test
    public void testBooleanState() throws IllegalAccessException {
        assertToStringContainsAllFields(new BooleanState());
        assertGettersAndSetters(new BooleanState());
    }

    @Test
    public void testDiagnosticCode() throws IllegalAccessException {
        assertToStringContainsAllFields(new DiagnosticCode());
        assertGettersAndSetters(new DiagnosticCode());
    }

    @Test
    public void testDistance() throws IllegalAccessException {
        assertToStringContainsAllFields(new Distance());
        assertGettersAndSetters(new Distance());
    }

    @Test
    public void testDuration() throws IllegalAccessException {
        assertToStringContainsAllFields(new Duration());
        assertGettersAndSetters(new Duration());
    }

    @Test
    public void testEmail() throws IllegalAccessException {
        assertToStringContainsAllFields(new Email());
        assertGettersAndSetters(new Email());
    }

    @Test
    public void testFuelCapacity() throws IllegalAccessException {
        assertToStringContainsAllFields(new FuelCapacity());
        assertGettersAndSetters(new FuelCapacity());
    }

    @Test
    public void testFuelEfficiency() throws IllegalAccessException {
        assertToStringContainsAllFields(new FuelEfficiency());
        assertGettersAndSetters(new FuelEfficiency());
    }

    @Test
    public void testFuelLevel() throws IllegalAccessException {
        assertToStringContainsAllFields(new FuelLevel());
        assertGettersAndSetters(new FuelLevel());
    }

    @Test
    public void testHeading() throws IllegalAccessException {
        assertToStringContainsAllFields(new Heading());
        assertGettersAndSetters(new Heading());
    }

    @Test
    public void testImage() throws IllegalAccessException {
        assertToStringContainsAllFields(new Image());
        assertGettersAndSetters(new Image());
    }

    @Test
    public void testMeasurementStatistics() throws IllegalAccessException {
        assertToStringContainsAllFields(new MeasurementStatistics());
        assertGettersAndSetters(new MeasurementStatistics());
    }

    @Test
    public void testOdometer() throws IllegalAccessException {
        assertToStringContainsAllFields(new Odometer());
        assertGettersAndSetters(new Odometer());
    }

    @Test
    public void testPermission() throws IllegalAccessException {
        assertToStringContainsAllFields(new Permission());
        assertGettersAndSetters(new Permission());
    }

    @Test
    public void testPhoneNumber() throws IllegalAccessException {
        assertToStringContainsAllFields(new PhoneNumber());
        assertGettersAndSetters(new PhoneNumber());
    }

    @Test
    public void testProperAcceleration() throws IllegalAccessException {
        assertToStringContainsAllFields(new ProperAcceleration());
        assertGettersAndSetters(new ProperAcceleration());
    }

    @Test
    public void testRpm() throws IllegalAccessException {
        assertToStringContainsAllFields(new Rpm());
        assertGettersAndSetters(new Rpm());
    }

    @Test
    public void testScore() throws IllegalAccessException {
        assertToStringContainsAllFields(new Score());
        assertGettersAndSetters(new Score());
    }

    @Test
    public void testSpeed() throws IllegalAccessException {
        assertToStringContainsAllFields(new Speed());
        assertGettersAndSetters(new Speed());
    }

    @Test
    public void testVinDetails() throws IllegalAccessException {
        assertToStringContainsAllFields(new VinDetails());
        assertGettersAndSetters(new VinDetails());
    }

    @Test
    public void testVoltage() throws IllegalAccessException {
        assertToStringContainsAllFields(new Voltage());
        assertGettersAndSetters(new Voltage());
    }

}
