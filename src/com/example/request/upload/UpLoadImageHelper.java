package com.example.request.upload;

import java.util.ArrayList;
import java.util.Map;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.example.request.comfort.parser.DianZanParser;
import com.example.request.comfort.parser.SaveImgParser;
import com.example.request.comfort.parser.UpHeadParser;
import com.example.request.comfort.response.DianZanResponse;
import com.example.request.comfort.response.SaveImgResponse;
import com.example.request.comfort.response.UpHeadResponse;




public class UpLoadImageHelper {
    private UploadImageCompleteListener onUploadImageCompleteListener;
    private static UpLoadImageHelper sharedPrefHelper = null;

    public static synchronized UpLoadImageHelper getInstance() {
        if (null == sharedPrefHelper) {
            sharedPrefHelper = new UpLoadImageHelper();
        }
        return sharedPrefHelper;
    }

    public UpLoadImageHelper() {
        super();
    }

    public void upLoadingImage(String uploadUrl, Map<String, String> paramsMap, String inputStreamKey, String filePath,
                               UploadImageCompleteListener onUploadImageCompleteListener) {

        this.onUploadImageCompleteListener = onUploadImageCompleteListener;
        new UploadingImageTask(uploadUrl, paramsMap, inputStreamKey, filePath).execute();
    }

    public void upLoadingImages(String uploadUrl, Map<String, String> paramsMap, ArrayList<String> inputStreamKey, ArrayList<String> filePath,
                               UploadImageCompleteListener onUploadImageCompleteListener) {
        this.onUploadImageCompleteListener = onUploadImageCompleteListener;
        new UploadingImageTasks(uploadUrl, paramsMap, inputStreamKey, filePath).execute();
    }

    private class UploadingImageTask extends AsyncTask<String, Integer, String> {

        String mUploadUrl;
        Map<String, String> mParamsMap;
        String mInputStreamKey;
        String mFilePath;
        int mPostion;

        public UploadingImageTask(String uploadUrl, Map<String, String> paramsMap, String inputStreamKey,
                                  String filePath) {
            super();
            this.mUploadUrl = uploadUrl;
            this.mParamsMap = paramsMap;
            this.mInputStreamKey = inputStreamKey;
            this.mFilePath = filePath;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // showProgressDialog("姝ｅ湪涓婁紶鍥剧墖锛岃绋嶅��...");
            if (onUploadImageCompleteListener != null) {
                onUploadImageCompleteListener.onPrepare();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            // String postResult = HttpRequester.post(request, formFile);
            String postResult = HttpRequester.post(mUploadUrl, mParamsMap, mInputStreamKey, mFilePath);
            return postResult;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // dismissProgressDialog();
            if (onUploadImageCompleteListener != null) {
                if (!TextUtils.isEmpty(result)) {
                    SaveImgParser parser = new SaveImgParser();
                    SaveImgResponse response = parser.parse(result);
                    if (response != null) {
                        onUploadImageCompleteListener.onUploadImageSuccess(response);
                        return;
                    }

                }
                onUploadImageCompleteListener.onUploadImageFailed();
            }
        }
    }

    private class UploadingImageTasks extends AsyncTask<String, Integer, String> {

        String mUploadUrl;
        Map<String, String> mParamsMap;
        ArrayList<String> mInputStreamKey;
        ArrayList<String> mFilePath;
        int mPostion;

        public UploadingImageTasks(String uploadUrl, Map<String, String> paramsMap, ArrayList<String> inputStreamKey,
                                   ArrayList<String> filePath) {
            super();
            this.mUploadUrl = uploadUrl;
            this.mParamsMap = paramsMap;
            this.mInputStreamKey = inputStreamKey;
            this.mFilePath = filePath;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // showProgressDialog("姝ｅ湪涓婁紶鍥剧墖锛岃绋嶅��...");
            if (onUploadImageCompleteListener != null) {
                onUploadImageCompleteListener.onPrepare();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            // String postResult = HttpRequester.post(request, formFile);
            String postResult = HttpRequester.post(mUploadUrl, mParamsMap, mInputStreamKey, mFilePath);
            return postResult;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // dismissProgressDialog();
            if (onUploadImageCompleteListener != null) {
                if (!TextUtils.isEmpty(result)) {
                    DianZanParser parser = new DianZanParser();
                    DianZanResponse response = parser.parse(result);
                    if (response != null) {
                        onUploadImageCompleteListener.onUploadImageSuccess(response);
                        return;
                    }

                }
                onUploadImageCompleteListener.onUploadImageFailed();
            }
        }
    }

}
