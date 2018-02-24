package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.AdvertCategoryMapper;
import com.play001.cloud.support.entity.AdvertCategory;
import com.play001.cloud.support.entity.IException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertCategoryService {

    @Autowired
    private AdvertCategoryMapper advertCategoryMapper;

    public List<AdvertCategory> findAll(){
        return advertCategoryMapper.findAll();
    }

    public AdvertCategory findById(Integer id) throws IException {
        if(id == null){
            throw new IException("参数错误");
        }
        return advertCategoryMapper.findById(id);
    }
}
