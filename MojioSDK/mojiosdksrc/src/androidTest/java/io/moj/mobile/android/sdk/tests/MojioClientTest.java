package io.moj.mobile.android.sdk.tests;

import android.content.Context;
import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;

import io.moj.mobile.android.sdk.MojioClient;
import io.moj.mobile.android.sdk.models.User;

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

        // If still set, logout and then recreate
        if (mMojioClient != null) {
            mMojioClient.logout();
            mMojioClient = null;
        }

        mMojioClient = new MojioClient(mCtx,TestHelper.MOJIO_APP_ID, TestHelper.MOJIO_SECRET_KEY, TestHelper.REDIRECT_URL);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
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

    //==================================================================
    // Tests
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
}
