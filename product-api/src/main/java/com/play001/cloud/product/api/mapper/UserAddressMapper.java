package com.play001.cloud.product.api.mapper;

import com.play001.cloud.product.api.mapper.fallback.DefaultFallbackFactory;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.UserAddress;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface UserAddressMapper {

    @RequestMapping(value = "/user/address/findById", method = RequestMethod.GET)
    ResponseEntity<UserAddress> findById(@RequestParam("id")Long id, @RequestHeader("userJwt")String userJwt);
}
