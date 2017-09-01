package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/31.
 */

import java.util.List;

public class GiftBean {

    /**
     * name : 小黄瓜
     * gold : 1
     * cl : [255,255,255]
     * bundle_ids : [1]
     * image : http://img2.inke.cn/MTUwMDk1MDcwNTg2OSM4MDgjanBn.jpg
     * extra : {"gift_bundle":[{"num":1,"cont":"默认"}]}
     * dyna : 0
     * exp : 10
     * gold_type : 1
     * second_currency : 0
     * type : 1
     * id : 542
     * icon : http://img2.inke.cn/MTUwMDk1MDY5NzI2NSM5NTkjanBn.jpg
     */

    private String name;
    private int gold;
    private String image;
    private ExtraBean extra;
    private int dyna;
    private int exp;
    private int gold_type;
    private int second_currency;
    private int type;
    private int id;
    private String icon;
    private List<Integer> cl;
    private List<Integer> bundle_ids;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public int getDyna() {
        return dyna;
    }

    public void setDyna(int dyna) {
        this.dyna = dyna;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold_type() {
        return gold_type;
    }

    public void setGold_type(int gold_type) {
        this.gold_type = gold_type;
    }

    public int getSecond_currency() {
        return second_currency;
    }

    public void setSecond_currency(int second_currency) {
        this.second_currency = second_currency;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Integer> getCl() {
        return cl;
    }

    public void setCl(List<Integer> cl) {
        this.cl = cl;
    }

    public List<Integer> getBundle_ids() {
        return bundle_ids;
    }

    public void setBundle_ids(List<Integer> bundle_ids) {
        this.bundle_ids = bundle_ids;
    }

    public static class ExtraBean {
        private List<GiftBundleBean> gift_bundle;

        public List<GiftBundleBean> getGift_bundle() {
            return gift_bundle;
        }

        public void setGift_bundle(List<GiftBundleBean> gift_bundle) {
            this.gift_bundle = gift_bundle;
        }

        public static class GiftBundleBean {
            /**
             * num : 1
             * cont : 默认
             */

            private int num;
            private String cont;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getCont() {
                return cont;
            }

            public void setCont(String cont) {
                this.cont = cont;
            }
        }
    }
}
