package com.app.utils;

import android.util.Log;

/**
 * Created by w on 2016/9/28.
 */
public class MyLog {

	public static boolean print = true;

	public static void Logi(String result) {
		if (print) {
			Log.i("aaaaaaaaa", result);
		}
	}

	public static void Logi(String tag, String result) {
		if (print) {
			Log.i(tag, result);
		}
	}
}
