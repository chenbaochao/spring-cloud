package com.play001.cloud.web.service.impl;

import com.netflix.discovery.converters.Auto;
import com.play001.cloud.common.entity.Category;
import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryService categoryService;

    public List<Category> findAll() throws IException {
        Response<List<Category>> response = categoryService.findAll();
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getStatus());
        return response.getMessage();
    }
}
