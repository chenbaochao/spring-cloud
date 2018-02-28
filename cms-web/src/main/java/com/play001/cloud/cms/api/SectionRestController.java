package com.play001.cloud.cms.api;

import com.play001.cloud.cms.service.SectionService;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.Section;
import com.play001.cloud.support.entity.user.SectionCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/section")
public class SectionRestController {

    @Autowired
    private SectionService sectionService;

    //分类列表
    @RequestMapping(value = "/category/getList", method = RequestMethod.GET)
    public List<SectionCategory> getCategoryList(){
        return sectionService.getCategoryList();
    }

    //栏目列表
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Section> getList(Integer sectionCategoryId){
        return sectionService.getList(sectionCategoryId);
    }


    //更新
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(@Valid @RequestBody Section section, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数不完整");
        }
        return sectionService.update(section);
    }
    //添加
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Integer> create(@Valid @RequestBody Section section, BindingResult result ){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数不完整");
        }
        return sectionService.create(section);
    }
    //删除
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> delete(Integer id){
        return sectionService.delete(id);
    }
    //禁用
    @RequestMapping(value = "/setInvalid", method = RequestMethod.POST)
    public ResponseEntity<Integer> setInvalid(Integer id){
        return sectionService.setStatus(id, Section.INVALID);
    }
    //启用
    @RequestMapping(value = "/setValid", method = RequestMethod.POST)
    public ResponseEntity<Integer> setValid(Integer id){
        return sectionService.setStatus(id, Section.VALID);
    }
}
