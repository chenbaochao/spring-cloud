package com.play001.cloud.common.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 导航栏
 */
public class NavigationBar {


    private Integer id;
    @NotBlank(message = "名称长度错误")
    private String name;
    @NotBlank(message = "请选择打开方式")
    private String target;
    @NotNull(message = "请输入排序序号")
    private Integer sort;
    @NotBlank(message = "链接不能为空")
    private String href;
    @NotNull(message = "状态错误")
    private Boolean status;
    private String createTime;

    private String remarks;
    //导航
    @NotNull(message = "所属导航错误")
    private Navigation navigation;

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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }
}
