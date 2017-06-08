package com.example.request.android.volley;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.app.utils.MyLog;
import com.example.request.api.json.BaseParser;
import com.example.request.api.json.ResponseListener;

/**
 * Json璇锋眰鐨勫疄鐜扮被.
 * 
 * @author TG.1
 * @date 2015-10-09
 * 
 */
public class JsonRequestImpl {

	protected static final String TAG = "JsonRequestImpl";

	// private Context mContext;
	private final RequestQueue requestQueue;

	/**
	 * Constructor.
	 */
	public JsonRequestImpl(Context context) {
		// this.mContext = context;
		this.requestQueue = Volley.newRequestQueue(context);

	}

	/**
	 * 缃戠粶璇锋眰.
	 * 
	 * @param url
	 *            鎺ュ彛鍦板潃.
	 * @param jsonStr
	 *            寰呬笂浼犵殑Json鏁版嵁.
	 * @param params
	 *            鍙傛暟.
	 * @param responseListener
	 *            璇锋眰缁撴灉鐩戝惉鍣�.
	 * @hide
	 */
	public void request(String url, String jsonStr, BaseParser parser,
			final Map<String, String> params,
			final ResponseListener responseListener) {
		if (TextUtils.isEmpty(jsonStr)) {
			if (responseListener != null) {
				responseListener.onError(new VolleyError(
						"invalid requesting params."));
			}
			return;
		}

		JSONObject json = null;

		try {
			json = new JSONObject(jsonStr);
		} catch (JSONException e) {
			e.printStackTrace();
			if (responseListener != null) {
				responseListener.onError(new VolleyError("invalid json"));
			}
			return;
		}

		request(url, json, parser, params, responseListener);
	}

	public void requestArray(String url, String jsonStr, BaseParser parser,
			final Map<String, String> params,
			final ResponseListener responseListener) {
		if (TextUtils.isEmpty(jsonStr)) {
			if (responseListener != null) {
				responseListener.onError(new VolleyError(
						"invalid requesting params."));
			}
			return;
		}

		JSONArray jsonArray = null;

		try {
			jsonArray = new JSONArray(jsonStr);
		} catch (JSONException e) {
			e.printStackTrace();
			if (responseListener != null) {
				responseListener.onError(new VolleyError("invalid json"));
			}
			return;
		}

		requestArray(url, jsonArray, parser, params, responseListener);
	}

	/**
	 * 缃戠粶璇锋眰.
	 * 
	 * @param url
	 *            鎺ュ彛鍦板潃.
	 * @param json
	 *            寰呬笂浼犵殑Json鏁版嵁.
	 * @param params
	 *            鍙傛暟.
	 * @param responseListener
	 *            璇锋眰缁撴灉鐩戝惉鍣�.
	 * @hide
	 */
	public synchronized Request request(String url, JSONObject json,
			BaseParser parser, final Map<String, String> params,
			final ResponseListener responseListener) {
		if (json == null) {
			if (responseListener != null) {
				responseListener.onError(new VolleyError("invalid json"));
			}
			return null;
		}
		
		
		JsonJRequest jsonRequest = new JsonJRequest(url, json){
//	}
//		JsonStringRequest jsonRequest = new JsonStringRequest(url,
//				json.toString(), parser, responseListener) {
			
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				if (params != null) {
					return params;
				}
				return super.getParams();
			}

			@Override
			public Map<String, String> getHeaders() {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Accept", "application/json");
				headers.put("Content-Type", "application/json; charset=UTF-8");
				return headers;
			}
		};

		jsonRequest.setRetryPolicy(new DefaultRetryPolicy(18000, 5, 0.01f));
		jsonRequest.setShouldCache(false);

		requestQueue.add(jsonRequest);

		return jsonRequest;
	}

	public synchronized Request requestArray(String url, JSONArray json,
			BaseParser parser, final Map<String, String> params,
			final ResponseListener responseListener) {
		if (json == null) {
			if (responseListener != null) {
				responseListener.onError(new VolleyError("invalid json"));
			}
			return null;
		}

		JsonStringRequest jsonRequest = new JsonStringRequest(url,
				json.toString(), parser, responseListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				if (params != null) {
					return params;
				}
				return super.getParams();
			}
		};

		jsonRequest.setRetryPolicy(new DefaultRetryPolicy(18000, 5, 0.01f));
		jsonRequest.setShouldCache(false);

		requestQueue.add(jsonRequest);

		return jsonRequest;
	}

	/**
	 * 鍋滄鎵�鏈夎姹�.
	 * 
	 * @hide
	 */
	public void stopAll() {
		if (requestQueue == null) {
			return;
		}

		requestQueue.cancelAll(new RequestQueue.RequestFilter() {
			@Override
			public boolean apply(Request<?> request) {
				return true;
			}
		});

		requestQueue.stop();
	}

	public synchronized Request request(int type,String url,
			final Map<String, String> params, BaseParser parser,
			final ResponseListener responseListener) {

		MyLog.Logi("aaaaaaaaaRequestMap", params.toString());
		StringRequestJson jsonRequest = new StringRequestJson(type,url, parser,
				responseListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				if (params != null) {
					return params;
				}
				return super.getParams();
			}
		};

		jsonRequest.setRetryPolicy(new DefaultRetryPolicy(18000, 5, 0.01f));
		jsonRequest.setShouldCache(false);

		requestQueue.add(jsonRequest);

		return jsonRequest;
	}

}
