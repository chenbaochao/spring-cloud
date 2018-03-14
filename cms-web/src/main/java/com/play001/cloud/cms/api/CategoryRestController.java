package com.play001.cloud.cms.api;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.CategoryService;
import com.play001.cloud.support.entity.Category;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @PermissionCode("category_view")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Category> getList(){
        return categoryService.findAll();
    }

    /**
     * 设置状态
     */
    @PermissionCode("category_update")
    @RequestMapping(value = "/setStatus", method = RequestMethod.POST)
    public ResponseEntity<Integer> setStatus(Integer id, Byte status){
        return categoryService.setStatus(id, status);
    }
    /**
     * 删除
     */
    @PermissionCode("category_delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> delete(Integer id){
        return categoryService.delete(id);
    }
    /**
     * update
     */

    @PermissionCode("category_update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(@Valid Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        return categoryService.update(category);
    }
    /**
     * 创建
     */
    @PermissionCode("category_create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Integer> create(@Valid Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        return categoryService.create(category);
    }
}