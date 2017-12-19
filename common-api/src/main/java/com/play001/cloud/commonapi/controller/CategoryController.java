package com.play001.cloud.commonapi.controller;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.commonapi.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.play001.cloud.common.entity.Category;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Response<Category> findById(Integer id) throws IException {
        if(id == null || id < 1) throw new IException("参数错误");
        Response<Category> response = new Response<>(categoryService.findById(id));
        return response;
    }



    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Response<List<Category>> findAll() throws IException {
        Response<List<Category>> response = new Response<>();
        response.setMessage(categoryService.findAll());
        return response;
    }

}
