package com.dxq.inke.http;
/*
 * Created by CREEPER_D on 2017/8/30.
 */

import com.dxq.inke.bean.GiftListBean;
import com.dxq.inke.bean.ViewerBean;
import com.dxq.inke.bean.ViewerListBean;
import com.dxq.inke.utils.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IViewerServices {

    @GET(Constant.GET_ROOM_VIEWERS)
    Call<ViewerListBean> getViewerData(@Query("id") String id);

    @GET(Constant.STATUS_LIVE)
    Call<ResponseBody> getLiveStatus(@Query("id") String id);

    @GET(Constant.GIFT_ALL)
    Call<GiftListBean> getGiftInfo();
}
