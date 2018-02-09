package com.play001.cloud.product.api.mapper;

import com.play001.cloud.support.entity.product.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecommendMapper {

    /**
     * 明星产品
     */
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "show_price", property = "showPrice"),
            @Result(column = "thumb_id", property = "thumb", one = @One(select = "com.play001.cloud.support.mapper.ImageMapper.findById"))
    })
    @Select("select p.id, p.name, p.title, p.thumb_id, p.show_price  from os_product p, os_product_recommend pr " +
            " where pr.recommend_id = 1 and p.id  = pr.product_id")
    List<Product> getStartProduct();

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "show_price", property = "showPrice"),
            @Result(column = "thumb_id", property = "thumb", one = @One(select = "com.play001.cloud.support.mapper.ImageMapper.findById"))
    })
    @Select("select p.id, p.name, p.title, p.thumb_id, p.show_price  from os_product p, os_product_recommend pr " +
            " where pr.recommend_id = #{recommendId} and p.id  = pr.product_id")
    List<Product> findRecommendById(Integer recommendId);
}
