package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/27.
 */


import java.util.List;

public class LiveNodesBean implements TypeBean {

    private String title;
    private List<HotLiveBean> lives;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HotLiveBean> getLives() {
        return lives;
    }

    public void setLives(List<HotLiveBean> lives) {
        this.lives = lives;
    }

    @Override
    public int getType() {
        return TYPE_SEARCH_ANCHOR_TYPES;
    }
}