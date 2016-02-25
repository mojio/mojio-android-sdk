package io.moj.mobile.android.sdk.auth;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.google.common.collect.Sets;

import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import java.util.Set;

import io.moj.mobile.android.sdk.Environment;
import io.moj.mobile.android.sdk.RobolectricTest;

import static com.google.common.truth.Truth.assertThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

/**
 * Created by skidson on 16-02-23.
 */
@PrepareForTest(OAuthFragment.class)
public class OAuthActivityTest extends RobolectricTest {

    @Test
    public void testNewIntent() {
        Context context = RuntimeEnvironment.application;
        Environment environment = Environment.STAGING;
        String clientId = "testClientId";
        String scope = "testScope";
        String redirectUri = "testRedirectUri";

        Set<Object> actualExtras = Sets.newHashSet();
        Intent intent = OAuthActivity.newIntent(context, environment, clientId, scope, redirectUri);
        for (String key : intent.getExtras().keySet()) {
            actualExtras.add(intent.getExtras().get(key));
        }
        assertThat(actualExtras).containsAllOf(environment.getPrefix(), clientId, scope, redirectUri);
    }

    @Test
    public void testNewIntent_defaultEnv() {
        Context context = RuntimeEnvironment.application;
        String clientId = "testClientId";
        String scope = "testScope";
        String redirectUri = "testRedirectUri";

        Set<Object> actualExtras = Sets.newHashSet();
        Intent intent = OAuthActivity.newIntent(context, clientId, scope, redirectUri);
        for (String key : intent.getExtras().keySet()) {
            actualExtras.add(intent.getExtras().get(key));
        }
        assertThat(actualExtras).containsAllOf(Environment.getDefault().getPrefix(), clientId, scope, redirectUri);
    }

    @Test
    public void testOnCreate() {
        Context context = RuntimeEnvironment.application;
        Environment environment = Environment.STAGING;
        String clientId = "testClientId";
        String scope = "testScope";
        String redirectUri = "testRedirectUri";
        Intent intent = OAuthActivity.newIntent(context, environment, clientId, scope, redirectUri);

        mockStatic(OAuthFragment.class);

        OAuthActivity activity = Robolectric.buildActivity(OAuthActivity.class).withIntent(intent).create().get();
        verifyStatic();
        OAuthFragment.newInstance(environment, clientId, scope, redirectUri);

        Fragment f = activity.getSupportFragmentManager()
                .findFragmentById(R.id.container_content);
        assertThat(f).isNotNull();
        assertThat(f).isInstanceOf(OAuthFragment.class);
    }

}
