package com.play001.cloud.cmsweb.entity;

/**
 * 用户组/角色
 */
public class Role {
    private Integer id;
    private String name;

    /**
     * 0冻结,1启用
     */
    private byte status;
    /**
     * 备注,描述
     */
    private String remarks;

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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
