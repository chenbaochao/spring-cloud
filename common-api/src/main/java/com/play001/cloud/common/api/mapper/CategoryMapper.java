package com.play001.cloud.common.api.mapper;

import com.play001.cloud.common.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {


    Category findById(Integer id);

    List<Category> findAll();

}
