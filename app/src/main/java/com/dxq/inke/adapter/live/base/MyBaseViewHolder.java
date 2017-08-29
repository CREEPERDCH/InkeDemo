package com.dxq.inke.adapter.live.base;
/*
 * Created by CREEPER_D on 2017/8/25.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dxq.inke.R;
import com.dxq.inke.bean.BannerBean;
import com.dxq.inke.utils.FrescoImageLoader;
import com.dxq.inke.utils.ImageUtils;
import com.dxq.inke.utils.UIUtlis;
import com.youth.banner.Banner;

import java.util.ArrayList;

public class MyBaseViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "dxq";
    private SparseArray<View> mSparseArray;

    public MyBaseViewHolder(View itemView) {
        super(itemView);
        mSparseArray = new SparseArray<>();
    }

    public void setText(int viewId, String text) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
    }

    public void setImageRes(int viewId, int imgId) {
        View view = getView(viewId);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(imgId);
        }
    }

    public void setBanner(int viewId, ArrayList<BannerBean> list) {
        View view = getView(viewId);
        if (view instanceof Banner) {
            //是轮播图控件,设置轮播图效果
            ArrayList<String> urls = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                BannerBean bannerBean = list.get(i);
                urls.add(bannerBean.getImage());
            }
            //给轮播图控件设置数据
            ((Banner) view).setImages(urls).setImageLoader(new FrescoImageLoader()).start();
        }
    }

    public void setTags(int viewId, ArrayList<String> tags) {
        View view = getView(viewId);
        if (view instanceof LinearLayout) {
            //先移除掉以前复用之前所添加的一些view
            ((LinearLayout) view).removeAllViews();
            Log.d(TAG, "setTags: tags.size" + tags.size());
            //添加TextView到容器中
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, UIUtlis.dp2px(8), 0);
            for (int i = 0; i < tags.size(); i++) {
                String s = tags.get(i);
                TextView textView = new TextView(view.getContext());
                textView.setText(s);
                textView.setTextSize(10);
                textView.setTextColor(view.getResources().getColor(R.color.top_gray));
                textView.setBackgroundResource(R.drawable.bg_tag);
                //给textView设置内部的padding,将背景图撑大
                textView.setPadding(UIUtlis.dp2px(10), UIUtlis.dp2px(5), UIUtlis.dp2px(10), UIUtlis.dp2px(5));
                textView.setGravity(Gravity.CENTER);
                ((LinearLayout) view).addView(textView, params);
            }
        }
    }

    public void loadImage(int viewId, String url) {
        View view = getView(viewId);
        ImageUtils.getSingleTon().loadImage(view, url);
    }

    public View getView(int viewId) {
        View view = mSparseArray.get(viewId);
        if (view == null) {
            //如果map中没有的话,就去findViewById,并且将这个view存到map里面去
            view = itemView.findViewById(viewId);
            mSparseArray.put(viewId, view);
        }
        //有的话直接返回
        return view;
    }
}
