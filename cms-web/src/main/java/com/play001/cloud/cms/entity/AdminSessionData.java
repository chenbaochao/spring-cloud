package com.play001.cloud.cms.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 存放于session的数据
 */
public class AdminSessionData implements Serializable {
    private Integer id;
    private String username;
    private Role role;
    private String realName;

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


    /**
     * 判断是否有权限
     * @param menuCode 标志
     */
    public boolean hasPermission(String menuCode){
        System.out.println("menuCode="+menuCode);
        if(role == null) return false;
        List<MenuPermission> menuPermissions = role.getPermissions();
        if(menuPermissions == null || menuPermissions.size() == 0) return false;
        for(MenuPermission menuPermission : menuPermissions){
            if(menuPermission.getMenu().getCode().equals(menuCode)){
                return menuPermission.getFlag().equals((byte)1);
            }
        }
        return false;
    }
}
