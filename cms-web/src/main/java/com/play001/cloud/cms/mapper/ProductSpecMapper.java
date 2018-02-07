package com.play001.cloud.cms.mapper;


import com.play001.cloud.support.entity.Specification;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductSpecMapper {



    /**
     * 查找商品规格
     */
    @Select("select id, name, stock, price, sold_number as soldNumber from os_product_specification where product_id = #{0}")
    List<Specification> findByProductId(Long productId);


    //添加产品规格
    @Insert("insert into os_product_specification(product_id, name, price, stock, sold_number)" +
            " value( #{productId}, #{name}, #{price}, #{stock}, #{soldNumber})")
    void add(Specification specification);

    /**
     * update
     */
    @Update("update os_product_specification set name = #{name}, price = #{price}, stock = #{stock} where id = #{id}")
    Integer update(Specification specification);

    /**
     * delete
     */
    @Delete("delete from os_product_specification where id = #{id}")
    Integer delete(Long id);
}
