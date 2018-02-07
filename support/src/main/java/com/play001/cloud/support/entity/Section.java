package com.play001.cloud.support.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 首页各种商品栏目
 */
public class Section  implements Serializable {
    private Integer id;
    private Integer sort;
    private String name;
    private Boolean status;
    private List<Category> categories;
    private List<Advert> advert;//广告

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
