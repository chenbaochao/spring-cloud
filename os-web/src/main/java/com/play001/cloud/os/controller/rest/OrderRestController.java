package com.play001.cloud.os.controller.rest;

import com.play001.cloud.os.service.OrderService;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.interceptor.UserPermissionVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    /**
     * 下单
     * @param cartId 购物车Id数组
     * @param userJwt 用户jwt
     * @param addressId 收件人地址Id
     *  返回订单Id
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Long> checkout(Long cartId[], @CookieValue("userJwt") String userJwt, Long addressId ) throws IException {
        return orderService.order(cartId, userJwt, addressId);
    }


}
