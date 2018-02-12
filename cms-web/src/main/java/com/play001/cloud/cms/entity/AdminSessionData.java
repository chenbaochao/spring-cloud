package com.play001.cloud.cms.entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 存放于session的数据
 */
public class AdminSessionData implements Serializable {
    private Integer id;
    private String username;
    private Role role;
    private String realName;
    /**
     * 菜单code对应权限true or false
     */
    private HashMap<String, Boolean> permission;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public HashMap<String, Boolean> getPermission() {
        return permission;
    }

    public void setPermission(HashMap<String, Boolean> permission) {
        this.permission = permission;
    }
}
