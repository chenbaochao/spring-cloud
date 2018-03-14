package com.play001.cloud.cms.mapper.order;

import com.play001.cloud.support.entity.OrderProduct;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderProductMapper {

    @Results({
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "productSpecName", column = "product_spec_name"),
            @Result(property = "productNumber", column = "product_number"),
            @Result(property = "productAmount", column = "product_amount"),
            @Result(property = "commentStatus", column = "comment_status"),
            @Result(property = "productThumb", column = "product_thumb_id", one = @One(select = "com.play001.cloud.cms.mapper.ImageMapper.findById"))

    })
    @Select("select id, order_id, product_id, product_name, product_thumb_id,product_spec_name, product_number, product_amount, comment_status" +
            " from os_order_product where order_id = #{orderId}")
    List<OrderProduct> findByOrderId(Long orderId);
}
