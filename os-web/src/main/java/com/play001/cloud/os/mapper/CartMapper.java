package com.play001.cloud.os.mapper;

import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.ShopCart;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface CartMapper {

    @RequestMapping(value = "/product/cart/add", method = RequestMethod.POST)
    ResponseEntity<Integer> add(@RequestParam("productId") Long productId, @RequestParam("productSpecId")Long productSpecId, @RequestHeader("userJwt") String userJwt);

    @RequestMapping(value = "/product/cart/list", method = RequestMethod.GET)
    ResponseEntity<List<ShopCart>> list(@RequestHeader("userJwt") String userJwt);

    @RequestMapping(value = "/product/cart/delete", method = RequestMethod.POST)
    ResponseEntity<Integer> delete(@RequestParam("cartId")Long cartId, @RequestHeader("userJwt") String userJwt);

    @RequestMapping(value = "/product/cart/findById", method = RequestMethod.GET)
    ResponseEntity<ShopCart> findById(@RequestParam("id") Long id, @RequestHeader("userJwt") String userJwt);

}
