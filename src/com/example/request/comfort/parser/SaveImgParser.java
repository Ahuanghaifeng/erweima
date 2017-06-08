package com.example.request.comfort.parser;

import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.request.api.json.BaseParser;
import com.example.request.comfort.enty.SaveImgBean;
import com.example.request.comfort.enty.UserInfoBean;
import com.example.request.comfort.response.CheckUserResponse;
import com.example.request.comfort.response.DianZanResponse;
import com.example.request.comfort.response.SaveImgResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by w on 2016/10/14.
 */
public class SaveImgParser extends BaseParser<SaveImgResponse>{
    @Override
    public SaveImgResponse parse(String jsonString) {
    	SaveImgResponse mResponse = new SaveImgResponse();
        parseMsg(jsonString,mResponse);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            Gson gson = new Gson();
            Type type = new TypeToken<SaveImgBean>(){}.getType();
            mResponse.data = gson.fromJson(jsonObject.toString(), type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mResponse;
    }
}
