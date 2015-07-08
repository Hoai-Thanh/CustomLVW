package com.custom.customlvw.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.custom.customlvw.R;

public class SplashScreenActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lvw_splash_screen_activity);
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				changeToWelcomeScreen();
			}
		}, 2000);
	}
	
	private void changeToWelcomeScreen() {
		Intent intent = new Intent(this, ListProductActivity.class);
		startActivity(intent);
	}
}
