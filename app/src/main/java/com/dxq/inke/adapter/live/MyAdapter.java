package com.dxq.inke.adapter.live;
/*
 * Created by CREEPER_D on 2017/8/30.
 */

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dxq.inke.InkeApplication;
import com.dxq.inke.R;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {

    private final ArrayList<RelativeLayout> mLayouts;

    public MyAdapter(ArrayList<RelativeLayout> relativeLayouts) {
        mLayouts = relativeLayouts;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RelativeLayout relativeLayout = new RelativeLayout(InkeApplication.sContext);
        relativeLayout.setBackgroundResource(R.drawable.room_change_bg);
        container.addView(relativeLayout);
        //add a id
        relativeLayout.setId(position);
        return relativeLayout;
//        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return mLayouts.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
