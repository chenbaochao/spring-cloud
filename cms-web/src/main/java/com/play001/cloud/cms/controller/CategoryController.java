package com.play001.cloud.cms.controller;

import com.play001.cloud.cms.service.CategoryService;
import com.play001.cloud.common.entity.Category;
import com.play001.cloud.common.entity.IException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(){
        return "category/category_list";
    }

    /**
     * 编辑更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, Integer id) throws IException {
        model.addAttribute("category", categoryService.findById(id));
        return "category/category_update";
    }
    /**
     * 创建
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(){
        return "category/category_create";
    }
}
