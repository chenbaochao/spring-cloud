package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.MenuMapper;
import com.play001.cloud.common.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getMenus(){
        return menuMapper.getMenus();
    }
}
