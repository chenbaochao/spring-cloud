package com.play001.cloud.cms.mapper.order;

import com.play001.cloud.support.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    /**
     *
     * @param offset 开始位置
     * @param limit 数据条数
     * @param status 状态 0表示全部
     * @param order 倒序 正序
     * @param sort 排序依据
     */
    List<Order> list(@Param("offset")Long offset, @Param("limit")Integer limit, @Param("status")byte status, @Param("order")String order, @Param("sort")String sort);


    Long count(@Param("status") byte status);

    @Update("update os_order set status = #{status} where id = #{id}")
    Integer updateStatus(@Param("id") Long id, @Param("status") Byte status);

    @Select("select status from os_order where id = #{id} limit 1")
    Byte getStatus(Long id);
}
