package io.moj.mobile.android.sdk.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.common.collect.Sets;

import org.junit.Test;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.util.Set;

import io.moj.mobile.android.sdk.Environment;
import io.moj.mobile.android.sdk.RobolectricTest;

import static com.google.common.truth.Truth.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by skidson on 16-02-25.
 */
public class OAuthFragmentTest extends RobolectricTest {

    @Test
    public void testNewInstance() {
        Environment environment = Environment.STAGING;
        String clientId = "testClientId";
        String scope = "testScope";
        String redirectUri = "testRedirectUri";

        OAuthFragment f = OAuthFragment.newInstance(environment, clientId, scope, redirectUri);
        assertThat(f).isNotNull();

        Bundle args = f.getArguments();
        assertThat(args).isNotNull();

        Set<Object> actualExtras = Sets.newHashSet();
        for (String key : f.getArguments().keySet()) {
            actualExtras.add(f.getArguments().get(key));
        }
        assertThat(actualExtras).containsAllOf(environment.getAccountsUrl(), clientId, scope, redirectUri);
    }

    @Test
    public void testNewInstance_defaultEnv() {
        String clientId = "testClientId";
        String scope = "testScope";
        String redirectUri = "testRedirectUri";

        OAuthFragment f = OAuthFragment.newInstance(clientId, scope, redirectUri);
        assertThat(f).isNotNull();

        Bundle args = f.getArguments();
        assertThat(args).isNotNull();

        Set<Object> actualExtras = Sets.newHashSet();
        for (String key : f.getArguments().keySet()) {
            actualExtras.add(f.getArguments().get(key));
        }
        assertThat(actualExtras).containsAllOf(Environment.getDefault().getAccountsUrl(), clientId, scope, redirectUri);
    }

    @Test
    public void testLoad() {
        Environment environment = Environment.getDefault();
        String clientId = "testClientId";
        String scope = "testScope";
        String redirectUri = "test://oauth.redirect";
        OAuthFragment f = OAuthFragment.newInstance(environment, clientId, scope, redirectUri);

        SupportFragmentTestUtil.startVisibleFragment(f);
        View rootView = f.getView();
        assertThat(rootView).isNotNull();

        WebView webView = (WebView) rootView.findViewById(R.id.webview);
        assertThat(webView).isNotNull();

        String url = shadowOf(webView).getLastLoadedUrl();
        assertThat(url).isNotNull();
        assertThat(url).startsWith(environment.getAccountsUrl());
        assertThat(url).contains(redirectUri);
        assertThat(url).contains(clientId);
        assertThat(url).contains(scope);
    }

    @Test
    public void testRedirect() {
        Environment environment = Environment.getDefault();
        String clientId = "testClientId";
        String scope = "testScope";
        String redirectUri = "test://oauth.redirect";
        OAuthFragment f = OAuthFragment.newInstance(environment, clientId, scope, redirectUri);

        SupportFragmentTestUtil.startVisibleFragment(f);
        View rootView = f.getView();
        assertThat(rootView).isNotNull();
        FragmentActivity parentActivity = f.getActivity();
        assertThat(parentActivity).isNotNull();
        WebView webView = (WebView) rootView.findViewById(R.id.webview);

        // load the success response URL and ensure we got the right token out of it
        String expectedAccessToken = "testAccessToken";
        long expectedExpiresIn = 1337L;
        String expectedTokenType = "testTokenType";
        Uri authResponseUri = Uri.parse(redirectUri).buildUpon()
                .appendQueryParameter("access_token", expectedAccessToken)
                .appendQueryParameter("expires_in", String.valueOf(expectedExpiresIn))
                .appendQueryParameter("token_type", expectedTokenType)
                .build();
        // the OAuth RFC uses an anchor (#) instead of query params
        WebViewClient webViewClient = shadowOf(webView).getWebViewClient();

        // ensure the WebView doesn't redirect from a non-redirect request
        assertThat(webViewClient.shouldOverrideUrlLoading(webView, "www.google.com")).isFalse();

        // we have to test this explicitly because apparently loadUrl bypasses
        // shouldOverrideUrlLoading() (http://stackoverflow.com/a/6739042/2136792)
        String successUri = authResponseUri.toString().replaceFirst("\\?", "#");
        assertThat(webViewClient.shouldOverrideUrlLoading(webView, successUri)).isTrue();

        Intent intent = shadowOf(parentActivity).getNextStartedActivity();
        Bundle extras = intent.getExtras();
        assertThat(extras.getString(OAuthActivity.EXTRA_ACCESS_TOKEN)).isEqualTo(expectedAccessToken);
        assertThat(extras.getLong(OAuthActivity.EXTRA_EXPIRES_IN)).isEqualTo(expectedExpiresIn);
        assertThat(extras.getString(OAuthActivity.EXTRA_TOKEN_TYPE)).isEqualTo(expectedTokenType);
    }

}
