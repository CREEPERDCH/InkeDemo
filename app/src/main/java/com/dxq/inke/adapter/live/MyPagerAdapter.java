package com.dxq.inke.adapter.live;
/*
 * Created by CREEPER_D on 2017/8/31.
 */

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<RecyclerView> mRecyclerViews;

    public MyPagerAdapter(ArrayList<RecyclerView> recyclerViews) {
        mRecyclerViews = recyclerViews;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
        RecyclerView recyclerView = mRecyclerViews.get(position);
        container.addView(recyclerView);
        return recyclerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mRecyclerViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
