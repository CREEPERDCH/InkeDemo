package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/27.
 */

import java.util.List;

public class UserNodesBean {

    private String title;
    private List<UsersBean> users;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }
}
