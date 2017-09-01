package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

import java.io.Serializable;

public class LabelBean implements Serializable {
    String tab_key;

    public LabelBean(String tab_key) {
        this.tab_key = tab_key;
    }

    public String getTab_key() {
        return tab_key;
    }

    public void setTab_key(String tab_key) {
        this.tab_key = tab_key;
    }

    @Override
    public String toString() {
        return "LabelBean{" +
                "tab_key='" + tab_key + '\'' +
                '}';
    }
}
