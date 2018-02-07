package com.play001.cloud.support.enums;

public enum RecommendEnum {

    STAR_ID(1);//首页-明星产品
    private int id;

    RecommendEnum(int id) {
        this.id = id;
    }
    public int value(){
        return id;
    }
}
