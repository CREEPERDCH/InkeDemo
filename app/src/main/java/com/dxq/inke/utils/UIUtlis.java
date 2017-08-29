package com.dxq.inke.utils;
/*
 * Created by CREEPER_D on 2017/8/25.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.dxq.inke.InkeApplication;

public class UIUtlis {

    public static Context getContext() {
        return InkeApplication.sContext;
    }

    public static String[] getStringArray(int resId) {
        return getContext().getResources().getStringArray(resId);
    }

    //让代码强制运行到主线程
    public static void runOnUIThread(Runnable runnable) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }

    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }
}
