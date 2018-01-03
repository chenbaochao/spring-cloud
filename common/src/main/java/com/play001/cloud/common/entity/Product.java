package com.play001.cloud.common.entity;

import java.util.List;

/**
 * 产品
 */
public class Product {
    public static final Integer NOT_SHOW = 0;
    public static final Integer SHOW = 1;
    private Long id;
    private String name;//商品名字
    private Long showPrice;//展示价格
    private String title;//商品简介
    private String showPic;//封面
    private String introduction;//商品介绍
    private byte isShow;//是否上架,0否,1是
    private String createdTime;//创建时间
    private Integer quantitySold;//卖出数量
    private List<String> pics;//图片
    private Category category;//分类
    private List<Parameter> parameters;//参数详情
    private List<String> labels;//商品标签
    private List<Specification> specs;//商品类别,套餐
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

    public List<Specification> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Specification> specs) {
        this.specs = specs;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
