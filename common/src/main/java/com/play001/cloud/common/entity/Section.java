package com.play001.cloud.common.entity;

import java.util.List;

/**
 * 首页各种商品栏目
 */
public class Section {
    private Integer id;
    private Integer status;
    private Integer sort;
    private byte isHot;//是否显示热门
    private String name;
    private List<Category> categories;
    private List<Advert> advert;//广告
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public byte getIsHot() {
        return isHot;
    }

    public void setIsHot(byte isHot) {
        this.isHot = isHot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Advert> getAdvert() {
        return advert;
    }

    public void setAdvert(List<Advert> advert) {
        this.advert = advert;
    }
}
