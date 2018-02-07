package com.play001.cloud.support.entity;

import java.io.Serializable;

/**
 * 广告
 */
public class Advert implements Serializable{
    private Integer id;
    /**
     * 广告分类ID,共同属性
     */
    private Integer advertCategoryId;
    /**
     * 首页sectionID,部分属性
     */
    private Integer sectionId;
    private String title;
    private Integer sort;
    private String href;
    private String showPic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdvertCategoryId() {
        return advertCategoryId;
    }

    public void setAdvertCategoryId(Integer advertCategoryId) {
        this.advertCategoryId = advertCategoryId;
    }


    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic;
    }
}
