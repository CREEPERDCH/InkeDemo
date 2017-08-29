package com.dxq.inke.utils;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class ActivityUtils {
    public static void startNew(Context context, Class<? extends AppCompatActivity> clazz, boolean isFinish) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }
}
