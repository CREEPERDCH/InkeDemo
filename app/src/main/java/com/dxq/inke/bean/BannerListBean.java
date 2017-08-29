package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

import java.util.ArrayList;

public class BannerListBean implements TypeBean {
    ArrayList<BannerBean> ticker;

    public BannerListBean(ArrayList<BannerBean> ticker) {
        this.ticker = ticker;
    }

    public ArrayList<BannerBean> getTicker() {
        return ticker;
    }

    public void setTicker(ArrayList<BannerBean> ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "BannerListBean{" +
                "ticker=" + ticker +
                '}';
    }

    @Override
    public int getType() {
        return TYPE_BANNER;
    }
}
