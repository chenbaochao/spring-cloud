package com.play001.cloud.cms.api.section;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.SectionCategoryService;
import com.play001.cloud.cms.service.SectionService;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.Section;
import com.play001.cloud.support.entity.SectionCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/section/category")
public class SectionCategoryRestController {

    @Autowired
    private SectionCategoryService sectionCategoryService;

    //分类列表
    @PermissionCode("section_view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<SectionCategory> getCategoryList(){
        return sectionCategoryService.getCategoryList();
    }
}
