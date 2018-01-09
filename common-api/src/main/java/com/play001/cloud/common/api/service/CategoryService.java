package com.play001.cloud.common.api.service;

import com.play001.cloud.common.entity.Category;
import com.play001.cloud.common.api.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public Category findAllWithProduct(Integer id){
        return categoryMapper.findById(id);
    }
    public List<Category> findAllWithProduct(){
        return categoryMapper.findAllWithProduct();
    }
}
