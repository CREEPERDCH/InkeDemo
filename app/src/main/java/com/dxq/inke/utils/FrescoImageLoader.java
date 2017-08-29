package com.dxq.inke.utils;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

import android.content.Context;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoader;

public class FrescoImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageUtils.getSingleTon().loadImage(imageView, (String) path);
    }

    //提供createImageView 方法，方便fresco自定义ImageView
    @Override
    public ImageView createImageView(Context context) {
        return new SimpleDraweeView(context);
    }
}
