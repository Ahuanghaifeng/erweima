package com.app.activity;

import com.example.erweima.R;
import com.example.mtreader.IdentifyActivity;
import com.example.mtreader.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

public class BaseActivity extends Activity {
	
	private int SHOW_ANOTHER_ACTIVITY = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		resetTime();
	}

	public void initBack() {
		findViewById(R.id.back).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
	
//	@Override  
//    public boolean dispatchTouchEvent(MotionEvent ev) {  
//        resetTime();  
//        return super.dispatchTouchEvent(ev);  
//    }  
//      
//    private void resetTime() {  
//        // TODO Auto-generated method stub  
//        mHandler.removeMessages(SHOW_ANOTHER_ACTIVITY);//從消息隊列中移除  
//        Message msg = mHandler.obtainMessage(SHOW_ANOTHER_ACTIVITY);  
//        mHandler.sendMessageDelayed(msg, 1000*60*2);//無操作2分钟后進入屏保  
//    }  
//      
//    private Handler mHandler = new Handler(){  
//        @Override  
//        public void handleMessage(Message msg) {  
//            // TODO Auto-generated method stub  
//            super.handleMessage(msg);  
//            if(msg.what==SHOW_ANOTHER_ACTIVITY)  
//            {  
//                 Intent intent=new Intent(BaseActivity.this,MainActivity.class);  
//                 startActivity(intent);  
//            }  
//        }  
//    };  
//	
//	
}
