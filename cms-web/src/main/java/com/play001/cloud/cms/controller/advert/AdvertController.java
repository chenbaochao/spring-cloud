package com.play001.cloud.cms.controller.advert;


import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.AdvertCategoryService;
import com.play001.cloud.cms.service.AdvertService;
import com.play001.cloud.cms.service.SectionService;
import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.entity.IException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/advert")
public class AdvertController {

    @Autowired
    private AdvertCategoryService advertCategoryService;
    @Autowired
    private AdvertService advertService;
    @Autowired
    private SectionService sectionService;


    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public String list(Model model, Integer categoryId) throws IException {
        model.addAttribute("advertCategory", advertCategoryService.findById(categoryId));
        return "advert/advert_list";
    }

    //编辑页面
    @PermissionCode("advert_update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, Integer id) throws IException {
        Advert advert = advertService.findById(id);
        model.addAttribute("advert", advert);
        //categoryId为3表示分栏广告, 需要将分栏列出供以选择
        if(advert.getAdvertCategory().getId() == 3){
            //section只有2才有显示广告的功能
            model.addAttribute("sections", sectionService.listByCategory(2));;
        }
        return "advert/advert_update";
    }

    //新增
    @PermissionCode("advert_create")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, Integer categoryId) throws IException {
        model.addAttribute("advertCategory", advertCategoryService.findById(categoryId));
        //categoryId为3表示分栏广告, 需要将分栏列出供以选择
        if(categoryId == 3){
            model.addAttribute("sections", sectionService.listByCategory(2));;
        }
        return "advert/advert_add";
    }
}
