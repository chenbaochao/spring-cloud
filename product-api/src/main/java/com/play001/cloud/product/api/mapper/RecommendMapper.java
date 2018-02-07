package com.play001.cloud.product.api.mapper;

import com.play001.cloud.support.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecommendMapper {

    /**
     * 明星产品
     */
    @Select("select p.id, p.name, p.title, p.show_pic, p.show_price as showPrice from os_product p, os_product_recommend pr " +
            " where pr.recommend_id = 1 and p.id  = pr.product_id")
    List<Product> getStartProduct();

    @Select("select p.id, p.name, p.title, p.show_pic, p.show_price as showPrice from os_product p, os_product_recommend pr " +
            " where pr.recommend_id = #{recommendId} and p.id  = pr.product_id")
    List<Product> findRecommendById(Integer recommendId);
}
