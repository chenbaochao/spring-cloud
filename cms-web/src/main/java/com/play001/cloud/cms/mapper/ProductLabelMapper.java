package com.play001.cloud.cms.mapper;

import com.play001.cloud.common.entity.Label;
import com.play001.cloud.common.entity.Parameter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductLabelMapper {

    /**
     * 查找商品标签
     */
    @Select("select id, name, sort from os_product_label where product_id = #{0} order by sort")
    List<Label> findByProductId(Long productId);

    //添加label
    @Insert("insert into os_product_label(product_id, sort, name)" +
            " value(#{productId}, " +
            " (select max_sort from (select IFNULL(max(sort)+1,1) max_sort from os_product_label where product_id  = #{productId})t ), " +
            " #{name})")
    void add(Label label);

    /**
     * update
     */
    @Update("update os_product_label set name = #{name} where id = #{id}")
    Integer update(Label label);

    /**
     * delete
     */
    @Delete("delete from os_product_label where id = #{id}")
    Integer delete(Long id);

}
