package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.MenuMapper;
import com.play001.cloud.support.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final Integer TOP_MENU_ID = 1;

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getMenusByRoleId(Integer roleId){
        return menuMapper.getMenusByRoleId(roleId,TOP_MENU_ID);
    }

    public List<Menu> getAllMenus(){
        return menuMapper.findAll(TOP_MENU_ID);
    }
}
