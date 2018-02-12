package com.play001.cloud.product.api.mapper;

import com.play001.cloud.support.entity.OrderAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderAddressMapper {

    @Insert("insert into os_order_address(order_id, username, user_phone, user_addr, zipcode) " +
            "value(#{orderId}, #{username}, #{userPhone}, #{userAddr}, #{zipcode})")
    void add(OrderAddress orderAddress);

    @Select("select id, username, user_phone userPhone, user_addr userAddr, zipcode from os_order_address where order_id = #{orderId} limit 1")
    OrderAddress findByOrderId(Long orderId);
}
