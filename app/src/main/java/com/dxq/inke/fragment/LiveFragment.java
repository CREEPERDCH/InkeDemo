package com.dxq.inke.fragment;
/*
 * Created by CREEPER_D on 2017/8/25.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dxq.inke.R;
import com.dxq.inke.activity.SearchActivity;
import com.dxq.inke.adapter.live.LiveFragmentPagerAdapter;
import com.dxq.inke.fragment.live.FocusFragment;
import com.dxq.inke.fragment.live.HotFragment;
import com.dxq.inke.fragment.live.NearFragment;
import com.dxq.inke.fragment.live.OtherFragment;
import com.dxq.inke.utils.ActivityUtils;
import com.dxq.inke.utils.UIUtlis;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LiveFragment extends LogFragment {
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tabLayout)
    SlidingTabLayout mTabLayout;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    Unbinder unbinder;

    @Override
    public View onChildCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_live, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        String[] stringArray = UIUtlis.getStringArray(R.array.title);
        for (int i = 0; i < stringArray.length; i++) {
            if (i == 0) {
                fragments.add(new FocusFragment());
            } else if (i == 1) {
                fragments.add(new HotFragment());
            } else if (i == 2) {
                fragments.add(new NearFragment());
            } else {
                fragments.add(new OtherFragment());
            }
        }
        LiveFragmentPagerAdapter liveFragmentPagerAdapter = new LiveFragmentPagerAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(liveFragmentPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                updateTitleTextSize(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //设置标题数据
        mTabLayout.setViewPager(mViewPager, stringArray);
        //打开默认切换热门
        mViewPager.setCurrentItem(1);
    }

    private int lastIndex = 0;

    private void updateTitleTextSize(int position) {
        TextView lastTitleView = mTabLayout.getTitleView(lastIndex);
        lastTitleView.setTextSize(16);
        TextView titleView = mTabLayout.getTitleView(position);
        titleView.setTextSize(20);
        lastIndex = position;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_left, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                ActivityUtils.startNew(getContext(), SearchActivity.class, false);
                break;
            case R.id.iv_right:
                Toast.makeText(getActivity(), "iv_right", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
