package com.example.request.comfort.parser;


import org.json.JSONException;
import org.json.JSONObject;

import com.example.request.api.json.BaseParser;
import com.example.request.comfort.response.UpHeadResponse;

/**
 * Created by w on 2016/7/29.
 */
public class UpHeadParser extends BaseParser<UpHeadResponse> {
    @Override
    public UpHeadResponse parse(String jsonString) {
        UpHeadResponse mResponse = new UpHeadResponse();
        parseMsg(jsonString,mResponse);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            if (jsonObject.has("data")){
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                mResponse.head = jsonObject1.getString("header");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mResponse;
    }
}
