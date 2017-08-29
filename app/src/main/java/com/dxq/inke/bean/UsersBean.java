package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/27.
 */

public class UsersBean implements TypeBean {

    private UserBean user;
    private String reason;
    private Object relation;
    private String live_id;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getRelation() {
        return relation;
    }

    public void setRelation(Object relation) {
        this.relation = relation;
    }

    public String getLive_id() {
        return live_id;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }

    @Override
    public int getType() {
        return TYPE_SEARCH_RECOMMEND;
    }
}
