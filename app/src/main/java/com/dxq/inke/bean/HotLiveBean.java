package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

public class HotLiveBean implements TypeBean {

    CreatorBean creator;
    ExtraBean extra;
    String id;
    String online_users;
    String room_id;
    String stream_addr;

    public HotLiveBean(CreatorBean creator, ExtraBean extra, String id, String online_users, String room_id, String stream_addr) {
        this.creator = creator;
        this.extra = extra;
        this.id = id;
        this.online_users = online_users;
        this.room_id = room_id;
        this.stream_addr = stream_addr;
    }

    public CreatorBean getCreator() {
        return creator;
    }

    public void setCreator(CreatorBean creator) {
        this.creator = creator;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOnline_users() {
        return online_users;
    }

    public void setOnline_users(String online_users) {
        this.online_users = online_users;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getStream_addr() {
        return stream_addr;
    }

    public void setStream_addr(String stream_addr) {
        this.stream_addr = stream_addr;
    }

    @Override
    public String toString() {
        return "HotLiveBean{" +
                "creator=" + creator +
                ", extra=" + extra +
                ", id='" + id + '\'' +
                ", online_users='" + online_users + '\'' +
                ", room_id='" + room_id + '\'' +
                ", stream_addr='" + stream_addr + '\'' +
                '}';
    }

    @Override
    public int getType() {
        return TYPE_HOT_LIVE_ITEM;
    }
}
