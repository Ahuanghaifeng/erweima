package com.example.request.comfort.parser;

import java.lang.reflect.Type;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.request.api.json.BaseParser;
import com.example.request.comfort.enty.SelectClassesBean;
import com.example.request.comfort.response.SelectClassesResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by w on 2016/10/14.
 */
public class SelectClassesParser extends BaseParser<SelectClassesResponse>{
    @Override
    public SelectClassesResponse parse(String jsonString) {
    	SelectClassesResponse mResponse = new SelectClassesResponse();
        parseMsg(jsonString,mResponse);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.has("items")){
            	Gson gson = new Gson();
                Type type = new TypeToken<SelectClassesBean>(){}.getType();
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for(int i = 0;i<jsonArray.length();i++){
                	SelectClassesBean bean = gson.fromJson(jsonArray.getString(i), type);
                	mResponse.mData.add(bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mResponse;
    }
}
