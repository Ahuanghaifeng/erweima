package com.example.request.api.json;

import org.json.JSONObject;

import java.io.Serializable;


/**
 * 基类,解析封装后的对象.将服务器返回的Json串封装成对象.
 * 
 * @author youpeng
 * 
 */
public abstract class BaseResponse implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 5375804597574885028L;

    public String code;

    public String message;

    /**
     * 服务端返回的原始数据.
     */
    public JSONObject originalResult;
}
