package com.play001.cloud.cms.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 用户组/角色
 */
public class Role implements Serializable{

    private Integer id;
    @NotBlank
    private String name;
    /**
     * 0冻结,1启用
     */
    @NotNull
    @Max(value = 1)
    @Min(value = 0)
    private Byte status;
    /**
     * 备注,描述
     */
    private String remarks;

    /**
     * 权限
     */
    private List<MenuPermission> permissions;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<MenuPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<MenuPermission> permissions) {
        this.permissions = permissions;
    }


}
