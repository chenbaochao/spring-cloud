package com.play001.cloud.cmsweb.service;


import com.play001.cloud.cmsweb.entity.Menu;
import com.play001.cloud.cmsweb.entity.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("ZUUL")
public interface MenuService {

    @RequestMapping("/cms/menu/getMenus")
    Response<List<Menu>> getMenus();
}
