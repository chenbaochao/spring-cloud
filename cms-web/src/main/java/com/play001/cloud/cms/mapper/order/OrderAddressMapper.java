package com.play001.cloud.cms.mapper.order;

import com.play001.cloud.support.entity.OrderAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderAddressMapper {

    @Select("select username, user_phone userPhone, user_addr userAddr, zipcode from os_order_address where order_id = #{orderId} limit 1")
    OrderAddress findByOrderId(Long orderId);
}
