package com.dxq.inke.utils;

/* 
 * ============================================================
 * Editor: MuMuXuan(6511631@qq.com)
 *  
 * Time: 2017-04-27 15:18 
 * 
 * Description: 
 *
 * Version: 1.0
 * ============================================================
 */

import android.util.Log;

public class Constant {
    /**
     * websocket服务器地址
     */
//    public static final String WSURL = "ws://192.168.191.1:8080/websocket";
    public static final String WSURL = "ws://47.93.30.78:8080/WebSocketYK/websocket";

    public static final String BASE_URL_IP = "http://218.11.0.112/";
    //热门页面轮播图
    public static final String INDEX_BANNER = "api/live/ticker";
    //热门页面的所有数据
    public static final String INDEX_LIVE_ALL_DATE = "api/live/simpleall?uid=260095067";
    //主播的在线状态查询
    public static final String STATUS_LIVE = "/api/live/stat";
    //附近
    public static final String NEAR_ALL_DATE = "api/live/near_recommend?lc=3000000000030074&cv=IK3.4.00_Android&cc=TG36109&ua=HTCm7spr&uid=260095067&sid=20Lei1rsdSUzDPBi1r34vRhZYR4Rf0E54Ii2HnLOtj4Nj2Y1i0ti136&devi=990004283876450&imsi=&imei=990004283876450&icc=&conn=WIFI&vv=1.0.3-201610121413.android&aid=286a0f38f1bb27b1&osversion=android_23&mtid=4f7e5427b4778d3eeb583ae00e5368bc&mtxid=b0d59d2abf96&proto=4&smid=DuEyeb0b%2Fi60x6MRg46gR0SOfjbNSrk%2F%2FyoE6lSUx4xRN7XvMfzXo30xPPuw47w8hkpKvTVkqAVkPz2Lvf6P0sGw&longitude=113.3752&latitude=23.132093&interest=0&s_sg=3e701fc6e3fc96e04d94c6a3109572dd&s_sc=100&s_st=1479115432";
    //关键词主播房间搜索
    public static final String SEARCH_KEYWORD = "api/user/search?mtxid=FCAA14EFE150&devi=864394102521707&uid=278794547&sid=20apIKRuUve4iOLo7oSi2i0Coi2ZKfLDhW6OzONeysOV8i2cQFaIWk&conn=WIFI&imei=864394102521707&mtid=0acd9551fb5aa92442069df37393691c&aid=FCAA14EFE1500000&cv=IK3.4.20_Android&smid=DuNRd%2FUxsbeK1L9tQw0TNRvGvuiTGcC08%2FFDr9KEdjdfkXbds8BheQpVHHOYmMkTErodalrJMOW1OTVu4Hamaemw&osversion=android_19&proto=4&logid=&vv=1.0.3-201610291749.android&icc=89860081012521701423&ua=LENOVOLenovoS898t%2B&lc=3000000000034000&imsi=460072521701423&cc=GF10000&count=10&start=0&keyword=_@keyword@_&r_c=1215378052&s_sg=5372685bd13f113826e5f18004f2daf5&s_sc=100&s_st=1478678705";
    //搜索所有
    public static final String SEARCH_ALL = "api/recommend/aggregate";
    //礼物商店
    public static final String GIFT_ALL = "api/resource/gift_info?type=2";

    //获得房间内的所有观众头像等信息
    public static final String GET_ROOM_VIEWERS = "api/live/users";

//    public static String getScaledImgUrl(String imgUrl, int width, int height) {
////        图片地址抓包数据示例
//// http://image.scale.inke.com/imageproxy2/dimgm/scaleImage?url=http://img2.inke.cn/MTQ5MjU2MzgwNzQxNiMxMzcjanBn.jpg&w=100&h=100&s=80&c=0&o=0
//        String scaleUrl = "http://image.scale.inke.com/imageproxy2/dimgm/scaleImage?url=%1s&w=%2s&h=%3s&s=80";
//        imgUrl = getEscapeImgUrl(imgUrl);
//        Log.e("xmg", "getScaledImgUrl: " + "" + imgUrl);
//        String format = String.format(scaleUrl, imgUrl, String.valueOf(width), String.valueOf(height));
//        Log.e("xmg", "getScaledImgUrl: " + "" + format);
//        format = getEscapeImgUrl(format);
//        return format;
//    }

    //对Url中的特殊符号做转义，规避加载图片时因为特殊符号不识别而失败
//    /	    分隔目录和子目录	        %2F（忽略）
//    ? 	分隔实际的URL和参数	        %3F（忽略）
//    % 	指定特殊字符	            %25（忽略）
//    +     URL中+号表示空格	        %2B
//    # 	表示书签	                %23
//    & 	URL中指定的参数间的分隔符	%26
//    = 	URL中指定参数的值	        %3D
//    * 	URL中指定参数的值	        %2A
    public static String getEscapeImgUrl(String imgUrl) {
        if (!imgUrl.contains("http:")) {
            imgUrl = "http://img2.inke.cn/" + imgUrl;
        }
        imgUrl = imgUrl.replaceAll("\\+", "%2B")
                .replaceAll("\\=", "%3D")
                .replaceAll("\\=", "%2A")
                .replaceAll("\\#", "%23");
        return imgUrl;
    }

    public static String getSearchKeyword(String parms) {
        return SEARCH_KEYWORD.replaceAll("_@keyword@_", parms);
    }

}
