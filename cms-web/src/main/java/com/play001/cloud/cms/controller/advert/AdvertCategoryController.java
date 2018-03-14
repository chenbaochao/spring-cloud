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
@RequestMapping(value = "/advert/category")
public class AdvertCategoryController {

    @Autowired
    private AdvertCategoryService advertCategoryService;

    @PermissionCode("advert_view")
    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public String categoryList(){
        return "advert/advert_category_list";
    }

/*    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Integer categoryId) throws IException {
        model.addAttribute("advertCategory", advertCategoryService.findById(categoryId));
        return "advert/advert_list";
    }*/


}
