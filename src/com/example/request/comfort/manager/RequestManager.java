package com.example.request.comfort.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;













import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Request.Method;
import com.example.request.api.json.BaseParser;
import com.example.request.api.json.BaseResponse;
import com.example.request.api.json.JsonRequestManager;
import com.example.request.api.json.ResponseListener;
import com.example.request.comfort.parser.CheckInParser;
import com.example.request.comfort.parser.CheckUserParser;
import com.example.request.comfort.parser.SelectClassesParser;
import com.example.request.comfort.response.CheckInResponse;
import com.example.request.comfort.response.CheckUserResponse;
import com.example.request.comfort.response.SelectClassesResponse;
import com.example.request.constants.Constans;
import com.example.request.upload.UpLoadImageHelper;
import com.example.request.upload.UploadImageCompleteListener;

public class RequestManager {
	
	/**
	 * 检查是否绑定微信
	 * @param idCard
	 * @param response
	 */
	public static void CheckUser(String idCard,ResponseListener<CheckUserResponse> response){
		Map<String,String> map = new HashMap<String, String>();
		String url = Constans.ServiceUrl+"check_user?idcard="+idCard;
		JsonRequestManager.getInstance().request(Method.GET,url, map, new CheckUserParser(), response);
	}
	
	/**
	 * 获取课表
	 * @param responseListener
	 */
	public static void ShowClasses(String idcard,ResponseListener<SelectClassesResponse> responseListener){
		Map<String,String> map = new HashMap<String, String>();
		String url = Constans.ServiceUrl+"gathers?s=all&myuuid="+idcard;
		JsonRequestManager.getInstance().request(Method.GET,url, map, new SelectClassesParser(), responseListener);
	}
	
	
	/**
	 * 上传文件
	 * @param path
	 * @param responseListener
	 */
	public static void savePic(String path,UploadImageCompleteListener responseListener){
		Map<String,String> map = new HashMap<String,String>();
        UpLoadImageHelper mLoadImageHelper = UpLoadImageHelper.getInstance();
        map.put("encrypt", "0");
        mLoadImageHelper.upLoadingImage(Constans.ServiceUrl+"files", map, "file", path,responseListener);
	}
	
	/**
	 * 报道
	 * @param idcard
	 * @param uuids
	 * @param fileuuid
	 * @param filePath
	 * @param responseListener
	 */
	public static void checkIn(String idcard,String username,ArrayList<String> uuids,String fileuuid,String filepath,ResponseListener<CheckInResponse> responseListener){
//		JSONObject jsonObject = new JSONObject();
//		try {
//			jsonObject.put("idcard", "429004199409170738");
//			JSONArray jsArray = null;
//			for(int i = 0;i <uuids.size();i++){
//				jsArray = new JSONArray();
//				jsArray.put(uuids.get(i));
//			}
//			jsonObject.put("uuid", jsArray);
//			jsonObject.put("file_uuid", fileuuid);
//			jsonObject.put("file_url", "www.baidu.com");
//			JsonRequestManager.getInstance().jsonRequest(Constans.ServiceUrl+"check_in", jsonObject, new CheckInParser(),responseListener);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("idcard", "");
//		map.put("file_uuid", fileuuid);
		String uuid = null;
		for(int i =0;i<uuids.size();i++){
			uuid = uuids.get(i);
		}
//		map.put("uuid", uuid);
//		map.put("file_url", filepath);
//		map.put("name", username);
		String url = Constans.ServiceUrl+"check_in?"+"idcard="+idcard+"&name="+username+"&file_uuid="+fileuuid+"&uuid="+uuid+"&file_url="+filepath;
		JsonRequestManager.getInstance().request(Method.GET, url, map, new CheckInParser(), responseListener);
	}
}
