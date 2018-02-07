package com.play001.cloud.support.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 导航
 */
public class Navigation  implements Serializable {
    @NotNull
    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    private Boolean status;
    private String createTime;
    @NotNull
    private String remarks;
    //导航栏
    private List<NavigationBar> navigationBars;

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

    public List<NavigationBar> getNavigationBars() {
        return navigationBars;
    }

    public void setNavigationBars(List<NavigationBar> navigationBars) {
        this.navigationBars = navigationBars;
    }
}
