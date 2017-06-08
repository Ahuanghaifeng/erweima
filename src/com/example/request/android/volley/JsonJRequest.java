package com.example.request.android.volley;

import java.util.HashMap;

import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

public class JsonJRequest extends JsonObjectRequest{

	public JsonJRequest(String url, JSONObject jsonRequest) {
		super(Method.POST, url, jsonRequest, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject arg0) {
				Log.i("aaaaaaaa", arg0.toString());
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
