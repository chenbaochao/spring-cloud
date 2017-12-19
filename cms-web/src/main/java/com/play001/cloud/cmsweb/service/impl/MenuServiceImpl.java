package com.play001.cloud.cmsweb.service.impl;


import com.play001.cloud.common.entity.Menu;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.cmsweb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl {

    @Autowired
    private MenuService menuService;

    public List<Menu> getMenus() throws Exception {
        Response<List<Menu>> result = menuService.getMenus();
        if(!Response.SUCCESS.equals(result.getStatus())) throw new Exception("出错了,重新试试?");
        List<Menu> menus = result.getMessage();
        return menus;
    }
}
