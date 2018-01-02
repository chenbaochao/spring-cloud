package com.play001.cloud.web.controller;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.web.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @Autowired
    private CategoryServiceImpl categoryService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) throws IException {
        model.addAttribute("categories", categoryService.findAllWithProduct());
        return "webfront/index";
    }
}
