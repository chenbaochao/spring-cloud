package com.play001.cloud.product.api.mapper;

import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ZUUL")
public interface UserMapper {

    @RequestMapping(value = "/user/getInfo", method = RequestMethod.GET)
    ResponseEntity<User> getInfo(@RequestHeader("userJwt")String userJwt);
}
