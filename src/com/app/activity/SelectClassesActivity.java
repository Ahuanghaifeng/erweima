package com.app.activity;

import java.io.File;
import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.app.MyApp;
import com.app.adapter.SelectClassesAdapter;
import com.app.adapter.SelectClassesAdapter.onItemCheckListener;
import com.app.utils.ToastUtil;
import com.example.erweima.R;
import com.example.request.api.json.ResponseListener;
import com.example.request.comfort.enty.SelectClassesBean;
import com.example.request.comfort.manager.RequestManager;
import com.example.request.comfort.response.SelectClassesResponse;
import com.synjones.bluetooth.BmpUtil;

public class SelectClassesActivity extends BaseActivity {

	private ListView listView;
	private TextView commit;
	private SelectClassesAdapter mAdapter;
	private ArrayList<SelectClassesBean> mData;
	private Bitmap bmp;

	private ArrayList<String> result;
	private LinearLayout lsfz;
	private ImageView head;
	private TextView tname, thaoma;
	String haoma;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_classes);
		initBack();
		initView();
		requestData();
	}

	public void initView() {
		listView = (ListView) findViewById(R.id.listview);
		commit = (TextView) findViewById(R.id.tv_commit);
		tname = (TextView) findViewById(R.id.tv_name);
		thaoma = (TextView) findViewById(R.id.tv_sfz);
		head = (ImageView) findViewById(R.id.imageViewPhoto);
		lsfz = (LinearLayout) findViewById(R.id.ll_sfz);

		commit.setOnClickListener(onClickListener);
		result = new ArrayList<String>();
		String name = getIntent().getStringExtra("name");
		haoma = getIntent().getStringExtra("sfz");
		String bmppath = getIntent().getStringExtra("photo");
		if(name==null){
			lsfz.setVisibility(View.GONE);
		}else{
			displayIDCard(bmppath);
			tname.setText(name);
			thaoma.setText(haoma);
		}
	}

	private void displayIDCard(String bmpPath) {
		int i = 0;
		if (i == 0) {
			try {
				File f = new File(bmpPath);
				if (bmp != null) {
					if (!bmp.isRecycled()) {
						bmp.recycle();
					}
					bmp = null;
				}

				if (f.exists()) {
					bmp = BitmapFactory.decodeFile(bmpPath);
					BmpUtil bu = new BmpUtil();
					head.setImageBitmap(bmp);
				} else {
					Resources res = getResources();
					bmp = BitmapFactory.decodeResource(res, R.drawable.photo);
					head.setImageBitmap(bmp);
				}
				System.gc();

			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
		} else {
			head.setImageResource(R.drawable.photo);
		}

	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.tv_commit:
				if (result != null && result.size() != 0) {
					Intent intent = new Intent(SelectClassesActivity.this,
							WriteSignActivity.class);
					intent.putStringArrayListExtra("data", result);
					startActivity(intent);
				}
				break;
			}

		}
	};

	public void requestData() {
		ToastUtil.showToast(this, "获取班级信息中,请稍后!");
		RequestManager.ShowClasses(haoma,
				new ResponseListener<SelectClassesResponse>() {

					@Override
					public void onResponse(SelectClassesResponse result) {
						if (!isFinishing()) {
							if (result != null && result.code != null) {
								if ("200".equals(result.code)) {
									mData = result.mData;
									mAdapter = new SelectClassesAdapter(
											SelectClassesActivity.this, mData);
									listView.setAdapter(mAdapter);
									setListener();
								} else if ("100".equals(result.code)) {
									ToastUtil.showToast(
											SelectClassesActivity.this,
											result.message);
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

	public void setListener() {
		mAdapter.setListener(new onItemCheckListener() {

			@Override
			public void OnItemCheck(int position, boolean isCheck) {
				if (isCheck) {
					if (!result.contains(mData.get(position).getUuid())) {
						result.add(mData.get(position).getUuid());
					}
				} else {
					if (result.contains(mData.get(position).getUuid()))
						result.remove(mData.get(position).getUuid());
				}
			}
		});
	}

}
