package com.app.activity;

import com.app.MyApp;
import com.example.erweima.R;
import com.example.mtreader.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BDSuccessActivity extends Activity {

	private TextView back;
	private Handler mhandler;
	private TextView hintOne,hintTwo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bd_success);
		back = (TextView) findViewById(R.id.back);
		hintOne = (TextView) findViewById(R.id.hintone);
		hintTwo = (TextView) findViewById(R.id.hinttwo);
		if(!MyApp.isCard){
			hintOne.setVisibility(View.GONE);
			hintTwo.setVisibility(View.GONE);
		}
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startAcitity();
			}
		});
		mhandler = new Handler();
		mhandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				startAcitity();
			}
		}, 30000);
	}

	public void startAcitity() {
		Intent intent = new Intent(BDSuccessActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mhandler!=null){
			mhandler.removeCallbacksAndMessages(null);			
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}

}
