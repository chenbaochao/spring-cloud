package com.play001.cloud.support.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 首页各种商品栏目
 */
public class Section  implements Serializable {
    public static final Byte INVALID = 0;
    public static final Byte VALID = 1;
    private Integer id;
    @NotNull
    private Integer sort;
    @NotBlank
    private String name;
    @NotNull
    private Byte status;
    @NotNull
    private List<Category> categories;
    private List<Advert> advert;//广告
    //栏目分类
    private SectionCategory sectionCategory;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public SectionCategory getSectionCategory() {
        return sectionCategory;
    }

    public void setSectionCategory(SectionCategory sectionCategory) {
        this.sectionCategory = sectionCategory;
    }
}
