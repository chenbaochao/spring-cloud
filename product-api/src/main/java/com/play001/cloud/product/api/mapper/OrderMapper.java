package com.play001.cloud.product.api.mapper;

import com.play001.cloud.support.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    @Insert("insert into os_order(user_id, status, create_time, amount) value(#{user.id}, #{status}, #{createTime}, #{amount})")
    @Options(useGeneratedKeys = true)
    void add(Order order);

    @Select("select id, user_id, amount, status, create_time from os_order where id = #{id} and user_id = #{userId} limit 1")
    @ResultMap("orderResult")
    Order findById(@Param("id")Long id, @Param("userId") Long userId);

    @Update("update os_order set status = #{status} where id = #{id} and user_id = #{userId}")
    void setStatus(@Param("id")Long id, @Param("status") byte status, @Param("userId") Long userId);

    Integer count(@Param("userId") Long userId, @Param("status")Integer status);

    List<Order> pagination(@Param("userId") Long userId, @Param("start")Long start, @Param("limit")Integer limit,
                           @Param("status")Integer status);
}
