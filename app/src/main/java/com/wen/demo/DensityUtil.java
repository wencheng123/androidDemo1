package com.wen.demo;

import android.content.Context;

/***
 * @Author wen
 * @Date 2023/4/3 16:36
 * @Desc
 *
 ***/
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
