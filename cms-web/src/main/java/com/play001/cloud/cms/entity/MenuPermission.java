package com.play001.cloud.cms.entity;

import com.play001.cloud.common.entity.Menu;

//菜单权限
public class MenuPermission {
    private Menu menu;
    private Byte flag;//有权限为1，没有为0

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }
}
