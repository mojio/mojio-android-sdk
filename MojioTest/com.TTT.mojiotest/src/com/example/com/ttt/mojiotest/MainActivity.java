package com.example.com.ttt.mojiotest;


import com.TTT.MojioSDKSource.MojioSDKActivity;
import com.TTT.MojioSDKSource.MojioSDKSource;
import com.TTT.MojioSDKSource.Interfaces.AsyncResponse;
import com.TTT.MojioSDKSource.OauthHelper.OauthHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends Activity implements AsyncResponse{

	private int REQUEST_CODE= 0x1;
	WebView webview;
	Button loginbutton;
	TextView accesstokentextview;
	
	private TextView _ResponseShowTextView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loginbutton = (Button) findViewById(R.id.login_button);
        loginbutton.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				MojioSDKSource.RequestAccessToken(MainActivity.this);

			}
		});
        
        TextView t = (TextView) findViewById(R.id.access_token_text);
        t.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				MojioSDKSource.GetEntityWithPath("", MainActivity.this);
//				MojioSDKSource.getVehicle(MainActivity.this);
				MojioSDKSource.GetElement("https://api.moj.io/v1/Vehicles/53cdeca5-b268-4a25-bfde-3938b5cf7d47");
				
			}
		});
      

        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Pass back to authenticator
   
    }

	@Override
	public void processFinish(String output) {
		Log.e("TESTING", "THIS IS THE OUTPUT: " + output);
		
	}
    


}
