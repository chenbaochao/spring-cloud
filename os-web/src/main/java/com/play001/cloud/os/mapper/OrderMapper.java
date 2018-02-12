package com.play001.cloud.os.mapper;

import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import com.play001.cloud.support.entity.Order;
import com.play001.cloud.support.entity.Pagination;
import com.play001.cloud.support.entity.ResponseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface OrderMapper {

    //下订单,返回订单编号
    @RequestMapping(value = "/produtc/order/add", method = RequestMethod.POST)
    ResponseEntity<Long> add(@RequestParam("cartIds") Long cartIds[],@RequestParam("userJwt") String userJwt,@RequestParam("addrssId") Long addrssId);

    //findById
    @RequestMapping(value = "/product/order/findById", method = RequestMethod.GET)
    ResponseEntity<Order> findById(@RequestParam("id") Long id, @RequestHeader("userJwt") String userJwt);
    //订单列表
    @RequestMapping(value = "/product/order/list", method = RequestMethod.GET)
    ResponseEntity<Pagination<Order>> list(@RequestParam("type")Integer type, @RequestParam("pageNo") Integer pageNo, @RequestHeader("userJwt") String userJwt);


}
