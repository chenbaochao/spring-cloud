package com.play001.cloud.productapi.mapper;

import com.play001.cloud.productapi.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {


    Product findById(Long id);

    List<Product> search(String keyword);
}
