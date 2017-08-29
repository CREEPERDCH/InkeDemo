package com.dxq.inke.http;
/*
 * Created by CREEPER_D on 2017/8/26.
 * 快速生成retrofit对应请求的接口的实例
 */

import com.dxq.inke.utils.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static volatile ServiceGenerator sInstance;

    private final Retrofit mRetrofit;

    public static ServiceGenerator getSingleTon() {
        if (sInstance == null) {
            synchronized (ServiceGenerator.class) {
                if (sInstance == null) {
                    sInstance = new ServiceGenerator();
                }
            }
        }
        return sInstance;
    }

    public ServiceGenerator() {
        //别忘了添加转换
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL_IP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //使用泛型来创建对应的接口实例
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
