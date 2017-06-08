package com.example.request.android.volley;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.app.utils.MyLog;
import com.example.request.api.json.BaseParser;
import com.example.request.api.json.BaseResponse;
import com.example.request.api.json.EmptyParser;
import com.example.request.api.json.ResponseListener;




public class RequestString extends StringRequest{
	
	private final BaseParser mParser;
    private final ResponseListener mIResponseListener;

	public RequestString(String url, BaseParser parser, final ResponseListener responseListener) {
		super(Method.POST, url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				if (responseListener != null) {
                    responseListener.onResponse(response);
                }
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				if (responseListener != null) {
                    responseListener.onError(error);
                }
			}
		});
		this.mParser = parser;
        this.mIResponseListener = responseListener;
//        mMap = map;
	}
	
		
//	    @Override
//	protected Map<String, String> getParams() {
//		// 鍦ㄨ繖閲岃缃渶瑕乸ost鐨勫弬鏁�
////		Map<String, String> map = new HashMap<String, String>();
////		map.put("name1", "value1");
////		map.put("name2", "value2");
//		return mMap;
//	}
	
	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		try {
            String jsonString = new String(response.data, "utf-8");
            MyLog.Logi("aaaaaaaaaaaa",jsonString);
            jsonString = fromEncodedUnicode(jsonString.toCharArray(), 0, jsonString.length());

            // 濡傛灉瑙ｆ瀽鍣ㄩ潪绌�,鍒欒В鏋�.
            if (mParser != null) {
                BaseResponse baseResponse = mParser.parse(jsonString);
//                Tools.logE(TAG, "parser:" + mParser.getClass().getSimpleName());
                if (baseResponse != null) {
//                    Tools.logE(TAG, "baseResponse:" + baseResponse.getClass().getSimpleName());
                }
                return Response.success(jsonString,HttpHeaderParser.parseCacheHeaders(response));
                //
//                return Response.success(baseResponse, HttpHeaderParser.parseCacheHeaders(response));

            } else {
                // 鍚﹀垯,杩斿洖String.
                EmptyParser parser = new EmptyParser();
                BaseResponse baseResponse = parser.parse(jsonString);
                return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));

            }

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    // 瑙ｄ腑鏂囦贡鐮侊紙-->utf-8锛�
    private String fromEncodedUnicode(char[] in, int off, int len) {
        char aChar;
        char[] out = new char[len];
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = aChar;
            }
        }
        return new String(out, 0, outLen);
    }
}
