package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class ExtraBean implements Serializable {
    ArrayList<LabelBean> label;

    public ExtraBean(ArrayList<LabelBean> label) {
        this.label = label;
    }

    public ArrayList<LabelBean> getLabel() {
        return label;
    }

    public void setLabel(ArrayList<LabelBean> label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ExtraBean{" +
                "label=" + label +
                '}';
    }
}
