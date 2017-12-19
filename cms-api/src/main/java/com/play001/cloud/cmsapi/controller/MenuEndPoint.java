package com.play001.cloud.cmsapi.controller;

import com.play001.cloud.common.entity.Menu;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.cmsapi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuEndPoint {


    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/getMenus", method = RequestMethod.GET)
    public Response<List<Menu>> getAllMenu(){
        List<Menu> menus = menuService.getMenus();
        Response<List<Menu>> response = new Response<>(menus);
        return response;
    }
}
