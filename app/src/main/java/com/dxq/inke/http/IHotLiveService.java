package com.dxq.inke.http;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

import com.dxq.inke.bean.BannerListBean;
import com.dxq.inke.bean.HotLiveListBean;
import com.dxq.inke.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IHotLiveService {

    @GET(Constant.INDEX_LIVE_ALL_DATE)
    Call<HotLiveListBean> getHotAllInfo();

    @GET(Constant.INDEX_BANNER)
    Call<BannerListBean> getBannerInfo();
}
