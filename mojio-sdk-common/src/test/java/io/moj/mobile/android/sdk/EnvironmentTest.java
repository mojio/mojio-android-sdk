package io.moj.mobile.android.sdk;

import android.net.Uri;

import com.google.common.base.Strings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Locale;

import io.moj.mobile.android.sdk.test.RobolectricTest;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by skidson on 16-02-18.
 */
public class EnvironmentTest extends RobolectricTest {

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

    /**
     * Validates that environments with a prefix have a dash and those that don't do not.
     */
    @Test
    public void testPrefixes() {
        for (Environment environment : Environment.values()) {
            String prefix = environment.getPrefix();

            Uri accountsUri = Uri.parse(environment.getAccountsUrl());
            Uri myMojioUri = Uri.parse(environment.getMyMojioUrl());
            Uri passwordRecoveryUri = Uri.parse(environment.getPasswordRecoveryUrl());
            Uri pushUri = Uri.parse(environment.getPushUrl());
            Uri apiUri = Uri.parse(environment.getApiUrl());
            Uri[] uris = new Uri[] {
                    accountsUri, myMojioUri, passwordRecoveryUri, pushUri, apiUri
            };

            for (Uri uri : uris) {
                assertThat(uri.getAuthority()).startsWith(prefix);

                // assert the authority doesn't start with a dash
                assertWithMessage("Uri '" + uri + " should not start with a dash")
                        .that(uri.getAuthority()).doesNotMatch("^-.*");
            }
        }
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
