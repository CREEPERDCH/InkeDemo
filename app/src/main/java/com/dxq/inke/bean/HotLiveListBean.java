package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

import java.util.ArrayList;

public class HotLiveListBean {

    ArrayList<HotLiveBean> lives;

    public HotLiveListBean(ArrayList<HotLiveBean> lives) {
        this.lives = lives;
    }

    public ArrayList<HotLiveBean> getLives() {
        return lives;
    }

    public void setLives(ArrayList<HotLiveBean> lives) {
        this.lives = lives;
    }

    @Override
    public String toString() {
        return "HotLiveListBean{" +
                "lives=" + lives +
                '}';
    }
}
