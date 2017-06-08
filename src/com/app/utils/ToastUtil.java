package com.app.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by w on 2016/11/22.
 */
public class ToastUtil {

    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextSize(45);
            toast.setGravity(Gravity.CENTER,0,0);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showToast(Context context, int content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
