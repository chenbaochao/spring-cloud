package com.play001.cloud.cms.controller.section;

import com.google.gson.Gson;
import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.CategoryService;
import com.play001.cloud.cms.service.SectionService;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/section/category")
public class SectionCategoryController {

    @Autowired
    private SectionService sectionService;
    @Autowired
    private CategoryService categoryService;

    //分类列表
    @PermissionCode("section_view")
    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public String categoryList(){
        return "section/section_category_list";
    }
}
