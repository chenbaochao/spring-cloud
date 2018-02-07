package com.play001.cloud.cms.mapper;


import com.play001.cloud.support.entity.ProductImage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductImageMapper {


    /**
     * 查找产品相册
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "image", column = "image_id", one = @One(select = "com.play001.cloud.cms.mapper.ImageMapper.findById"))
    })
    @Select("select id, sort, image_id from os_product_image where product_id = #{0} order by sort")
    List<ProductImage> findByProductId(Long productId);

    //添加商品相册
    @Insert("insert into os_product_image(product_id, sort, image_id)" +
            " value(#{productId}, #{sort}, #{image.id})")
    void add(ProductImage productImage);

    /**
     * findById
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "image", column = "image_id", one = @One(select = "com.play001.cloud.cms.mapper.ImageMapper.findById"))
    })
    @Select("select id, sort, image_id from os_product_image where id = #{id} limit 1")
    ProductImage findById(Long id);

    /**
     * 删除
     */
    @Delete("delete from os_product_image where id = #{id}")
    Integer delete(Long id);




}
