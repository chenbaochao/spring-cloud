package com.play001.cloud.cms.mapper;


import com.play001.cloud.common.entity.Parameter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductParaMapper {


    /**
     * 查找商品参数
     */
    @Select("select id, name, value, sort from os_product_parameter where product_id = #{0} order by sort")
    List<Parameter> findByProductId(Long productId);

    //添加产品参数
    @Insert("insert into os_product_parameter(product_id, name, value, sort)" +
            "value(#{productId}, #{name}, #{value}, #{sort} )")
    void add(Parameter parameter);

    /**
     * update
     */
    @Update("update os_product_parameter set name = #{name}, value = #{value} where id = #{id}")
    Integer update(Parameter parameter);

    /**
     * delete
     */
    @Delete("delete from os_product_parameter where id = #{id}")
    Integer delete(Long id);


}
