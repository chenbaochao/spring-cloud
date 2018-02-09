package com.play001.cloud.user.api.mapper;

import com.play001.cloud.support.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface UserAddressMapper {


    @Select("select id ,user_id userId, username, user_phone userPhone, user_address userAddress, user_zipcode zipcode from os_user_address where user_id = #{userId}")
    List<UserAddress> getAll(Long userId);
}
