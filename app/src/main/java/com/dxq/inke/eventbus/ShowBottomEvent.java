package com.dxq.inke.eventbus;
/*
 * Created by CREEPER_D on 2017/8/31.
 */

public class ShowBottomEvent {

    boolean isShow;

    public ShowBottomEvent(boolean isShow) {
        this.isShow = isShow;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
