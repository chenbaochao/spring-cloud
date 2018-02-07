package com.play001.cloud.product.api.mapper;

import com.play001.cloud.support.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {


    Product findById(Long id);

    //搜索,需要分页
    List<Product> search(String keyword, Long start, Integer quantity);

    //搜索,计算总条数
   Long countSearch(String keyword);

   //分类,计算总条数
   Long countByCategory(Integer categoryId);


    /**
     * * 分类列出产品
     * @param categoryId 产品目录ID
     * @param sort 排序方式,1.新品,2.销量,3.价格up,4.价格down
     * @param start 开始位置
     * @param quantity 取数据条数
     * @return
     */
    List<Product> listByCategoryId(@Param("categoryId") Integer categoryId,
                                   @Param("sort")Integer sort,
                                   @Param("start")Long start,
                                   @Param("quantity")Integer quantity);


}
