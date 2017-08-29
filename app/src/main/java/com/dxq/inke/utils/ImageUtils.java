package com.dxq.inke.utils;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

public class ImageUtils {
    private static volatile ImageUtils sInstance;

    public static ImageUtils getSingleTon() {
        if (sInstance == null) {
            synchronized (ImageUtils.class) {
                if (sInstance == null) {
                    sInstance = new ImageUtils();
                }
            }
        }
        return sInstance;
    }

    private ImageUtils() {
    }

    public void loadImage(View view, String url) {

        if (view instanceof SimpleDraweeView) {
            ((SimpleDraweeView) view).setImageURI(Uri.parse(url));
        } else if (view instanceof ImageView) {
            //使用Glide UIL
            Log.e(getClass().getSimpleName() + " xmg", "loadImage: " + "普通图片未配置使用三方库来加载");
        }
    }
}

