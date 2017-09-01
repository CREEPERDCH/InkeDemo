package com.dxq.inke.fragment.live;
/*
 * Created by CREEPER_D on 2017/8/31.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dxq.inke.R;
import com.dxq.inke.adapter.live.GiftGridAdapter;
import com.dxq.inke.adapter.live.MyPagerAdapter;
import com.dxq.inke.adapter.live.base.MyBaseAdapter;
import com.dxq.inke.bean.GiftBean;
import com.dxq.inke.bean.GiftListBean;
import com.dxq.inke.eventbus.ShowBottomEvent;
import com.dxq.inke.fragment.LogFragment;
import com.dxq.inke.http.IViewerServices;
import com.dxq.inke.http.ServiceGenerator;
import com.dxq.inke.utils.UIUtlis;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiftShopFragment extends LogFragment {
    private static final int DEFAULT_GIFT_SHOW_COUNT = 8;
    @BindView(R.id.back)
    View mBack;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.ll_golds)
    LinearLayout mLlGolds;
    @BindView(R.id.ll_dots)
    LinearLayout mLlDots;
    @BindView(R.id.tv_send_store)
    TextView mTvSendStore;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R.id.ll_content)
    LinearLayout mLlContent;
    Unbinder unbinder;
    private Animation mAnimIn;
    private Animation mAnimOut;
    private int lastSelectedPage = 0;
    private ArrayList<GiftGridAdapter> mGridViewAdapters;

    @Override
    public View onChildCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.frag_gift_shop, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initAnim();
        return inflate;
    }

    private void initAnim() {
        mAnimIn = AnimationUtils.loadAnimation(getContext(), R.anim.gift_shop_in);
        mAnimOut = AnimationUtils.loadAnimation(getContext(), R.anim.gift_shop_out);
        //退出动画在播放完后需要隐藏布局
        mAnimOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLlContent.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    protected void initData() {
        mGridViewAdapters = new ArrayList<>();
        requestGiftData();
        showContent();
    }

    private void requestGiftData() {
        IViewerServices giftServices = ServiceGenerator.getSingleTon().create(IViewerServices.class);
        Call<GiftListBean> call = giftServices.getGiftInfo();
        call.enqueue(new Callback<GiftListBean>() {
            @Override
            public void onResponse(Call<GiftListBean> call, Response<GiftListBean> response) {
                GiftListBean body = response.body();
                setGiftData(body != null ? body.getGifts() : null);
            }

            @Override
            public void onFailure(Call<GiftListBean> call, Throwable t) {
            }
        });
    }

    private void setGiftData(ArrayList<GiftBean> gifts) {
        //将数据设置给ViewPager
        //ViewPager每个页面都是网格,使用RecyclerView来写网格
        //VIewPager的页面=gifts.size()/8
        ArrayList<RecyclerView> recyclerViews = new ArrayList<>();
        //最后一页如果不够8个,就填充一些空的数据,将页面填充完整一点,免得参差不齐
        //        36 % 8 =4  还需要补 8 -4 = 4个进来
        int pageCount = gifts.size() % DEFAULT_GIFT_SHOW_COUNT;
        for (int i = 0; i < DEFAULT_GIFT_SHOW_COUNT - pageCount; i++) {
            gifts.add(new GiftBean());
        }
        //36 / 8 = 4
        //补齐了最后一页以后 能够得到正常的页码数了 40 / 8 = 5
        int finalPageCount = gifts.size() / DEFAULT_GIFT_SHOW_COUNT;
        for (int i = 0; i < finalPageCount; i++) {
            RecyclerView recyclerView = generateRecyclerView(gifts, i);
            recyclerViews.add(recyclerView);
        }
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(recyclerViews);
        mViewPager.setAdapter(myPagerAdapter);

        //设置完礼物数据后,添加小点
        initDots();
        selectDot(0);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initDots() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, -2);
        layoutParams.setMargins(0, 0, UIUtlis.dp2px(5), 0);
        for (int i = 0; i < mGridViewAdapters.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(R.drawable.gray_dot);
            mLlDots.addView(imageView, layoutParams);
        }
    }

    private void selectDot(int index) {
        int childCount = mLlDots.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = mLlDots.getChildAt(i);
            if (i == index) {
                child.setBackgroundResource(R.drawable.white_dot);
            } else {
                child.setBackgroundResource(R.drawable.gray_dot);
            }
        }
    }

    private RecyclerView generateRecyclerView(ArrayList<GiftBean> gifts, final int index) {
        ArrayList<GiftBean> currentPageGift = new ArrayList<>();
        for (int i = 0; i < DEFAULT_GIFT_SHOW_COUNT; i++) {
            currentPageGift.add(gifts.get(i + index * DEFAULT_GIFT_SHOW_COUNT));
        }
        final GiftGridAdapter giftGridAdapter = new GiftGridAdapter(currentPageGift);
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4, RecyclerView.VERTICAL, false));
        giftGridAdapter.setOnItemClickListener(new MyBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                GiftGridAdapter adapter = mGridViewAdapters.get(lastSelectedPage);
                adapter.cancelAllSelect();
                giftGridAdapter.updateSelect(position);
                lastSelectedPage = index;
            }
        });
        recyclerView.setAdapter(giftGridAdapter);
        mGridViewAdapters.add(giftGridAdapter);
        return recyclerView;
    }

    public void showContent() {
        mLlContent.setVisibility(View.VISIBLE);
        mLlContent.startAnimation(mAnimIn);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.back, R.id.tv_send_store})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                //隐藏布局
                hideContent();
                break;
            case R.id.tv_send_store:
                break;
        }
    }

    private void hideContent() {
        mLlContent.startAnimation(mAnimOut);
        EventBus.getDefault().post(new ShowBottomEvent(true));
    }

    ////后退键按下会触发里面的代码
    //返回false ,代表Activity不用关闭
    public boolean onBackPressed() {
        if (mLlContent.getVisibility() == View.VISIBLE) {
            hideContent();
            return false;
        }
        return true;
    }
}
