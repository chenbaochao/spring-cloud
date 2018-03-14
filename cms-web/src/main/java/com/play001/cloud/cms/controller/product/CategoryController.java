package com.play001.cloud.cms.controller.product;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.CategoryService;
import com.play001.cloud.support.entity.IException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类列表
     */
    @PermissionCode("category_view")
    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public String list(){
        return "category/category_list";
    }

    /**
     * 编辑更新
     */
    @PermissionCode("category_update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, Integer id) throws IException {
        model.addAttribute("category", categoryService.findById(id));
        return "category/category_update";
    }
    /**
     * 创建
     */
    @PermissionCode("category_create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(){
        return "category/category_create";
    }
}
