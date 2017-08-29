package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

public class CreatorBean {

    String nick;//昵称 主播名字
    String portrait;//形象照图片地址
    String id;

    public CreatorBean(String nick, String portrait, String id) {
        this.nick = nick;
        this.portrait = portrait;
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreatorBean{" +
                "nick='" + nick + '\'' +
                ", portrait='" + portrait + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
