package com.yu.meituan.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by yu on 2021/3/9.
 */

public class CommonUtils {

    public static int getScreenWidth(Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return width;
    }
}
