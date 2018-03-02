package com.play001.cloud.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(){
        return "order/order_list";
    }
}
