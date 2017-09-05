package com.app;

import com.example.request.api.json.JsonRequestManager;

import android.R.string;
import android.app.Application;

public class MyApp extends Application{
	
	public static String idCard;
	public static boolean isCard = false;
	public static String userName = "";
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		JsonRequestManager.start(this, null, true); //volley
	}

}
