package com.example.request.api.json;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.example.request.android.volley.JsonRequestImpl;
import com.example.request.android.volley.Tools;




/**
 * Json璇锋眰绠＄悊鍣�.
 * 
 * @author TG.1
 * @date 2015-10-09
 * 
 */
public class JsonRequestManager {

	private static JsonRequestManager mInstance;
	private static JsonRequestImpl mJsonRequestImpl;

	// protected final static String URL =
	// "http://218.200.229.165:8091/Api.html";

	private JsonRequestManager() {
	}

	/**
	 * 鍒濆鍖�,鎺ュ彛浣跨敤鍓嶅繀椤昏皟鐢╯tart鏂规硶.
	 * 
	 * @param context
	 *            涓婁笅鏂�.
	 */
	public static void start(Context context) {
		start(context, null);
	}

	/**
	 * 鍒濆鍖�,鎺ュ彛浣跨敤鍓嶅繀椤昏皟鐢╯tart鏂规硶.
	 * 
	 * @param context
	 *            涓婁笅鏂�.
	 * @param url
	 *            鏈嶅姟鍣ㄥ湴鍧�.
	 */
	public static void start(Context context, String url) {
		if (context == null) {
			return;
		}
		if (!TextUtils.isEmpty(url)) {
			Cfg.URL = url;
		}

		if (mJsonRequestImpl == null) {
			mJsonRequestImpl = new JsonRequestImpl(context);
		}

	}

	/**
	 * 鍒濆鍖�,鎺ュ彛浣跨敤鍓嶅繀椤昏皟鐢╯tart鏂规硶.
	 * 
	 * @param context
	 *            涓婁笅鏂�.
	 * @param url
	 *            鏈嶅姟鍣ㄥ湴鍧�.
	 * @param debug
	 *            鏄惁鎵撳嵃Log.
	 */
	public static void start(Context context, String url, boolean debug) {
		start(context, url);
		Tools.DEBUG = debug;
	}

	/**
	 * 鑾峰彇鍗曞疄渚�.璋冪敤鍓嶅繀椤昏皟鐢╯tart(context)鏂规硶.
	 * 
	 * @return Json璇锋眰绠＄悊鍣�.
	 */
	public static JsonRequestManager getInstance() {
		if (mInstance == null) {
			mInstance = new JsonRequestManager();
		}
		return mInstance;
	}

	/**
	 * 璇锋眰缃戠粶鎺ュ彛.
	 * 
	 * @param url
	 *            鎺ュ彛鍦板潃.
	 * @param json
	 *            寰呬笂浼犵殑json瀛楃涓�.
	 * @param params
	 *            璇锋眰澶�.
	 * @param responseListener
	 *            璇锋眰缁撴灉鐩戝惉鍣�.
	 */
	public void request(String url, String json, Map<String, String> params,
						BaseParser parser, ResponseListener responseListener) {
		mJsonRequestImpl.request(url, json, parser, params, responseListener);
	}

	/**
	 * 璇锋眰缃戠粶鎺ュ彛.
	 * 
	 * @param url
	 *            鎺ュ彛鍦板潃.
	 * @param json
	 *            寰呬笂浼犵殑Json.
	 * @param parser
	 *            杩斿洖缁撴灉瑙ｆ瀽鍣�.
	 * @param responseListener
	 *            杩斿洖缁撴灉鐩戝惉鍣�.
	 * @return 灏佽鍚庣殑璇锋眰瀵硅薄.
	 */
	public Request jsonRequest(String url, JSONObject json, BaseParser parser,
							   ResponseListener responseListener) {
		return mJsonRequestImpl.request(url, json, parser, null,
				responseListener);
	}

	public Request jsonArrayRequest(String url, JSONArray json,
									BaseParser parser, ResponseListener responseListener) {
		return mJsonRequestImpl.requestArray(url, json, parser, null,
				responseListener);
	}

	/**
	 * 璇锋眰缃戠粶鎺ュ彛.
	 * 
	 * @param url
	 *            鎺ュ彛鍦板潃.
	 * @param json
	 *            寰呬笂浼犵殑Json.
	 * @param params
	 *            寰呬笂浼犵殑鍙傛暟.
	 * @param parser
	 *            杩斿洖缁撴灉瑙ｆ瀽鍣�.
	 * @param responseListener
	 *            杩斿洖缁撴灉鐩戝惉鍣�.
	 */
	public void request(String url, JSONObject json,
						Map<String, String> params, BaseParser parser,
						ResponseListener responseListener) {
		mJsonRequestImpl.request(url, json, parser, params, responseListener);
	}


	/**
	 * 鍙栨秷鎵�鏈夌殑璇锋眰搴忓垪,鏋愭瀯.
	 */
	public static void stop() {
		mJsonRequestImpl.stopAll();
	}

	public void request(int type,String url, Map<String,String> params, BaseParser parser, ResponseListener responseListener){
		mJsonRequestImpl.request(type,url,params,parser,responseListener);
	}
}
