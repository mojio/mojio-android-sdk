package io.moj.mobile.android.sdk;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Base class for Robolectric tests to extend for a consistent config across all
 * Created by skidson on 16-02-23.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(RobolectricGradleTestRunner.class)
@Config(constants = io.moj.mobile.android.sdk.auth.BuildConfig.class, sdk = 21)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*", "org.jacoco.*" })
public abstract class RobolectricTest {
}
