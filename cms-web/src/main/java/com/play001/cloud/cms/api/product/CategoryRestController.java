package com.play001.cloud.cms.api.product;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.CategoryService;
import com.play001.cloud.support.entity.Category;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product/category")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @PermissionCode("category_view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Category> getList(){
        return categoryService.findAll();
    }

    /**
     * 设置状态
     */
    @PermissionCode("category_update")
    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> setStatus(@PathVariable Integer id, @PathVariable Byte status){
        return categoryService.setStatus(id, status);
    }
    /**
     * 删除
     */
    @PermissionCode("category_delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> delete(@PathVariable Integer id){
        return categoryService.delete(id);
    }
    /**
     * update
     */

    @PermissionCode("category_update")
    @RequestMapping(method = RequestMethod.PUT)
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
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Integer> create(@Valid Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        return categoryService.create(category);
    }
}
