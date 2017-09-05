package com.app.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.android.volley.VolleyError;
import com.app.MyApp;
import com.app.utils.ToastUtil;
import com.app.view.SignView;
import com.app.view.SignView.isSignListener;
import com.example.erweima.R;
import com.example.request.api.json.BaseResponse;
import com.example.request.api.json.ResponseListener;
import com.example.request.comfort.manager.RequestManager;
import com.example.request.comfort.response.CheckInResponse;
import com.example.request.comfort.response.SaveImgResponse;
import com.example.request.upload.UploadImageCompleteListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class WriteSignActivity extends BaseActivity {

	private SignView mView;
	private TextView commit,clear;
	private Bitmap mSignBitmap;
	private String signPath;
	private ArrayList<String> uuids;
	private ProgressBar bar;
	
	private boolean isSign = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_write);
		initBack();
		initView();
	}

	public void initView() {
		mView = (SignView) findViewById(R.id.signView);
		commit = (TextView) findViewById(R.id.tv_commit);
		clear = (TextView) findViewById(R.id.tv_clear);
		bar = (ProgressBar) findViewById(R.id.progressbar);
		commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(isSign){
					commit.setClickable(false);
					saveSign(mView.getCachebBitmap());					
				}else{
					ToastUtil.showToast(WriteSignActivity.this, "您还没有签名");
				}
			}
		});
		clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mView.clear();
			}
		});
		uuids = getIntent().getStringArrayListExtra("data");
		mView.setIsListener(new isSignListener() {
			
			@Override
			public void sign() {
				isSign = true;
			}
		});
	}

	public void saveSign(Bitmap bit) {
		mSignBitmap = bit;
		signPath = createFile();
		ToastUtil.showToast(this, "图片保存在" + signPath);
		savePic();
	}

	public void savePic(){
		try {
            InputStream inputStream = new FileInputStream(signPath);
            inputStream.close();
            RequestManager.savePic(signPath, new UploadImageCompleteListener() {
                @Override
                public void onPrepare() {
                	bar.setVisibility(View.VISIBLE);
                	ToastUtil.showToast(WriteSignActivity.this, "签名上传中...请稍后");
                }

                @Override
                public void onUploadImageSuccess(BaseResponse response) {
                	commit.setClickable(true);
                	SaveImgResponse mres = (SaveImgResponse) response;
                    ToastUtil.showToast(WriteSignActivity.this, "签名上传成功");
                    String fileuuid = mres.data.getUuid();
                    checkIn(fileuuid,mres.data.getPath());
                }

                @Override
                public void onUploadImageFailed() {
                	commit.setClickable(true);
                	ToastUtil.showToast(WriteSignActivity.this, "签名上传失败");
                }
            });
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void checkIn(String fileuuid,String filepath){
		ToastUtil.showToast(this, "正在报到,请稍后!");
		RequestManager.checkIn(MyApp.idCard,MyApp.userName,uuids, fileuuid,filepath, new ResponseListener<CheckInResponse>() {
			
			@Override
			public void onResponse(CheckInResponse result) {
				if("200".equals(result.code)){
					commit.setClickable(true);
					bar.setVisibility(View.GONE);
					Intent intent = new Intent(WriteSignActivity.this,BDSuccessActivity.class);
					startActivity(intent);
					finish();
				}else{
					ToastUtil.showToast(WriteSignActivity.this, "报道失败");
				}
				
			}
			
			@Override
			public void onError(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	/**
	 * @return
	 */
	private String createFile() {
		ByteArrayOutputStream baos = null;
		String _path = null;
		try {
			String sign_dir = Environment.getExternalStorageDirectory()
					.getPath() + "/";
			_path = sign_dir + "sign.jpg";
			baos = new ByteArrayOutputStream();
			mSignBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] photoBytes = baos.toByteArray();
			if (photoBytes != null) {
				new FileOutputStream(new File(_path)).write(photoBytes);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null)
					baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return _path;
	}
	
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mSignBitmap != null) {
			mSignBitmap.recycle();
		}
	}
}
