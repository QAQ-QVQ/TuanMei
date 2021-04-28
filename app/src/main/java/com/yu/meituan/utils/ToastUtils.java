package com.yu.meituan.utils;

import android.widget.Toast;

import com.yu.meituan.MyApplication;

/**
 * Created by yu on 2021/3/10.
 * Toast工具
 */

public class ToastUtils {

    private static Toast mToast;

    private ToastUtils(){
    }

    public static void show(String str){
        if(mToast == null){
            mToast = Toast.makeText(MyApplication.getAppContext(), str, Toast.LENGTH_SHORT);
        }else {
            mToast.setText(str);
        }
        mToast.show();
    }


}
