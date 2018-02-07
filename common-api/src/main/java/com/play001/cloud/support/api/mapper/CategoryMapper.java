package com.play001.cloud.support.api.mapper;

import com.play001.cloud.support.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {


    Category findById(Integer id);

    List<Category> findAll();


}
