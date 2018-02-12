package com.play001.cloud.os.service;

import com.play001.cloud.support.entity.Category;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> findAll() throws IException {
        ResponseEntity<List<Category>> responseEntity = categoryMapper.findAll();
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getStatus());
        return responseEntity.getMessage();
    }

    public Category findById(Integer categoryId) throws IException {
        ResponseEntity<Category> responseEntity = categoryMapper.findById(categoryId);
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getStatus());
        return responseEntity.getMessage();
    }
}
