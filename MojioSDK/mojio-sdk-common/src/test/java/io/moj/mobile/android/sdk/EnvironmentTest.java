package io.moj.mobile.android.sdk;

import org.junit.Test;

import java.util.Locale;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by skidson on 16-02-18.
 */
public class EnvironmentTest {

    @Test
    public void testUrlLocales() {
        Environment e = Environment.getDefault();
        Locale.setDefault(Locale.US);
        String apiUrl = e.getApiUrl();
        String pushUrl = e.getPushUrl();
        String accountsUrl = e.getAccountsUrl();
        String passwordRecoveryUrl = e.getPasswordRecoveryUrl();
        String myMojioUrl = e.getMyMojioUrl();

        // Turkey's Locale is known for affecting capitalization, ensure we aren't using the default
        Locale.setDefault(new Locale("tr-TR"));
        assertEquals(apiUrl, e.getApiUrl());
        assertEquals(pushUrl, e.getPushUrl());
        assertEquals(accountsUrl, e.getAccountsUrl());
        assertEquals(passwordRecoveryUrl, e.getPasswordRecoveryUrl());
        assertEquals(myMojioUrl, e.getMyMojioUrl());
    }

    @Test
    public void testGetDefault() {
        // sanity check to make sure we don't ever accidentally release pointing anywhere but prod
        assertEquals(Environment.PROD, Environment.getDefault());
    }

    @Test
    public void testTypeFromKey() {
        for (Environment environment : Environment.values()) {
            Environment environmentFromPrefix = Environment.fromPrefix(environment.getPrefix());
            assertEquals(environment, environmentFromPrefix);
        }
        assertNull(Environment.fromPrefix("NotARealPrefix"));
    }

}
