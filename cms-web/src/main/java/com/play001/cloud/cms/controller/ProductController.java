package com.play001.cloud.cms.controller;

import com.play001.cloud.cms.service.CategoryService;
import com.play001.cloud.common.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private CategoryService categoryService;
    /**
     * 添加
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(){
        return "product/product_create";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "product/product_list";
    }
}
