package com.play001.cloud.cms.controller;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/order")
public class OrderController {


    @PermissionCode("order_view")
    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public String list(){
        return "order/order_list";
    }
}
