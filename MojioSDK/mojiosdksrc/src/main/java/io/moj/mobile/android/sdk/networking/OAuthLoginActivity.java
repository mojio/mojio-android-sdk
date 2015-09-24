package io.moj.mobile.android.sdk.networking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.moj.mobile.android.sdk.DataStorageHelper;
import io.moj.mobile.android.sdk.R;

public class OAuthLoginActivity extends Activity {

    private static String TAG = OAuthLoginActivity.class.getSimpleName();
    private static final String EXTRA_URL_AUTH = "EXTRA_URL_AUTH";
    private static final String EXTRA_URL_REDIRECT = "EXTRA_URL_REDIRECT";

    private DataStorageHelper oAuthHelper;
    private String redirectUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_login);

        Bundle extras = getIntent().getExtras();
        String authUrl = extras.getString("URL_AUTH_PATH");
        redirectUrl = extras.getString("REDIRECT_URL");
        authUrl += "&redirect_uri=" + redirectUrl; // Add redirectUrl
        oAuthHelper = new DataStorageHelper(this);

        WebView webView = (WebView) findViewById(R.id.loginwebview);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Redirecting to url: " + url);
                if (url.startsWith(redirectUrl)) {

                    // Note, the url returned cannot be parsed correctly via Uri parse.
                    // Need to manually pull out access_token, expires_in
                    String[] parameters = url.split("&");
                    String[] accessToken = parameters[0].split("=");
                    String[] expiresIn = parameters[2].split("=");
                    oAuthHelper.setAccessToken(accessToken[1], expiresIn[1]);

                    // Return in bundle, but also stored in shared prefs
                    Bundle bundle = new Bundle();
                    bundle.putString("accessToken", oAuthHelper.getAccessToken());

                    Intent resultIntent = new Intent();
                    resultIntent.putExtras(bundle);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    view.loadUrl(url);
                }
                return true;
            }
        });
        webView.loadUrl(authUrl);
    }

    public static Intent newIntent(Context context, String authUrl, String redirectUrl) {
        Intent intent = new Intent(context, OAuthLoginActivity.class);
        intent.putExtra(EXTRA_URL_AUTH, authUrl);
        intent.putExtra(EXTRA_URL_REDIRECT, redirectUrl);
        return intent;
    }
}
