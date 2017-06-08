package com.example.request.android.volley;

import android.text.TextUtils;
import android.util.Log;

/**
 * 工具类.
 * 
 * @author TG.1
 * @date 2015-07-12
 */
public class Tools {
    private static final String TAG = Tools.class.getSimpleName();

    public static boolean DEBUG = false;

    /**
     * 打印Log. Log.i(TAG,String);
     * 
     * @param tag
     * @param string
     */
    public static void logE(String tag, String string) {
        if (!DEBUG) {
            return;
        }

        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }

        Log.e(tag, "" + string);
    }

    /**
     * 打印Log. Log.d(TAG,String);
     * 
     * @param tag
     * @param string
     */
    public static void logD(String tag, String string) {
        if (!DEBUG) {
            return;
        }

        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }

        Log.d(tag, string);
    }

}