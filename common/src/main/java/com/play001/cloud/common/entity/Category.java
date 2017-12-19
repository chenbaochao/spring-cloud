package com.play001.cloud.common.entity;

/**
 * 产品分类
 */
public class Category {
    private Integer id;
    private String name;
    private Integer sort;
    //是否显示在分类中
    private byte status;
    //是否显示在导航栏
    private byte showInNav;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getShowInNav() {
        return showInNav;
    }

    public void setShowInNav(byte showInNav) {
        this.showInNav = showInNav;
    }
}