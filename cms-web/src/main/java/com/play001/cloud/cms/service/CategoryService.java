package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.product.ProductCategoryMapper;
import com.play001.cloud.support.entity.Category;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private ProductCategoryMapper categoryMapper;


    public List<Category> findAll(){
        return categoryMapper.findAll();
    }

    /**
     * 设置状态
     */
    public ResponseEntity<Integer> setStatus(Integer id, Byte status){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(id == null || status == null ||
                status < 0 || status > 1){
            return responseEntity.setErrMsg("参数错误");
        }
        categoryMapper.setStatus(id, status);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
    /**
     * 删除
     */
    public ResponseEntity<Integer> delete(Integer id){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(id == null){
            return responseEntity.setErrMsg("参数错误");
        }
        categoryMapper.delete(id);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
    /**
     * findById
     */
    public Category findById(Integer id) throws IException {
        if(id == null){
            throw new IException("栏目不存在");
        }
        Category category = categoryMapper.findById(id);
        if(category == null){
            throw new IException("栏目不存在");
        }
        return category;
    }
    /**
     * update
     */
    public ResponseEntity<Integer> update(Category category){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(category.getId() == null){
            return responseEntity.setErrMsg("参数错误");
        }
        categoryMapper.update(category);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
    /**
     * create
     */
    public ResponseEntity<Integer> create(Category category){
        categoryMapper.create(category);
        return new ResponseEntity<Integer>().setStatus(ResponseEntity.SUCCESS);
    }
}
