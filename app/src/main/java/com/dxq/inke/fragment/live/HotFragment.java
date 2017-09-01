package com.dxq.inke.fragment.live;
/*
 * Created by CREEPER_D on 2017/8/25.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dxq.inke.R;
import com.dxq.inke.activity.LiveShowActivity;
import com.dxq.inke.adapter.live.HotListAdapter;
import com.dxq.inke.adapter.live.base.MyBaseAdapter;
import com.dxq.inke.bean.BannerListBean;
import com.dxq.inke.bean.HotLiveBean;
import com.dxq.inke.bean.HotLiveListBean;
import com.dxq.inke.bean.TypeBean;
import com.dxq.inke.fragment.LogFragment;
import com.dxq.inke.http.IHotLiveService;
import com.dxq.inke.http.ServiceGenerator;
import com.dxq.inke.utils.ImageUtils;
import com.dxq.inke.widget.refresh.InkeXRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotFragment extends LogFragment {
    @BindView(R.id.recyclerView)
    InkeXRecyclerView mRecyclerView;
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
        initListener();
        requestData();
        requestBannerData();

        mHotListAdapter.setOnItemClickListener(new MyBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), LiveShowActivity.class);
                //get datas from adapter
                ArrayList<TypeBean> data = mHotListAdapter.getData();
                //just need ups data
                ArrayList<HotLiveBean> liveList = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    TypeBean typeBean = data.get(i);
                    if (typeBean.getType() == TypeBean.TYPE_HOT_LIVE_ITEM) {
                        liveList.add((HotLiveBean) typeBean);
                    }
                }
                intent.putExtra("LIVE_LIST", liveList);
                //1.need to putExtra click item
                //2.consider banner's position,-1
                intent.putExtra("LIVE_INDEX", position - 1);
                startActivity(intent);
            }
        });
    }

    private void initListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        //ImageLoader Pausing
                        ImageUtils.getSingleTon().pause();
                        break;
                    case RecyclerView.SCROLL_STATE_IDLE:
                        //ImageLoader Resume
                        ImageUtils.getSingleTon().restart();
                        break;
                }
            }
        });
        // loading more enabled
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setLoadingListener(new InkeXRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                requestData();
                requestBannerData();
            }

            @Override
            public void onLoadMore() {
                //todo loading more
            }
        });
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
                mRecyclerView.refreshComplete();
                HotLiveListBean body = response.body();
                ArrayList<HotLiveBean> lives = body != null ? body.getLives() : null;
                mHotListAdapter.updateData(lives);
            }

            @Override
            public void onFailure(@NonNull Call<HotLiveListBean> call, @NonNull Throwable t) {
                mRecyclerView.refreshComplete();
            }
        });
    }

    private void requestBannerData() {
        IHotLiveService hotLiveService = ServiceGenerator.getSingleTon().create(IHotLiveService.class);
        Call<BannerListBean> call = hotLiveService.getBannerInfo();
        call.enqueue(new Callback<BannerListBean>() {
            @Override
            public void onResponse(@NonNull Call<BannerListBean> call, @NonNull Response<BannerListBean> response) {
                BannerListBean body = response.body();
                mHotListAdapter.updateBannerData(body);
            }

            @Override
            public void onFailure(@NonNull Call<BannerListBean> call, @NonNull Throwable t) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
