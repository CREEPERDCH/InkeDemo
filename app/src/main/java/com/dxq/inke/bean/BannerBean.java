package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

public class BannerBean {

    private String image;
    private String link;
    private int atom;

    public BannerBean() {
    }

    public BannerBean(String image, String link, int atom) {
        this.image = image;
        this.link = link;
        this.atom = atom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getAtom() {
        return atom;
    }

    public void setAtom(int atom) {
        this.atom = atom;
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", atom=" + atom +
                '}';
    }
}

