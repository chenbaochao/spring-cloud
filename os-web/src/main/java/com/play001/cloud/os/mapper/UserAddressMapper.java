package com.play001.cloud.os.mapper;

import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.UserAddress;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface UserAddressMapper {

    @RequestMapping(value = "/user/address/getAll", method = RequestMethod.GET)
    ResponseEntity<List<UserAddress>> getAll(@RequestHeader("userJwt") String userJwt);

    @RequestMapping(value = "/user/address/add", method = RequestMethod.POST)
    ResponseEntity<Integer> add(@RequestBody UserAddress userAddress, @RequestHeader("userJwt") String userJwt);

    @RequestMapping(value = "/user/address/update", method = RequestMethod.POST)
    ResponseEntity<Integer> update(@RequestBody UserAddress userAddress, @RequestHeader("userJwt") String userJwt);

    @RequestMapping(value = "/user/address/delete", method = RequestMethod.POST)
    ResponseEntity<Integer> delete(@RequestParam("id") Long id, @RequestHeader("userJwt") String userJwt);
}
