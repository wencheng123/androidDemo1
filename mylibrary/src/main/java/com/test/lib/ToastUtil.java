package com.test.lib;

import android.content.Context;
import android.widget.Toast;

/***
 * @Author wen
 * @Date 2023/2/27 15:23
 * @Desc
 *
 ***/
public class ToastUtil {

    /**
     * Toast提示
     * @param context
     * @param msg
     */
    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
