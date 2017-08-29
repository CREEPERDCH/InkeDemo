package com.dxq.inke.http;
/*
 * Created by CREEPER_D on 2017/8/28.
 */

import com.dxq.inke.utils.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ISearchService {

    @GET(Constant.SEARCH_ALL)
    Call<ResponseBody> getRecommend();

    @GET
    Call<ResponseBody> getSearchResult(@Url String url);
}
