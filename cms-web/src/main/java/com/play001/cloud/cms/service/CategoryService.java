package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.CategoryMapper;
import com.play001.cloud.common.entity.Category;
import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    public List<Category> findAll(){
        return categoryMapper.findAll();
    }

    /**
     * 设置状态
     */
    public Response<Integer> setStatus(Integer id, Byte status){
        Response<Integer> response = new Response<>();
        if(id == null || status == null ||
                status < 0 || status > 1){
            return response.setErrMsg("参数错误");
        }
        categoryMapper.setStatus(id, status);
        return response.setStatus(Response.SUCCESS);
    }
    /**
     * 删除
     */
    public Response<Integer> delete(Integer id){
        Response<Integer> response = new Response<>();
        if(id == null){
            return response.setErrMsg("参数错误");
        }
        categoryMapper.delete(id);
        return response.setStatus(Response.SUCCESS);
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
    public Response<Integer> update(Category category){
        Response<Integer> response = new Response<>();
        if(category.getId() == null){
            return response.setErrMsg("参数错误");
        }
        categoryMapper.update(category);
        return response.setStatus(Response.SUCCESS);
    }
    /**
     * create
     */
    public Response<Integer> create(Category category){
        categoryMapper.create(category);
        return new Response<Integer>().setStatus(Response.SUCCESS);
    }
}
