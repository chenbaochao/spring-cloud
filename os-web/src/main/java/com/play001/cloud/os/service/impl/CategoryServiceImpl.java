package com.play001.cloud.os.service.impl;

import com.play001.cloud.common.entity.Category;
import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.os.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryService categoryService;

    public List<Category> findAllWithProduct() throws IException {
        Response<List<Category>> response = categoryService.findAllWithProduct();
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getStatus());
        return response.getMessage();
    }

    public Category findById(Integer categoryId) throws IException {
        Response<Category> response = categoryService.findById(categoryId);
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getStatus());
        return response.getMessage();
    }
}
