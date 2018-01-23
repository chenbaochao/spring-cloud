package com.play001.cloud.cms.controller;

import com.play001.cloud.common.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return "product/add";
    }
}
