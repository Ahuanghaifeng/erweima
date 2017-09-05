package com.example.mtreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.app.MyApp;
import com.app.activity.SelectClassesActivity;
import com.app.utils.ToastUtil;
import com.example.erweima.R;
import com.example.mtreader.MainActivity;
import com.example.request.api.json.ResponseListener;
import com.example.request.comfort.manager.RequestManager;
import com.example.request.comfort.response.CheckUserResponse;
import com.example.request.comfort.response.DianZanResponse;
import com.synjones.bluetooth.DecodeWlt;

public class IdentifyActivity extends Activity {

	int result;
	private TextView back;
	private Button start;
	private int SHOW_ANOTHER_ACTIVITY = 100;
	
	private String name,path;

	public native int Wlt2Bmp(String wltPath, String bmpPath);
	static {
		System.loadLibrary("DecodeWlt");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// //文本输入框默认不获得焦点
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layidentify);
		back = (TextView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		start = (Button) findViewById(R.id.btn_IdentifyRead);
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				int st = 0;
				int nRecLen[] = new int[8];
				byte szName[] = new byte[128];
				byte szSex[] = new byte[128];
				byte szNation[] = new byte[128];
				byte szBirth[] = new byte[128];
				byte szAddress[] = new byte[128];
				byte szIDNo[] = new byte[36];
				byte szDepartment[] = new byte[128];
				byte szDateStart[] = new byte[128];
				byte szDateEnd[] = new byte[128];
				byte szdata[] = new byte[3072];

				st = MainActivity.mt8IDCardRead(szName, szSex, szNation, szBirth,
						szAddress, szIDNo, szDepartment, szDateStart, szDateEnd,
						nRecLen, szdata);

				if (st != 0) {
					ToastUtil.showToast(IdentifyActivity.this, "身份证读卡失败");
					MyApp.idCard = null;
				} else {
					try {
						String StrIDNo = new String(szIDNo, "UTF-16LE");
						name = new String(szName,"UTF-16LE");
						String StrWltFilePath =  getFileStreamPath("photo.wlt").getAbsolutePath();				
						String StrBmpFilePath =  getFileStreamPath("photo.bmp").getAbsolutePath();
						File wltFile = new File(StrWltFilePath);			
						FileOutputStream fos;
						try {
							fos = new FileOutputStream(wltFile);
							fos.write(szdata,0,nRecLen[0]);
							fos.close();
							DecodeWlt dw = new DecodeWlt();
							dw.Wlt2Bmp(StrWltFilePath, StrBmpFilePath);
							path = StrBmpFilePath;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						MyApp.idCard = StrIDNo;
						MyApp.userName = name;
						Intent intent = new Intent(IdentifyActivity.this,
								SelectClassesActivity.class);
					    intent.putExtra("name", name);
					    intent.putExtra("sfz", StrIDNo);
					    intent.putExtra("photo", path);
						startActivity(intent);	
//						CheckUser(StrIDNo);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public void CheckUser(final String str){
		RequestManager.CheckUser(str, new ResponseListener<CheckUserResponse>() {
			
			@Override
			public void onResponse(CheckUserResponse result) {
				if(!isFinishing()){
					if(result!=null&&result.code!=null){
						if("200".equals(result.code)){
								
						}else if("100".equals(result.code)){
							ToastUtil.showToast(IdentifyActivity.this,"身份认证失败，请按本页操作说明绑定");
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
}
