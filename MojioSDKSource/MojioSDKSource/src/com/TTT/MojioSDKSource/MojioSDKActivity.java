package com.TTT.MojioSDKSource;

import java.util.Calendar;

import com.TTT.MojioSDKSource.Interfaces.AsyncResponse;
import com.TTT.MojioSDKSource.OauthHelper.OauthHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MojioSDKActivity extends Activity{
	
	
	
	private WebView loginWebView;
	private OauthHelper oauthHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mojio_sdksource);

		oauthHelper = new OauthHelper(this);

		loginWebView = (WebView) findViewById(R.id.loginwebview);
		loginWebView.loadUrl("https://api.moj.io/OAuth2/authorize?response_type=token&client_id=3d431a5d-472f-4a10-b0dd-29d9f7f7c6dc&redirect_uri=mojioios://");
		loginWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) { 
                if(url.startsWith("mojioios://")){
                	Log.e("testing", "the whole url is: " + url);
                	oauthHelper.SetAccessToken(url);
                	oauthHelper.SetExpireTime(url);
                	
                	Bundle bundle = new Bundle();
                	bundle.putString("accessToken", oauthHelper.GetAccessToken());
                	
                    Intent resultIntent = new Intent();
                    resultIntent.putExtras(bundle);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                    
                    
                }
                else{
                   view.loadUrl(url); 
                }                 
                return true;
            }
        });


	}

	
	

}
