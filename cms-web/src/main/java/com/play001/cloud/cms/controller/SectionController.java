package com.play001.cloud.cms.controller;

import com.google.gson.Gson;
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
@RequestMapping(value = "/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;
    @Autowired
    private CategoryService categoryService;

    //分类列表
    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    public String categoryList(){
        return "section/section_category_list";
    }

    //栏目列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Integer categoryId, Model model) throws IException {
        model.addAttribute("sectionCategory", sectionService.findCategoryById(categoryId));
        return "section/section_list";
    }
    //显示产品分类列表
    @RequestMapping(value = "/detail/list", method = RequestMethod.GET)
    public String detailList(Integer categoryId, Model model) throws IException {
        model.addAttribute("sectionCategory", sectionService.findCategoryById(categoryId));
        return "section/section_list";
    }
    //修改栏目
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Integer id, Model model) throws IException {
        Section section = sectionService.findById(id);
        Gson gson = new Gson();
        model.addAttribute("categoriesJson", gson.toJson(categoryService.findAll()));
        model.addAttribute("sectionCategoriesJson", gson.toJson(section.getCategories()));
        model.addAttribute("section", section);
        return "section/section_update";
    }
    //创建栏目
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Integer categoryId, Model model) throws IException {
        Gson gson = new Gson();
        model.addAttribute("categoriesJson", gson.toJson(categoryService.findAll()));
        model.addAttribute("sectionCategory", sectionService.findCategoryById(categoryId));
        return "section/section_create";
    }

}
