package com.play001.cloud.web.service;

import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.entity.ShopCart;
import com.play001.cloud.web.service.fallback.DefaultFallbackFactory;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface CartService {

    @RequestMapping(value = "/common/cart/add", method = RequestMethod.POST)
    Response<String> add(@RequestParam("productId") Long productId,  @RequestParam("productSpecId")Long productSpecId, @RequestHeader("userJwt") String userJwt);

    @RequestMapping(value = "/common/cart/list", method = RequestMethod.GET)
    Response<List<ShopCart>>  list(@RequestHeader("userJwt") String userJwt);

}
