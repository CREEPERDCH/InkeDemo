package com.dxq.inke.fragment.live;
/*
 * Created by CREEPER_D on 2017/8/25.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxq.inke.R;
import com.dxq.inke.adapter.live.HotListAdapter;
import com.dxq.inke.bean.BannerListBean;
import com.dxq.inke.bean.HotLiveBean;
import com.dxq.inke.bean.HotLiveListBean;
import com.dxq.inke.bean.TypeBean;
import com.dxq.inke.fragment.LogFragment;
import com.dxq.inke.http.IHotLiveService;
import com.dxq.inke.http.ServiceGenerator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotFragment extends LogFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private HotListAdapter mHotListAdapter;

    @Override
    public View onChildCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_live_hot, container, false);
        unbinder = ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    protected void initData() {
        mHotListAdapter = new HotListAdapter(new ArrayList<TypeBean>());
        mRecyclerView.setAdapter(mHotListAdapter);
        requestData();
        requestBannerData();
    }

    /**
     * 1.新建一个接口interface,在里面声明一些get请求方法(post请求方法)
     * 2.创建一个retrofit对象
     * 3.生成一个接口的实例
     * 4.调用接口实例中的请求方法 得到Call
     * 5.OKHttp
     * 6.给Adapter中的数据进行刷新一下
     */
    private void requestData() {
        IHotLiveService hotLiveService = ServiceGenerator.getSingleTon().create(IHotLiveService.class);
        Call<HotLiveListBean> call = hotLiveService.getHotAllInfo();
        call.enqueue(new Callback<HotLiveListBean>() {
            @Override
            public void onResponse(@NonNull Call<HotLiveListBean> call, @NonNull Response<HotLiveListBean> response) {
                HotLiveListBean body = response.body();
                ArrayList<HotLiveBean> lives = body != null ? body.getLives() : null;
                mHotListAdapter.updateData(lives);
            }

            @Override
            public void onFailure(Call<HotLiveListBean> call, Throwable t) {
            }
        });
    }

    private void requestBannerData() {
        IHotLiveService hotLiveService = ServiceGenerator.getSingleTon().create(IHotLiveService.class);
        Call<BannerListBean> call = hotLiveService.getBannerInfo();
        call.enqueue(new Callback<BannerListBean>() {
            @Override
            public void onResponse(Call<BannerListBean> call, Response<BannerListBean> response) {
                BannerListBean body = response.body();
                mHotListAdapter.updateBannerData(body);
            }

            @Override
            public void onFailure(Call<BannerListBean> call, Throwable t) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
