package com.play001.cloud.cms.api.section;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.SectionService;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.Section;
import com.play001.cloud.support.entity.SectionCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/section")
public class SectionRestController {

    @Autowired
    private SectionService sectionService;


    //栏目列表
    @PermissionCode("section_view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Section> getList(Integer sectionCategoryId){
        return sectionService.getList(sectionCategoryId);
    }


    //更新
    @PermissionCode("section_update")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Integer> update(@Valid @RequestBody Section section, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数不完整");
        }
        return sectionService.update(section);
    }
    //添加
    @PermissionCode("section_create")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Integer> create(@Valid @RequestBody Section section, BindingResult result ){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数不完整");
        }
        return sectionService.create(section);
    }
    //删除
    @PermissionCode("section_delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> delete(Integer id){
        return sectionService.delete(id);
    }


    //启用/禁用
    @PermissionCode("section_update")
    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.POST)
    public ResponseEntity<Integer> setValid(@PathVariable Integer id, @PathVariable Byte status){
        return sectionService.setStatus(id, status);
    }
}
