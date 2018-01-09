package com.play001.cloud.common.api.controller;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.api.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.play001.cloud.common.entity.Category;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryEndPoint {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Response<Category> findById(Integer categoryId) throws IException {
        if(categoryId == null || categoryId < 0) throw new IException("参数错误");
        if(categoryId == 0){
            Category category = new Category();
            category.setId(0);
            category.setName("全部商品");
            return new Response<>(category);
        }
        return new Response<>(categoryService.findAllWithProduct(categoryId));
    }



    @RequestMapping(value = "/findAllWithProduct", method = RequestMethod.GET)
    public Response<List<Category>> findAllWithProduct() throws IException {
        return  new Response<>(categoryService.findAllWithProduct());
    }


}
