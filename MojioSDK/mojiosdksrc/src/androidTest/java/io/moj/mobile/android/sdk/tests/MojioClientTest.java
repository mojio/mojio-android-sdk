package io.moj.mobile.android.sdk.tests;

import android.content.Context;
import android.test.AndroidTestCase;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import io.moj.mobile.android.sdk.MojioClient;
import io.moj.mobile.android.sdk.models.User;
import io.moj.mobile.android.sdk.models.Vehicle;

/**
 * Created by ssawchenko on 15-05-15.
 */
public class MojioClientTest extends AndroidTestCase {

    private final String mValidUserName = "shayla.sawchenko@gmail.com";
    private final String mValidUserPass = "Test1234";

    Context mCtx;
    MojioClient mMojioClient;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mCtx = getContext();

        mMojioClient = new MojioClient(mCtx,TestHelper.MOJIO_APP_ID, TestHelper.MOJIO_SECRET_KEY, TestHelper.REDIRECT_URL);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        mMojioClient.logout();
        mMojioClient = null;
    }

    //==================================================================
    // Helpers
    //==================================================================
    private class LoginResult {
        public boolean success;
        public User user;
        public MojioClient.ResponseError error;
    }

    private LoginResult loginUser(String email, String pass) {
        final CountDownLatch forceSync = new CountDownLatch(1);
        final LoginResult loginResult = new LoginResult();

        mMojioClient.login(email, pass, new MojioClient.ResponseListener<User>() {
            @Override
            public void onSuccess(User result) {
                loginResult.success = true;
                loginResult.user = result;
                forceSync.countDown();
            }

            @Override
            public void onFailure(MojioClient.ResponseError error) {
                loginResult.success = false;
                loginResult.error = error;
                forceSync.countDown();
            }
        });

        try {
            forceSync.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return loginResult;
    }

    private User loginValidUser() {
        LoginResult lr = loginUser(mValidUserName, mValidUserPass);

        if (lr.success) {
            return lr.user;
        }

        assertTrue("Login failed, test cannot continue", false);
        return null;
    }

    //==================================================================
    // Login Tests
    //==================================================================

    /**
     * Tests login, isUserLoggedIn and logout
     */
    public void testValidLogin() {
        // Login the user
        LoginResult loginResult = loginUser(mValidUserName, mValidUserPass);
        assertTrue(loginResult.success);
        assertNotNull(loginResult.user);
        assertEquals(loginResult.user.Email, mValidUserName);

        // Test that isUserLoggedIn is working correctly
        assertEquals("isUserLoggedIn reporting incorrect value", loginResult.success, mMojioClient.isUserLoggedIn());

        // Test logout
        mMojioClient.logout();

        // Test that isUserLoggedIn is working correctly
        assertFalse("isUserLoggedIn reporting incorrect value", mMojioClient.isUserLoggedIn());
    }

    /**
     * Tests login, isUserLoggedIn and logout
     */
    public void testInvalidPasswordLogin() {
        // Login the user
        LoginResult loginResult = loginUser(mValidUserName, "wrongpassword");
        assertFalse(loginResult.success);
        assertNotNull(loginResult.error);

        // Note: comes back with "No authentication challenges found" error message; perhaps not the most descriptive.

        // Test that isUserLoggedIn is working correctly
        assertEquals("isUserLoggedIn reporting incorrect value", loginResult.success, mMojioClient.isUserLoggedIn());
    }

    public void testInvalidUserLogin() {
        // Login the user
        LoginResult loginResult = loginUser("idontexist", mValidUserPass);
        assertFalse(loginResult.success);
        assertNotNull(loginResult.error);

        // Test that isUserLoggedIn is working correctly
        assertEquals("isUserLoggedIn reporting incorrect value", loginResult.success, mMojioClient.isUserLoggedIn());
    }


    public void testInvalidUserAndPasswordLogin() {
        // Login the user
        LoginResult loginResult = loginUser("idontexist", "idontexist");
        assertFalse(loginResult.success);
        assertNotNull(loginResult.error);

        // Test that isUserLoggedIn is working correctly
        assertEquals("isUserLoggedIn reporting incorrect value", loginResult.success, mMojioClient.isUserLoggedIn());
    }

    //==================================================================
    // GET Tests
    //==================================================================
    private Vehicle mVehicle;
    public void testGet() {
        // Login
        User user = loginValidUser();
        if (user == null) { return; }
        mVehicle = null;

        //---------------------------------
        // First test get Array
        //---------------------------------
        String entityPath = String.format("Users/%s/Vehicles", user._id);
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("sortBy", "Name");
        queryParams.put("desc", "true");

        final CountDownLatch forceSyncArray = new CountDownLatch(1);
        mMojioClient.get(Vehicle[].class, entityPath, queryParams, new MojioClient.ResponseListener<Vehicle[]>() {
            @Override
            public void onSuccess(Vehicle[] result) {
                assertTrue(result.length > 0);
                if (result.length > 0) {
                    Vehicle v = result[0];
                    assertEquals(v.Type, "Vehicle");
                    mVehicle = v;
                }
                forceSyncArray.countDown();
            }

            @Override
            public void onFailure(MojioClient.ResponseError error) {
                assertTrue("getVehicles should have returned a result", false);
                forceSyncArray.countDown();
            }
        });

        try {
            forceSyncArray.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //---------------------------------
        // Now test single get (non array) using mVehicleID
        //---------------------------------
        entityPath = String.format("Vehicles/%s/", mVehicle._id);
        queryParams = new HashMap<>();

        final CountDownLatch forceSyncSingle = new CountDownLatch(1);
        mMojioClient.get(Vehicle.class, entityPath, queryParams, new MojioClient.ResponseListener<Vehicle>() {
            @Override
            public void onSuccess(Vehicle result) {
                // Pick a few values to test to see if these are the same car.
                assertEquals(result._id, mVehicle._id);
                assertEquals(result.VehicleName, mVehicle.VehicleName);
                assertEquals(result.LastContactTime, mVehicle.LastContactTime);
                forceSyncSingle.countDown();
            }

            @Override
            public void onFailure(MojioClient.ResponseError error) {
                assertTrue("getVehicles should have returned a result", false);
                forceSyncSingle.countDown();
            }
        });

        try {
            forceSyncSingle.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
