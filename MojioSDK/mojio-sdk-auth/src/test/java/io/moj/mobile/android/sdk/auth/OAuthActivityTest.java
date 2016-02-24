package io.moj.mobile.android.sdk.auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by skidson on 16-02-23.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*", "org.jacoco.*" })
@PrepareForTest(OAuthFragment.class)
public class OAuthActivityTest {

    private OAuthActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(OAuthActivity.class).create().get();
    }

    @Test
    public void test() {

    }

}
