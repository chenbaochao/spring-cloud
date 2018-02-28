package com.play001.cloud.cms.mapper;

import com.play001.cloud.support.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {

    @Insert("insert into os_product(name, show_price, title, status, create_time, remarks, sold_number, category_id, thumb_id)" +
            " value(#{name}, #{showPrice}, #{title}, #{status}, #{createTime}, #{remarks}, #{soldNumber}, #{category.id}, #{thumb.id} )")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addProduct(Product product);

    Long count(@Param("categoryId") Integer categoryId);

    List<Product> getList(@Param("offset") Long offset, @Param("limit") Integer limit,
                          @Param("sort") String sort, @Param("order") String order,
                          @Param("categoryId") Integer categoryId);



    //增加产品介绍
    @Insert("insert into os_product_introduction(introduction, product_id) " +
            "value(#{introduction}, #{productId})")
    void addIntroduction(@Param("productId") Long productId, @Param("introduction") String introduction);


    //findById
    @Select("select id, name, show_price, title, status, create_time, remarks, sold_number, category_id, thumb_id from os_product where id = #{productId} limit 1")
    @ResultMap("com.play001.cloud.cms.mapper.ProductMapper.productResult")
    Product findById(Long productId);


    /**
     *查找商品介绍
     */
    @Select("select introduction from os_product_introduction where product_id = #{0} limit 1")
    String findIntroduction(Long productId);

    /**
     * 修改
     */
    @Update("update os_product set name = #{name}, show_price = #{showPrice}, title = #{title}, status = #{status}, remarks = #{remarks}, category_id = #{category.id}" +
            ", thumb_id = #{thumb.id} where id = #{id}")
    Integer update(Product product);

    /**
     * 修改商品介绍
     */
    @Update("update os_product_introduction set introduction = #{introduction} where product_id = #{id}")
    Integer updateIntroduction(Product product);
}
