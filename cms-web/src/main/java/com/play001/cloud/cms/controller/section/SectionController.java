package com.play001.cloud.cms.controller.section;

import com.google.gson.Gson;
import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.CategoryService;
import com.play001.cloud.cms.service.SectionCategoryService;
import com.play001.cloud.cms.service.SectionService;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.Section;
import com.play001.cloud.support.entity.SectionCategory;
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
    private SectionCategoryService sectionCategoryService;
    @Autowired
    private CategoryService categoryService;


    //栏目列表
    @PermissionCode("section_view")
    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public String list(Integer categoryId, Model model) throws IException {
        model.addAttribute("sectionCategory", sectionCategoryService.findCategoryById(categoryId));
        return "section/section_list";
    }


    //修改栏目
    @PermissionCode("section_update")
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
    @PermissionCode("section_create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Integer categoryId, Model model) throws IException {
        Gson gson = new Gson();
        model.addAttribute("categoriesJson", gson.toJson(categoryService.findAll()));
        model.addAttribute("sectionCategory", sectionCategoryService.findCategoryById(categoryId));
        return "section/section_create";
    }

}
