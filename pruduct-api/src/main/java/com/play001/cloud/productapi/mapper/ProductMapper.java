package com.play001.cloud.productapi.mapper;

import com.play001.cloud.common.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {


    Product findById(Long id);

    //搜索,需要分页
    List<Product> search(String keyword, Long start, Integer quantity);

    //搜索,计算总条数
   Long countSearch(String keyword);
}
