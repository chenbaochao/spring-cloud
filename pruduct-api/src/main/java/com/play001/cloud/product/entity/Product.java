package com.play001.cloud.product.entity;

import java.util.List;

public class Product {
    public static final Integer NOT_SHOW = 0;
    public static final Integer SHOW = 1;
    private Long id;
    private String name;//商品名字
    private Long showPrice;
    private String introduction;//商品简介
    private String showPic;//封面
    private String title;//商品页面标题
    private String description;//商品介绍
    private Integer isShow;//是否上架,0否,1是
    private String createdTime;//创建时间

    private List<Parameter> parameters;//参数详情
    private List<String> labels;//商品标签
    private String remarks;//备注



}
