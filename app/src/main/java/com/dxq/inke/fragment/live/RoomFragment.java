package com.dxq.inke.fragment.live;
/*
 * Created by CREEPER_D on 2017/8/30.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dxq.inke.R;
import com.dxq.inke.adapter.live.ViewerListAdapter;
import com.dxq.inke.bean.CreatorBean;
import com.dxq.inke.bean.HotLiveBean;
import com.dxq.inke.bean.ViewerBean;
import com.dxq.inke.bean.ViewerListBean;
import com.dxq.inke.eventbus.ShowBottomEvent;
import com.dxq.inke.fragment.LogFragment;
import com.dxq.inke.http.IViewerServices;
import com.dxq.inke.http.ServiceGenerator;
import com.dxq.inke.utils.ImageUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RoomFragment extends LogFragment {

    public static final String LIVE_BEAN = "LIVE_BEAN";
    public static final String TAG_GIFT_SHOP = "TAG_GIFT_SHOP";
    @BindView(R.id.recyclerView_chat)
    RecyclerView mRecyclerViewChat;
    @BindView(R.id.iv_anchor_icon)
    SimpleDraweeView mIvAnchorIcon;
    @BindView(R.id.tv_anchor_name)
    TextView mTvAnchorName;
    @BindView(R.id.tv_online_number)
    TextView mTvOnlineNumber;
    @BindView(R.id.tv_focus)
    TextView mTvFocus;
    @BindView(R.id.ll_left)
    LinearLayout mLlLeft;
    @BindView(R.id.tv_gold_number)
    TextView mTvGoldNumber;
    @BindView(R.id.card)
    LinearLayout mCard;
    @BindView(R.id.iv_send)
    ImageView mIvSend;
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.iv_gift_shop)
    ImageView mIvGiftShop;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.rl_bottom)
    RelativeLayout mRlBottom;
    @BindView(R.id.tv_anchor_number)
    TextView mTvAnchorNumber;
    @BindView(R.id.iv_msg_off)
    ImageView mIvMsgOff;
    @BindView(R.id.tv_send_msg)
    TextView mTvSendMsg;
    @BindView(R.id.edt)
    EditText mEdt;
    @BindView(R.id.rl_edit)
    RelativeLayout mRlEdit;
    @BindView(R.id.fl_gift_shop)
    FrameLayout mFlGiftShop;
    @BindView(R.id.rl_content)
    RelativeLayout mRlContent;
    Unbinder unbinder;
    @BindView(R.id.recyclerView_Viewer)
    RecyclerView mRecyclerViewViewer;
    private ViewerListAdapter mViewerListAdapter;

    @Override
    public View onChildCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.frag_room, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        mRecyclerViewViewer.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewerListAdapter = new ViewerListAdapter(new ArrayList<ViewerBean>());
        mRecyclerViewViewer.setAdapter(mViewerListAdapter);

        //EventBus
        EventBus.getDefault().register(this);
        return inflate;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    //接受事件
    @Subscribe
    public void showBottom(ShowBottomEvent event) {
        boolean show = event.isShow();
        if (show) {
            mRlBottom.setVisibility(View.VISIBLE);
        } else {
            mRlBottom.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        HotLiveBean hotLiveBean = (HotLiveBean) bundle.getSerializable(LIVE_BEAN);
        if (hotLiveBean != null) {
            setUI(hotLiveBean);
        }
    }

    public void setUI(HotLiveBean hotLiveBean) {
        CreatorBean creator = hotLiveBean.getCreator();
        mTvAnchorName.setText(creator.getNick());
        ImageUtils.getSingleTon().loadImage(mIvAnchorIcon, creator.getPortrait());
        mTvAnchorNumber.setText("映客号: " + creator.getId());
        mTvGoldNumber.setText(hotLiveBean.getId());

        //围观群众的网络请求
        IViewerServices viewerServices = ServiceGenerator.getSingleTon().create(IViewerServices.class);
        Call<ViewerListBean> call = viewerServices.getViewerData(hotLiveBean.getId());
        call.enqueue(new Callback<ViewerListBean>() {
            @Override
            public void onResponse(@NonNull Call<ViewerListBean> call, @NonNull Response<ViewerListBean> response) {
                ViewerListBean body = response.body();
                setViewerData(body != null ? body.getUsers() : null);
            }

            @Override
            public void onFailure(Call<ViewerListBean> call, Throwable t) {
            }
        });
    }

    private void setViewerData(ArrayList<ViewerBean> users) {
        mViewerListAdapter.updateData(users);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_focus, R.id.iv_send, R.id.iv_close, R.id.iv_share, R.id.iv_gift_shop, R.id.iv_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_focus:
                break;
            case R.id.iv_send:
                break;
            case R.id.iv_close:
                FragmentActivity activity = getActivity();
                activity.finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.iv_gift_shop:
                hideBottom();
                //展示礼物商店(使用一个Fragment来展示在线礼物的信息)
                //第一次展示,就用事务来添加进来
                GiftShopFragment giftShopFragment = (GiftShopFragment) getChildFragmentManager().findFragmentByTag(TAG_GIFT_SHOP);
                if (giftShopFragment == null) {
                    giftShopFragment = new GiftShopFragment();
                    getChildFragmentManager().beginTransaction().add(R.id.fl_gift_shop, giftShopFragment, TAG_GIFT_SHOP).commitAllowingStateLoss();
                } else {
                    //不是第一次展示,就直接将先前隐藏的布局重新展示出来即可
                    giftShopFragment.showContent();
                }
                break;
            case R.id.iv_message:
                break;
        }
    }

    private void hideBottom() {
        mRlBottom.setVisibility(View.GONE);
    }

    public boolean onBackPressed() {
        GiftShopFragment fragment = (GiftShopFragment) getChildFragmentManager().findFragmentByTag(TAG_GIFT_SHOP);
        return fragment == null || fragment.onBackPressed();
    }
}
