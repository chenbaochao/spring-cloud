package com.play001.cloud.support.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 产品分类
 */
public class Category  implements Serializable {
    private Integer id;
    @Length(min = 2, max = 20, message = "类目名称长度错误")
    @NotBlank(message = "类目名称长度错误")
    private String name;
    @NotNull(message = "排序错误")
    private Integer sort;
    //是否显示在分类中
    @Max(value = 1, message = "状态只能为1或者0")
    @Min(value = 0, message = "状态只能为1或者0")
    @NotNull(message = "状态错误")
    private Byte status;
    //是否显示在导航栏首页顶部
    @Max(value = 1, message = "是否顶部只能为1或者0")
    @Min(value = 0, message = "是否顶部只能为1或者0")
    @NotNull(message = "是否顶部只能为1或者0")
    private Byte showInTop;
    //有时可能会需要
    private List<Product> products;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getShowInTop() {
        return showInTop;
    }

    public void setShowInTop(Byte showInTop) {
        this.showInTop = showInTop;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}