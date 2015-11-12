package io.moj.mobile.android.sdk.oauth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import io.moj.mobile.android.sdk.oauth.R;

/**
 * Activity for guiding the user through the OAuth login flow. This activity should be started via
 * {@link #startActivityForResult(Intent, int, Bundle)}. The result will be passed back as the result
 * intent with the following format:
 * {@link Intent#getData()}: {redirect_uri}#access_token={access_token}&token_type={token_type}&expires_in={expires_in}
 * <br/><br/>
 * For your convenience, each field as also parsed into extras available on the intent:<br/>
 * <code>
 * protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 *      String accessToken = data.getStringExtra(OAuthActivity.EXTRA_ACCESS_TOKEN);
 *      String tokenType = data.getStringExtra(OAuthActivity.EXTRA_TOKEN_TYPE);
 *      long expiresIn = data.getLongExtra(OAuthActivity.EXTRA_EXPIRES_IN, 0);
 *      ... store your access token and use for future requests ...
 * </code>
 * Created by skidson on 15-11-10.
 */
public class OAuthActivity extends FragmentActivity {

    public static final String EXTRA_ENVIRONMENT = "EXTRA_CLIENT_ID";
    public static final String EXTRA_CLIENT_ID = "EXTRA_CLIENT_ID";
    public static final String EXTRA_SCOPE = "EXTRA_SCOPE";
    public static final String EXTRA_REDIRECT_URI = "EXTRA_REDIRECT_URI";

    public static final String EXTRA_ACCESS_TOKEN = "io.moj.mobile.android.sdk.intent.extra.ACCESS_TOKEN";
    public static final String EXTRA_EXPIRES_IN = "io.moj.mobile.android.sdk.intent.extra.EXPIRES_IN";
    public static final String EXTRA_TOKEN_TYPE = "io.moj.mobile.android.sdk.intent.extra.TOKEN_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);

        Environment environment = Environment.fromPrefix(getIntent().getStringExtra(EXTRA_ENVIRONMENT));
        String clientId = getIntent().getStringExtra(EXTRA_CLIENT_ID);
        String scope = getIntent().getStringExtra(EXTRA_SCOPE);
        String redirectUri = getIntent().getStringExtra(EXTRA_REDIRECT_URI);

        OAuthFragment f = OAuthFragment.newInstance(environment, clientId, scope, redirectUri);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_content, f)
                .commit();
    }

    /**
     * Returns an intent that can be used to start this activity.
     * @param context the activity you are calling from
     * @param clientId your app's ID
     * @param scope the scope of permissions you are request for your app
     * @param redirectUri your app's redirect URI. This will be
     * @return an Intent to start with {@link #startActivityForResult(Intent, int, Bundle)}.
     */
    public static Intent newIntent(@NonNull Context context, @NonNull String clientId,
                                   @NonNull String scope, @NonNull String redirectUri) {
        return newIntent(context, Environment.getDefault(), clientId, scope, redirectUri);
    }

    /**
     *
     * @param context the activity you are calling from
     * @param environment the environment your app should talk to
     * @param clientId your app's ID
     * @param scope the scope of permissions you are request for your app
     * @param redirectUri your app's redirect URI. This will be
     * @return an Intent to start with {@link #startActivityForResult(Intent, int, Bundle)}.
     */
    public static Intent newIntent(@NonNull Context context, @NonNull Environment environment,
                                   @NonNull String clientId, @NonNull String scope, @NonNull String redirectUri) {
        Intent intent = new Intent(context, OAuthActivity.class);
        intent.putExtra(EXTRA_ENVIRONMENT, environment.getPrefix());
        intent.putExtra(EXTRA_CLIENT_ID, clientId);
        intent.putExtra(EXTRA_SCOPE, scope);
        intent.putExtra(EXTRA_REDIRECT_URI, redirectUri);
        return intent;
    }
}
