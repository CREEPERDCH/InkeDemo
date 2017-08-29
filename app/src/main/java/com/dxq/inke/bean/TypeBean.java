package com.dxq.inke.bean;
/*
 * Created by CREEPER_D on 2017/8/26.
 */

public interface TypeBean {
    //返回的int值代表JavaBean对应的类型
    int getType();

    //代表轮播图类型
    int TYPE_BANNER = 0;
    //代表展示主播信息的item
    int TYPE_HOT_LIVE_ITEM = 1;
    //今日推荐类型
    int TYPE_SEARCH_RECOMMEND = 2;
    //今日推荐 标题
    int TYPE_RECOMMEND_TITLE = 3;
    //好声音、游戏达人，用户分组类型
    int TYPE_SEARCH_ANCHOR_TYPES = 4;

    //还有其他页面的列表
    //...type
    //...type
}
