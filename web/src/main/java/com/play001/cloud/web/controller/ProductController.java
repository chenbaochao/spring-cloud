package com.play001.cloud.web.controller;

import com.play001.cloud.web.entity.IException;
import com.play001.cloud.web.service.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @RequestMapping("/detail-{id}")
    public String detail(@PathVariable Long id, Model model) throws IException {
        productService.getDetial(id, model);
        return "product/detail";
    }
}
