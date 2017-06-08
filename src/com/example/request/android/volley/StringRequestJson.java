package com.example.request.android.volley;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.app.utils.MyLog;
import com.example.request.api.json.BaseParser;
import com.example.request.api.json.BaseResponse;
import com.example.request.api.json.EmptyParser;
import com.example.request.api.json.ResponseListener;



/**
 * Created by w on 2016/7/28.
 */
public class StringRequestJson extends Request<BaseResponse> {

    private static final String TAG = JsonStringRequest.class.getSimpleName();
    private final BaseParser mParser;
    private final ResponseListener mIResponseListener;

    public StringRequestJson(int type,String url, BaseParser parser, final ResponseListener responseListener) {
        super(type, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (responseListener != null) {
                    responseListener.onError(volleyError);
                }
            }
        });
        this.mParser = parser;
        this.mIResponseListener = responseListener;
    }

    @Override
    protected Response<BaseResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String jsonString = new String(networkResponse.data, "utf-8");

            // 濡傛灉瑙ｆ瀽鍣ㄩ潪绌�,鍒欒В鏋�.
            if (mParser != null) {
                MyLog.Logi("aaaaaaaaaaaa",jsonString);
                BaseResponse baseResponse = mParser.parse(jsonString);
                if (baseResponse != null) {
                    Tools.logE(TAG, "baseResponse:" + baseResponse.getClass().getSimpleName());
                }
                return Response.success(baseResponse, HttpHeaderParser.parseCacheHeaders(networkResponse));
            } else {
                // 鍚﹀垯,杩斿洖String.
                EmptyParser parser = new EmptyParser();
                BaseResponse baseResponse = parser.parse(jsonString);
                return Response.success(baseResponse, HttpHeaderParser.parseCacheHeaders(networkResponse));

            }

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(BaseResponse response) {
        mIResponseListener.onResponse(response);
    }

}
