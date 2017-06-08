package com.example.request.comfort.parser;


import com.example.request.api.json.BaseParser;
import com.example.request.comfort.response.CheckInResponse;

/**
 * Created by w on 2016/10/14.
 */
public class CheckInParser extends BaseParser<CheckInResponse>{
    @Override
    public CheckInResponse parse(String jsonString) {
    	CheckInResponse mResponse = new CheckInResponse();
        parseMsg(jsonString,mResponse);
        return mResponse;
    }
}
