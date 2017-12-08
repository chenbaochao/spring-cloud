package com.play001.cloud.productapi.entity;

import java.util.List;

public class Product {
    public static final Integer NOT_SHOW = 0;
    public static final Integer SHOW = 1;
    private Long id;
    private String name;//商品名字
    private Long showPrice;
    private String introduction;//商品简介
    private String showPic;//封面
    private String pageTitle;//商品页面标题
    private String pageIntroduction;//商品介绍
    private byte isShow;//是否上架,0否,1是
    private String createdTime;//创建时间

    private List<Parameter> parameters;//参数详情
    private List<String> labels;//商品标签
    private String remarks;//备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(Long showPrice) {
        this.showPrice = showPrice;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageIntroduction() {
        return pageIntroduction;
    }

    public void setPageIntroduction(String pageIntroduction) {
        this.pageIntroduction = pageIntroduction;
    }

    public byte getIsShow() {
        return isShow;
    }

    public void setIsShow(byte isShow) {
        this.isShow = isShow;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
