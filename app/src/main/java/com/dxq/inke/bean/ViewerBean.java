package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/31.
 */

public class ViewerBean {

    /**
     * ext : {"light":"http://img2.inke.cn/MTQ4ODg3MTU3NTA4MCM4NTEjanBn.jpg","cl":"#FFE042"}
     * emotion :
     * inke_verify : 0
     * verified : 0
     * description : 111
     * gender : 0
     * profession : Ta好像忘记写职业了
     * sex : 0
     * verified_reason :
     * nick : 哇塞
     * location :
     * birth : 1995-11-19
     * hometown : 火星
     * id : 129202633
     * portrait : http://img2.inke.cn/MTQ4Nzc3MzAyMjYzMyM5NjgjanBn.jpg
     * gmutex : 0
     * third_platform : 0
     * level : 64
     * rank_veri : 9
     * veri_info : 白富美
     */

    private ExtBean ext;
    private String emotion;
    private int inke_verify;
    private int verified;
    private String description;
    private int gender;
    private String profession;
    private int sex;
    private String verified_reason;
    private String nick;
    private String location;
    private String birth;
    private String hometown;
    private int id;
    private String portrait;
    private int gmutex;
    private String third_platform;
    private int level;
    private int rank_veri;
    private String veri_info;

    public ExtBean getExt() {
        return ext;
    }

    public void setExt(ExtBean ext) {
        this.ext = ext;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public int getInke_verify() {
        return inke_verify;
    }

    public void setInke_verify(int inke_verify) {
        this.inke_verify = inke_verify;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getVerified_reason() {
        return verified_reason;
    }

    public void setVerified_reason(String verified_reason) {
        this.verified_reason = verified_reason;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getGmutex() {
        return gmutex;
    }

    public void setGmutex(int gmutex) {
        this.gmutex = gmutex;
    }

    public String getThird_platform() {
        return third_platform;
    }

    public void setThird_platform(String third_platform) {
        this.third_platform = third_platform;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRank_veri() {
        return rank_veri;
    }

    public void setRank_veri(int rank_veri) {
        this.rank_veri = rank_veri;
    }

    public String getVeri_info() {
        return veri_info;
    }

    public void setVeri_info(String veri_info) {
        this.veri_info = veri_info;
    }

    public static class ExtBean {
        /**
         * light : http://img2.inke.cn/MTQ4ODg3MTU3NTA4MCM4NTEjanBn.jpg
         * cl : #FFE042
         */

        private String light;
        private String cl;

        public String getLight() {
            return light;
        }

        public void setLight(String light) {
            this.light = light;
        }

        public String getCl() {
            return cl;
        }

        public void setCl(String cl) {
            this.cl = cl;
        }
    }
}
