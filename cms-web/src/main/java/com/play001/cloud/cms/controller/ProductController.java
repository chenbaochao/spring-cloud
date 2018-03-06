package com.play001.cloud.cms.controller;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.CategoryService;
import com.play001.cloud.cms.service.ProductService;
import com.play001.cloud.support.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    /**
     * 添加
     */
    @PermissionCode("product_create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "product/product_create";
    }

    /**
     * 列表
     */
    @PermissionCode("product_view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "product/product_list";
    }
    /**
     * 更新
     */
    @PermissionCode("product_update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, Long id){
        model.addAttribute("categories", categoryService.findAll());
        Product product = productService.findById(id);
        model.addAttribute("product1", product);
        model.addAttribute("productJson", product.toJson());
        return "product/product_update";
    }
}
