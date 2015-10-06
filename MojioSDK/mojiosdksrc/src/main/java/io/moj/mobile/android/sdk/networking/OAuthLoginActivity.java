package io.moj.mobile.android.sdk.networking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.moj.mobile.android.sdk.DataStorageHelper;
import io.moj.mobile.android.sdk.R;

public class OAuthLoginActivity extends Activity {

    private static String TAG = "MOJIO";

    private WebView _loginWebView;
    private DataStorageHelper _oauthHelper;
    private String _urlPath, _redirectUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_login);

        Bundle extras = getIntent().getExtras();
        final String authUrl = extras.getString("URL_AUTH_PATH");
        _redirectUrl = extras.getString("REDIRECT_URL");
        _urlPath = authUrl + "&redirect_uri=" + _redirectUrl; // Add redirectUrl
        _oauthHelper = new DataStorageHelper(this);

        _loginWebView = (WebView) findViewById(R.id.loginwebview);
        _loginWebView.getSettings().setJavaScriptEnabled(true);
        Log.e(TAG, "Auth url: " + _urlPath);
        _loginWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e(TAG, "Redirecting to url: " + url);
                if (url.startsWith(_redirectUrl)) {

                    // Note, the url returned cannot be parsed correctly via Uri parse.
                    // Need to manually pull out access_token, expires_in
                    String [] parameters = url.split("&");
                    String [] accessToken = parameters[0].split("=");
                    String [] expiresIn = parameters[2].split("=");

                    _oauthHelper.setAccessToken(accessToken[1], expiresIn[1], authUrl);

                    // Return in bundle, but also stored in shared prefs
                    Bundle bundle = new Bundle();
                    bundle.putString("accessToken", _oauthHelper.getAccessToken());

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
        _loginWebView.loadUrl(_urlPath);
    }
}
