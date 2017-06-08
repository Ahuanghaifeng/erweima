/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.request.android.volley;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.app.utils.MyLog;
import com.example.request.api.json.BaseParser;
import com.example.request.api.json.BaseResponse;
import com.example.request.api.json.EmptyParser;
import com.example.request.api.json.ResponseListener;
/**
 * A request for retrieving a response body at a given URL, allowing for an
 * optional to be passed in as part of the request body.
 */
public class JsonStringRequest extends JsonRequest<BaseResponse> {
    private static final String TAG = JsonStringRequest.class.getSimpleName();
    private final BaseParser mParser;

    public JsonStringRequest(String url, String jsonRequest, BaseParser parser, final ResponseListener responseListener) {
        super(Method.POST, url, (jsonRequest == null) ? null : jsonRequest, new Listener<BaseResponse>() {

            @Override
            public void onResponse(BaseResponse response) {
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
    }

    @Override
    protected Response<BaseResponse> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, "utf-8");
//            jsonString = fromEncodedUnicode(jsonString.toCharArray(), 0, jsonString.length());
            MyLog.Logi("aaaaaaaaaaaa",jsonString);
            // 濡傛灉瑙ｆ瀽鍣ㄩ潪绌�,鍒欒В鏋�.
            if (mParser != null) {
                BaseResponse baseResponse = mParser.parse(jsonString);
                if (baseResponse != null) {
                }
                return Response.success(baseResponse, HttpHeaderParser.parseCacheHeaders(response));

            } else {
                // 鍚﹀垯,杩斿洖String.
                EmptyParser parser = new EmptyParser();
                BaseResponse baseResponse = parser.parse(jsonString);
                return Response.success(baseResponse, HttpHeaderParser.parseCacheHeaders(response));

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
