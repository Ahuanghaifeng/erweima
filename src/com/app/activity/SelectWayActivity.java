package com.app.activity;

import com.app.MyApp;
import com.example.erweima.ErWeiMaActivity;
import com.example.erweima.R;
import com.example.mtreader.IdentifyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SelectWayActivity extends BaseActivity {

	private Button erweima, sfCard;
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_way);
		initView();
		initBack();
	}

	public void initView() {
		erweima = (Button) findViewById(R.id.erweima);
		sfCard = (Button) findViewById(R.id.sfcard);
		erweima.setOnClickListener(onClickListener);
		sfCard.setOnClickListener(onClickListener);
		mIntent = new Intent();
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.erweima:
				MyApp.isCard = false;
				mIntent.setClass(SelectWayActivity.this, ErWeiMaActivity.class);
				startActivity(mIntent);
				break;
			case R.id.sfcard:
				MyApp.isCard = true;
				mIntent.setClass(SelectWayActivity.this, IdentifyActivity.class);
				startActivity(mIntent);
				break;
			}
		}
	};
}
