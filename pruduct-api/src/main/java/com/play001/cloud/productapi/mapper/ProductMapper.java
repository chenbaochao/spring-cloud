package com.play001.cloud.productapi.mapper;

import com.play001.cloud.productapi.entity.Pagination;
import com.play001.cloud.productapi.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import java.util.List;

@Mapper
public interface ProductMapper {


    Product findById(Long id);

    //搜索,需要分页
    List<Product> search(String keyword, Long start, Integer quantity);

    //搜索,计算总条数
   Long count(String keyword);
}
