package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/27.
 */

import java.util.List;

public class SearchListBean {

    private int dm_error;
    private String error_msg;
    private List<LiveNodesBean> live_nodes;
    private List<UserNodesBean> user_nodes;

    public int getDm_error() {
        return dm_error;
    }

    public void setDm_error(int dm_error) {
        this.dm_error = dm_error;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public List<LiveNodesBean> getLive_nodes() {
        return live_nodes;
    }

    public void setLive_nodes(List<LiveNodesBean> live_nodes) {
        this.live_nodes = live_nodes;
    }

    public List<UserNodesBean> getUser_nodes() {
        return user_nodes;
    }

    public void setUser_nodes(List<UserNodesBean> user_nodes) {
        this.user_nodes = user_nodes;
    }
}
