package io.moj.mobile.android.sdk.oauth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Locale;

import io.moj.mobile.android.sdk.oauth.R;

/**
 * Fragment for guiding the user through the OAuth login flow. This can be used by client apps
 * who wish to embed the login flow inside their own layout. If clients choose to use
 * {@link OAuthFragment} directly, they must register an activity to intercept an Intent for the
 * specified {@code redirectUri}:<br/><br/>
 *
 * {@link Intent#getData()}: {redirect_uri}#access_token={access_token}&token_type={token_type}&expires_in={expires_in}
 * <br/><br/>
 * For your convenience, each field as also parsed into extras available on the intent:<br/>
 * <code>
 * protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 *      String accessToken = data.getStringExtra(OAuthActivity.EXTRA_ACCESS_TOKEN);
 *      String tokenType = data.getStringExtra(OAuthActivity.EXTRA_TOKEN_TYPE);
 *      long expiresIn = data.getLongExtra(OAuthActivity.EXTRA_EXPIRES_IN, 0);
 *      ... store your access token and use for future requests ...
 * }
 * </code>
 *
 * Created by skidson on 15-11-09.
 */
public class OAuthFragment extends Fragment {

    private static final String TAG = OAuthFragment.class.getSimpleName();
    private static final String ARG_AUTH_URL = "ARG_AUTH_URL";
    private static final String ARG_CLIENT_ID = "ARG_CLIENT_ID";
    private static final String ARG_SCOPE = "ARG_SCOPE";
    private static final String ARG_REDIRECT_URI = "ARG_REDIRECT_URI";
    private static final String FORMAT_OAUTH_URL = "%1$s/oauth2/authorize?response_type=token&redirect_uri=%2$s&client_id=%3$s&scope=%4$s";

    private String authUrl;
    private String clientId;
    private String scope;
    private Uri redirectUri;

    /**
     * Instantiates a new {@link OAuthFragment} instance.
     * @param clientId your app's identification token
     * @param redirectUri the URI to be invoked with the authentication result
     * @param scope the scope of permissions your app is requesting
     * @return
     */
    public static OAuthFragment newInstance(String clientId, String scope, String redirectUri) {
        return newInstance(Environment.getDefault(), clientId, scope, redirectUri);
    }

    /**
     * Instantiates a new {@link OAuthFragment} instance.
     * @param environment the environment to authenticate against
     * @param clientId your app's identification token
     * @param redirectUri the URI to be invoked with the authentication result
     * @param scope the scope of permissions your app is requesting
     * @return
     */
    public static OAuthFragment newInstance(Environment environment, String clientId, String scope, String redirectUri) {
        Bundle args = new Bundle();
        args.putString(ARG_AUTH_URL, environment.getAccountsUrl());
        args.putString(ARG_CLIENT_ID, clientId);
        args.putString(ARG_SCOPE, scope);
        args.putString(ARG_REDIRECT_URI, redirectUri);

        OAuthFragment f = new OAuthFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        authUrl = args.getString(ARG_AUTH_URL);
        redirectUri = Uri.parse(args.getString(ARG_REDIRECT_URI));
        clientId = args.getString(ARG_CLIENT_ID);
        scope = args.getString(ARG_SCOPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_oauth, container, false);
        WebView webview = (WebView) root.findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // OAuth2 RFC specifies the token to use the fragment (#) instead of ? for parameters
                // but this tricks Uri.parse into thinking everything is a fragment
                Uri uri = Uri.parse(url.replaceFirst("#", "?"));
                if (!isRedirectUri(uri))
                    return false;

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.putExtra(OAuthActivity.EXTRA_ACCESS_TOKEN, uri.getQueryParameter("access_token"));
                intent.putExtra(OAuthActivity.EXTRA_EXPIRES_IN, Long.parseLong(uri.getQueryParameter("expires_in")));
                intent.putExtra(OAuthActivity.EXTRA_TOKEN_TYPE, uri.getQueryParameter("token_type"));

                if (getActivity().getCallingActivity() != null) {
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                } else {
                    startActivity(intent);
                }
                return true;
            }
        });

        String oauthUrl = String.format(Locale.US, FORMAT_OAUTH_URL,
                authUrl, redirectUri, clientId, scope);
        webview.loadUrl(oauthUrl);

        return root;
    }

    private boolean isRedirectUri(Uri uri) {
        return uri.buildUpon().fragment("").clearQuery().build().equals(redirectUri);
    }
}
