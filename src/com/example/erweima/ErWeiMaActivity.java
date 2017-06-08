package com.example.erweima;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.android.volley.VolleyError;
import com.app.MyApp;
import com.app.activity.BaseActivity;
import com.app.activity.SelectClassesActivity;
import com.app.utils.ToastUtil;
import com.example.erweima.DecodeCallBack.onErWeiMaListener;
import com.example.erweima.R;
import com.example.mtreader.IdentifyActivity;
import com.example.request.api.json.ResponseListener;
import com.example.request.comfort.manager.RequestManager;
import com.example.request.comfort.response.CheckUserResponse;
import com.vguang.VguangApi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class ErWeiMaActivity extends BaseActivity {

	private VguangApi vguangApi;
	private String result;
	private DecodeCallBack decodeCallBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weixing_help);
		initVguang();
		initBack();
	}

	public void initVguang() {
		vguangApi = VguangApi.getInstance();
		// 设置摄像头打开方式，1-非阻塞（缺省）， 2-阻塞
		vguangApi.setCameraMode(2);
		// 设置扫码回调接口
		decodeCallBack = new DecodeCallBack();
		VguangApi.setDecodeCallBack(decodeCallBack);
		// 设置设备状态变化回调接口
		// DeviceStatusCallBack deviceStatusCallBack = new
		// DeviceStatusCallBack(this, tvState);
		// VguangApi.setDeviceStatusCallBack(deviceStatusCallBack);
		// 设置QR状态，true时qr引擎开启；false时qr引擎关闭
		vguangApi.setQRable(true);
		// 设置DM引擎状态，true时dm引擎开启；false时dm引擎关闭
		vguangApi.setDMable(false);
		// 设置一维码引擎状态，true时一维码引擎开启；false时一维码引擎关闭
		vguangApi.setBarcode(false);

		// 设置解码间隔时间，单位毫秒
		vguangApi.setDeodeIntervalTime(300);

		// 设置自动休眠状态，true时自动休眠开启；false时自动休眠关闭
		vguangApi.setAI(false);

		// 设置扬声器状态，true时扬声器(缺省声音)开启；false时扬声器(缺省声音)关闭
		vguangApi.setBeepable(true);

		decodeCallBack.setOnErWeiMaListener(listener);
		vguangApi.openDevice();
	}
	
	private onErWeiMaListener listener = new onErWeiMaListener() {
		
		@Override
		public void erWeiMaListener(final String str) {
			vguangApi.closeDevice();
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					result = str;
					MyApp.idCard = result;
					isCheckSf(result);
				}
			});
		}
	};
	
	public void isCheckSf(String idcard){
		Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		Matcher idNumMatcher = idNumPattern.matcher(idcard);
		if(idNumMatcher.matches()){
			ToastUtil.showToast(this, "二维码识别成功,身份认证中,请稍后!");
			CheckUser(idcard);
		}else{
			ToastUtil.showToast(this, "二维码无效，请重新获取二维码");
		}
	}
	
	public void CheckUser(final String str){
		RequestManager.CheckUser(str, new ResponseListener<CheckUserResponse>() {
			
			@Override
			public void onResponse(CheckUserResponse result) {
				if(!isFinishing()){
					if(result!=null&&result.code!=null){
						if("200".equals(result.code)){
							Intent intent = new Intent(ErWeiMaActivity.this,
									SelectClassesActivity.class);
							intent.putExtra("sfz", str);
							startActivity(intent);
						}else if("100".equals(result.code)){
							ToastUtil.showToast(ErWeiMaActivity.this,"请按照本页面操作说明，在微信公众号上完善您的个人信息!");
						}
					}
				}
			}
			
			@Override
			public void onError(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (vguangApi != null) {
			vguangApi.closeDevice();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// 打开设备
		if (vguangApi != null)
			vguangApi.openDevice();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (vguangApi != null) {
			vguangApi.closeDevice();
		}
		vguangApi = null;
		decodeCallBack = null;
		VguangApi.setDecodeCallBack(null);
	}
}
