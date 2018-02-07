package com.play001.cloud.support.enums;

public enum AdvertEnum{
    /*
     * advert_category_id
     * 1.首页-轮播广告
     * 2.首页-轮播下方
     * 3.首页-分栏广告
     */

    SLIDER_CATEGORY_ID("首页-轮播广告",1),
    UNDER_SLIDER_CATEGORY_ID("首页-轮播下方",2),
    SLIDER_DATA_NUMBER("首页-轮播广告数量",8),
    UNDER_SLIDER_DATA_NUMBER("首页-轮播下方",3);
    private String name;
    private int index;

    AdvertEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public int value(){
        return index;
    }
}
