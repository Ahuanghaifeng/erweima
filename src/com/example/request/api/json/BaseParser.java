package com.example.request.api.json;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 閸╄櫣琚�,Json鐟欙絾鐎介崳锟�.鐟欙絾鐎介張宥呭缁旑垵绻戦崶鐐垫畱Json閺佺増宓�.
 * 
 * @author youpeng
 * 
 * @param <T>
 */
public abstract class BaseParser<T> {

    /**
     * Json鐟欙絾鐎�,鐏忓捈son鐟欙絾鐎介崥搴ｆ畱鐏炵偞锟窖囨肠閸氬牆鐨濈憗鍛灇鐎电钖�.
     * 
     * @param jsonString
     *            閺堝秴濮熺粩顖濈箲閸ョ偟娈戦弫鐗堝祦.
     * @return 鐏忎浇顥婇幋鎬瀉vaBean閸氬海娈戠仦鐐达拷褔娉﹂崥锟�.
     */
    public abstract BaseResponse parse(String jsonString);

    /**
     * 鐟欙絾鐎介崺铏诡攨娣団剝浼�.
     * 
     * @param jo
     *            鐏忎浇顥婇崥搴ｆ畱Json鐎电钖�.
     * @param br
     *            鐏忎浇顥婇幋鎬瀉vaBean閸氬海娈戠仦鐐达拷褔娉﹂崥锟�.
     */
    public void parseMsg(JSONObject jo, BaseResponse br) {
        if (jo != null) {
            try {
                br.code = jo.getString("code");
                br.message = jo.getString("message");
                br.originalResult = jo;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public void parseMsg(String jsonString, BaseResponse br) {
        if (jsonString != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.has("code")){
                    br.code = jsonObject.getString("code");
                    br.message = jsonObject.getString("message");
                    br.originalResult = jsonObject;
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
