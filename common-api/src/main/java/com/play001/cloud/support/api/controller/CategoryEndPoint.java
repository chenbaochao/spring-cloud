package com.play001.cloud.support.api.controller;

import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.api.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.play001.cloud.support.entity.Category;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryEndPoint {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<Category> findById(Integer categoryId) throws IException {
        if(categoryId == null || categoryId < 0) throw new IException("参数错误");
        if(categoryId == 0){
            Category category = new Category();
            category.setId(0);
            category.setName("全部商品");
            return new ResponseEntity<>(category);
        }
        return new ResponseEntity<>(categoryService.findById(categoryId));
    }



    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findAll() throws IException {
        return  new ResponseEntity<>(categoryService.findAll());
    }


}
