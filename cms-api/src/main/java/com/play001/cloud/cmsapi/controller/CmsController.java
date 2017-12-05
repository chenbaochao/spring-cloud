package com.play001.cloud.cmsapi.controller;

import com.play001.cloud.cmsapi.entity.Menu;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CmsController {

    @RequestMapping(value = "getAllMenu", method = RequestMethod.GET)
    public List<Menu> getAllMenu(){
        List<Menu> menus = null;

        return menus;
    }

}
