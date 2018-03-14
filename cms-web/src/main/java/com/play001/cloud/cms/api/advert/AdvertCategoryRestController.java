package com.play001.cloud.cms.api.advert;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.AdvertCategoryService;
import com.play001.cloud.cms.service.AdvertService;
import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.entity.AdvertCategory;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/advert/category")
public class AdvertCategoryRestController {

    @Autowired
    private AdvertCategoryService advertCategoryService;

    //广告分类列表
    @PermissionCode("advert_view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public  List<AdvertCategory> getCategoryList(){
        return advertCategoryService.findAll();
    }

}
