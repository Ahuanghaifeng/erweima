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
		// ��������ͷ�򿪷�ʽ��1-��������ȱʡ���� 2-����
		vguangApi.setCameraMode(2);
		// ����ɨ��ص��ӿ�
		decodeCallBack = new DecodeCallBack();
		VguangApi.setDecodeCallBack(decodeCallBack);
		// �����豸״̬�仯�ص��ӿ�
		// DeviceStatusCallBack deviceStatusCallBack = new
		// DeviceStatusCallBack(this, tvState);
		// VguangApi.setDeviceStatusCallBack(deviceStatusCallBack);
		// ����QR״̬��trueʱqr���濪����falseʱqr����ر�
		vguangApi.setQRable(true);
		// ����DM����״̬��trueʱdm���濪����falseʱdm����ر�
		vguangApi.setDMable(false);
		// ����һά������״̬��trueʱһά�����濪����falseʱһά������ر�
		vguangApi.setBarcode(false);

		// ���ý�����ʱ�䣬��λ����
		vguangApi.setDeodeIntervalTime(300);

		// �����Զ�����״̬��trueʱ�Զ����߿�����falseʱ�Զ����߹ر�
		vguangApi.setAI(false);

		// ����������״̬��trueʱ������(ȱʡ����)������falseʱ������(ȱʡ����)�ر�
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
			ToastUtil.showToast(this, "��ά��ʶ��ɹ�,�����֤��,���Ժ�!");
			CheckUser(idcard);
		}else{
			ToastUtil.showToast(this, "��ά����Ч�������»�ȡ��ά��");
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
							ToastUtil.showToast(ErWeiMaActivity.this,"�밴�ձ�ҳ�����˵������΢�Ź��ں����������ĸ�����Ϣ!");
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

		// ���豸
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
