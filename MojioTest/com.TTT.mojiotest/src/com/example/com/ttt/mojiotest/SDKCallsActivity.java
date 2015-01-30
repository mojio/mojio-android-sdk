package com.example.com.ttt.mojiotest;

import com.TTT.MojioSDKSource.MojioSDKSource;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SDKCallsActivity extends Activity{
	
	TextView getTextView;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdkcalls_layout);
        
        getTextView = (TextView)findViewById(R.id.get_textview);
        getTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MojioSDKSource.GetElement("https://api.moj.io/v1/Vehicles/53cdeca5-b268-4a25-bfde-3938b5cf7d47", SDKCallsActivity.this);
				
			}
		});
        
    }

}
