package com.example.request.comfort.parser;

import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.request.api.json.BaseParser;
import com.example.request.comfort.enty.UserInfoBean;
import com.example.request.comfort.response.DianZanResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by w on 2016/10/14.
 */
public class DianZanParser extends BaseParser<DianZanResponse>{
    @Override
    public DianZanResponse parse(String jsonString) {
        DianZanResponse mResponse = new DianZanResponse();
        parseMsg(jsonString,mResponse);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.has("userinfo")){
            	Gson gson = new Gson();
                Type type = new TypeToken<UserInfoBean>(){}.getType();
                mResponse.data = gson.fromJson(jsonObject.getString("userinfo"), type);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mResponse;
    }
}
